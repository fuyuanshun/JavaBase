package com.fys.util;

/**
 * 获取一个字符串的hashcode
 */
public class MyHashCodeClass {
    public static int getHashCode(String string) {
        int hashCode = 0;
        if (0 == string.length()) {
            return 0;
        }
        //将字符串转化为一个字符数组
        char[] chars = string.toCharArray();
        //把每个字符加起来
        for (int i = 0; i < chars.length; i++) {
            hashCode += chars[i]+1;
        }

        hashCode *= 23;
        //取绝对值
        hashCode = hashCode < 0 ? hashCode*-1 : hashCode;

        //如果大于2000， 则取余数,否则还是原数字
        hashCode %= 2000;

        return hashCode;
    }
}