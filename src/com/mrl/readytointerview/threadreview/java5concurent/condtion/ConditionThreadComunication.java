package com.mrl.readytointerview.threadreview.java5concurent.condtion;

public class ConditionThreadComunication
{
    public static void main(String[] args) {

        BusinessCode businessCode = new BusinessCode();

        new Thread(new Runnable()
        {

            @Override
            public void run() {
                for (int i = 1; i <= 50; i++) {
                    businessCode.sub(i);
                }
            }
        }).start();

        for (int j = 1; j <= 50; j++) {
            businessCode.main(j);
        }
    }
}
