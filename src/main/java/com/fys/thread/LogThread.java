package com.fys.thread;

import java.util.List;

/**
 * 日志线程
 */
public class LogThread implements Runnable {
    //获得穷举密码线程的List，该List里面是遍历的所有密码,且最后一位是生成的密码
    //写这个方法其实有些多余，但是使程序拓展性更强，解耦
    List<String> strings = GetPasswordThread.selectedPass;
    String password = GetThePassword.password;
    static boolean isFind = false;
    @Override
    public void run() {
        while(!isFind) {
            //其实strings不可能为空，因为从穷举密码的线程拿过来的List是已经初始化好的
            /*while (strings.isEmpty()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/
            String pass = strings.remove(0);
            System.out.println("穷举生成的密码为:" + pass);

            if (password.equals(pass)) {
                System.out.println("密码找到:" + pass);
                isFind = true;
            }
        }
    }
}