package com.fys.collection;

import com.fys.bean.People;

import java.util.*;

/**
 * 比较器
 */
public class Comparator_ {
    public static void main(String[] args) {
        Random random = new Random();
        int ran = 0;
        List<People> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ran = random.nextInt(30);
            list.add(new People(i, "fys"+ran, (ran+1)*2));
        }

        //使用匿名类
       /* Comparator<People> comparator = (People o1, People o2) -> {
            return o1.getAge() - o2.getAge();
        };*/

        //如果不添加Comparator接口则会报错，因为People不是一个可比较的类型
        //另一种方法是让People实现Comparable接口，就不需要在此类中写内部类


        //lambda引用静态方法
//        Collections.sort(list, Comparator_::comparatorAgeByStatic);

        //引用对象方法
        Comparator_ comparator = new Comparator_();
        Collections.sort(list, comparator::comparatorAgeByObject);

        System.out.println(list);
    }

    /**
     * lambda引用对象方法
     * @param o1
     * @param o2
     * @return
     */
    private int comparatorAgeByObject(People o1, People o2) {
        return o1.getAge() - o2.getAge();
    }


    /**
     * lambda引用静态方法
     * @param o1
     * @param o2
     * @return
     */
    private static int comparatorAgeByStatic(People o1, People o2) {
        return o1.getAge() - o2.getAge();
    }
}