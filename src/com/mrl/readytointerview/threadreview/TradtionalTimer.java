package com.mrl.readytointerview.threadreview;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 传统定时器
 *  [功能详细描述]
 * @作者 lwq
 * @version [版本号, 2018年4月26日]
 * @see [相关类/方法]
 * @since [产品/模块版本] 
 */
public class TradtionalTimer
{
    public static void main(String[] args) {
        //来一个定时器
        Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run() {
                System.out.println("boom!!!");
            }
        }, 3000,2000);
       
       //计时
      /*  while(true){
            try {
                Thread.sleep(1000);
                System.out.println(new Date().getSeconds());
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }*/
        
        
        //
        
        
    }
}
