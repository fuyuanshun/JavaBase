package com.fys.thread;

import com.fys.bean.People;

/**
 * 如果线程不添加同步， 会出现读取到脏数据的错误
 */
public class SynchronizedThread {

    public static void main(String[] args) {
        SynchronizedThread synchronizedThread = new SynchronizedThread();
        for (int i = 0; i < 1000; i++) {
            if (0 == synchronizedThread.addOneS()) {
                System.out.println("线程出现不同步错误!");
            }
        }
    }

    private int addOneS() {
        //初始化age的值
        People people = new People(1, "fys", 1000000);
        Thread incrThread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                    people.incrAge();
            }
        });
        incrThread.start();

        Thread decrThread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                    people.decrAge();
            }
        });
        decrThread.start();

        try {
            decrThread.join();
            incrThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (1000000 != people.getAge()) {
            return 0;
        } else {
            System.out.println(people.getAge());
            return people.getAge();
        }
    }

}