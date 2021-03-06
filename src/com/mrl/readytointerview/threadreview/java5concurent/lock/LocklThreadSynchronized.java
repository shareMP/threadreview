package com.mrl.readytointerview.threadreview.java5concurent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 传统线程互斥
 *  [功能详细描述]
 * @作者 lwq
 * @version [版本号, 2018年4月26日]
 * @see [相关类/方法]
 * @since [产品/模块版本] 
 */
public class LocklThreadSynchronized
{
    public static void main(String[] args) {

        final Output output = new Output();

        new Thread(new Runnable()
        {
            @Override
            public void run() {
                while (true) {
                    
                    output.output("李文强");
                }
            }
        }).start();

        new Thread(new Runnable()
        {
            @Override
            public void run() {
                while (true) {
                   
                    output.output("孙中山");
                }
            }
        }).start();
    }

}

class Output
{

    //定义一把锁
    Lock lock = new ReentrantLock();

    public void output(String name) {
        //上个锁
       lock.lock();
        try{
            for (int i = 0; i < name.length(); i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }finally{
          //释放这把锁,防止程序异常锁不能被释放
          lock.unlock();
        }
        
    }

    public synchronized void output1(String name) {
        //一定要用同一个对象,一般都用Output字节码对象,因为只有一份
        //如果要保证两块代码能够互斥，一定要同一个监视器对象(即锁对象)
        //synchronized
        System.out.println(Thread.currentThread().getName() + "拿到了this监视器对象");
        ;
        //拿到this监视器对象的时候，先休息一下，确保另一个线程能够拿到OutPut这个监视器
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < name.length(); i++) {
            System.out.print(name.charAt(i));
        }
        System.out.println();
        synchronized (Output.class) {
            System.out.println(Thread.currentThread().getName() + "拿到了Output监视器对象");
            System.out.println("测试测试测试..............");
        }
    }

    public static synchronized void output2(String name) {
        //一定要用同一个对象,一般都用Output字节码对象,因为只有一份
        //如果要保证两块代码能够互斥，一定要同一个监视器对象(即锁对象)
        for (int i = 0; i < name.length(); i++) {
            System.out.print(name.charAt(i));
        }
        System.out.println();
    }
}
