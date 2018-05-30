package com.fys.bean;

public class TerrorBook implements Book{
    private String name;
    private String author;

    public TerrorBook(String name, String author) {
        this.name = name;
        this.author = author;
    }

    @Override
    public String toString() {
        return "TerrorBook{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}