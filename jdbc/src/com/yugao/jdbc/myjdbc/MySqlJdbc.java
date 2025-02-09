package com.yugao.jdbc.myjdbc;

public class MySqlJdbc implements JdbcInterface {

    @Override
    public Object getConnection() {
        System.out.println("Connecting to Database");
        return null;
    }

    @Override
    public void crud() {
        System.out.println("Crud Operation");
    }

    @Override
    public void close() {
        System.out.println("Close MySqlJdbc");
    }
}
