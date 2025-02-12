package com.yugao.dao_.dao;

import com.yugao.jdbc.datasource.JDBCUtlsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

// BasicDAO是其他DAO的父类
// DAO => data access object
public class BasicDAO<T> {

    private QueryRunner queryRunner = new QueryRunner();

    // 定义通用的DML方法 针对任意的表
    public int update(String sql, Object... params) {
        Connection conn = null;
        try {
            conn = JDBCUtlsByDruid.getConnection();
            return queryRunner.update(conn, sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtlsByDruid.close(null, null, conn);
        }
    }

    // 返回多个对象 即查询的结果是多行, 针对任意的表
    public List<T> queryMulti(String sql, Class<T> clazz, Object... params) {
        Connection conn = null;
        try {
            conn = JDBCUtlsByDruid.getConnection();
            return queryRunner.query(conn, sql, new BeanListHandler<T>(clazz), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtlsByDruid.close(null, null, conn);
        }
    }

    // 返回单行结果, 针对任意的表
    public T querySingle(String sql, Class<T> clazz, Object... params) {
        Connection conn = null;
        try {
            conn = JDBCUtlsByDruid.getConnection();
            return queryRunner.query(conn, sql, new BeanHandler<>(clazz), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtlsByDruid.close(null, null, conn);
        }
    }

    // 查询单行单列的方法, 即返回单值的方法
    public Object queryScalar(String sql, Object... params) {
        Connection conn = null;
        try {
            conn = JDBCUtlsByDruid.getConnection();
            return queryRunner.query(conn, sql, new ScalarHandler(), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtlsByDruid.close(null, null, conn);
        }
    }

}
