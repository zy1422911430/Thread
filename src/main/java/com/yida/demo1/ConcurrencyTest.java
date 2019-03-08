package com.yida.demo1;

/**
 * @ClassName: ConcurrencyTest
 * @Description TODO 测试并发与串行执行效率
 * @Author: zyd
 * @Date: 2018/12/6 14:34
 * @Version: 1.0
 */
public class ConcurrencyTest {

    private static final int count = 100000;

    public static void concurrency() {
        long startTime = System.currentTimeMillis();

        Thread thread = new Thread(new Runnable() {
            public void run() {
                int a = 1;
                for (int i = 0; i < count; i++) {
                    a = i;
                }
            }
        });
        thread.start();

        int b = 0;
        for (int i = count; i >= 0; i--) {
            b = i;
        }

        long endTime = System.currentTimeMillis();

        System.out.println("并发执行耗时：" + (endTime - startTime));
    }

    public static void serial() {
        long startTime = System.currentTimeMillis();

        int a = 1;
        for (int i = 0; i < count; i++) {
            a = i;
        }

        int b = 0;
        for (int i = count; i >= 0; i--) {
            b = i;
        }

        long endTime = System.currentTimeMillis();

        System.out.println("串行执行耗时：" + (endTime - startTime));
    }

    public static void main(String[] args) {
        //第1次测试值 3000   并发执行1ms  串行执行0ms
        //第2次测试值 5000   并发执行2ms  串行执行0ms
        //第3次测试值 10000   并发执行1ms  串行执行1ms
        //第4次测试值 30000   并发执行2ms  串行执行1ms
        //第5次测试值 50000   并发执行2ms  串行执行2ms
        //第6次测试值 100000   并发执行3ms  串行执行4ms
        concurrency();
        serial();

        //线程的创建和上下文切换需要花费时间，在操作不到一定的次数时，执行时间并不会比串行快
    }
}
