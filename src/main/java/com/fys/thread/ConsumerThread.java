package com.fys.thread;


public class ConsumerThread implements Runnable{
    private MyStackSynchronized<String> myStackSynchronized;

    public ConsumerThread(MyStackSynchronized<String> myStackSynchronized, String name) {
        Thread.currentThread().setName(name);
        this.myStackSynchronized = myStackSynchronized;
    }

    @Override
    public void run() {
        while (true) {
            String str = myStackSynchronized.pull();
            System.out.println(Thread.currentThread().getName() + "弹出:" + str);
            System.out.println("当前剩余:"+myStackSynchronized.linkedList.size());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
