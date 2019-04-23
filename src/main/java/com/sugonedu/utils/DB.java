package com.sugonedu.utils;


import java.sql.*;

public class DB {
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if(connection == null){
            //替换这里的链接地址
            connection = DriverManager.getConnection(
                    "jdbc:mysql://cdb-c8qio6wy.cd.tencentcdb.com:10053/notebook",
                    "notebook", "notebook0");
        }
        return connection;
    }

}



