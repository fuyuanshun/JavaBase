package com.fys.bean;

import java.io.Serializable;

public class People implements Serializable {
    private Integer id;
    private String name;
    private int age;
    static String hello;
    static{
        System.out.println("初始化 hello");
        hello = "HELLO!";
    }

    public People() {

    }

    public People(Integer id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "id:" + id + " name:" + name + " age:" + age + "";
    }

    /**
     * 测试lambda表达式引用容器中的方法
     *
     * @return
     */
    public boolean matched() {
        return this.age < 10 && this.age > 0;
    }

    /**
     * 添加线程安全
     */
    public synchronized void decrAge() {
        if (age == 900) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        age = age - 1;
    }

    public synchronized void incrAge() {
        if (age == 1000) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        age = age + 1;
        this.notify();
    }
}