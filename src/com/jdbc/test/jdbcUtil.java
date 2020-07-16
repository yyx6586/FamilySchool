package com.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbcUtil {

    private static String url;
    private static String username;
    private static String password;

    public static Connection open(){

        url = "jdbc:mysql://localhost:3306/FamilySchool";
        username = "root";
        password = "root";

        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void  close(Connection connection){
        try{
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
