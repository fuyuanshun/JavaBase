package com.fys.reflection;

import com.fys.bean.People;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 通过配置文件获取对象
 */
public class GetObjectByConf {
    public static void main(String[] args) {
        String className = getClassName();
        People people = getPeople(className);
    }

    /**
     * 通过反射获取对象
     * @param className
     * @return
     */
    private static People getPeople(String className) {
        People people = null;
        try {
            Class pClass = Class.forName(className);
            Constructor constructor = pClass.getConstructor();
            people = (People) constructor.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return people;
    }

    /**
     * 读取配置文件，获得类名称
     * @return
     */
    private static String getClassName() {
        File file = new File("F://People.conf");
        FileInputStream fileInputStream = null;
        String str = "";
        try {
            byte[] bytes = new byte[(int) file.length()];
            fileInputStream = new FileInputStream(file);
            while (-1 != fileInputStream.read(bytes)) {
                str = new String(bytes);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fileInputStream) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return str;
    }
}