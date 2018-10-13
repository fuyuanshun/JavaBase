package com.fys.operation;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 读取doc和docx文档，提取简历信息
 */
public class ReadDoc {

    static BufferedInputStream bufferedInputStream = null;
    static FileInputStream fileInputStream = null;
    static XWPFDocument doc = null;
    static XWPFWordExtractor extractor = null;
    static WordExtractor wordExtractor = null;


    public static void main(String[] args) throws Exception {
        getInfo("C:\\Users\\Administrator\\Documents\\Tencent Files\\849485789\\FileRecv");
    }

    /**
     * 读取文档信息
     *
     * @param path 当前路径,不可是文件名
     * @throws Exception
     */
    private static void getInfo(String path) throws Exception {
        int count = 0;

        File file = new File(path);

        if (file.isDirectory()) {
            if (file.length() == 0) {
                System.out.println("此文件夹为空！");
                return;
            }
            for (File f : file.listFiles()) {
                if (f.isFile()) {
                    count += 1;
                    System.out.println("-------------第 " + count + " 个简历,文件名称:" + f.getName() + " --------------");
                    fileInputStream = new FileInputStream(f.getPath());
                    bufferedInputStream = new BufferedInputStream(fileInputStream);

                    //如果是Doc类型
                    if (FileMagic.valueOf(bufferedInputStream) == FileMagic.OOXML) {
                        String s = readDoc(bufferedInputStream);
                        regWithName(s);
                        regWithPhone(s);
                        regWithEmail(s);
                        //如果是Docx类型
                    } else if (FileMagic.valueOf(bufferedInputStream) == FileMagic.OLE2) {
                        String s = readDocx(bufferedInputStream);
                        regWithName(s);
                        regWithPhone(s);
                        regWithEmail(s);
                    } else {
                        System.out.println("该文档不是doc或者docx文档，无法解析!");
                    }
                    System.out.println("-----------------------------------------");
                }
            }
            close();
        } else {
            System.out.println("路径错误!");
        }
    }

    /**
     * 读取doc文档
     *
     * @param bufferedInputStream
     * @throws IOException
     */
    private static String readDoc(BufferedInputStream bufferedInputStream) throws IOException {
        doc = new XWPFDocument(bufferedInputStream);
        extractor = new XWPFWordExtractor(doc);
        return extractor.getText();
    }

    /**
     * 读取docx文档
     *
     * @param bufferedInputStream
     * @throws IOException
     */
    private static String readDocx(BufferedInputStream bufferedInputStream) throws IOException {
        wordExtractor = new WordExtractor(bufferedInputStream);
        return wordExtractor.getText();
    }

    private static void regWithName(String content) {
        //匹配姓名的正则表达式
        Pattern p = Pattern.compile("(\\姓\\s*\\名\\s+\\S{2,3})|(\\姓\\s*\\名\\s*\\:\\s*\\S{2,3})|(\\姓\\s*\\名\\s*\\：\\s*\\S{2,3})");
        Matcher m = p.matcher(content);
        while (m.find()) {
            System.out.println(m.group(0));
        }
    }

    private static void regWithPhone(String content) {
        //匹配手机号的正则表达式
        Pattern p = Pattern.compile("([1][37589]\\d{9})");
        Matcher m = p.matcher(content);
        while (m.find()) {
            System.out.println("手机号: " + m.group(0));
        }
    }

    private static void regWithEmail(String content) {
        //匹配手机号的正则表达式
        Pattern p = Pattern.compile("(\\S{5,15}\\@\\S{2,8}.)(com|cn)");
        Matcher m = p.matcher(content);
        while (m.find()) {
            System.out.println("电子邮箱: " + m.group(0));
        }
    }

    /**
     * 关闭所有已经打开的连接.
     */
    private static void close() {
        try {
            if (null != wordExtractor) {
                wordExtractor.close();
                wordExtractor = null;
            }
            if (null != doc) {
                doc.close();
                doc = null;
            }
            if (null != extractor) {
                extractor.close();
                extractor = null;
            }
            if (null != fileInputStream) {
                fileInputStream.close();
                fileInputStream = null;
            }
            if (null != bufferedInputStream) {
                bufferedInputStream.close();
                bufferedInputStream = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}