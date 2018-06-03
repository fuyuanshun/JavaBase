package com.fys.socket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * 聊天机器人服务器端
 */
public class ChatRobot {
    static PreparedStatement preparedStatement = null;
    static ServerSocket serverSocket = null;
    static OutputStream outputStream = null;
    static InputStream inputStream = null;
    static DataOutputStream dataOutputStream = null;
    static DataInputStream dataInputStream = null;
//
    public static void main(String[] args) {
        //匹配1-65535端口的正则表达式
        String pattern = "(^\\d$)|(^[1-9]\\d{3}$)|(^[1-6][0-5][0-5][0-3][0-5]$)";
        Scanner input = new Scanner(System.in);
        System.out.println("请输入要绑定的端口号");
        String port = input.nextLine();
        //如果符合正则表达式，则监听一个指定的端口，否则指定默认的端口
        if (Pattern.matches(pattern, port)) {
            startServer(port);
        } else {
            System.out.println("端口格式有误，使用默认端口:8889");
            startServer("8889");
        }
    }

    /**
     * 启动服务器端
     */
    private static void startServer(String port) {
        String url = "jdbc:mysql://localhost/robot?characterEncoding=UTF-8";
        String username = "root";
        String password = "root";
        Connection connection = getConnection(url, username, password);
        try {
            serverSocket = new ServerSocket();
            //绑定一个端口
            serverSocket.bind(new InetSocketAddress(Integer.parseInt(port)));
            //等待客户端连接
            Socket socket = serverSocket.accept();
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
            dataOutputStream = new DataOutputStream(outputStream);
            dataInputStream = new DataInputStream(inputStream);
            //读取客户端发送的内容
            while (true) {
                //读取客户端发来的信息，然后查询数据库
                String receive = dataInputStream.readUTF();
                //将从数据库查询到的数据写回到客户端
                dataOutputStream.writeUTF(selectDic(connection, receive));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != preparedStatement) {
                    preparedStatement.close();
                }

                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }

    }

    /**
     * 从数据库查询对应的字典并返回
     * @param connection
     * @param receive
     * @return
     */
    private static String selectDic(Connection connection, String receive) {
        String sql = "select * from dictionary where receive = ?";
        String response = "";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, receive);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                response = resultSet.getString("response");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 获得一个数据库连接
     * @return
     */
    private static Connection getConnection(String url, String username, String password) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭已经打开的连接
     */
    private static void close() {
        try {
            if (null != dataOutputStream) {
                dataOutputStream.close();
            }
            if (null != outputStream) {
                outputStream.close();
            }
            if (null != dataInputStream) {
                dataInputStream.close();
            }
            if (null != inputStream) {
                inputStream.close();
            }
            if (null != serverSocket) {
                serverSocket.close();
            }
            if (null != preparedStatement) {
                preparedStatement.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}