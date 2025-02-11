package com.yugao.jdbc.datasource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBUtils_use {

    @Test
    public void testQueryMany() throws SQLException {
        Connection conn = JDBCUtlsByDruid.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from actor";
        List<Actor> rsList = qr.query(conn, sql, new BeanListHandler<Actor>(Actor.class));
        for (Actor actor : rsList) {
            System.out.println(actor);
        }
        JDBCUtlsByDruid.close(null, null, conn);

    }
}
