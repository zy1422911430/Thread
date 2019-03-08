package com.yida.demo5;

/**
 * @ClassName: WaitNotify
 * @Description TODO
 * @Author: zyd
 * @Date: 2018/12/10 15:24
 * @Version: 1.0
 */
public class WaitNotify {

    static final Object lock = new Object();

    static int flag = 1;

    public static void main(String[] args) {
        Thread thread = new Thread(new TestA());
        thread.start();
        Thread thread1 = new Thread(new TestB());
        thread1.start();
        Thread thread2 = new Thread(new TestC());
        thread2.start();
    }

    static class TestA implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                while (true) {
                    try {
                        if (flag == 1) {
                            System.out.println("执行TestA");
                            Thread.sleep(1000);
                            flag = 2;
                        }
                        lock.notifyAll(); //唤醒其他线程
                        lock.wait();//当前线程等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class TestB implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                while (true) {
                    try {
                        if (flag == 2) {
                            System.out.println("执行TestB");
                            Thread.sleep(1000);
                            flag = 3;
                        }
                        lock.notifyAll();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class TestC implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                while (true) {
                    try {
                        if (flag == 3) {
                            System.out.println("执行TestC");
                            Thread.sleep(1000);
                            flag = 1;
                        }
                        lock.notifyAll();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
