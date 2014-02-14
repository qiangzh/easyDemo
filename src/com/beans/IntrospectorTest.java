package com.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;

public class IntrospectorTest {
    
    public static void main(String[] args) throws IntrospectionException{
        
        String str = new String("ssss");
        BeanInfo beanInfo = Introspector.getBeanInfo(str.getClass(), Object.class);
        
       System.out.println(beanInfo.getBeanDescriptor()); 
    }

}




