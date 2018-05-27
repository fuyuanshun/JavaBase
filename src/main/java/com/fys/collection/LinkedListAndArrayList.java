package com.fys.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * linkedList 实现了List接口和Quene、Deque、
 * Quene 队列
 * Deque 双向链表
 * insertUsedTime方法: 测试ArrayList和LinkedList插入数据所用时间的比较
 * selectMumber方法: 测试ArrayList和LinkedList查找数据所用时间的比较
 */
public class LinkedListAndArrayList {
    public static void main(String[] args) {
      insertUsedTime();
//        selectMumber();
    }

    /**
     * 测试ArrayList和LinkedList插入数据所用时间的比较
     */
    private static void insertUsedTime() {
        List<Integer> list = null;
        //ArrayList是顺序结构，插入数据较慢，查找数据快
        list = new ArrayList<>();
        insert(list, "ArrayList");
        //LinkedList是链表结构，插入数据快，查找数据慢
        list = new LinkedList<>();
        insert(list, "LinkedList");
    }

    private static void insert(List<Integer> list, String type) {
        int total = 100*1000;
        long startTimeByFirst = System.currentTimeMillis();
        //在最前面插入10w条记录
        for (int i = 0; i < total; i++) {
            list.add(0, i+2);
        }
        long endTimeByFirst = System.currentTimeMillis() - startTimeByFirst;
        System.out.println("类型为"+ type +"类型的List<<最前面>>添加"+ total +"条记录的时间为:"+ endTimeByFirst +"毫秒");
        //添加完数据后，清空List，否则会影响下面的结果.
        list.clear();

        long startTimeByMiddle = System.currentTimeMillis();
        //在中间插入10w条记录
        for (int i = 0; i < total; i++) {
            list.add(i/2, i+2);
        }
        long endTimeByMiddle = System.currentTimeMillis() - startTimeByMiddle;
        System.out.println("类型为"+ type +"类型的List<<中间>>添加"+ total +"条记录的时间为:"+ endTimeByMiddle +"毫秒");
        list.clear();


        //linkedList在最后面添加数据的时候，其他数据不需要移动，所以速度和链表不相上下
        long startTimeByLast = System.currentTimeMillis();
        //在最前面插入10w条记录
        for (int i = 0; i < total; i++) {
            list.add(i, i+2);
        }
        long endTimeByLast = System.currentTimeMillis() - startTimeByLast;
        System.out.println("类型为"+ type +"类型的List<<最后面>>添加"+ total +"条记录的时间为:"+ endTimeByLast +"毫秒");
        list.clear();
    }

    /**
     * 测试ArrayList和LinkedList查找数据所用时间的比较
     */
    private static void selectMumber() {
        List<Integer> list = null;
        //ArrayList是顺序结构，插入数据较慢，查找数据快
        list = new ArrayList<>();
        selectUsedTime(list, "ArrayList");
        //LinkedList是链表结构，插入数据快，查找数据慢
        list = new LinkedList<>();
        selectUsedTime(list, "LinkedList");
    }

    private static void selectUsedTime(List<Integer> list, String type) {
        int total = 100*1000;
        //添加数据
        for (int i = 0; i < total; i++) {
            list.add(i);
        }

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < total; i++) {
            list.get(total / 2);
        }
        long endTime = System.currentTimeMillis() - startTime;
        System.out.println("类型为"+ type +"类型的List遍历"+ total +"条记录的时间为:"+ endTime +"毫秒");
    }
}