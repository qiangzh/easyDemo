package com.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

    public static void main(String[] args) throws Exception {
        //1、simple
        StudentService studentService = new StudentServiceImpl();
        studentService.addStudent("Java");

        //2、reflect
        CourseService courseService = (CourseService) Class.forName("com.reflect.CourseServiceImpl").newInstance();
        courseService.addCourse("reflect");

        // 静态代理
        StudentService staticProxy = new StudentServiceProxy(studentService);
        staticProxy.addStudent("static");

        // 动态代理，动态是指动态生成Class--代理类
        //3、proxy
        InvocationHandler handler1 = (InvocationHandler) new TransactionInvocationHandler(studentService);
        StudentService proxy1 = (StudentService) Proxy.newProxyInstance(StudentService.class.getClassLoader(), new Class[] { StudentService.class }, handler1);
        proxy1.addStudent("proxy");        

        InvocationHandler handler2 = (InvocationHandler) new TransactionInvocationHandler(courseService);
        CourseService proxy2 = (CourseService) Proxy.newProxyInstance(StudentService.class.getClassLoader(), new Class[] { CourseService.class }, handler2);
        proxy2.getCourse("proxy");

        //4、多接口
        InvocationHandler handler = new TransactionInvocationHandler(courseService);
        Class proxyClass = Proxy.getProxyClass(StudentService.class.getClassLoader(), new Class[] { StudentService.class, CourseService.class }); // 生成代理类
        Object f = proxyClass.getConstructor(InvocationHandler.class).newInstance(handler); // 用反射实例化代理类
        ((StudentService) f).addStudent("ccc"); // 调用方法
        ((CourseService) f).addCourse("ddd");
    }
}

interface StudentService {
    public void addStudent(String name);

    public void delStudent(String name);
}

class StudentServiceImpl implements StudentService {
    public void addStudent(String name) {
        System.out.println("add a student:" + name);
    }

    public void delStudent(String name) {
        System.out.println("delete a student:" + name);
    }
}

interface CourseService {
    public void addCourse(String name);

    public void getCourse(String name);
}

class CourseServiceImpl implements CourseService, StudentService {

    public void addCourse(String name) {
        System.out.println("add a Course:" + name);
    }

    public void getCourse(String name) {
        System.out.println("get a Course:" + name);
    }

    public void addStudent(String name) {
        System.out.println("add a student:" + name);
    }

    public void delStudent(String name) {
        System.out.println("delete a student:" + name);
    }

}

/**
 * 
 *  静态代理
 *  @author zhqiang
 *  @created 2013-3-19 下午07:07:24
 *  @lastModified       
 *  @history
 */
class StudentServiceProxy implements StudentService {
    private StudentService service;

    public StudentServiceProxy(StudentService service) {
        this.service = service;
    }

    public void addStudent(String name) {
        doBefore();
        service.addStudent(name);
        doAfter();
    }

    public void delStudent(String name) {
        doBefore();
        service.delStudent(name);
        doAfter();
    }

    private void doBefore() {
        System.out.println("Transaction start>>>>>");
    }

    private void doAfter() {
        System.out.println("Transaction end<<<<<<<");
    }

}

/**
 * 
 *  动态代理
 *  @author zhqiang
 *  @created 2013-3-19 下午07:07:36
 *  @lastModified       
 *  @history
 */
class TransactionInvocationHandler implements InvocationHandler {
    public Object obj;

    public TransactionInvocationHandler(Object obj) {
        this.obj = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // position1
        if (method.getName().startsWith("add")) {
            doBefore();
        }
        
        Object result = null;
        try {
            result = method.invoke(this.obj, args);            
        }
        catch (Exception e) {
            // position2
        }

        // position3
        if (method.getName().startsWith("add")) {
            doAfter();
        }
        return result;
    }

    private void doBefore() {
        System.out.println("Transaction start>>>>>");
    }

    private void doAfter() {
        System.out.println("Transaction end<<<<<<<");
    }

}
