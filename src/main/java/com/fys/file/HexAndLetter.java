package com.fys.file;

import java.io.*;

/**
 *  根据十六进制找到对应的中文汉字
 */
public class HexAndLetter {
    public static void main(String[] args) {
        String str = "付元顺超级帅";
        String codeType = "utf-8";
        String[] hexCode = toHex(str, codeType);
        System.out.println("十六进制为:");
        for (String b : hexCode) {
            System.out.print(b+" ");
            System.out.printf("");
        }
        System.out.println();
        System.out.println("转化为中文汉字为:");
        toLetter(hexCode);
    }

    /**
     *  将汉字转化为十六进制
     * @param str
     */
    private static String[] toHex(String str, String codeType) {
        String[] ret = null;
        try {
            byte[] bytes = str.getBytes(codeType);
            ret = new String[bytes.length];
            for (int i=0; i<bytes.length; i++) {
                int ch = bytes[i]&0xff;
                ret[i] = Integer.toHexString(ch);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 将字符串转化为十六进制并且根据返回值的字节数组创建字符串内容，然后输出
     * @param hexCode
     */
    private static void toLetter(String[] hexCode) {
        byte[] bytes = new byte[hexCode.length];
        for (int i = 0; i < bytes.length; i++) {
            //将字符串转化为十六进制
            bytes[i] = (byte) ((byte)0xff & Integer.parseInt(hexCode[i].substring(0, 2),16));
        }
        //根据字节数组的内容创建一个字符串
        System.out.println(new String(bytes));
    }
}
