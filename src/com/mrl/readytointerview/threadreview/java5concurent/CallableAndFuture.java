package com.mrl.readytointerview.threadreview.java5concurent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFuture
{
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        Future<String> future =  
        pool.submit(new Callable<String>()
        {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "hello";
            }
        });
        
        System.out.println("等待结果");
        System.out.println(future.get());
    }
}
