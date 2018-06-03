package com.fys.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * 聊天客户端
 */
public class ChatClient {
    static InputStream inputStream = null;
    static DataInputStream dataInputStream = null;
    static OutputStream outputStream = null;
    static DataOutputStream dataOutputStream = null;

    public static void main(String[] args) {
        startChat();
    }

    /**
     * 启动客户端
     */
    private static void startChat() {

        Scanner input = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 8889);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            dataInputStream = new DataInputStream(inputStream);
            dataOutputStream = new DataOutputStream(outputStream);
            System.out.println("聊天机器人Start...请输入:");
            while (true) {
                String msg = input.nextLine();
                dataOutputStream.writeUTF(msg);
                String ret = dataInputStream.readUTF();
                System.out.println(ret);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private static void close() {
        try {
            if (null != dataOutputStream) {
                dataOutputStream.close();
            }
            if (null != outputStream) {
                outputStream.close();
            }
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