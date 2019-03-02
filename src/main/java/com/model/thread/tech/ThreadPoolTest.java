package com.model.thread.tech;

import com.model.thread.PoolRunnable;
import com.model.thread.PoolRunnableThreadLocal;
import com.model.thread.UserTest;
import com.model.thread.http.SimpleHttpServer;
import com.model.thread.pool.DefaultThreadPool;
import com.model.thread.pool.ThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {

    static ThreadPool<PoolRunnable> threadPool = new DefaultThreadPool<PoolRunnable>(16);
    static ExecutorService executorService = Executors.newFixedThreadPool(16);

    public static void main(String[] args){


        for(int i = 0;i<100;i++){
            UserTest userTest = new UserTest();
            userTest.setName("test"+i);
            userTest.setNum(i);
            threadPool.execute(new PoolRunnable(userTest));
            //executorService.execute(new PoolRunnable(userTest));
        }


        /*for(int i = 0;i<100;i++){
            UserTest userTest = new UserTest();
            userTest.setName("test"+i);
            userTest.setNum(i);
            executorService.execute(new PoolRunnableThreadLocal(userTest));
        }*/


        //threadPool.shutdown();

    }


}
