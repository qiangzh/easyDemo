package com.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *  用Lock实现线程协作问题
 *  @author zhqiang
 *  @created 2013-2-19 上午09:12:30
 *  @lastModified       
 *  @history
 */
public class ThreadTogetherTest2 {

    public static void main(String[] args) {
        final Copper copper = new Copper();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 50; i++) {
                    copper.lightHit(i);
                }
            }
        }).start();

        for (int i = 0; i < 50; i++) {
            copper.heavyHit(i);
        }
    }
}

class Copper {
    public boolean isLight = true;

    public Lock lock = new ReentrantLock();

    public final Condition lightCond = lock.newCondition();

    public final Condition heavyCond = lock.newCondition();

    public void lightHit(int i) {
        lock.lock();
        try {
            while (!isLight) {
                try {
                    lightCond.await();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            for (int j = 0; j < 2; j++) {
                System.out.println(i + " light hit count " + j);
            }
            isLight = false;
            heavyCond.signal();

        }
        finally {
            lock.unlock();
        }

    }

    public void heavyHit(int i) {
        lock.lock();
        try {
            while (isLight) {

                try {
                    heavyCond.await();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 0; j < 3; j++) {
                System.out.println(i + " heavy hit count " + j);
            }
            isLight = true;
            lightCond.signal();
        }
        finally {
            lock.unlock();

        }
    }

}