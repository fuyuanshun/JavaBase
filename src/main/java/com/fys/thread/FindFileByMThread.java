package com.fys.thread;

import java.io.File;
import java.util.regex.Pattern;

/**
 * 使用多线程查找Java文件
 * 缺点: 线程创建的太多，没有上限，严重影响性能
 */
public class FindFileByMThread {
    public static void main(String[] args) {
        String fileName = "E:/JAVA/";
        long startTime = System.currentTimeMillis();
        iteratorFile(fileName);
        System.out.println("共消耗了: " + (System.currentTimeMillis() - startTime) +"毫秒");
    }

    private static void iteratorFile(String fileName) {
        File file = new File(fileName);

        //如果文件是目录结构，则继续调用方法
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            //使用多线程遍历文件夹的内容
            for (File f : files) {
                //递归调用本方法
//                80000-100000毫秒 创建线程要消耗大量的时间，影响性能
                try {
                    Thread thread = new Thread(() -> iteratorFile(f.getPath()));
                    thread.start();
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //9000-10000毫秒
//                iteratorFile(f.getPath());
            }
            //如果是.java结尾的文件，直接输出路径
        } else {
            if (Pattern.matches("^\\S+\\.java$", fileName)) {
                System.out.println("找到Java目标文件,文件名称:"+fileName);
            }
        }
    }
}