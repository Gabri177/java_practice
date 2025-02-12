package com.yugao.jdbc.datasource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
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

    @Test
    public void testQuerySingle() throws SQLException {
        Connection conn = JDBCUtlsByDruid.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from actor where id=?";
        Actor actor = qr.query(conn, sql, new BeanHandler<Actor>(Actor.class), 1);
        System.out.println(actor);
        JDBCUtlsByDruid.close(null, null, conn);
    }

    // 使用apache-DBUtils + druid完成查询结果是单行单列 - 返回值为 Object
    @Test
    public void testScalar() throws SQLException {
        Connection conn = JDBCUtlsByDruid.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select name from actor where id=?";
        Object query = qr.query(conn, sql, new ScalarHandler(), 1);
        System.out.println(query);
        JDBCUtlsByDruid.close(null, null, conn);
    }

    // 使用apache-DBUtils + druid完成DML
    @Test
    public void testDML() throws SQLException {
        Connection conn = JDBCUtlsByDruid.getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "update actor set name=? where id=?";
        int affectedRow = qr.update(conn, sql, "jajaja1", 1);
        System.out.println(affectedRow);
        JDBCUtlsByDruid.close(null, null, conn);
    }
}
