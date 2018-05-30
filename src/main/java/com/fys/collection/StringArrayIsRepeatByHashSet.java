package com.fys.collection;

import java.util.HashSet;
import java.util.Random;

/**
 * 使用HashSet遍历一个String数组，找出重复的字符串
 */
public class StringArrayIsRepeatByHashSet {
    public static void main(String[] args) {
        getRandomStr();
    }

    private static void getRandomStr() {
        Random random = new Random();
        int ran = 0;
        String str = "";
        HashSet<String> hashSet = new HashSet<>();
        char[] chars = new char[2];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < chars.length; j++) {
                ran = random.nextInt(122-65+1)+65;
                chars[j] = (char)(ran>=91 && ran < 97 ? ran+6 : ran);
            }
            str = new String(chars);
            if (false == hashSet.add(str)) {
                System.out.println("字符串已经存在... 该字符串为:" + str);
            }
        }
    }
}