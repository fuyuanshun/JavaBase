package com.fys.file;

import java.io.*;

/**
 *  对文件进行加密
 *  如果原文件数字为1，加密后变为2，原来为a，变为b
 *  只要按照某种算法和规律，就可以完成加密和解密
 */
public class EncodingFile {
    //被加密的文件
    private static String encodingFileName = "E:/sql.sql";
    //被加密后的文件位置
    private static String encodedFileName = "E:/encodingFile.txt";
    public static void main(String[] args) {
        encoding(encodingFileName, encodedFileName);
    }

    private static void encoding(String encodingFileName, String encodedFileName) {
        FileReader fileReader = null;
        FileWriter fileWriter = null;

        try {
            fileReader = new FileReader(encodingFileName);
            fileWriter = new FileWriter(encodedFileName);
            //可以一次性将文件内容全部装完的字节数组
            char[] fileContent = new char[(int) new File(encodingFileName).length()];
            //读取原来的文件到字符数组中
            fileReader.read(fileContent);
            //加密文件
            encodedFile(fileContent);

            //将加密后的文件内容写入到目标文件中
            System.out.println("文件加密start:");
            fileWriter.write(fileContent);
            System.out.println("文件加密stop");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != fileWriter) {
                    fileWriter.close();
                }
                if(null != fileReader) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //加密文件
    private static void encodedFile(char[] fileContent) {
        for(int i=0; i<fileContent.length; i++) {
            char c = fileContent[i];
            if(isLetterOrDigit(c)) {
                //如果读取到的字符是z，则变为a，如果是Z则变为A，如果是9则变为0.其他情况Ascii码向后移动一位
                switch (c) {
                    case 'z':
                        c = 'a';
                        break;
                    case 'Z' :
                        c = 'A';
                        break;
                    case '9':
                        c = '0';
                        break;
                    default:
                        c++;
                        break;
                }
            }
            //将修改后的字符赋值给原来的数组
            fileContent[i] = c;
        }
    }

    //判断一个字符是不是字母或者数字
    private static boolean isLetterOrDigit(char c) {
        String pattern = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        return -1 == pattern.indexOf(c) ? false : true;
    }
}
