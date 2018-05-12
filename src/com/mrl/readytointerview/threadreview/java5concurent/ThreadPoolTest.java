package com.mrl.readytointerview.threadreview.java5concurent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *  java5 线程池
 *  [功能详细描述]
 * @作者 lwq
 * @version [版本号, 2018年5月10日]
 * @see [相关类/方法]
 * @since [产品/模块版本] 
 */
public class ThreadPoolTest
{
    public static void main(String[] args) {
        
        //声明一个线程池，固定线程个数的线程池
//        ExecutorService pool = Executors.newFixedThreadPool(3);
        //缓存线程池
//        ExecutorService pool = Executors.newCachedThreadPool();
//        //单个线程  线程死掉之后重新搞一个，保证池子里面始终有一个线程来提供服务
        ExecutorService pool = Executors.newSingleThreadExecutor();
        
        
        //向线程池里面放一些任务
        for(int i=1;i<=10;i++){
            final int task = i;
            pool.execute(new Runnable()
            {
                
                @Override
                public void run() {
                    for(int j=1;j<=10;j++){
                        try {
                            Thread.sleep(20);
                        }
                        catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+" is looping of "+j+" and the task order is "+task);
                    }
                }
            });
        }
        //池子同一时间以3个线程提供服务
        System.out.println(" 10 task is in the pool");
        pool.shutdown();//池中无任务的时候销毁池子 
        
        Executors.newScheduledThreadPool(3).schedule(new Runnable()
        {
            
            @Override
            public void run() {
                //定时运行的任务
                System.out.println("boom!");
            }
        }, 10, TimeUnit.SECONDS);
        
    }
}
