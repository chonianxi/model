package com.model.thread.pool;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadPoolLocal <Job extends Runnable> implements ThreadPool<Job> {

    private static final int MAX_WORK_THREAD = 10;

    private static final int DEFAULT_WORK_THREAD = 2;

    private static final int MIN_WORK_THREAD = 1;

    private final LinkedList<Job> jobs = new LinkedList<Job>();

    private final List<Worker> wrokerList = Collections.synchronizedList(new ArrayList<Worker>());

    private int workNum = DEFAULT_WORK_THREAD;

    private AtomicLong threadNum = new AtomicLong();



    @Override
    public void execute(Job job) {
        if (job!=null){
            synchronized (jobs){
                jobs.addLast(job);
                jobs.notify();
            }
        }

    }

    @Override
    public void shutdown() {
        for (Worker worker : wrokerList){
            worker.shutDown();
        }

    }

    public void initWorkers(int num){
        for (int i=0; i<num; i++){
            Worker worker = new Worker();
            wrokerList.add(worker);
            Thread thread = new Thread(worker,"ThreadPool-Worker-"+this.threadNum.incrementAndGet());
            thread.start();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs){
            if (num+this.workNum>MAX_WORK_THREAD){
                num = MAX_WORK_THREAD-this.workNum;
            }
            initWorkers(num);
            this.workNum += num;
        }
    }

    @Override
    public void removeWorker(int num) {
        synchronized ((jobs)){
            if (num>=this.workNum){
                throw new IllegalArgumentException("当前运行的线程少于该删除的线程。");
            }
            int stopCount = 0;
            while (stopCount<num){
                Worker worker = wrokerList.get(stopCount);
                if (wrokerList.remove(worker)){
                    worker.shutDown();
                    ++stopCount;
                }
            }
            this.workNum = workNum-stopCount;

        }

    }

    @Override
    public int getJobSize() {
        return wrokerList.size();
    }

    class Worker implements Runnable{
        private volatile  boolean running = true;

        @Override
        public void run() {

            while (running){
                Job job = null;
                synchronized (jobs){
                    while (jobs.isEmpty()){
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.getFirst();
                }
                if (null!=job){
                    try{
                        job.run();
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                }

            }

        }

        public void shutDown(){
            running = false;
        }
    }


}
