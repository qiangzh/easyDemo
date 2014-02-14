package com.reflect;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibTest {

    public static void main(String[] args) {
        Lamp proxy = (Lamp) new CglibProxy().getProxy(Lamp.class);
        System.out.println(proxy.say()); 
    }
}
class Lamp{
    Lamp(String bb){
        
    }
    
    public String say(){
        return "Lamp";
    }
    
}

class CglibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {

        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);

        return enhancer.create();
    }

    public Object intercept(Object obj, Method method, Object[] arg2, MethodProxy proxy) throws Throwable {

        System.out.println("打印前");

        Object result = proxy.invokeSuper(obj, arg2);

        System.out.println("打印后");

        return result;
    }

}
