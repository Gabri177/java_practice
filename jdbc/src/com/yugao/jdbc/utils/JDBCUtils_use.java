package com.yugao.jdbc.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtils_use {
    public static void main(String[] args) {

        Connection conn = null;
        String sql = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            //System.out.println(conn);
            sql = "SELECT * FROM admin";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("id: " + rs.getInt("id")
                        + " username: " + rs.getString("username")
                        + "password: " + rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, rs);
            System.out.println("Query Finished");
        }
    }
}
