package com.mrl.readytointerview.threadreview.java5concurent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *  局部变量不被线程共享,线程隔离的
 *  [功能详细描述]
 * @作者 lwq
 * @version [版本号, 2018年5月10日]
 * @see [相关类/方法]
 * @since [产品/模块版本] 
 */
public class AutoIntegerDemo
{
     private AtomicInteger count = new AtomicInteger(18);
     
     
     
}
