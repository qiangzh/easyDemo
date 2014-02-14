package com.thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalTest2 {
    public static void main(String[] args) throws Exception {
        for (int i = 1; i < 10; i++) {
            final int m = i;
            new Thread(new Runnable() {
                public void run() {
                    Bird bird = Bird.getInstance();
                    List list =bird.getList();
                    List threadLocalList = bird.getThreadLocalList();
                    for(int j =0;j<m;j++){
                        list.add(j);  
                        threadLocalList.add(j);
                        System.out.println(Thread.currentThread().getName()+":list.size = "+list.size());
                        System.out.println(Thread.currentThread().getName()+":threadLocalList.size="+threadLocalList.size());
                    }     
                    System.out.println(Thread.currentThread().getName()+":list.TotalSize = "+list.size());
                    System.out.println(Thread.currentThread().getName()+":threadLocalList.TotalSize="+threadLocalList.size());            
                }
            }).start();
        }
        
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        Bird bird = Bird.getInstance();
        List list =bird.getList();
        System.out.println(list.size());
    }
}

class Bird {
    private Bird() {

    }
    private static Bird bird = new Bird();

    public static Bird getInstance() {
        return bird;

    }

    private ThreadLocal<List> threadLocal = new ThreadLocal<List>();
    private List list = new ArrayList();        

    public List getList() {
        return list;
    }
    
    public List getThreadLocalList() {
        List list = threadLocal.get();
        if (list == null) {
            list = new ArrayList();
            threadLocal.set(list);
        }
        return list;
    }
}