package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactoryBean {

    private Advicer advicer;

    private Object target;

    public Advicer getAdvicer() {
        return advicer;
    }

    public void setAdvicer(Advicer advicer) {
        this.advicer = advicer;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object getProxy() {
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
