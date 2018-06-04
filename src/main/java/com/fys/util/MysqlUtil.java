package com.fys.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接工具, 注解
 */
@JDBCConfig(dbName = "robot")
public class MysqlUtil {
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        JDBCConfig jdbcConfig = MysqlUtil.class.getAnnotation(JDBCConfig.class);
        String dbName = jdbcConfig.dbName();
        String username = jdbcConfig.username();
        String password = jdbcConfig.password();

        String url = "jdbc:mysql://localhost/" + dbName + "?characterEncoding=UTF-8";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}