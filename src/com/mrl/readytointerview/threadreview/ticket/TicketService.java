package com.mrl.readytointerview.threadreview.ticket;

public class TicketService
{
    private static int ticketNum = 2000;
    
    //卖票方法
    public synchronized boolean saleTicket(){
        
        if(ticketNum <=0){
            System.out.println("票已经售完....");
            return false;
        }
        
        System.out.println("当前窗口是："+Thread.currentThread().getName()+"当前卖出的票号是："+ticketNum--);
        return true;
    }
}
