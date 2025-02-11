package com.yugao.jdbc.datasource;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtilsByDruid_use {

    @Test
    public void test() throws SQLException {
        Connection conn = JDBCUtlsByDruid.getConnection();
        System.out.println("链接成功");
        String sql = "select * from actor";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            System.out.println(
                    "id:" + rs.getInt("id")
                    + " name:" + rs.getString("name")
                    + " sex:" + rs.getString("sex")
            );
        }
        JDBCUtlsByDruid.close(rs, ps, conn);
    }
}
