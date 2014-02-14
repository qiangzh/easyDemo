package com.rsa;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * RSA 工具类。提供加密，解密，生成密钥对等方法。
 * 需要到http://www.bouncycastle.org下载bcprov-jdk14-123.jar。
 * 
 */
public class RSAUtil {

    /**
     * 生成密钥对 
     * @return KeyPair
     * @throws EncryptException
     */
    public static KeyPair generateKeyPair() throws Exception {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA", new BouncyCastleProvider());
            final int KEY_SIZE = 1024;// 没什么好说的了，这个值关系到块加密的大小，可以更改，但是不要太大，否则效率会低
            keyPairGen.initialize(KEY_SIZE, new SecureRandom());
            KeyPair keyPair = keyPairGen.generateKeyPair();
            saveKeyPair(keyPair);
            return keyPair;
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static KeyPair getKeyPair() throws Exception {
        String file = "D:/Java/eLibrary/config/RSAKey";//ConfigurationManager.getProperty(ConstrtsProperty.RSAFILE);
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream oos = new ObjectInputStream(fis);
        KeyPair kp = (KeyPair) oos.readObject();
        oos.close();
        fis.close();
        return kp;
    }

    public static void saveKeyPair(KeyPair kp) throws Exception {
        String file = "D:/Java/eLibrary/config/RSAKey";//ConfigurationManager.getProperty(ConstrtsProperty.DIR);
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        // 生成密钥
        oos.writeObject(kp);
        oos.close();
        fos.close();
    }

    /**
     * 生成公钥
     * @param modulus
     * @param publicExponent
     * @return RSAPublicKey 
     * @throws Exception
     */
    public static RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) throws Exception {
        KeyFactory keyFac = null;
        try {
            keyFac = KeyFactory.getInstance("RSA", new BouncyCastleProvider());
        }
        catch (NoSuchAlgorithmException ex) {
            throw new Exception(ex.getMessage());
        }

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
        try {
            return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
        }
        catch (InvalidKeySpecException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * * 生成私钥 
     * @param modulus
     * @param privateExponent
     * @return RSAPrivateKey 
     * @throws Exception
     */
    public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) throws Exception {
        KeyFactory keyFac = null;
        try {
            keyFac = KeyFactory.getInstance("RSA", new BouncyCastleProvider());
        }
        catch (NoSuchAlgorithmException ex) {
            throw new Exception(ex.getMessage());
        }

        RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(privateExponent));
        try {
            return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
        }
        catch (InvalidKeySpecException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * 加密
     * @param key 加密的密钥
     * @param data 待加密的明文数据
     * @return 加密后的数据
     * @throws Exception
     */
    public static byte[] encrypt(PublicKey pk, byte[] data) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, pk);
            int blockSize = cipher.getBlockSize();// 获得加密块大小，如：加密前数据为128个byte，而key_size=1024
            // 加密块大小为127
            // byte,加密后为128个byte;因此共有2个加密块，第一个127
            // byte第二个为1个byte
            int outputSize = cipher.getOutputSize(data.length);// 获得加密块加密后块大小
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];
            int i = 0;
            while (data.length - i * blockSize > 0) {
                if (data.length - i * blockSize > blockSize)
                    cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
                else
                    cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
                // 这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把byte[]放到
                // ByteArrayOutputStream中，而最后doFinal的时候才将所有的byte[]进行加密，可是到了此时加密块大小很可能已经超出了
                // OutputSize所以只好用dofinal方法。
                i++;
            }
            return raw;
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 解密
     * @param key 解密的密钥
     * @param raw 已经加密的数据
     * @return 解密后的明文
     * @throws Exception
     */
    public static byte[] decrypt(PrivateKey pk, byte[] raw) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(cipher.DECRYPT_MODE, pk);
            int blockSize = cipher.getBlockSize();
            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
            int j = 0;

            while (raw.length - j * blockSize > 0) {
                bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
                j++;
            }
            return bout.toByteArray();
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {

        //        RSAPublicKey rsap = (RSAPublicKey) RSAUtil.generateKeyPair().getPublic();
        //        rsap.getModulus();
        //        rsap.getPublicExponent();
        //        String test = "hello world";
        //        byte[] en_test = encrypt(getKeyPair().getPublic(), test.getBytes());
        //        byte[] de_test = decrypt(getKeyPair().getPrivate(), en_test);
        //        System.out.println(new String(de_test));
        //
        //        RSAPublicKey pubk = RSAUtil.generateRSAPublicKey("108368048788890560576572359774440094528241439236977435842254047613639902146619552188260903793435066711695213909830538008019609061114995118579433741950108897670960853465900185040198867356782389765444400474968011359678815704914512251924472572119499063342297571073597918012349131762728165202050464377633235643721".getBytes(), "10001".getBytes());
        //        RSAPrivateKey prik = RSAUtil.generateRSAPrivateKey("108368048788890560576572359774440094528241439236977435842254047613639902146619552188260903793435066711695213909830538008019609061114995118579433741950108897670960853465900185040198867356782389765444400474968011359678815704914512251924472572119499063342297571073597918012349131762728165202050464377633235643721".getBytes(), "247dfc60927b0343ddce035402291f3fa2eb155188a99da7b92a2a7f3e92d9ac2c98e9949adb376ed4e1d2594f874bc28c34b965bde555a9fc08cda3a01730f5b1e7c6aed9022ecd3d8bdda36dbb0b8a50a1d06660eafdd497883d95c683f44be484fdd1782c6c0094ed3a7584e260e663311ed90d4aa0a16b6a4360fcd7cee1".getBytes());
        //        byte[] en_test = RSAUtil.encrypt(pubk, "abc".getBytes());
        //        byte[] de_test = RSAUtil.decrypt(prik, en_test);
        //        System.out.println(new String(de_test));

        RSAPublicKey rsap = (RSAPublicKey) RSAUtil.generateKeyPair().getPublic();
        System.out.println(rsap.toString());
        System.out.println(rsap.toString());
        System.out.println(rsap.getModulus());
        System.out.println(rsap.getPublicExponent());
    }
}