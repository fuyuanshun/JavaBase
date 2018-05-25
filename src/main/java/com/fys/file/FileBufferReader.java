package com.fys.file;

import java.io.*;

/**
 *  使用缓冲区Buffered读取文件内容
 */
public class FileBufferReader {
    public static void main(String[] args) {
        String fileName = "F:/test.txt";
        writeFileByBufferedStream(fileName);
    }

    /**
     * 使用BufferInputStream直接读取文件字节数的内容
     * @param fileName
     */
    private static void readFileByBuffer(String fileName) {
        BufferedInputStream bufferedInputStream = null;
        File file = new File(fileName);
        try {
            byte[] fileContent = new byte[(int)file.length()];
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            bufferedInputStream.read(fileContent);
            System.out.println(new String(fileContent));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != bufferedInputStream) {
                    bufferedInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用BufferedOutPutStream
     */
    private static void writeFileByBufferedStream(String fileName) {
        fileName = "F:/裙下之臣.mp3";
        String targetFileName = "F:/裙下之臣writed.mp3";
        BufferedOutputStream bufferedOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        File file = new File(fileName);
        File targetFile = new File(targetFileName);
        byte[] fileContent = new byte[(int) file.length()];
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            while(-1 != bufferedInputStream.read(fileContent)) {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(targetFile));
                bufferedOutputStream.write(fileContent);
                bufferedOutputStream.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != bufferedInputStream) {
                    bufferedInputStream.close();
                }
                if (null != bufferedOutputStream) {
                    bufferedOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用BufferedReader每次读取一行
     */
    private static void readFileByBufferedReader(String fileName) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        File file = new File(fileName);
        try {
            String str;
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            while (null != (str=bufferedReader.readLine())) {
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != bufferedReader) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}