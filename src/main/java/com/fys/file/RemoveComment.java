package com.fys.file;

import java.io.*;
import java.util.regex.Pattern;

/**
 * 读取一个文件内容， 如果一行为//开头，则认为该行为注释，去掉该行
 * rmComment: 普通逻辑判断，去掉注释
 * rmCommentByRegex: 使用正则表达式判断某一行是否为注释
 */
public class RemoveComment {
    public static void main(String[] args) {
        String fileName = "E:\\JAVA\\JavaBase\\src\\main\\java\\com\\fys\\file\\EncodingFile.java";
        rmCommentByRegex(fileName);
    }

    private static void rmComment(String fileName) {
        BufferedReader bufferedReader = null;
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("文件不存在!");
            return;
        }

        try {
            String str = null;
            bufferedReader = new BufferedReader(new FileReader(file));
            while(null != (str=bufferedReader.readLine())) {
                //当读取到的内容去掉前后的空格后大于2才取出前两个字符，否则会
                // 出现StringIndexOutOfBoundsException异常.
                //当字符串的前两位是//时，说明是注释，将此字符串设置为空串
                if(str.trim().length() >= 2 && str.trim().substring(0, 2).equals("//")) {
                    str = "";
                }
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    /**
     * 使用正则表达式去掉注释
     */
    private static void rmCommentByRegex(String fileName) {
        BufferedReader bufferedReader = null;
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("文件不存在!");
            return;
        }

        try {
            //判断是否是注释的标记
            boolean isCom = false;
            String str = null;
            bufferedReader = new BufferedReader(new FileReader(file));
            while(null != (str=bufferedReader.readLine())) {
                //正则表达式， 模板: 如果匹配到开头为0个或者多个空格，
                // 并且后面为//和0个和多个字符的字符串，则为注释， 将此字符串设为空串
                if(Pattern.matches("^\\s*/{2}\\S*", str)) {
                    str = "";
                }
                // 去掉/**注释
                if (Pattern.matches("\\s*/\\*{2}", str)) {
                    isCom = true;
                    System.out.println("注释开始!!!!!!!!!!!!!!");
                }
                //如果接下来的是注释内容， 并且不与*/匹配，则将内容换位空串
                if(isCom && !Pattern.matches("\\s*\\*/", str)) {
                    //如果是注释，将注释内的所有字符串替换为空串
                    str = "";
                    //如果读取到的内容与*/匹配，则表明注释结束，将*/替换为空串，打印出注释结束，将标记设置为false
                } else if (Pattern.matches("\\s*\\*/", str)) {
                    str = "";
                    System.out.println("注释结束!!!!!!!!!!");
                    isCom = false;
                }
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}