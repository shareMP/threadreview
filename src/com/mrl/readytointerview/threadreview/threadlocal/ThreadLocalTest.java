package com.mrl.readytointerview.threadreview.threadlocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.mrl.readytointerview.threadreview.threadlocal.ThreadScopeShareData.A;
import com.mrl.readytointerview.threadreview.threadlocal.ThreadScopeShareData.B;

public class ThreadLocalTest
{
    
    private static ThreadLocal<Integer> local = new ThreadLocal<>();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable()
            {
                
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName()+" has put data :"+data);
                    local.set(data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    static class A
    {
        public void get() {
            int data =  local.get();
            System.out.println("A from "+Thread.currentThread().getName()+" get data "+data);
        }
    }

    static class B
    {
        public void get() {
            int data =   local.get();
            System.out.println("B from "+Thread.currentThread().getName()+" get data "+data);
        }
    }
}
