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
 * RSA �����ࡣ�ṩ���ܣ����ܣ�������Կ�Եȷ�����
 * ��Ҫ��http://www.bouncycastle.org����bcprov-jdk14-123.jar��
 * 
 */
public class RSAUtil {

    /**
     * ������Կ�� 
     * @return KeyPair
     * @throws EncryptException
     */
    public static KeyPair generateKeyPair() throws Exception {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA", new BouncyCastleProvider());
            final int KEY_SIZE = 1024;// ûʲô��˵���ˣ����ֵ��ϵ������ܵĴ�С�����Ը��ģ����ǲ�Ҫ̫�󣬷���Ч�ʻ��
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
        // ������Կ
        oos.writeObject(kp);
        oos.close();
        fos.close();
    }

    /**
     * ���ɹ�Կ
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
     * * ����˽Կ 
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
     * ����
     * @param key ���ܵ���Կ
     * @param data �����ܵ���������
     * @return ���ܺ������
     * @throws Exception
     */
    public static byte[] encrypt(PublicKey pk, byte[] data) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, pk);
            int blockSize = cipher.getBlockSize();// ��ü��ܿ��С���磺����ǰ����Ϊ128��byte����key_size=1024
            // ���ܿ��СΪ127
            // byte,���ܺ�Ϊ128��byte;��˹���2�����ܿ飬��һ��127
            // byte�ڶ���Ϊ1��byte
            int outputSize = cipher.getOutputSize(data.length);// ��ü��ܿ���ܺ���С
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];
            int i = 0;
            while (data.length - i * blockSize > 0) {
                if (data.length - i * blockSize > blockSize)
                    cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
                else
                    cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
                // ������doUpdate���������ã��鿴Դ�������ÿ��doUpdate��û��ʲôʵ�ʶ������˰�byte[]�ŵ�
                // ByteArrayOutputStream�У������doFinal��ʱ��Ž����е�byte[]���м��ܣ����ǵ��˴�ʱ���ܿ��С�ܿ����Ѿ�������
                // OutputSize����ֻ����dofinal������
                i++;
            }
            return raw;
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * ����
     * @param key ���ܵ���Կ
     * @param raw �Ѿ����ܵ�����
     * @return ���ܺ������
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