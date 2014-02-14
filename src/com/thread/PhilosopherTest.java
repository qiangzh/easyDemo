package com.thread;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PhilosopherTest {

    public static void main(String[] args) {
        BlockingQueue q = new LinkedBlockingQueue(5);
        Producer p = new Producer(q);
        Consumer c1 = new Consumer(q);
        Consumer c2 = new Consumer(q);
        new Thread(p).start();
        new Thread(c1).start();
        new Thread(c2).start();
    }
}

class Producer implements Runnable {
    private final BlockingQueue queue;

    Producer(BlockingQueue q) {
        queue = q;
    }

    public void run() {
        try {
            for(int i =0;i<10;i++) {
            //while (true) {
                queue.put(produce());
            }
        }
        catch (InterruptedException ex) {

        }
    }

    Object produce() {
        return (new Random()).nextInt(10) + "";

    }
}

class Consumer implements Runnable {
    private final BlockingQueue queue;

    Consumer(BlockingQueue q) {
        queue = q;
    }

    public void run() {
        try {
            while (true) {
                //consume(queue.take());
                consume(queue.poll(1, TimeUnit.SECONDS));
            }
        }
        catch (Exception ex) {
            System.out.println(ex);

        }
    }

    void consume(Object x) {
        System.out.println(Thread.currentThread().getName()+" deal with "+x);

    }
}
