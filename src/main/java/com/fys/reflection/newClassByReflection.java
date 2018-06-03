package com.fys.reflection;

import com.fys.bean.People;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 通过反射创建一个对象
 */
public class newClassByReflection {
    public static void main(String[] args) {
        createClassByRef();
    }

    private static void createClassByRef() {
        String className = "com.fys.bean.People";
        try {
            Class pClass = Class.forName(className);
            Constructor constructor = pClass.getConstructor();
            People people = (People) constructor.newInstance();
            people.setName("heihei");
            System.out.println(people.getName());
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
    }
}