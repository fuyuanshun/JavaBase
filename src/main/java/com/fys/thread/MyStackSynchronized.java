package com.fys.thread;

import java.util.Collections;
import java.util.LinkedList;

/**
 * 简单栈实现 线程安全
 */
public class MyStackSynchronized<T> {
    LinkedList<T> linkedList = new LinkedList<T>();

    public MyStackSynchronized() {
        //将linkedList转化为线程安全
        Collections.synchronizedList(linkedList);
    }

    public synchronized void push(T t) {
        if (linkedList.size() == 200) {
            try {
                System.out.println("压入"+ t +"失败..已经达到上限200个，正在等待消费者消费...");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            linkedList.addLast(t);
            this.notify();
        }
    }

    public synchronized void pull() {
        if (linkedList.size() == 0) {
            try {
                System.out.println("弹出失败..当前剩余0个，正在等待生产者生产...");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            linkedList.removeLast();
            this.notify();
        }
    }

    public synchronized T peek() {
        if (linkedList.size() == 0) {
            try {
                System.out.println("当前剩余0,等待生产..");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return linkedList.getLast();
    }

    public synchronized static void main(String[] args) {
        MyStackSynchronized<String> stringStack = new MyStackSynchronized();
        stringStack.push("fys1");
        stringStack.push("fys2");
        stringStack.pull();
        System.out.println(stringStack.peek());

        MyStackSynchronized<Integer> integerStack = new MyStackSynchronized();
        integerStack.push(9);
        System.out.println(integerStack.peek());
    }
}