package com.fys.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接工具
 */
public class MysqlUtil {
    public static Connection getConn(String dbName, String username, String password) {
        String url = "jdbc:mysql://localhost/" + dbName + "?characterEncoding=UTF-8";
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
}