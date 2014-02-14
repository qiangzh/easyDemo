package com.reflect;

import java.lang.reflect.Field;

public class ExtendsTest {
    public static void main(String[] args) {
        IBook book = new WorkBook("workBook");
        book.say();
    }
}

interface IBook {
    public void say();
}

class TT {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void sayHello() {
        System.out.println("Hello " + name);
    }
}

class Book implements IBook {

    public Book(String name) {
        this.name = name;
    }

    private String name;

    private TT age = new TT();

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(TT age) {
        this.age = age;
    }

    @Override
    public void say() {
        System.out.println("This is Super Book" + " age:" + age);
    }
}

class WorkBook extends Book implements IBook {

    public WorkBook(String name) {
        super(name);

    }

    public void say() {
        try {
            Field age = getClass().getSuperclass().getDeclaredField("age");
            age.setAccessible(true);
            TT aa = (TT) age.get(this);
            aa.setName("zhqiang");
            aa.sayHello();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
