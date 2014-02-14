package com.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;

public class ProxyTest2 {

    public static void main(String[] args) {
        
        Class proxyClass =Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
        System.out.println(proxyClass.getName());
        System.out.println(proxyClass.getName() + " constructors");
        Constructor[] constructors = proxyClass.getConstructors();
        for(Constructor constructor:constructors){
            StringBuilder builder = new StringBuilder();
            builder.append(constructor.getName());
            builder.append("(");
            Class[] parameters = constructor.getParameterTypes();
            for(Class parameter:parameters){
                builder.append(parameter.getName());
                builder.append(",");
            }
            if(parameters!=null&&parameters.length>0){
                builder.deleteCharAt(builder.length()-1);
            }
            builder.append(")");   
            System.out.println(builder.toString());
        }
        

        System.out.println(proxyClass.getName() + " methods");
        Method[] methods = proxyClass.getMethods();
        for(Method method:methods){
            StringBuilder methodBuilder = new StringBuilder();
            methodBuilder.append(method.getName());
            methodBuilder.append("(");
            Class[] parameters = method.getParameterTypes();
            for(Class parameter:parameters){
                methodBuilder.append(parameter.getName());
                methodBuilder.append(",");
            }
            if(parameters!=null&&parameters.length>0){
                methodBuilder.deleteCharAt(methodBuilder.length()-1);
            }
            methodBuilder.append(")");  
            System.out.println(methodBuilder.toString());
        }

    }

}
