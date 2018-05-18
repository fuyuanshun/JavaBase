package com.fys.file;

import java.io.*;

/**
 *  使用Reader读取文件，适合读取文本内容
 */
public class FileReaderReview {
    private static FileReader fileReader;
    private static FileWriter fileWriter;
    public static void main(String[] args) {
        File file = new File("E:/sql.sql");
        int len = 0;
        try {
            fileReader = new FileReader(file);
            fileWriter = new FileWriter("E:/FileReader.txt");
            while(-1 != (len = fileReader.read())) {
                System.out.write(len);
                fileWriter.write(len);
            }
            System.out.println("文件写入成功!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != fileReader) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
