package com.fys.gui;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 获取窗口位置的线程
 */
public class GetLocaltionThread implements Runnable{
    //初始化JFrame
    private JFrame jFrame;
    //写出配置文件的路径
    private File file = new File("F:/killLK.txt");
    private FileOutputStream fileOutputStream;

    public GetLocaltionThread(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //使用：分隔XY坐标
                String XInfo = "" + jFrame.getX();
                String YInfo = ":" + jFrame.getY();
                //写出
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(XInfo.getBytes());
                fileOutputStream.write(YInfo.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}