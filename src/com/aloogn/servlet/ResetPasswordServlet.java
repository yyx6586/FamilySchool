package com.aloogn.servlet;

import com.jdbc.test.JdbcUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ResetPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("utf-8");

        Result result = new Result();
        result.setCode(-1);

        String account = (String) request.getParameter("account");
        String password = (String) request.getParameter("password");
//
//        if(account == null || "".equals(account)){
//            System.out.println(account + "不能为空");
//            result.setMsg("帐号不能为空");
//            response.getWriter().write(JSONObject.fromObject(result).toString());
//            return;
//        }
//
//        if(password == null || "".equals(password)){
//            System.out.println(password + "不能为空");
//            result.setMsg("密码不能为空");
//            response.getWriter().write(JSONObject.fromObject(result).toString());
//            return;
//        }

        Connection connection = JdbcUtil.open();
//        String sql1 = "select * from user where id='"+account+"'";
//        String accountSql = null;
        String sql = "update user set password ='"+password+"' where id ='"+account+"'";
        try{
            Statement statement = connection.createStatement();
            int flag = statement.executeUpdate(sql);
            if(flag > 0){
                result.setMsg("密码修改成功");
                response.getWriter().write(JSONObject.fromObject(result).toString());
            }else {
                result.setMsg("该用户不存在");
                response.getWriter().write(JSONObject.fromObject(result).toString());
            }
//            ResultSet resultSet = statement.executeQuery(sql1);
//            while (resultSet.next()){
//                accountSql = resultSet.getString("id");
//            }
//
//            if(account == accountSql && account.equals(accountSql)){
//                String sql = "update user set password ='"+password+"' where id ='"+account+"'";
//                int flag = statement.executeUpdate(sql);
//
//                if(flag > 0){
//                    result.setMsg("密码修改成功");
//                    response.getWriter().write(JSONObject.fromObject(result).toString());
//                }else{
//                    result.setMsg("密码修改失败");
//                    response.getWriter().write(JSONObject.fromObject(result).toString());
//                }
//            }else {
//                result.setMsg("该用户不存在");
//                response.getWriter().write(JSONObject.fromObject(result).toString());
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(connection);
        }
    }
}
