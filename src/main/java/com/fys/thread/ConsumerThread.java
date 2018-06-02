package com.fys.thread;


public class ConsumerThread implements Runnable{
    MyStackSynchronized<String> myStackSynchronized = ProducerThread.getMyStackSynchronized();

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("消费者线程弹出:" + myStackSynchronized.peek());
            myStackSynchronized.pull();
            System.out.println("当前剩余:"+myStackSynchronized.linkedList.size());

        }
    }
}
