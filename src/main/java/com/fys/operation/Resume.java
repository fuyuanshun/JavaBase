package com.fys.operation;

import java.util.*;

public class Resume {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String name = createStrInput(input, "name");
        String sex = createStrInput(input, "sex");
        Integer age = createNumInput(input, "age");

        System.out.println("信息采集完成，是否生成个人简介？ \n 1.是 2.退出");
        boolean flag = true;
        while (flag) {
            String isContinue = createStrInput(input, "isContinue");
            if (isContinue.equals("1")) {
                createResume(name, sex, age);
                flag = false;
            } else if (isContinue.equals("2")) {
                flag = false;
            } else {
                System.out.println("输入错误，请重新输入!");
            }
        }
    }

    private static void createResume(String name, String sex, Integer age) {
        System.out.println("姓名:" + name + "\n性别:" + sex + "\n年龄:" +age);
    }

    private static String createStrInput(Scanner input, String inputName) {
        //如果是名字和性别，则提示用户输入相关信息
        if(inputName.equals("name") || inputName.equals("sex")){
            System.out.println("Please input your " + inputName + ":");
        }
        inputName = input.nextLine();

        while (null == inputName || "".equals(inputName.trim())) {
            inputName = input.nextLine();
        }
        return inputName;
    }

    private static Integer createNumInput(Scanner input, String inputName) {
        System.out.println("Please input your " + inputName + ":");
        inputName = input.nextLine();
        Integer age = 0;

        //如果用户输入不符合该正则表达式，则一直输入
        while(!inputName.matches("^\\d|\\d\\d|[1][1-9][1-9]$")){
            System.out.println("请输入您的真实年龄!");
            inputName = input.nextLine();
        }
        age = Integer.parseInt(inputName);
        return age;
    }

    /**
     * 替换敏感字符串
     */
    private static void divSenstive() {
        String s = "色情恐怖分子";

        List<String> strs = new ArrayList<>();
        strs.add("爆炸");
        strs.add("色情");
        strs.add("恐怖");

        Iterator<String> iterator = strs.iterator();

        while (iterator.hasNext()) {
            String str = iterator.next();
            if (s.contains(str)) {
                s = s.replaceAll(str, createChar(str.length(), "*"));
                System.out.println("该字符串含有敏感字符:" + str);
            }
        }
        System.out.println("检查后的字符串为:" + s);

    }


    private static String createChar(int len, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < len; i++) {
            stringBuffer.append(str);
        }
        return new String(stringBuffer);
    }
}