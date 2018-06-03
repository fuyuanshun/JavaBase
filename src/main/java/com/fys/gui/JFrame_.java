package com.fys.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *  启动一个界面，并且启动一个线程记录界面的位置，在下次启动的时候从配置文件读取界面位置。
 */
public class JFrame_ {
    public static void main(String[] args) {
        //读取配置文件,看是否存在,如果存在，返回一个map,不存在返回null
        Map<String,String> map = getXY();
        //默认窗口坐标
        int x = 300;
        int y = 400;
        //如果读取到信息，使用配置文件的XY坐标，否则使用默认配置
        if (null != map) {
            x = Integer.parseInt(map.get("X"));
            y = Integer.parseInt(map.get("Y"));
        }
        //否则使用默认配置
        JFrame jFrame = startFrame(x, y);
        //开启一个线程，记录Xy的坐标，并写出到配置文件
        new Thread(new GetLocaltionThread(jFrame)).start();
    }

    /**
     * 读取配置文件的信息，并放到一个Map中
     * @return
     */
    private static Map<String, String> getXY() {
        Map<String, String> map = new HashMap<>();
        File file = new File("F:/killLK.txt");
        byte[] bytes = new byte[1024];
        int len = 0;
        String[] strings = null;
        //如果文件存在,则读取文件内容
        if (file.exists()) {
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
                while (-1 != (len = fileInputStream.read(bytes))) {
                    strings = new String(bytes, 0, len).split(":", 0);
                }
                map.put("X", strings[0]);
                map.put("Y", strings[1]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return map;
        } else {
            return null;
        }
    }

    /**
     * 启动图形化界面
     * @param x
     * @param y
     * @return
     */
    private static JFrame startFrame(int x, int y) {

        JFrame jFrame = new JFrame("最新DNF卢克挂");
        //界面位置和大小
        jFrame.setBounds(x, y, 700, 700);
        //布局
        jFrame.setLayout(null);
        //按钮
        JButton jButton = new JButton("一键秒杀卢克");
        //按钮位置和大小
        jButton.setBounds(50, 50, 500, 50);
        //将按钮添加到界面中
        jFrame.add(jButton, BorderLayout.CENTER);
        //关闭界面的同时退出程序
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //可见
        jFrame.setVisible(true);
        //返回这个JFrame
        return jFrame;
    }
}