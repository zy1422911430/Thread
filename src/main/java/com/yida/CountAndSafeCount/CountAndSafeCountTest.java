package com.yida.CountAndSafeCount;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @className: CountAndSafeCountTest
 * @description: TODO
 * @author: zyd
 * @date: 2019/3/4 10:51
 * @version: 1.0
 */
public class CountAndSafeCountTest {

    private AtomicInteger atomicInteger = new AtomicInteger();

    private List<Thread> threads = new ArrayList<Thread>();

    private Integer count = 0;

    public static void main(String[] args) {
        final CountAndSafeCountTest countAndSafeCountTest = new CountAndSafeCountTest();
        for (int i = 0; i < 100; i++) {
             Thread thread = new Thread(new Runnable() {
                 public void run() {
                     for (int j = 0; j < 1000; j++) {
                         countAndSafeCountTest.addCount();
                         countAndSafeCountTest.atomicAdd();
                     }
                 }
             });
            countAndSafeCountTest.threads.add(thread);
        }

        for (Thread thread : countAndSafeCountTest.threads) {
            thread.start();
        }

        for (Thread t : countAndSafeCountTest.threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(countAndSafeCountTest.count);
        System.out.println(countAndSafeCountTest.atomicInteger.get());
    }

    private void addCount() {
        count ++ ;
        System.out.println("非线程安全：" + count);
    }

    private void atomicAdd() {
        for (;;) {
            int i = atomicInteger.get();
            int c = i+1;
            boolean b = atomicInteger.compareAndSet(i, ++i);
            if (b) {
                break;
            }
        }
        System.out.println("线程安全：" + atomicInteger.get());
    }
}
