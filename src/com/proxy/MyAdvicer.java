package com.proxy;

import java.lang.reflect.Method;

public class MyAdvicer implements Advicer {

    public void beforeMethod(Method method) {
        System.out.println("before " + method.getName());

    }

    public void afterMethod(Method method) {
        System.out.println("after " + method.getName());
    }
}
