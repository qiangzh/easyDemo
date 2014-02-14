package com.thread;

import java.util.concurrent.Semaphore;

/**
 *  Test Semaphore 
 *  Semaphore ͨ���������ƿ��Է���ĳЩ��Դ��������߼��ģ����߳���Ŀ
 *  @author zhqiang
 *  @created 2013-2-21 ����04:43:24
 *  @lastModified       
 *  @history           
 */

public class SemaphoreTest {

    public static void main(String[] args) {
        int MAX_AVAILABLE = 2;
        final Semaphore semaphore = new Semaphore(MAX_AVAILABLE, true);
        for (int count = 0; count < 20; count++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();//���´���ͬһʱ��ֻ���������߳�ִ��
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Thread.currentThread().getName() + " loop " + i);
                            Thread.sleep(30);
                        }
                        semaphore.release();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }

}
