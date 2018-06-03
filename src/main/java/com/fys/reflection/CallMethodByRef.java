package com.fys.reflection;

import com.fys.bean.People;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过反射机制调用对象的方法
 */
public class CallMethodByRef {
    public static void main(String[] args) {
        People people = new People();
        callMethod(people);
    }

    /**
     * 通过反射调用方法，传入方法名称和参数类型，就可以获得方法，然后调用即可
     * @param people
     */
    private static void callMethod(People people) {
        try {
            //获取名字叫做setName， 参数类型是String的方法
            Method method = people.getClass().getMethod("setName", String.class);
            //调用方法
            method.invoke(people, "heihei");
            System.out.println(people.getName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}