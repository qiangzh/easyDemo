package com.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 *  ���� Future
 *  @author zhqiang
 *  @created 2013-2-22 ����08:57:17
 *  @lastModified       
 *  @history           
 */

public class FutureTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<Integer> future = threadPool.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                for (int i = 0; i < 15; i++) {
                    System.out.println("Sub " + Thread.currentThread().getName() + " loop " + i);
                    Thread.sleep(30);
                }
                return 200;
            }
        });        
        
        for (int j = 0; j < 10; j++) {
            System.out.println(" Main " + Thread.currentThread().getName() + " loop " + j);
            Thread.sleep(30);
        }
        while (!future.isDone()) {
            System.out.println("waiting....");
            Thread.sleep(10);
        }
        Integer res = future.get();
        System.out.println(res);

    }
}
