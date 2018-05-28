package com.fys.collection;


import com.fys.bean.People;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 使用二叉树根据人的年龄排序
 */
public class PeopleNode {
    public PeopleNode leftNode;
    public PeopleNode rightNode;
    public People value;

    public static void main(String[] args) {
        Random random = new Random();
        List<People> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new People(i, "fys"+i, random.nextInt(21)));
        }
        PeopleNode roots = new PeopleNode();
        for (int i = 0; i < list.size(); i++) {
            roots.add(list.get(i));
        }
        System.out.println(roots.get());
    }

    public void add(Object value) {
        if (null == this.value) {
            this.value = (People) value;
        } else {
            People people = (People) value;
            if (this.value.getAge() - people.getAge() >= 0) {
                if (null == leftNode) {
                    leftNode = new PeopleNode();
                }
                leftNode.add(value);
            } else {
                if (null == rightNode) {
                    rightNode = new PeopleNode();
                }
                rightNode.add(value);
            }
        }
    }

    public List<People> get() {
        List<People> list = new ArrayList<>();
        if (null != leftNode) {
            list.addAll(leftNode.get());
        }
        list.add(value);
        if (null != rightNode) {
            list.addAll(rightNode.get());
        }
        return list;
    }
}