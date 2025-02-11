package com.yugao.jdbc.transaction_;

import com.yugao.jdbc.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction_ {

    @Test
    public void noTransaction() {

        //操作转账业务
        Connection con = null;
        String sql1 = "UPDATE account SET balance = balance - 1000 WHERE id = 1";
        String sql2 = "UPDATE account SET balance = balance + 1000 WHERE id = 2";
        PreparedStatement ps = null;
        try {
            // 注意默认情况下, connetion是默认提交
            con = JDBCUtils.getConnection();
            ps = con.prepareStatement(sql1);
            ps.executeUpdate();
            ps.close();
            int i = 1 / 0; // 模拟异常操作
            ps = con.prepareStatement(sql2);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(con, ps, null);
        }
    }

    @Test
    public void useTransaction() {

        //操作转账业务
        Connection con = null;
        String sql1 = "UPDATE account SET balance = balance - 1000 WHERE id = 1";
        String sql2 = "UPDATE account SET balance = balance + 1000 WHERE id = 2";
        PreparedStatement ps = null;
        try {
            // 注意默认情况下, connetion是默认提交
            con = JDBCUtils.getConnection();
            // 将connection设置为不自动提交
            // 这里相当于开启了事物
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql1);
            ps.executeUpdate();
            ps.close();
            // int i = 1 / 0; // 模拟异常操作
            ps = con.prepareStatement(sql2);
            ps.executeUpdate();

            con.commit();
        } catch (Exception e) {
            // 当有异常抛出的时候 我们可以在这里进行回滚操作
            // 当不写参数的时候 默认回滚到事物开始的状态
            System.out.println("sql执行发生异常, 进行回滚操作, 撤销操作");
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        } finally {
            JDBCUtils.close(con, ps, null);
        }
    }
}
