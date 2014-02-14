package com.observer;

public class ObserverTest {

    public static void main(String[] args) {

        TV tv = new TV();
        Person person1 = new Person();
        Person person2 = new Person();
        tv.addObserver(person1);
        tv.addObserver(person2);

        System.out.println(tv.countObservers());

        tv.setChannel("CCTV5");
        System.out.println("=================");
        tv.setChannel("CCTV8");

    }
}
