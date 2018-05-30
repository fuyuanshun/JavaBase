package com.fys.collection;

import java.util.LinkedList;

/**
 * 简单栈实现
 */
public class MyStack<T> {
    LinkedList<T> linkedList = new LinkedList<T>();

    public void push(T t) {
        linkedList.addLast(t);
    }

    public void pull() {
        linkedList.removeLast();
    }

    public T peek() {
        return linkedList.getLast();
    }

    public static void main(String[] args) {
        MyStack<String> stringStack = new MyStack();
        stringStack.push("fys1");
        stringStack.push("fys2");
        stringStack.pull();
        System.out.println(stringStack.peek());

        MyStack<Integer> integerStack = new MyStack();
        integerStack.push(9);
        System.out.println(integerStack.peek());
    }
}