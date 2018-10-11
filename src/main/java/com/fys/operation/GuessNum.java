package com.fys.operation;

import java.util.Random;
import java.util.Scanner;

/**
 * 猜数字游戏
 */
public class GuessNum {
    public static void main(String[] args) {
        Random random = new Random();
        int ranNum = random.nextInt(100);

        System.out.println("Please input a number:");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        //当用户输入错误的时候，循环执行
        while (true) {
            //使用正则表达式使用户只能输入数字,如果不是数字，则重新输入
            while (!str.matches("^\\d+$")) {
                System.out.println("You can enter only number!");
                str = scanner.nextLine();
            }
            //用户输入的数字
            int num = Integer.parseInt(str);
            if (ranNum > num) {
                System.out.println("You input number is smaller than the random number. Please input again:");
                str = scanner.nextLine();
            } else if (ranNum < num) {
                System.out.println("You input number is bigger than the random number. Please input again:");
                str = scanner.nextLine();
            } else {
                System.out.println("Correct! The random number is " + ranNum);
                return;
            }
        }
    }
}