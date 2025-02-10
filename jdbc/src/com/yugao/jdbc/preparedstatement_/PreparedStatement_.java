package com.yugao.jdbc.preparedstatement_;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class PreparedStatement_ {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        Scanner sc = new Scanner(System.in);
        String admin_name, admin_password;
        System.out.println("请输入用户名:");
        admin_name = sc.next();
        System.out.println("请输入密码:");
        admin_password = sc.next();
        Properties properties = new Properties();
        properties.load(new FileInputStream("jdbc/src/mysql.properties"));
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        //System.out.println(connection);
        //Statement statement = connection.createStatement();

        //String searchName = "test' OR '1' = '1";
        //String sql = "SELECT * FROM actor WHERE `name` = '" + searchName + "'";
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, admin_name);
        preparedStatement.setString(2, admin_password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println("登录成功");
            while (resultSet.next()) {
                System.out.println("username: " + resultSet.getString(2));
                System.out.println("password: " + resultSet.getString(3));
            }
        } else {
            System.out.println("登录失败...");
            System.exit(0);
        }
        System.out.println("新用户名: ");
        admin_name = sc.next();
        System.out.println("新密码: ");
        admin_password = sc.next();
        sql = "INSERT INTO admin VALUES(NULL,?,?)";
        preparedStatement.close();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, admin_name);
        preparedStatement.setString(2, admin_password);
        int affect_rows = preparedStatement.executeUpdate();
        if (affect_rows > 0) {
            System.out.println("用户添加成功");
        } else {
            System.out.println("用户添加失败");
        }
        connection.close();
        preparedStatement.close();
        resultSet.close();

    }
}
