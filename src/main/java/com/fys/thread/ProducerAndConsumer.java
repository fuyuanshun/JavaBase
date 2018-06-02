package com.fys.thread;

public class ProducerAndConsumer {

    public static void main(String[] args) {
        Thread producerThread = new Thread(new ProducerThread());
        Thread consumerThread = new Thread(new ConsumerThread());
        producerThread.start();
        consumerThread.start();
    }
}