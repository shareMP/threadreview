package com.mrl.readytointerview.threadreview;

import org.junit.Test;

/**
 *  创建线程的方式
 *  [功能详细描述]
 * @作者 lwq
 * @version [版本号, 2018年4月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本] 
 */
public class CreateThreadWay
{
    
    //两种方式
    //创建Thread的子类，重写run方法，这样根据多态，就会执行run方法
    //创建Thread类，传一个runnable对象。
    
    //如果同时的话
    
    
    
    public static void main(String[] args) {
//        Thread t1 = new Thread(()->{
//            while(true){
//                try {
//                    Thread.sleep(500);
//                }
//                catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                System.out.println("0号线程："+Thread.currentThread().getName());
//            }
//        });
//        t1.start();
//        
//        Thread t2 = new Thread(()->{
//            while(true){
//                try {
//                    Thread.sleep(500);
//                }
//                catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                System.err.println("1号线程："+Thread.currentThread().getName());
//            }
//        });
//        t2.start();
//        
//        while(true){
//            try {
//                Thread.sleep(500);
//            }
//            catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            System.err.println("main线程："+Thread.currentThread().getName());
//        }
        
        Runnable task =  new Runnable()
        {
            
            @Override
            public void run() {
                System.out.println("run");
            }
        };
        
        Thread t1 =new CreateThreadWay().new MyThread(task);
        
        //t1.start();
        
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        //匿名内部类,声明一个匿名内部类，继承自Thread类,注意，匿名的意思是这个类没有名字，不是不可以把它赋值给一个变量
        //就是Thread的一个子类，这个类没有名字而已,不像是class MyThread extends Thread这样
        //赋值给t2完全没问题
        Thread t2 = new Thread()
        {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                super.run();
            }
        };
        
        //声明线程的第二种方式
        new Thread(new Runnable()
        {
            
            @Override
            public void run() {
                // TODO Auto-generated method stub
                
            }
        });
        
        
        //因为多态的原因，如果声明了子类，并且覆盖了run方法，根据多态，父类的run方法就不起作用了。
        //如果没有覆盖父类的run方法,就会调用父类的run方法,父类的run方法执行的代码是，调用target的run方法
        
        new Thread(new Runnable()
        {
            
            @Override
            public void run() {
                System.out.println("runable run");
            }
        })
        {
            @Override
            public void run() {
               System.out.println("run....");
            }
        }.start();;
        
    }
    
    
    @Test
    public void testWay1() throws InterruptedException{
        //创建线程的第一种方式
        //线程是程序的一条执行线索
        //在java里面用Thread这个对象来表示
        
        //先new 一个 Thread试下,瞄一下Thread的源代码
        // 打印一下1-200 数字
        //  主线程死掉，之后其他线程也会死掉
        Thread t1 = new Thread(()->{
              for (int i = 0; i < 19999; i++) {
                System.err.println(i);
                System.out.println(Thread.currentThread().getName());
            }
        });
        t1.start();
        Thread.sleep(2000);
       // System.out.println(Thread.currentThread().getName());
        
        while(true){
            //
        }
    }
   
    
    @Test
    public void testWay2(){
        
        //新建一个Thread的子类，覆盖其中的run方法
        
//        MyThread myThread = new MyThread();
//        myThread.start();
//        
        
            Thread t1 = new Thread(()->{
                while(true){
                    System.out.println("0号线程："+Thread.currentThread().getName());
                }
            });
            t1.start();
            
            Thread t2 = new Thread(()->{
                while(true){
                    System.err.println("1号线程："+Thread.currentThread().getName());
                }
            });
            t2.start();
        
    }
   
class MyThread extends Thread{
   
    
    
    public MyThread(Runnable task) {
       super(task);
    }

    //复写父类的方法
    @Override
    public void run() {
        for (int i = 0; i < 199; i++) {
            System.err.println(i);
            System.out.println(Thread.currentThread().getName());
        }
    }
}
     
}
