package com.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 
 *  ����Latch
 *  @author zhqiang
 *  @created 2013-2-20 ����10:53:28
 *  @lastModified       
 *  @history
 */

public class LatchTest {

    public static void main(String[] args) throws InterruptedException {
        int count = 10;
        final CountDownLatch startSignal = new CountDownLatch(1);
        final CountDownLatch endSignal = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        startSignal.await();
                        System.out.println(Thread.currentThread());
                        endSignal.countDown();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
        System.out.println("Main do first something");
        startSignal.countDown();//���߳̿�ʼ
        endSignal.await();//���̵߳ȴ�
        System.out.println("Main do second something");

    }

}
