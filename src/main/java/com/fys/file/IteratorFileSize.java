package com.fys.file;

import java.io.File;

/**
 * IO 遍历文件夹下的文件，不遍历子文件夹
 */
public class IteratorFileSize {
    private static long min = Integer.MAX_VALUE;
    private static long max = 0;
    private static File minFile ;
    private static File maxFile ;

    public static void main(String[] args) {
        String fileName = "C:/WINDOWS";
        File file = new File(fileName);
        if(file.exists()) {
            File[] files = file.listFiles();
            for (File fil : files) {
                if(fil.isFile() && fil.exists()) {
                    if(max < fil.length()) {
                        max = fil.length();
                        maxFile = fil;
                    }
                    if(0 != fil.length() && min > fil.length()) {
                        min = fil.length();
                        minFile = fil;
                    }
                } else if (fil.isDirectory()) {
                    System.out.println("遍历子文件夹Start 文件夹名称："+fil.getName());
                    File[] chiFiles = fil.listFiles();
                    if(null != chiFiles) {
                        iteratorFile(chiFiles);
                    } else {
                        System.out.println("您无权限访问此文件夹! 文件夹名称: "+fil.getName());
                    }
                }
            }
            System.out.println("最小的文件为: "+minFile.getName()+"大小为："+min+"字节");
            System.out.println("最大的文件为: "+maxFile.getName()+"大小为："+max+"字节");
        }
    }

    private static void iteratorFile(File[] files) {
        for(File f : files) {
            if(f.isFile() && f.exists()) {
                if(max < f.length()) {
                    max = f.length();
                    maxFile = f;
                }
                if(0 != f.length() && min > f.length()) {
                    min = f.length();
                    minFile = f;
                }
            } else if(f.isDirectory()) {
                iteratorFile(f.listFiles());
            }
        }
    }
}
