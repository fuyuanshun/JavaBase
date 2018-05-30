package com.fys.collection;

import java.util.Random;

/**
 * 随机生成一个字符串数组，每个字符串有两位，并遍历，查询里面是否有重复的字符串
 */
public class StringOfStringArrayIsRepeat {
    private static String[] foundDuplicated = new String[100];
    private static int pos = 0;

    public static void main(String[] args) {
        String[] strings = getRandomStrs();
        strIsRepeat(strings);
    }

    /**
     * 获得一个随机的字符串数组
     * @return
     */
    private static String[] getRandomStrs() {
        Random random = new Random();
        int ran = 0;
        //随机生成的字符串
        char[] chars = new char[2];
        //用来装随机字符串的字符数组
        String[] strings = new String[100];

        for (int j = 0; j < strings.length; j++) {
            for (int i = 0; i < chars.length; i++) {
                ran = random.nextInt(122-65+1)+65;
                //保证生成的数字在65-90或者97-122之间,即大小写英文字母
                chars[i] = (char)(91<=ran && ran < 97 ? ran+6 : ran);
            }
            //将随机生成的字符串保存在字符数组中
            strings[j] = new String(chars);
        }
        return strings;
    }

    /**
     * 查询字符串数组中是否有重复的字符串
     * @param strings
     */
    private static void strIsRepeat(String[] strings) {
        for (String str : strings) {
            System.out.print(str+" ");
            int repeat = 0;
            for (String str2 : strings) {
                if (str.equalsIgnoreCase(str2)) {
                    repeat++;
                    //当有一个字符串出现两次时，说明是一种重复的字符串
                    if (2 == repeat) {
                        putIntoDuplicatedArray(str);
                        break;
                    }
                }
            }
        }
        System.out.println();
        System.out.printf("总共有%d种重复的字符串%n", pos);
        if (pos != 0) {
            System.out.println("分别是：");
            for (int i = 0; i < pos; i++) {
                System.out.print(foundDuplicated[i] + " ");
            }
        }
    }

    private static void putIntoDuplicatedArray(String s) {
        for (int i = 0; i < pos; i++){
            //如果找到的字符串已经存在了，则结束函数
            if (foundDuplicated[i].equalsIgnoreCase(s))
                return;
        }
        //否则保存在找到的字符串中
        foundDuplicated[pos++] = s;
    }
}