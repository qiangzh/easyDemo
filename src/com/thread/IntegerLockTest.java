package com.thread;


/**
 * 
 *  测试Integer包装类锁互斥问题;i,j变量
 *  @author zhqiang
 *  @created 2013-2-19 上午09:08:39
 *  @lastModified       
 *  @history
 */
public class IntegerLockTest {
    public static void main(String[] args) {
        final Integer i = 5;
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (i) {
                    for (int m = 0; m < 10; m++) {
                        System.out.println("Thread sub : lock 5 ," + m);
                        try {
                            Thread.currentThread().sleep(50);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    Thread.currentThread().sleep(50);
                }
                catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                for (int m = 0; m < 10; m++) {
                    System.out.println("Thread sub : unlock 5 ," + m);
                    try {
                        Thread.currentThread().sleep(50);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        Integer j = 0;
        for (int m = 0; m < 10; m++) {
            System.out.println("Thread main: want get lock " + j);
            synchronized (j) {
                System.out.println("Thread main: lock " + j);
                j++;
            }
            try {
                Thread.currentThread().sleep(50);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
