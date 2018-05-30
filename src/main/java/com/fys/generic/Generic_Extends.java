package com.fys.generic;

import com.fys.bean.Book;
import com.fys.bean.RomanceBook;
import com.fys.bean.TerrorBook;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用? extends 添加某个泛型的多个子类泛型
 */
public class Generic_Extends {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        RomanceBook romanceBook = new RomanceBook("情深深雨蒙蒙", "unKnow");
        TerrorBook terrorBook = new TerrorBook("午夜凶铃", "unKnow");
        //添加言情类
        books.add(romanceBook);
        //添加恐怖类
        books.add(terrorBook);
        //遍历
        Generic_Extends.iteatorBook(books);
    }

    /**
     * 面向接口编程
     * 所传进来的类一定是该泛型或者该泛型的子类泛型，所以可以直接遍历父类类型
     * @param books
     */
    public static void iteatorBook(List<? extends Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

}