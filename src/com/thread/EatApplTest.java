package com.thread;

/**
 * 线程协同
 *  @author zhqiang
 *  @created 2013-2-28 上午08:55:43
 *  @lastModified       
 *  @history           
 */

public class EatApplTest {

    public static void main(String[] args) {
        final Plate plate = new Plate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    plate.eatApply();
                }
            }
        }).start();

        for (int i = 0; i < 5; i++) {
            plate.putApply();
        }
    }

}

class Plate {
    private int appleCount = 10;

    public synchronized void eatApply() {
        while (appleCount < 1) {
            this.notify();
            try {
                this.wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        appleCount--;
        System.out.println("eat an apply");
    }

    public synchronized void putApply() {
        while (appleCount > 0) {
            this.notify();
            try {
                this.wait(1);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        appleCount = 10;
        System.out.println("put applys on plate");
    }

}
