package com.fys.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * 多线程聊天客户端，可以一直发消息或者接收信息
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("请输入您的聊天名称:  (名称不能为'服务器端')");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        //输入的名称不能为‘服务器端’,否则会一直让用户输入
        while (str.equals("服务器端")) {
            str = input.nextLine();
        }
        startClient(str);
    }

    /**
     * 启动客户端
     * @param str
     */
    private static void startClient(String str) {

        try {
            Socket socket = new Socket("localhost", 8889);
            //开启一个发送消息的线程
            new Thread(new SendThread(str, socket)).start();
            //开启一个接收消息的线程
            new Thread(new ReceiveThread(socket)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}