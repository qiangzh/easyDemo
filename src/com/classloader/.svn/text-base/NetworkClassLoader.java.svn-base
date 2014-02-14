package com.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class NetworkClassLoader extends ClassLoader {

    public Class findClass(String name) {
        byte[] b = null;
        try {
            b = loadClassData(name);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(String name) throws Exception {
        //return Encrypt.decrypt(name);
        return Encrypt.unDecrypt(name);
    }

    public static void main(String[] args) throws Exception {
        String className = "com.classloader.OneClass";
        Encrypt.encrypt(className);

        NetworkClassLoader classLoader = new NetworkClassLoader();
        Object object = classLoader.findClass(className).newInstance();
        System.out.println(object.toString());

    }
}

class Encrypt {
    public static void encrypt(String name) throws Exception {

        String path = System.getProperty("java.class.path") + File.separator;
        path += name.replace('.', File.separatorChar) + ".class";

        String path2 = System.getProperty("java.class.path") + "1" + File.separator;
        path2 += name.replace('.', File.separatorChar) + ".class";
        System.out.println(path2);
        File file = new File(path2);
        file.createNewFile();


        FileInputStream fis = new FileInputStream(path);
        FileOutputStream fos = new FileOutputStream(path2);

        int b = -1;
        while ((b = fis.read()) != -1) {
            b = b ^ 0xff;
            fos.write(b);
        }

        fis.close();
        fos.close();
    }

    public static byte[] decrypt(String name) throws Exception {

        String path2 = System.getProperty("java.class.path") + "1" + File.separator;
        path2 += name.replace('.', File.separatorChar) + ".class";

        FileInputStream fis = new FileInputStream(path2);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        int b = -1;
        while ((b = fis.read()) != -1) {
            b = b ^ 0xff;
            bos.write(b);
        }

        fis.close();
        bos.close();
        return bos.toByteArray();
    }
    public static byte[] unDecrypt(String name) throws Exception {

        String path2 = System.getProperty("java.class.path") + "1" + File.separator;
        path2 += name.replace('.', File.separatorChar) + ".class";

        FileInputStream fis = new FileInputStream(path2);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        byte[] b = new byte[fis.available()];
        while (fis.read(b) != -1) {
            bos.write(b);
        }
        return bos.toByteArray();
    }

}
