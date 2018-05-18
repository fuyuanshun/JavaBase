package com.fys.file;

import java.io.*;

/**
 * 将拆分的文件整合为一个文件
 */
public class MergeFile {
    //被拆分的文件，配合index下标找到所有被拆分的文件
    private static String fileName = "E:/InletexEMCFree汉化版.exe";
    private static FileInputStream fileInputStream = null;
    private static FileOutputStream fileOutputStream = null;

    public static void main(String[] args) {
        // 输出的目标文件路径
        File targetFile = new File("E:/merge/merge.exe");
        mergeFile(targetFile, fileName);
    }

    private static void mergeFile(File targetFile, String fileName) {
        try {
            fileOutputStream = new FileOutputStream(targetFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 拆分的文件的下标值
        int index = 0;

        try {
            while (true) {
                File file = new File(fileName + index++);
                //如果文件不存在了，结束方法
                if (!file.exists()) {
                    return;
                }

                byte[] bytes = new byte[(int) file.length()];
                //读取分开的文件
                fileInputStream = new FileInputStream(file);
                fileInputStream.read(bytes);
                //输出到目标路径
                fileOutputStream.write(bytes);
                //刷新流
                fileOutputStream.flush();
            }
        } catch (FileNotFoundException e) {
            System.out.println("文件没有找到!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //程序执行完毕，关闭打开的流
            try {
                if (null != fileInputStream) {
                    fileInputStream.close();
                }
                if(null != fileOutputStream) {
                    fileOutputStream.close();
                }
                System.out.println("文件合并成功!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
