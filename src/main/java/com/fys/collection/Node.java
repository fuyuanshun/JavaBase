package com.fys.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 二叉树
 */
public class Node<T> {
    private Node<T> leftNode;
    private Node<T> rightNode;
    private T value;

    public static void main(String[] args) {
        Random random = new Random();
        int[] numbers = new int[80000];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(40000);
        }
        Node<Integer> roots = new Node<>();
        for (int number : numbers) {
            roots.add(number);
        }
        System.out.println(roots.get());
    }

    //添加节点
    private void add(T t) {
        //如果值为空，则把数据放在当前节点上
        if (null == this.value) {
            this.value = t;
        } else {
            // 如果新增的值小于等于原来节点的值,则添加到左节点
            if ((Integer) this.value - (Integer) t >= 0) {
                //如果左节点是空的，则创建一个左节点
                if (null == leftNode) {
                    leftNode = new Node<>();
                }
                leftNode.add(t);
                //否则添加到右节点
            } else {
                //如果右节点是空的，则创建一个右节点
                if (null == rightNode) {
                    rightNode = new Node<>();
                }
                rightNode.add(t);
            }
        }
    }

    //中序遍历所有的节点
    public List<T> get() {
        List<T> list = new ArrayList<>();

        //遍历左节点
        if (null != leftNode) {
            list.addAll(leftNode.get());
        }

        //当前节点
        list.add(value);

        //遍历右节点
        if (null != rightNode) {
            list.addAll(rightNode.get());
        }
        return list;
    }
}























