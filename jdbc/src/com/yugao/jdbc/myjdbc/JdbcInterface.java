package com.yugao.jdbc.myjdbc;

/*
* @author gao
* @version 1.0
* */

public interface JdbcInterface {

    // connect
    public Object getConnection();
    // crud
    public void crud();
    // close connection
    public void close();
}
