package com.classloader;

public class ClassLoaderTest {

    public static void main(String[] args) {
        // 1 classloader
        System.out.println(ClassLoaderTest.class.getClassLoader().getClass().getName());
        System.out.println(System.class.getClassLoader());

        // 2¡¢Î¯ÍÐÄ£Ê½
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        while (classLoader != null) {
            System.out.println(classLoader.getClass().getName());
            classLoader = classLoader.getParent();
        }
        System.out.println(classLoader);

        // 3¡¢ExtClassLoader

    }

}
