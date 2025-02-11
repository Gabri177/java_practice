package com.yugao.jdbc.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class C3P0_ {

    // 方式1: 相关参数, 在程序中指定user, url, passwd等
    @Test
    public void testC3P0_01() throws IOException, PropertyVetoException, SQLException {
        // 1.创建一个数据源对象
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        // 2.通过配置文件获取相关的属性值
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");
        // 3.给数据源设置相关的参数
        cpds.setJdbcUrl(url);
        cpds.setUser(user);
        cpds.setPassword(password);
        cpds.setDriverClass(driver);
        // 4.设置初始化连接数
        cpds.setInitialPoolSize(10);
        cpds.setMaxPoolSize(50);
        System.out.println("测试5000个链接的速度 测试开始");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i ++){
            Connection connection = cpds.getConnection();
            connection.close();
        }
        System.out.println("测试结束 总耗时: " + (System.currentTimeMillis() - start));
        cpds.close();
    }

    // 方式2: 使用配置文件模板来完成
    @Test
    public void testC3P0_02() throws IOException, PropertyVetoException, SQLException {
        ComboPooledDataSource cpds = new ComboPooledDataSource("MyCPDS");
        Connection connection = cpds.getConnection();
        System.out.println("链接成功");
        connection.close();
        cpds.close();
    }
}
