package com.yida.demo9;

/**
 * @ClassName: IThreadPool
 * @Description TODO
 * @Author: zyd
 * @Date: 2018/12/10 18:01
 * @Version: 1.0
 */
public interface IThreadPool<Job extends Runnable> {

    void execute(Job job);

    void shutdown();

    void addWorkers(int num);

    void removeWorker(int num);

    int getJobSize();
}
