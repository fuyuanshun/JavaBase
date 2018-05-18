package com.fys.file;

import java.io.*;

/**
 *  将一个比较大的文件拆分为几个小文件
 *
 *  先将源文件读取出来，然后每次写eachSize大小到新文件中
 */
public class SplitFile {
    private static FileInputStream fileInputStream = null;
    private static FileOutputStream fileOutputStream = null;

    public static void main(String[] args) {
        int eachSize = 150 * 1024; // 150k
        File file = new File("E:/InletexEMCFree汉化版.exe");
        splitFile(file, eachSize);
    }

    /**
     *  拆分文件的方法逻辑
     * @param file
     * @param eachSize
     */
    private static void splitFile(File file, int eachSize) {
        if (file.length() == 0) {
            System.out.println("文件长度为0，无法拆分");
        }

        try {
            //读取源文件内容的流
            fileInputStream = new FileInputStream(file);
            // 读取的长度
            int len;
            //创建一个长度为源文件长度的数组
            byte[] bytes = new byte[(int) file.length()];
            //要写多少次
            int count = (int) (file.length()%eachSize == 0 ? file.length()/eachSize : file.length() / eachSize +1);

            //循环写入新文件
            for (int i = 0; i<count; i++) {
                len = fileInputStream.read(bytes, 0, eachSize);
                write(file.getPath()+""+i, bytes, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //程序执行完毕，最后关闭流
            if(null != fileInputStream) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != fileOutputStream) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *  将每次读取到的大小写入新文件
     * @param fileName
     * @param bytes
     * @param len
     */
    private static void write(String fileName, byte[] bytes, int len) {
        try {
            fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(bytes, 0, len);
            System.out.println("写入完成! 文件名:"+fileName+" 大小为:"+len+"字节");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
