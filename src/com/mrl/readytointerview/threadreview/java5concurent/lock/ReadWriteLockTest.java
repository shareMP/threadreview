package com.mrl.readytointerview.threadreview.java5concurent.lock;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *  读写锁测试
 *  [功能详细描述]
 * @作者 lwq
 * @version [版本号, 2018年5月11日]
 * @see [相关类/方法]
 * @since [产品/模块版本] 
 */
public class ReadWriteLockTest
{
    public static void main(String[] args) {
        //读写锁，读的时候别的线程可以读，但是不能写
        //写的时候别的线程不能读也不能写
        //3个线程读 3个线程写  同一个数据  抽象出来一个类，读方法和写方法

        final Queue queue = new Queue();

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable()
            {
                public void run() {
                    while (true) {
                        queue.get();
                    }
                }
            }).start();
            ;

            new Thread(new Runnable()
            {
                public void run() {
                    while (true) {
                        queue.put(new Random().nextInt(1000));
                    }
                }
            }).start();
            ;
        }
    }

}

class Queue
{
    //共享数据，读的时候不能写，写的时候不能读，不能写
    private Object data;

    //先用锁,但是效率不好，读的时候别的线程不能读,用读写锁
//    Lock lock = new ReentrantLock();
    
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    
    
    
    public void get() {

        //得到读锁，锁上
        readWriteLock.readLock().lock();
        
        try {
            System.out.println(Thread.currentThread().getName() + "...准备读了....");
            Thread.sleep((long) Math.random() * 1000);
            System.out.println(Thread.currentThread().getName() + "读到了数据" + data);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            //释放读锁
            readWriteLock.readLock().unlock();
        }

    }

    public void put(Object data) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "...准备写了....");
            Thread.sleep((long) Math.random() * 1000);
            this.data = data;
            System.out.println(Thread.currentThread().getName() + "写入数据" + data);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            readWriteLock.writeLock().unlock();
        }
    }
}
