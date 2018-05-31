package com.fys.thread;


import java.util.Random;

/**
 * 生成一个三位的随机密码，并创建一个破解线程，使用穷举法破解,同时有一个日志线程打印信息
 */
public class GetThePassword {
    //此变量为包内可引用，将生成的密码提供给日志和穷举密码两个线程使用
    protected static String password;

    public static void main(String[] args) {
        //初始化password的值
        password = createPassword();
        iteratorPass();
    }

    /**
     * 生成一个随机的三位字符密码
     * @return
     */
    protected static String createPassword() {
        Random random = new Random();
        int ran = 0;
        char[] chars = new char[3];
        for (int i = 0; i < chars.length; i++) {
            ran = random.nextInt(90-65+1)+65;
            chars[i] = (char)ran;
        }
        return new String(chars);
    }

    /**
     * 穷举密码并打印详细的信息
     */
    protected static void iteratorPass() {
        Thread logThread = new Thread(new LogThread());
        Thread getPasswordThread = new Thread(new GetPasswordThread());
        //将日志线程设置为守护线程
        //如果设置为守护线程，穷举密码线程结束后日志线程会停止运行，使日志记录不完善，所以没有使用守护线程
//        logThread.setDaemon(true);
        //启动日志线程
        logThread.start();
        //启动穷举密码线程
        getPasswordThread.start();
    }
}