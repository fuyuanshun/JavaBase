package com.fys.reflection;

import com.fys.bean.People;

import java.lang.reflect.Field;

/**
 * 通过反射机制修改对象的属性
 */
public class UpdatePropertyByRef {
    public static void main(String[] args) {
        People people = new People();
        updatePro(people);
    }

    /**
     * 通过反射修改类的属性，private需要使用Field.setAccessible(true)才可以修改
     * @param people
     */
    private static void updatePro(People people) {
        Class pClass = people.getClass();
        try {
            //获取到name的字段
            Field field = pClass.getDeclaredField("name");
            //可修改private
            field.setAccessible(true);
            //修改这个字段的值
            field.set(people, "fys");
            System.out.println(people.getName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}