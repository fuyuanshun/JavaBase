package com.fys.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 接收消息的线程
 */
public class ReceiveThread implements Runnable{
    private Socket socket;

    static InputStream inputStream = null;
    static DataInputStream dataInputStream = null;

    public ReceiveThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            inputStream = socket.getInputStream();
            dataInputStream = new DataInputStream(inputStream);
            while (true) {
                //读取用户的名称
                String name = dataInputStream.readUTF();
                //读取用户输入的消息
                String str = dataInputStream.readUTF();
                //打印
                System.out.println(name + "说:" + str);
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
            if (null != dataInputStream) {
                dataInputStream.close();
            }
            if (null != inputStream) {
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}