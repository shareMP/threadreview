package com.mrl.readytointerview.threadreview.ticket;

public class TicketMain
{
    public static void main(String[] args) {
        
        SaleTicketTask task = new SaleTicketTask();
        
        //售票窗口1
        Thread t1 = new Thread(task,"窗口1");
        //售票窗口2
        Thread t2 = new Thread(task,"窗口2");
        //售票窗口3
        Thread t3 = new Thread(task,"窗口3");
        //售票窗口4
        Thread t4 = new Thread(task,"窗口4");
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class SaleTicketTask implements Runnable{
    
    TicketService ticketService = new TicketService();
    
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(10);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean saleTicket = new TicketService().saleTicket();
            if(!saleTicket){
                System.out.println("票已经售完！！！");
                return;
            }
        }
    }
    
}
