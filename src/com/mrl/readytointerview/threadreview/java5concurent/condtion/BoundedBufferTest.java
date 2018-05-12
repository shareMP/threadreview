package com.mrl.readytointerview.threadreview.java5concurent.condtion;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  阻塞队列
 *  [功能详细描述]
 * @作者 lwq
 * @version [版本号, 2018年5月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本] 
 */
public class BoundedBufferTest
{
    final Lock lock = new ReentrantLock();
    //是否满
    final Condition notFull = lock.newCondition();
    //是否空
    final Condition notEmpty = lock.newCondition();
    
    private Object[] items = new Object[100];
    
    //
    int putptr,takeptr,count;
    
    public void put(Object x) throws InterruptedException{
        lock.lock();
        try{
            while(count == items.length){
                //满了,不放了
                notFull.await();
            }
            
            //否则的话,赋值
            items[putptr] = x;
            //判断指针是否执行最后一位
            if(++putptr == items.length){
              //将指针指向0
                putptr = 0;
            }
            count++;//放入数据count+1
            //唤醒取数据的condition
            notEmpty.signal();
            
        }finally{
            lock.unlock(); 
        }
    }
    
    public Object take() throws InterruptedException{
        lock.lock();
        try{
            
            while(count == 0){
                //空了，阻塞
                notEmpty.wait();
            }
            
            //取数据
            Object x = items[takeptr];
            //判断指针的位置
            if(++takeptr == items.length){
                takeptr = 0;
            }
            count--;
            notFull.signal();
            
            return x;
            
        }finally{
            lock.unlock();
        }
    }
    
    
}
