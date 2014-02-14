package com.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class AdvicerTest {

    public static void main(String[] args) {
        List arrayList = new ArrayList();
        List listProxy = (List) getProxy(arrayList, new MyAdvicer());
        listProxy.add("aa");
        listProxy.add("bb");
        System.out.println(listProxy.size());

    }

    public static Object getProxy(final Object target, final Advicer advicer) {
        Object proxy = (Object) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            public Object invoke(Object obj, Method method, Object[] aobj) throws Throwable {
                advicer.beforeMethod(method);
                Object result = method.invoke(target, aobj);
                advicer.afterMethod(method);
                return result;
            }
        });
        return proxy;
    }

}

interface Advicer {
    void beforeMethod(Method method);

    void afterMethod(Method method);
}

class MyAdvicer implements Advicer {

    public void beforeMethod(Method method) {
        System.out.println("before " + method.getName());

    }

    public void afterMethod(Method method) {
        System.out.println("after " + method.getName());
    }

}
