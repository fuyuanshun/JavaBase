package com.fys.file;

import com.fys.bean.People;

import java.io.*;

/**
 * 写入和写出一个序列化对象
 */
public class SerializableObject {
    public static void main(String[] args) {
        //文件名称
        String fileName = "F:/people.txt";
        writeAndReadObject(fileName);
    }

    private static void writeAndReadObject(String  fileName) {
        People people = new People(1, "fys", 21);
        File file = new File(fileName);
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(people);

            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);
            People people2 = (People) objectInputStream.readObject();
            System.out.println(people2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}