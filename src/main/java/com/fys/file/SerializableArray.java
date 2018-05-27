package com.fys.file;

import com.fys.bean.People;

import java.io.*;

/**
 * 序列化数组
 */
public class SerializableArray {
    public static void main(String[] args) {
        String fileName = "F:/serializableArray.txt";
        serializableArrayList(fileName);
    }

    private static void serializableArrayList(String fileName) {
        File file = new File(fileName);
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        People[] peoples = new People[10];
        //往数组中添加十个people对象
        for (int i = 0; i < 10; i++) {
            peoples[i] = new People(i, "fys" + i, 21);
        }

        try {
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            //将数组写入到文件中
            objectOutputStream.writeObject(peoples);

            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);
            System.out.println("读取文件中的反序列化出来的数组内容...");
            People[] people = (People[]) objectInputStream.readObject();
            for (int i = 0; i < people.length; i++) {
                System.out.println(people[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}