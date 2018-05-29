package com.fys.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  定义一个长度为10的数组，使用shuffle方法不断打乱顺序，直到前三位出现3 1 4，
 *  使用shuffle 1，000，000 次 统计出现的概率
 */
public class NumberProbility {
    public static void main(String[] args) {
        //前三个数
        int firstNum = 3;
        int secondNum = 1;
        int thirdNum = 4;
        //统计的概率
        double probility;
        //总共计算的次数
        double total = 1000000;
        //3 1 4 出现的次数
        double count = 0;
        List<Integer> list = new ArrayList<>();
        //初始化List
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        //执行total次shuffle并计算3 1 4 出现的次数
        for (int i = 0; i < total; i++) {
            Collections.shuffle(list);
            //如果前三个数字为 3 1 4 则 增加一次次数
            if(firstNum == list.get(1) && secondNum == list.get(2) && thirdNum == list.get(3)) {
                count++;
            }
        }
        //计算概率
        probility = (count/total)*100;
        System.out.println("3 1 4 出现的次数为:"+ count +" 概率为:" + probility + "%");
    }
}