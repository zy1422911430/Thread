package com.yida.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @className: CallableTest
 * @description: TODO
 * @author: zyd
 * @date: 2019/3/14 20:06
 * @version: 1.0
 */
public class CallableTest implements Callable<String> {

    public String  call() throws Exception {
        System.out.println("我比你先打印");
        return "hahahaha";
    }

    public static void main(String[] args) {
        Callable<String> callable = new CallableTest();
        FutureTask<String> futureTask = new FutureTask<String>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println("看谁先打印");
        String str = null;
        try {
            str = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(str);
    }
}
