package com.fys.thread;

import java.util.Collections;
import java.util.LinkedList;

/**
 * 简单栈实现 线程安全
 */
public class MyStackSynchronized<T> {
    LinkedList<T> linkedList = new LinkedList<>();

    public MyStackSynchronized() {
        //将linkedList转化为线程安全
        Collections.synchronizedList(linkedList);
    }

    public synchronized void push(T t) {
        while (linkedList.size() >= 200) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        linkedList.addLast(t);
    }

    public synchronized T pull() {
        while (linkedList.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        return linkedList.removeLast();
    }

    public synchronized T peek() {
        return linkedList.getLast();
    }
    /*public synchronized static void main(String[] args) {
        MyStackSynchronized<String> stringStack = new MyStackSynchronized();
        stringStack.push("fys1");
        stringStack.push("fys2");
        stringStack.pull();
        System.out.println(stringStack.peek());

        MyStackSynchronized<Integer> integerStack = new MyStackSynchronized();
        integerStack.push(9);
        System.out.println(integerStack.peek());
    }*/
}