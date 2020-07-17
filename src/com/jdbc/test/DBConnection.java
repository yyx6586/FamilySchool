package com.jdbc.test;

import java.sql.*;

public class DBConnection {

    private Connection connection = null;
    private Statement statement = null;

    public static void main(String[] args){

        DBConnection dbConnection = new DBConnection();
        dbConnection.open();
        dbConnection.close();
    }



    //打开数据库
    public Connection open (){

        //数据库url，username，password
        final String DB_url = "jdbc:mysql://localhost:3306/FamilySchool";
        final String username = "root";
        final String password = "root";

        try {
            //1.注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取数据库连接
            connection = (Connection) DriverManager.getConnection(DB_url,username,password);
            //3.获取操作数据库的对象
           statement = ((java.sql.Connection) connection).createStatement();
//            String sql = "select * from user";
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            while (resultSet.next()){
//                String id = resultSet.getString("id");
//                String name = resultSet.getString("name");
//                String sqlPassword = resultSet.getString("passwprd");
//
//                System.out.println("id:" + id);
//                System.out.println("姓名:" + name);
//                System.out.println("密码:" + sqlPassword);
//            }
//            resultSet.close();
//            close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    //关闭数据库
    public void close() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
