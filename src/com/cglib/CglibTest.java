package com.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

/**
 * 
 *  CglibÊ¾Àý´úÂë
 *  @author zhqiang
 *  @created 2013-12-10 ÉÏÎç10:28:09
 *  @lastModified       
 *  @history
 */
public class CglibTest {

    public static void main(String[] args) {
        final Bird bird = new Bird();
        bird.setName("Bird");

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(bird.getClass());

        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object obj, Method method, Object[] aobj) throws Throwable {
                return method.invoke(bird, aobj);
            }
        });
        Bird proxy = (Bird) enhancer.create();
        System.out.println(proxy.getName());

    }

}

class Bird {

    public Bird() {
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}