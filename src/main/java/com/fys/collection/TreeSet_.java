package com.fys.collection;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * TreeSet的构造方法可以传入一个Comparator， 可以依赖Comparator接口实现倒序排序TreeSet
 */
public class TreeSet_ {
    public static void main(String[] args) {
        //倒序排列
        Comparator<Integer> comparator = (Integer i, Integer j) -> {
            if (i > j) {
                return -1;
            }
            return 1;
        };

        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
        treeSet.add(5);
        treeSet.add(7);
        treeSet.add(6);
        treeSet.add(8);
        System.out.println(treeSet);
    }
}