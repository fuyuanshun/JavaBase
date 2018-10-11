package com.fys.operation.operation_2;

public class Dog extends Animal {
    @Override
    public void eat() {
        System.out.println("汪汪汪..好吃");
    }

    @Override
    public void speak() {
        System.out.println("汪汪汪..");
    }

    @Override
    public void run() {
        System.out.println("汪汪汪..快跑");
    }

}