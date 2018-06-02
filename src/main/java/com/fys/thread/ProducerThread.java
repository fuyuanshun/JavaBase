package com.fys.thread;

import java.util.Random;

public class ProducerThread implements Runnable {
    private MyStackSynchronized<String> myStackSynchronized;

    public ProducerThread(MyStackSynchronized<String> myStackSynchronized, String name) {
        Thread.currentThread().setName(name);
        this.myStackSynchronized = myStackSynchronized;
    }

    @Override
    public void run() {
        while (true) {
            String str = randomStr();
            System.out.println(Thread.currentThread().getName() + "压入:" + str);
            myStackSynchronized.push(str);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private String randomStr() {
        Random random = new Random();
        char[] ch = new char[1];
        String str = "";
        int ran = 0;

        ran = random.nextInt(90 - 65 + 1) + 65;
        ch[0] = (char) ran;
        str = new String(ch);
        return str;
    }
}
