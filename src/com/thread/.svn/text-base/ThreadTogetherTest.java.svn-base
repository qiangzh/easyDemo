package com.thread;

/**
 * 
 *  线程协作问题
 *  @author zhqiang
 *  @created 2013-2-19 上午09:10:54
 *  @lastModified       
 *  @history
 */
public class ThreadTogetherTest {

    public static void main(String[] args) {
        final Iron iron = new Iron();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 50; i++) {
                    iron.lightHit(i);
                }
            }
        }).start();

        for (int i = 0; i < 50; i++) {
            iron.heavyHit(i);
        }
    }
}

class Iron {
    public boolean isLight = true;

    public synchronized void lightHit(int i) {
        while (!isLight) {
            try {
                this.wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 0; j < 2; j++) {
            System.out.println(i + " light hit count " + j);
        }
        isLight = false;
        this.notify();

    }

    public synchronized void heavyHit(int i) {
        while (isLight) {
            try {
                this.wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 0; j < 3; j++) {
            System.out.println(i + " heavy hit count " + j);
        }
        isLight = true;
        this.notify();
    }

}