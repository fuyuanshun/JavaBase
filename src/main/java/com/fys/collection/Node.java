package com.fys.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树
 */
public class Node {
    public Node leftNode;
    public Node rightNode;
    public Object value;

    public static void main(String[] args) {
        int[] numbers = new int[]{67, 7, 30, 73, 10, 0, 78, 81, 10, 74};
        Node roots = new Node();
        for (Object object : numbers) {
            roots.add(object);
        }
        System.out.println(roots.get());
    }

    //添加节点
    public void add(Object value) {
        //如果值为空，则把数据放在当前节点上
        if (null == this.value) {
            this.value = value;
            return;
        }

        // 如果新增的值小于等于原来节点的值,则添加到左节点
        if ((Integer) this.value - (Integer) value >= 0) {
            //如果左节点是空的，则创建一个左节点
            if (null == leftNode) {
                leftNode = new Node();
            }
            leftNode.add(value);
            //否则添加到右节点
        } else {
            //如果右节点是空的，则创建一个右节点
            if (null == rightNode) {
                rightNode = new Node();
            }
            rightNode.add(value);
        }
    }

    //中序遍历所有的节点
    public List<Object> get() {
        List<Object> list = new ArrayList<>();

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























