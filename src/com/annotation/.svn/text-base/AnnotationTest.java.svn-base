package com.annotation;


/**
 *  编写、使用、反射注解
 *  
 *  @author zhqiang
 *  @created 2013-3-11 上午09:09:47
 *  @lastModified       
 *  @history           
 */
@MyAnnotation(name = "AnnotationTest",arr={1,2,3})
public class AnnotationTest {

    public static void main(String[] args) {
        if (AnnotationTest.class.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation annotation = AnnotationTest.class.getAnnotation(MyAnnotation.class);
            System.out.println(annotation.name());
            System.out.println(annotation.arr());
        }

    }
}

