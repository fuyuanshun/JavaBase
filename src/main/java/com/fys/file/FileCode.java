package com.fys.file;

import java.io.UnsupportedEncodingException;

/**
 *  编码问题
 */
public class FileCode {
    public static void main(String[] args) {
        String str = "中";
        String[] encodeds = {"BIG5", "GBK", "GB2312", "UTF-8", "UTF-16", "UTF-32"};
        for (String encoded : encodeds) {
            showEncod(str, encoded);
        }
    }

    private static void showEncod(String str, String encoded) {
        System.out.printf("字符: \"%s\" 在编码方式%s下的十六进制值是%n", str, encoded);
        try {
            byte[] bytes = str.getBytes(encoded);
            for (byte b: bytes) {
                // b&0xFF 保证了数据一致性.
                int i = b&0xff;
                System.out.print(Integer.toHexString(i)+"\t");
            }
            System.out.println();
        } catch (UnsupportedEncodingException e) {
            System.out.printf("无法解析\"%s\"字符串",str);
        }
    }
}
