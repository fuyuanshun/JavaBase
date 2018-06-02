package com.fys.thread;

public class ProducerAndConsumer {

    public static void main(String[] args) {
        MyStackSynchronized<String> myStackSynchronized = new MyStackSynchronized<>();

        new Thread(new ProducerThread(myStackSynchronized, "生产者线程1")).start();
        new Thread(new ProducerThread(myStackSynchronized, "生产者线程2")).start();
        new Thread(new ConsumerThread(myStackSynchronized, "消费者线程1")).start();
        new Thread(new ConsumerThread(myStackSynchronized, "消费者线程2")).start();
        new Thread(new ConsumerThread(myStackSynchronized, "消费者线程3")).start();
    }
}