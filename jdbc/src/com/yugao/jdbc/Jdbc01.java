package com.yugao.jdbc;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Jdbc01 {
    public static void main(String[] args) throws SQLException {

        // 在项目下创建一个文佳佳  比如libs
        // 将mysql提供的jar包拷贝到该目录下, 然后右键
        // 将其加入到项目中
        // 1. 注册驱动
        Driver driver = new Driver();

        // 2. 得到链接
        String url = "jdbc:mysql://localhost:3306/GYH01";
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "123456");

        Connection connect = driver.connect(url, properties);
        // 3. 执行sql
        String sql = "insert into actor values(null, 'testeNam', '男', '1999-09-09', '110')";
        // Statement 用于执行静态SQL语句并返回其生成的结果的对象
        Statement statement = connect.createStatement();
        int rows = statement.executeUpdate(sql);
        System.out.println(rows > 0 ? "Success" : "Fail");
        // 4. 释放资源
        statement.close();
        connect.close();
    }
}
