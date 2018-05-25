package com.fys.file;

import java.io.*;

/**
 *  将加密文件解密
 */
public class EncodedFile {
    private static String encodingFileName = "E:/encodingFile.txt";
    private static String encodedFile = "E:/encodedFile.txt";
    public static void main(String[] args) {
        encodedFile(encodingFileName, encodedFile);
    }

    /**
     * 解密一个文件
     * @param encodingFileName
     * @param encodedFile
     */
    private static void encodedFile(String encodingFileName, String encodedFile) {
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        File file = new File(encodingFileName);
        try {
            fileReader = new FileReader(file);
            fileWriter = new FileWriter(encodedFile);
            char[] fileContent = new char[(int) file.length()];
            //将文件内容读取到字符数组中
            fileReader.read(fileContent);

            //解密文件
            encoded(fileContent);

            //将解密后的文件写出到一个文件内
            fileWriter.write(fileContent);
            System.out.println("文件解密完成，写出的文件为:"+encodedFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //程序执行完成，关闭流
                if (null != fileReader) {
                    fileReader.close();
                }
                if(null != fileWriter) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解密
     * @param fileContent
     */
    private static void encoded(char[] fileContent) {
        for (int i = 0; i < fileContent.length; i++) {
            char c = fileContent[i];
            if(isLetterOrDigit(c)) {
                switch (c) {
                    case 'a' :
                        c = 'z';
                        break;
                    case 'A':
                        c = 'Z';
                        break;
                    case '0':
                        c = 9;
                        break;
                    default:
                        c--;
                        break;
                }
            }
            fileContent[i] = c;
        }
    }

    /**
     * 判断一个字符是否为数字或者字母
     * @param c
     * @return
     */
    private static boolean isLetterOrDigit(char c) {
        String pattern = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        return -1 == pattern.indexOf(c) ? false : true;
    }
}
