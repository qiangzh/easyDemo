package com.beans;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * TestÄÚÊ¡
 *  
 *  @author zhqiang
 *  @created 2013-3-1 ÉÏÎç08:56:40
 *  @lastModified       
 *  @history           
 */

public class PropertyDescriptor2Test {

    public static void main(String[] args) throws Exception {
        Bird2 bird = new Bird2("BlueBird", "CPU");
        Bird2 bird1 = new Bird2("1", "2");
        Field[] fields = bird.getClass().getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);
            Object obj = field.get(bird);
            field.set(bird1, obj);
        }
        
        System.out.println(bird1);

    }

}

class Bird2 {
    public Bird2(String name, String cPU) {
        this.name2 = name;
        CPU = cPU;
    }

    private String name2;

    private String CPU;

    public String getName() {
        return name2;
    }

    public void setName(String name) {
        this.name2 = name;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String cPU) {
        CPU = cPU;
    }

    @Override
    public String toString() {
        return "Bird [name=" + name2 + ", CPU=" + CPU + "]";
    }

}
