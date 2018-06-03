package com.fys.reflection;

/**
 * 在对象方法前加Synchronized的时候，同步对象为当前实例，
 * 如果在静态方法前加Synchronized修的时候，同步对象为类对象
 */
public class ReflectionAndSynchronized {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            ReflectionAndSynchronized.method1();
        });
        thread.setName("线程1");
        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread2 = new Thread(() -> {
           ReflectionAndSynchronized.method2();
        });
        thread2.setName("线程2");
        thread2.start();
    }

    /**
     * 同步对象为当前类对象，执行method1的时候method2不会被执行，说明method2的同步对象也为当前的类对象
     */
    private static void method1() {
        synchronized (ReflectionAndSynchronized.class) {
            System.out.println("方法1执行，已经占用类对象..");
            try {
                System.out.println("方法1休眠五秒...");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized static void method2() {
        System.out.println("方法二运行...");
    }

    /**
     * 当获取类对象的时候，会初始化类属性
     */
    private static void initClassPro() {
        String className = "com.fys.bean.People";
        try {
            //获取类对象会初始化类属性
            Class people = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}