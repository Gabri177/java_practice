package com.yugao.jdbc;

import com.mysql.cj.jdbc.Driver;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
    @author yugao
    @version 1.1

    Five ways to connect to mysql in java
 */
public class JdbcConn {

    // 第一种方式: 静态加载
    @Test
    public void  connect01() throws SQLException {
        // 使用静态的方法加载类 依赖性强
        Driver driver1 = new Driver();
        String url = "jdbc:mysql://localhost:3306/GYH01";
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "123456");
        Connection connect = driver1.connect(url, properties);
        System.out.println(connect);
    }

    // 第二种方式: 动态加载
    @Test
    public void  connect02() throws SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        // 利用反射动态加载类 减少了依赖性
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver1 = (Driver)aClass.getDeclaredConstructor().newInstance();

        String url = "jdbc:mysql://localhost:3306/GYH01";
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "123456");
        Connection connect = driver1.connect(url, properties);
        System.out.println(connect);
    }

    // 第三种方式 DriverManager 替代 Driver 统一管理
    @Test
    public void  connect03() throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        // 通过DriverManager替代Driver进行统一管理
        // 通过反射机制加载Driver
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver1 = (Driver)aClass.getDeclaredConstructor().newInstance();
        // 创建 url, user, passwd
        String url = "jdbc:mysql://localhost:3306/GYH01";
        String username = "root";
        String password = "123456";

        // 注册Driver驱动
        DriverManager.registerDriver(driver1);
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println(connection);
    }

    // 第四种方式: 因为Class.forName 加载该类时 其static静态代码块已经用过DriverManager注册, 所以可以简化代码
    @Test
    public void  connect04() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        // 使用 Class.forName 自动完成注册驱动, 简化代码
        // 通过反射机制加载Driver 自动完成驱动注册
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        // 创建 url, user, passwd
        String url = "jdbc:mysql://localhost:3306/GYH01";
        String username = "root";
        String password = "123456";
        // 这里不需要注册Driver了 直接进行链接
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println(connection);
    }

    // 第五种方式: 使用配置文件从而避免硬编码, 这样链接数据库更加灵活
    @Test
    public void  connect05() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException, IOException {
        // 通过properties对象获取配置文件信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/mysql.properties"));

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
}
