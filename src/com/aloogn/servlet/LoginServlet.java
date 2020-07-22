package com.aloogn.servlet;

import com.jdbc.test.JdbcUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String account = (String)req.getParameter("account");
//        String password = (String)req.getParameter("password");
//        //数据库url，username，password
////        final String DB_url = "jdbc:mysql://localhost:3306/FamilySchool";
////        final String username = "root";
////        final String password = "root";
////
////        try {
////
////            //1.注册JDBC驱动
////            Class.forName("com.mysql.jdbc.Driver");
////            //2.获取数据库连接
////            Connection connection = (Connection) DriverManager.getConnection(DB_url, username, password);
////            //3.获取操作数据库的对象
////            Statement statement = ((java.sql.Connection) connection).createStatement();
////            String sql = "select * from user";
////            ResultSet resultSet = statement.executeQuery(sql);
////
////            while (resultSet.next()) {
////                String id = resultSet.getString("id");
////                String sqlpassword = resultSet.getString("password");
////
////                resp.setCharacterEncoding("utf-8");
////                resp.getWriter().write("id" + id + "密码" + sqlpassword);
////            }
////            resultSet.close();
////            statement.close();
////            connection.close();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//        Connection connection = jdbcUtil.open();
//
//        String sql = "select * from user where id='"+account+"' and password='"+password+"'";
//
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql);
//            int flag = 0;
//            while (resultSet.next()){
//                flag ++;
////                String id = resultSet.getString("id");
////                String password = resultSet.getString("password");
//
//
//            }
//
//            if(flag > 0){
//                resp.setCharacterEncoding("utf-8");
//                resp.getWriter().write("登录成功");
//            }else{
//                resp.setCharacterEncoding("utf-8");
//                resp.getWriter().write("登录失败");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        jdbcUtil.close(connection);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doGet(req,resp);

        resp.setCharacterEncoding("utf-8");

        Result result = new Result();
        result.setCode(-1);

        String account = (String)req.getParameter("account");
        String password = (String)req.getParameter("password");
        //数据库url，username，password
//        final String DB_url = "jdbc:mysql://localhost:3306/FamilySchool";
//        final String username = "root";
//        final String password = "root";
//
//        try {
//
//            //1.注册JDBC驱动
//            Class.forName("com.mysql.jdbc.Driver");
//            //2.获取数据库连接
//            Connection connection = (Connection) DriverManager.getConnection(DB_url, username, password);
//            //3.获取操作数据库的对象
//            Statement statement = ((java.sql.Connection) connection).createStatement();
//            String sql = "select * from user";
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            while (resultSet.next()) {
//                String id = resultSet.getString("id");
//                String sqlpassword = resultSet.getString("password");
//
//                resp.setCharacterEncoding("utf-8");
//                resp.getWriter().write("id" + id + "密码" + sqlpassword);
//            }
//            resultSet.close();
//            statement.close();
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        if(account == null || "".equals(account)){
//            result.setMsg("账号不能为空");
//            resp.getWriter().write(JSONObject.fromObject(result).toString());
//            return;
//        }
//
//        if (password == null || "".equals(password)){
//            result.setMsg("密码不能为空");
//            resp.getWriter().write(JSONObject.fromObject(result).toString());
//            return;
//        }

        Connection connection = JdbcUtil.open();

        String sqlId = "select * from user where id='"+account+"'";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlId);
            String role = null;
            String passwordSql = null;
            while (resultSet.next()){
                passwordSql = resultSet.getString("password");
                role = resultSet.getString("role");
            }

            if(passwordSql != null){
                if(passwordSql.equals(password)){
                    result.setCode(1);
                    result.setData(role);
                    result.setMsg("登录成功");
                    resp.getWriter().write(JSONObject.fromObject(result).toString());
                }else {
                    result.setMsg("密码错误");
                    resp.getWriter().write(JSONObject.fromObject(result).toString());
                }
            }else {
                result.setMsg("该用户不存在");
                resp.getWriter().write(JSONObject.fromObject(result).toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(connection);
        }
    }
}
