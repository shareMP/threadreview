package com.mrl.readytointerview.threadreview.interviewtest;

public class BusinessCode
{
    
    private boolean beShouldSub = true;
    
    public synchronized void sub(int j){
        while(!beShouldSub){
            try {
                this.wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i=1;i<=100;i++){
            System.out.println("sub method .."+i+" loop of "+j);
        } 
        beShouldSub = false;
        this.notifyAll();
    }
    
    public synchronized void main(int j){
        while(beShouldSub){
            try {
                this.wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        } 
        for(int i=1;i<=10;i++){
            System.out.println("main method .."+i+" loop of "+j);
        }
        
        //改变标志位
        beShouldSub = true;
        //将该对象上的等待池的线程唤醒->到锁池去竞争锁
        this.notifyAll();
    }
}
