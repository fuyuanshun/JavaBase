package com.fys.thread;

import com.fys.bean.People;

/**
 * 设计一个三个线程的死锁的场景
 */
public class ThreadLock {
    static People people1 = new People(1, "fys1", 21);
    static People people2 = new People(2, "fys2", 22);
    static People people3 = new People(3, "fys3", 23);

    public static void main(String[] args) {
        lock();
    }

    private static void lock() {
        new Thread(() ->{
            synchronized (people1) {
                try {
                    //线程休眠1秒，确保线程2获得People2控制权
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread1 People1工作中");
                System.out.println("Thread1 试图让People2接班");
                synchronized (people2) {
                    System.out.println("Thread1 People2工作中");
                }
            }
        }).start();
        new Thread(() ->{
            synchronized (people2) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread2 People2工作中");
                System.out.println("Thread2 试图让People3接班");
                    synchronized (people3) {
                        System.out.println("Thread2 People3工作中");
                    }
            }
        }).start();

        new Thread(() ->{
            synchronized (people3) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread3 People3工作中");
                System.out.println("Thread3 试图让People1接班");
                synchronized (people1) {
                    System.out.println("Thread3 People1工作中");
                }
            }
        }).start();
    }
}