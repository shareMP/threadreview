package com.mrl.readytointerview.threadreview.java5concurent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *  缓存
 *  [功能详细描述]
 * @作者 lwq
 * @version [版本号, 2018年5月11日]
 * @see [相关类/方法]
 * @since [产品/模块版本] 
 */
public class CacheDemo
{

    private Map<String, Object> cahche = new HashMap<>();

    //定义一把读写锁
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        //取数据
    }

    public Object getData(String key) {
        //检查内存是否存在
        // 没有去数据库取,缓存起来
        // 有就返回
        //上读锁
        readWriteLock.readLock().lock();
        Object value = null;
        try {
            cahche.get(key);
            if (value == null) {
                //value 去数据库去取
                // 模拟一下
                // 这里可能会被多个线程调用，可能存在线程共享问题,写的时候需要互斥
                //释放读取
                readWriteLock.readLock().unlock();
                //上写锁,可能有多个线程执行到这里，只有一个线程能上锁成功,其他的等待，第一个线程释放读锁的时候继续上写锁进行写
                readWriteLock.writeLock().lock();
                try {
                  //再次判断防止多个线程进入多次赋值  
                    if (value == null) {
                        value = new Random().nextInt(100);
                    }
                }
                finally {
                    //释放写锁
                    readWriteLock.writeLock().unlock();
                }
                //恢复读锁
                readWriteLock.readLock().lock();
            }
        }
        finally {
            //释放读锁
            readWriteLock.readLock().unlock();
        }
        return value;
    }
}
