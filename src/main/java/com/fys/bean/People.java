package com.fys.bean;

import java.io.Serializable;

public class People implements Serializable {
    private Integer id;
    private String name;
    private int age;

    public People(Integer id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "id:"+ id +" name:"+ name + " age:"+ age+"";
    }
}