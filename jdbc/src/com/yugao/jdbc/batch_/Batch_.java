package com.yugao.jdbc.batch_;

import com.yugao.jdbc.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Batch_ {

    @Test
    public void noBatch() throws SQLException {

        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into admin2 values (null, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        System.out.println("Start process...");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            ps.setString(1, "jack" + i );
            ps.setString(2, "666");
            ps.executeUpdate();
        }
        long end = System.currentTimeMillis();
        System.out.println("End process time: " + (end - start)+ "ms");
        JDBCUtils.close(conn, ps, null);
    }

    // 使用批量方法添加数据
    @Test
    public void batch() throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into admin2 values (null, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        System.out.println("Start process...");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            ps.setString(1, "jack" + i );
            ps.setString(2, "666");
            // ps.executeUpdate();
            // 在这里我们将sql语句加入到批处理包中
            ps.addBatch();
            // 当有1000条记录时, 再批量执行
            if ((i % 1000) == 0) {
                ps.executeBatch();
                // 执行完以后要清空处理包
                ps.clearBatch();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("End process time with Batch: " + (end - start)+ "ms");
        JDBCUtils.close(conn, ps, null);
    }
}
