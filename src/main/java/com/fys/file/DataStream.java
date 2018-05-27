package com.fys.file;

import java.io.*;

/**
 * DataOutputStream的使用
 */
public class DataStream {
    public static void main(String[] args) {
        String fileName = "F:/test2.txt";
        dataStream(fileName);
    }

    private static void dataStream(String fileName) {
        File file = new File(fileName);
        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            dataInputStream = new DataInputStream(new FileInputStream(file));
            dataOutputStream.writeBoolean(true);
            dataOutputStream.writeInt(300);
            dataOutputStream.writeUTF("String");
            System.out.println(dataInputStream.readBoolean());
            System.out.println(dataInputStream.readInt());
            System.out.println(dataInputStream.readUTF());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}