package com.fys.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 *  反转一个HashMap的key和value
 */
public class ExchangeHashMapKeyAndValue {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        //初始化十条数据
        for (int i = 0; i < 10; i++) {
            hashMap.put("fysKey" + i, "fysValue" + i);
        }
        //获得hashMap所有的key
        Set<String> keys = hashMap.keySet();
        //获得hashMap所有的value
        Collection<String> values = hashMap.values();
        //存放交换后的key Value键值对
        HashMap<String, String> newHashMap = new HashMap();

        for (String key : keys) {
            for (String value : values) {
                //如果遍历到的key和value相等，则交换key value， 然后放入到newHashMap里
                if (hashMap.get(key) == value) {
                    String temp = key;
                    key = value;
                    value = temp;
                    newHashMap.put(key, value);
                }
            }
        }
        //清除原来的key Value 键值对
        hashMap.clear();
        //完成交换
        hashMap.putAll(newHashMap);
        Set<String> newKeys = hashMap.keySet();
        for (String newKey : newKeys) {
            System.out.println(newKey);
        }
    }
}