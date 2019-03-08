package com.yida.demo7;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: Join
 * @Description TODO
 * @Author: zyd
 * @Date: 2018/12/10 16:32
 * @Version: 1.0
 */
public class Join {

    public static void main(String[] args) throws Exception {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            System.out.println(previous.getName());
            // 每个线程拥有前一个线程的引用，需要等待前一个线程终止，才能从等待中返回
            Thread thread = new Thread(new Domino(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }
        //System.out.println("main线程休眠5s");
        //TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }

    static class Domino implements Runnable {
        private Thread thread;
        public Domino(Thread thread) {
            this.thread = thread;
        }
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+"线程休眠1s");
                Thread.sleep(1000);
                thread.join();
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }
}
