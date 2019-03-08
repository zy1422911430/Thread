package com.yida.demo2;

import sun.misc.Signal;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @ClassName: MultiThread
 * @Description TODO 查看执行一个main方法所，java所启动的线程数量
 * @Author: zyd
 * @Date: 2018/12/8 14:47
 * @Version: 1.0
 */
public class MultiThread {

    public static void main(String[] args) {
        // 获取Java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的monitor和synchronizer信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程ID和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.
                    getThreadName());
        }

//        [5]Attach Listener      添加事件
//        [4]Signal Dispatcher    分发处理给JVM信号的线程
//        [3]Finalizer            调用对象finalize方法的线程
//        [2]Reference Handler    清除reference线程
//        [1]main                 main线程,程序入口
    }
}
