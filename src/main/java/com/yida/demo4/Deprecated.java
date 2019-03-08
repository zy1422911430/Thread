package com.yida.demo4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: Deprecated
 * @Description TODO 测试线程的启动、暂停、恢复、停止
 * @Author: zyd
 * @Date: 2018/12/10 10:29
 * @Version: 1.0
 */
public class Deprecated {

    public static void main(String[] args) throws Exception {
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        Runner runner = new Runner();
        Thread printThread = new Thread(runner, "PrintThread");
        printThread.setDaemon(true);
        printThread.start();
        TimeUnit.SECONDS.sleep(3);
        // 将PrintThread进行暂停，输出内容工作停止
        runner.pauseThread();
        System.out.println("Interrupted状态" + printThread.isInterrupted());
        System.out.println("main suspend PrintThread at " + format.format(new Date()) + ",当前线程状态" + printThread.getState());
        TimeUnit.SECONDS.sleep(3);
        // 将PrintThread进行恢复，输出内容继续
        runner.resumeThread();
        System.out.println("main resume PrintThread at " + format.format(new Date()) + ",当前线程状态" + printThread.getState());
        TimeUnit.SECONDS.sleep(3);
        // 将PrintThread进行终止，输出内容停止
        printThread.interrupt();
        System.out.println("main stop PrintThread at " + format.format(new Date()) + ",Interrupted状态" + printThread.isInterrupted());
        TimeUnit.SECONDS.sleep(3);
    }

    static class Runner implements Runnable {

        private static final Object lock = new Object();

        private boolean pause = false;

        private boolean isBreak = false;

        /**
         * @Author: zyd
         * @Description: //TODO 暂停当前线程
         * @Date: 11:01 2018/12/10
         * @Return: void
         **/
        void pauseThread() {
            pause = true;
        }


        /**
         * @Author: zyd
         * @Description: //TODO 恢复当前线程
         * @Date: 11:02 2018/12/10
         * @Return: void
         **/
        void resumeThread() {
            pause = false;
            synchronized (lock) {
                lock.notify();
            }
        }

        /**
         * @Author: zyd
         * @Description: //TODO 退出当前线程
         * @Date: 11:02 2018/12/10
         * @Return: void
         **/
        void breakThread() {
            isBreak = true;
        }

        /**
         * @Author: zyd
         * @Description: //TODO 使用lock来让当前线程处于阻塞状态
         * @Date: 11:03 2018/12/10
         
         * @Return: void
         **/
        void onPause() {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void run() {
            DateFormat format = new SimpleDateFormat("HH:mm:ss");
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    if (pause) {
                        onPause();
                    }
                    System.out.println(Thread.currentThread().getName() + " Run at " +
                            format.format(new Date()));
                    Thread.sleep(100);
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }
}
