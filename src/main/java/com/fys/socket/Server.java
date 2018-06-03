package com.fys.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 多线程聊天服务器，可以一直发消息或者接收信息
 */
public class Server {
    public static void main(String[] args) {
        startServer();
    }

    private static void startServer() {
        ServerSocket serverSocket;
        Socket socket;
        try {
            serverSocket = new ServerSocket(8889);
            socket = serverSocket.accept();
            //开启一个发送消息的线程
            new Thread(new SendThread("服务器端", socket)).start();
            //开启一个接收消息的线程
            new Thread(new ReceiveThread(socket)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}