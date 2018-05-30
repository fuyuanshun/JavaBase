package com.fys.util;

import com.fys.bean.People;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * People工具类
 */
public class PeopleListUtil {
    /**
     *  获取一个初始化后的List<People>,年龄为0-21随机生成
     * @param j
     * @return
     */
    public static List<People> getInitPeople(int j) {
        Random random = new Random();
        int ran = 0;
        List<People> list = new ArrayList<>();
        for (int i = 0; i < j; i++) {
            ran = random.nextInt(21);
            list.add(new People(i, "fys" + i, ran));
        }
        return list;
    }
}