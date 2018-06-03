package com.fys.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * 发送消息的线程
 */
public class SendThread implements Runnable{
    private Socket socket;
    private String name;

    static OutputStream outputStream = null;
    static DataOutputStream dataOutputStream = null;

    public SendThread(String name, Socket socket) {
        this.socket = socket;
        this.name = name;
    }

    @Override
    public void run() {
        Scanner input = new Scanner(System.in);
        try {
            outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);
            System.out.println("您现在可以输入要发送的消息:");
            while (true) {
                //输入信息
                String str = input.nextLine();
                //将用户的名称发送到接收的线程，获取用户的名称
                dataOutputStream.writeUTF(name);
                //发送用户输入的信息
                dataOutputStream.writeUTF(str);
                System.out.println("您说:" + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    /**
     * 关闭已经打开的连接
     */
    private static void close() {
        try {
            if (null != dataOutputStream) {
                dataOutputStream.close();
            }
            if (null != outputStream) {
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}