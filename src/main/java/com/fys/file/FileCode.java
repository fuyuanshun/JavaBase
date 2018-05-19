package com.fys.file;

import java.io.*;

/**
 *  编码问题
 */
public class FileCode {
    public static void main(String[] args) {
       /* String str = "中";
        String[] encodeds = {"BIG5", "GBK", "GB2312", "UTF-8", "UTF-16", "UTF-32"};
        for (String encoded : encodeds) {
            showEncod(str, encoded);
        }*/
        ChineseEncodingByFileInputStream();
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

    /**
     * 使用FileInputStream读取中文
     */
    private static void ChineseEncodingByFileInputStream() {
        File file = new File("E:/sql.sql");
        byte[] fileContent = new byte[(int) file.length()];
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(fileContent);
            //以字节的形式读取文件，并把字节转化为16进制
            for (byte b : fileContent) {
                int i = b&0x000000ff; //只取16进制的后两位
                Integer.toHexString(i);
            }
            System.out.println("以GBK编码创建一个String对象");
            String str = new String(fileContent, "GBK");
            System.out.println(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 解决读取文件中文乱码
     * FileInputStream 无法指定编码格式。需要外面再套一层流:InputStreamReader 并指定为GBK
     */
    private static void chineseEncodingByInputStreamReader() {
        File file = new File("E:/sql.sql");
        char[] fileContent = new char[(int) file.length()];
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        try {
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
            inputStreamReader.read(fileContent);
            System.out.println(new String(fileContent));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != fileInputStream) {
                    fileInputStream.close();
                }
                if(null != inputStreamReader) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
