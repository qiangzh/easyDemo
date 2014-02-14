package com.beans;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
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

public class PropertyDescriptorTest {

    public static void main(String[] args) throws Exception {
        Bird bird = new Bird("BlueBird", "CPU");
        String propertyName = "name";
        Object name = getProperty(bird, propertyName);
        System.out.println(name);

        propertyName = "CPU";
        Object value = "IO";
        setProperty(bird, propertyName, value);
        System.out.println(bird);

    }

    private static void setProperty(Object object, String propertyName, Object value) throws IntrospectionException, IllegalAccessException,
            InvocationTargetException {
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, Object.class);
        Method method = propertyDescriptor.getWriteMethod();
        method.invoke(object, value);
    }

    private static Object getProperty(Object object, String propertyName) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, object.getClass());
        Method method = propertyDescriptor.getReadMethod();
        return method.invoke(object);
    }

}

class Bird {
    public Bird(String name, String cPU) {
        this.name = name;
        CPU = cPU;
    }

    private String name;

    private String CPU;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String cPU) {
        CPU = cPU;
    }

    @Override
    public String toString() {
        return "Bird [name=" + name + ", CPU=" + CPU + "]";
    }

}
