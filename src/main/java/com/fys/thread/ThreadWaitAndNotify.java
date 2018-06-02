package com.fys.thread;

import com.fys.bean.People;

/**
 * 线程交互
 */
public class ThreadWaitAndNotify {
    static People people = new People(1, "fys", 1000);

    public static void main(String[] args) {
        ThreadWaitAndNotify thread = new ThreadWaitAndNotify();
        thread.waitAndNotify();
    }

    private void waitAndNotify() {
        new Thread(() -> {
            while (true) {
                people.decrAge();
                System.out.println("当前年龄:" + people.getAge());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                people.incrAge();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}