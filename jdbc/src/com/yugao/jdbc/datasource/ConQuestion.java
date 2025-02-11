package com.yugao.jdbc.datasource;

import com.yugao.jdbc.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConQuestion {


    // 假设 链接mysql 5000次 (实际场景中更多)
    @Test
    public void testCon(){

        System.out.println("开始链接...");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 5000; i ++){
            // 使用传统的JDBC方式得到链接
            Connection conn = JDBCUtils.getConnection();
            // 这里加入有一些sql操作
            // ......
            JDBCUtils.close(conn, null, null);
        }
        System.out.println("结束链接 耗时: " + (System.currentTimeMillis() - startTime) / 1000 + "s");
    }
}
