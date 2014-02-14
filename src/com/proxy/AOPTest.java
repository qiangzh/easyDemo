package com.proxy;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AOPTest {

    public static void main(String[] args) {
        InputStream inputStream = AOPTest.class.getClassLoader().getResourceAsStream("com/proxy/config.properties");
        BeanFactory beanFactory = new BeanFactory(inputStream);
        List list = (List) beanFactory.getBean("myProxy");
        System.out.println(list.getClass().getName());
        list.add("a");

    }

}
