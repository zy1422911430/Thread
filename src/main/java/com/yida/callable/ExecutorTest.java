package com.yida.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @className: ExecutorTest
 * @description: TODO
 * @author: zyd
 * @date: 2019/3/14 20:46
 * @version: 1.0
 */
public class ExecutorTest {

    public static void main(String[] args) {
        try {
            System.out.println("多线程准备启动中");
            int poolSize = 5;
            ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
            List<Future<String>> futures = new ArrayList<Future<String>>();
            for (int i = 0; i < 5; i++) {
                Callable<String> callable = new CallableTest();
                Future<String> submit = executorService.submit(callable);
                futures.add(submit);
            }
            executorService.shutdown();
            for (Future<String> future : futures) {
                System.out.println(future.get());
            }
            System.out.println("线程运行结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
