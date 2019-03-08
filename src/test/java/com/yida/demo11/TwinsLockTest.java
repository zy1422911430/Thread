package com.yida.demo11;

import org.junit.Test;

import java.util.concurrent.locks.Lock;

/**
 * @ClassName: TwinsLockTest
 * @Description TODO
 * @Author: zyd
 * @Date: 2018/12/14 10:53
 * @Version: 1.0
 */
public class TwinsLockTest {

    @Test
    public void test() {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
        // 启动10个线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.setName("thread"+i);
            w.start();
        }
        // 每隔1秒换行
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
