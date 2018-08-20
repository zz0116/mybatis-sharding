package com.zyz.demo.sharding.util;

import org.apache.ibatis.io.Resources;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    private final static Logger logger = Logger.getLogger(DBUtil.class);

    private static String url;
    private static String username;
    private static String password;
    private static String driverClassName;

    static {
        try {
            Properties properties = new Properties();
            Reader reader = Resources.getResourceAsReader("jdbc.properties");
            properties.load(reader);
            reader.close();

            url = properties.getProperty("mysql.url");
            username = properties.getProperty("mysql.username");
            password = properties.getProperty("mysql.password");
            driverClassName = properties.getProperty("mysql.driver");
        } catch (IOException e) {
            logger.error("无法加载数据库信息" + e);
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
