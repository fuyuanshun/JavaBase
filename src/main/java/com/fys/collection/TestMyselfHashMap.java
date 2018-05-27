package com.fys.collection;

import com.fys.bean.People;

/**
 * 测试自己的HashMap
 */
public class TestMyselfHashMap {
    public static void main(String[] args) {
        IHashMap iHashMap = new MyHashMap();
        for (int i = 0; i < 2000; i++) {
            iHashMap.put("fys"+i, new People(i, "fys"+i, 21));
        }
        String target = "fys1000";

        long startTimeByMyHM = System.currentTimeMillis();
        People people = (People)iHashMap.get(target);
        if (null == people) {
            System.out.println("没有找到该key对应的值");
            return;
        }
        System.out.println("找到啦。。");
        long endTimeByMyHM = System.currentTimeMillis() - startTimeByMyHM;
        System.out.println("使用MyHashMap查找共消耗"+ endTimeByMyHM +"毫秒");
    }


}