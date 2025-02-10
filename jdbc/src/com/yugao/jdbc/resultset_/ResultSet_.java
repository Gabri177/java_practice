package com.yugao.jdbc.resultset_;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ResultSet_ {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        System.out.println(System.getProperty("user.dir"));
        Properties properties = new Properties();
        properties.load(new FileInputStream("jdbc/src/mysql.properties"));
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        //System.out.println(connection);
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM actor";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            System.out.println(id + " " + name);
        }

        // close connection
        resultSet.close();
        statement.close();
        connection.close();
    }
}
