package com.fys.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 穷举密码线程
 */
public class GetPasswordThread implements Runnable {
    static List<String> selectedPass = new ArrayList<>();
    String password = "";

    @Override
    public void run() {
        password = GetThePassword.password;

        String selectPass = "";
        char[] chars = new char[3];
        boolean isFind = true;
        while (isFind) {
            for (int i = 65; i < 91; i++) {
                for (int j = 65; j < 91; j++) {
                    for (int k = 65; k < 91; k++) {
                        chars[0] = (char) i;
                        chars[1] = (char) j;
                        chars[2] = (char) k;
                        selectPass = new String(chars);
                        selectedPass.add(selectPass);
                        if (selectPass.equals(password)) {
                            System.out.println("密码找到啦。。" + selectPass);
                            System.out.println("守护线程即将结束!");
                            return;
                        }
                    }
                }
            }
        }
    }
}