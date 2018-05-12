package com.mrl.readytointerview.threadreview.java5concurent.condtion;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BusinessCode
{

    private Lock lock = new ReentrantLock();

    private boolean beShouldSub = true;
    
    Condition condition = lock.newCondition();

    public void sub(int j) {
        lock.lock();
        try {
            while (!beShouldSub) {
                try {
                   // this.wait();
                    condition.await();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (int i = 1; i <= 100; i++) {
                System.out.println("sub method .." + i + " loop of " + j);
            }
            beShouldSub = false;
            //this.notifyAll();
            condition.signal();
        }
        finally {
            lock.unlock();
        }

    }

    public void main(int j) {
        lock.lock();
        try {
            while (beShouldSub) {
                try {
                    //this.wait();
                    condition.await();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println("main method .." + i + " loop of " + j);
            }

            //改变标志位
            beShouldSub = true;
            //将该对象上的等待池的线程唤醒->到锁池去竞争锁
            //this.notifyAll();
            condition.signal();
        }
        finally {
            lock.unlock();
        }
    }
}
