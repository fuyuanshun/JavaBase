package com.fys.bean;

public class RomanceBook implements Book {
    private String name;
    private String author;

    public RomanceBook(String name, String author) {
        this.name = name;
        this.author = author;
    }

    @Override
    public String toString() {
        return "RomanceBook{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}