package com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 *  学习Executors线程池
 *  @author zhqiang
 *  @created 2013-2-19 上午09:08:07
 *  @lastModified       
 *  @history
 */
public class ExecutorTest {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        // ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int count = 0; count < 10; count++) {
            final int threadCount = count;
            threadPool.execute(new Runnable() {
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("Thread " + threadCount + " loop of " + i);
                        try {
                            Thread.sleep(10);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

}
