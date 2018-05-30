package com.fys.generic;


import java.util.ArrayList;
import java.util.List;

/**
 * 如果使用? extends Comparable,
 * 只有实现了Comparable接口的类才能被添加到NodeByGenericExtends中。
 * @param <T>
 */
public class NodeByGenericExtends<T extends Comparable<T>> {
    private NodeByGenericExtends<T> leftNode;
    private NodeByGenericExtends<T> rightNode;
    private T value;

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        //初始化100条数据
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        //根节点
        NodeByGenericExtends<Integer> roots = new NodeByGenericExtends<>();
        //因为Book没有实现Comparable接口，所以不能添加到Node中.
//        NodeByGenericExtends<Book> books = new NodeByGenericExtends<>();
        for (int i : list) {
            roots.add(i);
        }
        System.out.println(roots.get());
    }

    public void add(T t) {

        if (null == value) {
            value = t;
        } else {
            if (t.compareTo(value) > 0) {
                if (null == rightNode) {
                    rightNode = new NodeByGenericExtends<>();
                }
                rightNode.add(t);
            } else {
                if (null == leftNode) {
                    leftNode = new NodeByGenericExtends<>();
                }
                leftNode.add(t);
            }
        }
    }

    /**
     * 将二叉树的值添加到一个List中，并返回该List
     * @return
     */
    public List<T> get() {
        List<T> list = new ArrayList<>();
        //如果左节点不为空，则添加到list中
        if (null != leftNode) {
            list.addAll(leftNode.get());
        }
        //当前节点
        list.add(value);
        if (null != rightNode) {
            list.addAll(rightNode.get());
        }
        return list;
    }
}