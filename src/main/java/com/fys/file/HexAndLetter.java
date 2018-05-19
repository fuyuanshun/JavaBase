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
            System.out.println();
        }
        System.out.println();
        System.out.println("转化为中文汉字为:");
        //toLetter(hexCode);
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

    private static void toLetter(String[] hexCode) {
        byte[] bytes = new byte[hexCode.length];
        for (int i = 0; i < bytes.length; i++) {
        }
    }
}
