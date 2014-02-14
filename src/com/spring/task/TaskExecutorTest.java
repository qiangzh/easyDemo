package com.spring.task;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TaskExecutorTest {

    public static void main(String[] args) throws Exception {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:com/spring/task/applicationContext.xml");
        TaskExecutorExample taskExecutorExample = (TaskExecutorExample) appContext.getBean("taskExecutorExample");
        taskExecutorExample.printMessages();
                      
        System.out.println("aa");
        while(true){
            
        }
    }

}
