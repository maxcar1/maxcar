package com.maxcar.jdbc;

import com.maxcar.util.LoadProperties;

import java.sql.*;

public class JdbcUtils {

    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    // 加载驱动，获取数据库连接信息
    static {
        try {
            // 加载配置文件
            driver = LoadProperties.getProperties_3("../../../application.properties","dirverClass");
            url = LoadProperties.getProperties_3("../../../application.properties","market.jdbc.url");
//            url = url+"&characterEncoding=utf-8";
//            url = "jdbc:mysql://192.168.3.58:3306/maxcar?useSSL=false&characterEncoding=utf-8";
            username = LoadProperties.getProperties_3("../../../application.properties","market.jdbc.username");
            password = LoadProperties.getProperties_3("../../../application.properties","market.jdbc.password");
//            username = "root";
//            password = "Maxcar#2018d";
            // 加载驱动
            Class.forName(driver);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     *
     * 获取数据库连接
     *
     * @throws SQLException
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 释放资源
     *
     * @param connection
     * @param preparedStatement
     * @param resultSet
     */
    public static void releaseDB(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}