package com.fys.collection;

import com.fys.bean.People;

import java.util.*;

/**
 * 使用聚合方式遍历数组
 */
public class Aggregate {
    public static void main(String[] args) {
        List<People> list = new ArrayList<>();
        //初始化数组
        list = initList(list);
        //传统方式遍历数组
        iteratorList(list);
        //聚合方式遍历数组
        iteratorListByAggregate(list);
    }

    /**
     * 初始化数组
     * @param list
     * @return
     */
    private static List<People> initList(List<People> list) {
        Random random = new Random();
        int ran = 0;
        for (int i = 0; i < 10; i++) {
            ran = random.nextInt(21);
            list.add(new People(1, "fys" + i, ran));
        }
        System.out.println("数组初始化完成..该数组内容为:");
        System.out.println(list);
        return list;
    }

    /**
     * 传统方式遍历数组
     * @param list
     */
    private static void iteratorList(List<People> list) {
        Collections.sort(list, new Comparator<People>() {
            @Override
            public int compare(People o1, People o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        System.out.println("使用传统方式遍历数组,年龄最大的为");
        System.out.println(list.get(0).getAge());
    }

    /**
     * 使用lambda和聚合方式遍历数组
     * @param list
     */
    private static void iteratorListByAggregate(List<People> list) {
        System.out.println("使用聚合方式遍历数组:");
        int age =
                list
                        .stream()
                        .sorted((People o1, People o2) -> o1.getAge() > o2.getAge() ? -1 : 1)
                        .skip(0)
                        .map(People::getAge)
                        .findFirst()
                        .get();
        System.out.println(age);
    }
}