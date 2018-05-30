package com.fys.collection;

import com.fys.util.Entry;
import com.fys.util.MyHashCodeClass;

import java.util.LinkedList;

/**
 * 简单HashMap实现
 */
public class MyHashMap implements IHashMap {
    LinkedList<Entry>[] values = new LinkedList[2000];

    @Override
    public void put(String key, Object object) {
        int hashCode = MyHashCodeClass.getHashCode(key);
        LinkedList<Entry> list = values[hashCode];
        if (null == list) {
            list = new LinkedList<>();
            values[hashCode] = list;
        }
        boolean isFound = false;
        for (Entry entry : list) {
            //如果已经存在了，则替换掉
            if (key.equals(entry.key)) {
                entry.value = object;
                isFound = true;
                break;
            }
        }
        //如果不存在，则重新创建新的键值对
        if (!isFound) {
            Entry entry = new Entry(key, object);
            list.add(entry);
        }
    }

    @Override
    public Object get(String key) {
        int hashCode = MyHashCodeClass.getHashCode(key);
        LinkedList<Entry> list = values[hashCode];
        if (null == list) {
            return null;
        }
        Object result = null;
        for (Entry entiry : list) {
            if (entiry.key.equals(key)) {
                result = entiry.value;
                break;
            }
        }
        return result;
    }
}