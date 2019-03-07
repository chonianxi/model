package com.model.thread.pool;

import com.model.thread.TestUser;
import com.model.thread.Zaidongji;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestPool {

    public static void main (String[] args){
        ThreadVariable threadVariable = new ThreadVariable(4);
        Long beginTime = System.currentTimeMillis();
        for (int i=0;i<50000000;i++){
            TestUser testUser = new TestUser(i);
            threadVariable.execute(new Zaidongji(testUser));
        }

        System.out.println("END_TIME:"+(System.currentTimeMillis()-beginTime));

        /*DefaultThreadPool defaultThreadPool = new DefaultThreadPool(4);
        for(int i=0;i<50000000;i++){
            TestUser testUser = new TestUser(i);
            defaultThreadPool.execute(new Zaidongji(testUser));
        }*/

        /*ExecutorService executorService = Executors.newFixedThreadPool(4);
        for(int i=0;i<50000000;i++){
            TestUser testUser = new TestUser(i);
            executorService.execute(new Zaidongji(testUser));
        }*/

    }

}
