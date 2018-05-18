package com.fys.file;

import java.io.*;

/**
 *  IO 读取文件内容
 */
public class FileStream {
    private static FileInputStream fileInputStream = null;
    //要操作的文件名
    private static String fileName = "E:/sql.sql";

    public static void main(String[] args) {
        File file = new File(fileName);
        //fileByReadLine(file);
        readFileByBytes(file);
    }

    /**
     *     每次读取一行
     */
    private static void fileByReadLine(File file) {
        try {
            fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String str;
            while(null != (str= bufferedReader.readLine())) {
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(fileInputStream);
        }
    }

    /**
     *  通过字节流读取文件内容
     * @param file
     */
    private static void readFileByBytes(File file) {
        FileInputStream fileInputStream = null;
        long size = 0;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int len;
            while(-1 != (len = fileInputStream.read(bytes))) {
                size += len;
                System.out.write(bytes, 0, len);

                // 写入到文件内
                writeFile(bytes, len);
            }
            System.out.println("文件长度为:" +size+ "字节");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
           close(fileInputStream);
        }
    }

    /**
     *  将内容写入到一个文件内
     */
    private static void writeFile(byte[] bytes, int len) {
        File file = new File("E:/testWrite.txt");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes, 0, len);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *  关闭 FileInputStream
     */
    private static void close(FileInputStream fileInputStream) {
        if(null != fileInputStream) {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
