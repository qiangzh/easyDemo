package com.thread;

import java.util.Random;

/**
 * 
 *  ThreadLocal线程内共享
 *  @author zhqiang
 *  @created 2013-2-19 上午09:10:27
 *  @lastModified       
 *  @history
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    int age = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + " generate age " + age);
                    Student.getThreadLocalStudent().setName(Thread.currentThread().getName());
                    Student.getThreadLocalStudent().setAge(age);
                    moduleA();
                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    moduleB();

                }
            }).start();
        }
    }

    public static void moduleA() {
        System.out.println(Thread.currentThread().getName() + " module A age is "
                + Student.getThreadLocalStudent().getAge());

    }

    public static void moduleB() {
        System.out.println(Thread.currentThread().getName() + " module B age is "
                + Student.getThreadLocalStudent().getAge());

    }
}

class Student {
    private static ThreadLocal<Student> threadLocal = new ThreadLocal<Student>();

    public static Student getThreadLocalStudent() {
        Student student = threadLocal.get();
        if (student == null) {
            student = new Student();
            threadLocal.set(student);
        }
        return student;
    }

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        System.out.println(age);
        this.age = age;
    }

}
