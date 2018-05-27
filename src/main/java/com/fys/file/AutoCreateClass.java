package com.fys.file;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 自动生成一个JavaBean类
 */
public class AutoCreateClass {
    public static void main(String[] args) {
        Map fileInfo = getFileInfo();
        updateTemplate(fileInfo);
    }

    /**
     * 让用户输入类的基本信息，并且装进一个Map集合里，返回Map集合
     * @return
     */
    private static Map<String,String> getFileInfo() {
        Map<String, String> fileInfo = new HashMap();
        System.out.println("自动生成javaBean类，请输入相关信息:");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入类名:");
        String className = scanner.nextLine();
        while(null == className || className.equals("")){
            className = scanner.nextLine();
        }
        System.out.println("请输入属性类型:");
        String type = scanner.nextLine();
        while(null == type || type.equals("")){
            type = scanner.nextLine();
        }
        System.out.println("请输入属性名称:");
        String name = scanner.nextLine();
        while(null == name || name.equals("")){
            name = scanner.nextLine();
        }
        //将属性名称的第一个字母大写
        String uName = upperName(name);

        fileInfo.put("className", className);
        fileInfo.put("type", type);
        fileInfo.put("name", name);
        fileInfo.put("uName", uName);
        return fileInfo;
    }

    /**
     * 通过Map里的类信息，替换模板文件，将生成的模板文件写入到一个用户指定的目录里
     * @param fileInfo
     */
    private static void updateTemplate(Map fileInfo) {
        //获取map中的文件信息
        String className = (String) fileInfo.get("className");
        String type = (String) fileInfo.get("type");
        String name = (String) fileInfo.get("name");
        String uName = (String) fileInfo.get("uName");

        FileInputStream fileInputStream = null;
        //模板文件的路径
        File file = new File("E:\\JAVA\\JavaBase\\src\\main\\java\\com\\fys\\template\\ClassTemplate.txt");
        if(!file.exists()) {
            System.out.println("文件没有找到!");
            return;
        }
        byte[] bytes = new byte[(int) file.length()];
        String templateContent = "";
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytes);
            templateContent = new String(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        templateContent = templateContent.replaceAll("@class@", className);
        templateContent = templateContent.replaceAll("@type@", type);
        templateContent = templateContent.replaceAll("@Uproperty@", uName);
        templateContent = templateContent.replaceAll("@property@", name);
        System.out.println("文件替换完成!替换后的文件内容为:");
        System.out.println(templateContent);

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要生成的文件路径:");
        String filePath = scanner.nextLine();
        while(null == filePath || filePath.equals("")){
            filePath = scanner.nextLine();
        }
        createFile(filePath, className+".java", templateContent);
    }

    /**
     * 生成java文件
     * @param filePath
     * @param createFileName
     * @param templateContent
     */
    private static void createFile(String filePath, String createFileName, String templateContent) {
        File targetFile = new File(filePath, createFileName);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(targetFile);
            fileWriter.write(templateContent);
            fileWriter.flush();
            System.out.println("文件已经生成在"+ filePath +"下!");
        } catch (FileNotFoundException e) {
            System.out.println("您输入的文件路径不正确！生成失败!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String upperName(String name) {
        //将第一个字母转化为大写字母
        char upperName = Character.toUpperCase(name.charAt(0));
        //输入名称从第二个字符开始的字符串
        String rest = name.substring(1);
        //组合在一起
        return upperName+rest;
    }


}