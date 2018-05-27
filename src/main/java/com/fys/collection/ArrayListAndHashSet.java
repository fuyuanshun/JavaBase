package com.fys.collection;

import com.fys.bean.People;

import java.util.*;

/**
 * ArrayList和HashSet的比较
 * ArrayList查找一个元素，需要遍历数组中的所有元素，直到找到该元素，所以效率较低
 * HashMap查找数据，根据hashcode直接查找key对应位置的值，不需要遍历，所以效率极快,如果对应的位置有多个值
 *      则使用equals方法对比名称是否相同
 */
public class ArrayListAndHashSet {
    public static void main(String[] args) {
//        List<People> peoples = getPeopleArrayList();
        //使用ArrayList查找数据
//        selectTargetByArrayList(peoples);

        //使用HashMap查找数据
        HashMap<String, People> peopleHashMap = getPeopleHashMap();
        selectTargetByHashMap(peopleHashMap);
    }

    /**
     * 获得一个ArrayList并且初始化为200w条数据
     * @return
     */
    private static List<People> getPeopleArrayList() {
        List<People> peoples = new ArrayList<>();
        for (int i = 0; i < 2000000; i++) {
            peoples.add(new People(1, "fys" + i, 21));
        }
        return peoples;
    }

    /**
     * 使用ArrayList查找
     * @param peoples
     */
    private static void selectTargetByArrayList(List<People> peoples) {
        //进行十次查找
        for (int j = 1; j <= 10; j++) {
            //打乱List
            Collections.shuffle(peoples);
            String target = "fys100000";
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < peoples.size(); i++) {
                if (peoples.get(i).getName().equals(target)) {
                    System.out.println("找到啦。。");
                    break;
                }
            }
            long endTime = System.currentTimeMillis() - startTime;
            System.out.println("第" + j + "次: 一共花了" + endTime + "毫秒");
        }

    }

    /**
     * 获取一个HashMap并初始化
     * @return
     */
    private static HashMap<String,People> getPeopleHashMap() {
        Map<String, People> peopleMap = new HashMap<>();
        for (int i = 0; i < 2000000; i++) {
            peopleMap.put("fys" + i, new People(i, "fys" + i, 21));
        }
        return (HashMap<String, People>) peopleMap;
    }

    private static void selectTargetByHashMap(HashMap<String,People> peopleHashMap) {
        String target = "fys100000";
        //同样是进行10次查找
        for (int j = 1; j <= 10; j++) {
            long startTime = System.currentTimeMillis();
            People people = peopleHashMap.get(target);
            if (people == null) {
                System.out.println("没有找到该对象，请确认key是否存在");
                return;
            }
            System.out.println("找到啦。。");
            long endTime = System.currentTimeMillis() - startTime;
            System.out.println("第"+ j +"次,共花费了"+ endTime +"毫秒");
        }
    }

}