package com.yugao.jdbc.myjdbc;

public class TestJdbc {
    public static void main(String[] args) {
        JdbcInterface jdbcInterface = new MySqlJdbc();
        jdbcInterface.getConnection();
        jdbcInterface.crud();
        jdbcInterface.close();
    }
}
