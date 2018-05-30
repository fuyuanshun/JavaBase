package com.fys.collection;

import java.util.LinkedHashSet;

/**
 * 使用LinkedHashSet输出Math.PI的值， 要求按照顺序输出并且不出现重复的字符
 */
public class OutputPIByLinkedHashSet {
    public static void main(String[] args) {
        LinkedHashSet<Character> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet = getOrderPI(linkedHashSet);
        for (char c : linkedHashSet) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    /**
     * 获得排序好并且不含有重复字符的PI的LinkedHashSet
     * @param linkedHashSet
     * @return
     */
    private static LinkedHashSet<Character> getOrderPI(LinkedHashSet<Character> linkedHashSet) {
        double num = Math.PI;
        //将double转化为String类型，否则无法分开添加到LinkedHashSet中
        String str = String.valueOf(num);
        for (int i = 0; i < str.length(); i++) {
            //遍历添加，linkedHashSet中不允许有重复的值，所以不可能有重复的值
            linkedHashSet.add(str.charAt(i));
        }

        return linkedHashSet;
    }
}