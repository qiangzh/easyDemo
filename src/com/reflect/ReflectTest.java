package com.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *  
 *  @author zhqiang
 *  @created 2013-2-25 ÉÏÎç09:06:29
 *  @lastModified       
 *  @history           
 */

public class ReflectTest {

    public static void main(String[] args) throws Exception {
        Student student = new Student();
        Class class1 = student.getClass();
        Class class2 = Student.class;

        //Test constructor
        System.out.println(class1);
        System.out.println(class2);

        Class class3 = Class.forName("com.reflect.Student");
        System.out.println(class3);
        Student student2 = (Student) class3.newInstance();
        System.out.println(student2);
        Constructor constructor = class3.getConstructor(String.class, int.class);

        Object student3 = constructor.newInstance("Tom", 30);
        System.out.println(student3);

        //Test Field      
        Field fieldName = class3.getField("name");
        System.out.println(fieldName.get(student3));
        Field fieldAge = class3.getDeclaredField("age");
        fieldAge.setAccessible(true);
        System.out.println(fieldAge.get(student3));

        Field[] fields = class3.getFields();
        System.out.println("=========" + fields.length);
        for (Field field : fields) {
            if (field.getType() == String.class && field.getName().startsWith("str")) {
                field.set(student3, ((String) field.get(student3)).replace('a', 'z'));
            }

        }
        System.out.println(((Student) student3).getStr1());
        System.out.println(((Student) student3).getStr2());
        System.out.println(((Student) student3).getStr3());

        // Test Method
        Method method1 = class3.getMethod("setName", String.class);
        method1.invoke(student3, "Hello TY");
        System.out.println(student3);
        Method method2 = class3.getMethod("setName", String.class, int.class);
        method2.invoke(student3, "Hello GF", 1);
        System.out.println(student3);
        
        //Test Main Method;
        String strClassName="com.reflect.Student";
        Method method = Class.forName(strClassName).getMethod("main", String[].class);
        method.invoke(null, (Object)new String[]{"a","b","c"});   
        
        
        //Test Array
        int[] a1= new int[3];
        int[] a2 = new int[4];
        int[] a3 = new int[]{1,2,3};
        System.out.println(a1.getClass() == a2.getClass());
        System.out.println(Array.get(a3, 1));
        System.out.println(Array.getLength(a3));
        
        String[][] strings = (String[][])Array.newInstance(String.class, 10);
        System.out.println(strings.length);
        System.out.println(strings.getClass());
        
        String[] strings2 = (String[])Array.newInstance(String.class, 10);
        Array.set(strings2, 0, "arg");
        System.out.println(strings2[0]);
        
    }

}

class Student {
    public String name;

    private int age;

    private String str1 = "apply";

    public String str2 = "baby";

    public String str3 = "a big ball";

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setName(String name, int a) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public String getStr3() {
        return str3;
    }

    public void setStr3(String str3) {
        this.str3 = str3;
    }

    @Override
    public String toString() {
        return this.name + " age is " + this.age;

    }
    
    public static void main(String[] args){
        for(String str:args){
            System.out.println(str);
        }
    }
}