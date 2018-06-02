package com.fys.thread;

import java.util.Random;

public class ProducerThread implements Runnable {
    static  MyStackSynchronized<String> myStackSynchronized = new MyStackSynchronized<>();

    public static MyStackSynchronized getMyStackSynchronized() {
        return myStackSynchronized;
    }

    @Override
    public void run() {
        Random random = new Random();
        char[] ch = new char[1];
        String str = "";
        int ran = 0;
        while (true) {
            ran = random.nextInt(90 - 65 + 1) + 65;
            ch[0] = (char) ran;
            str = new String(ch);
            System.out.println("生产者线程压入:" + str);
            myStackSynchronized.push(str);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
