package com.yugao.dao_.test;

import com.yugao.dao_.dao.ActorDAO;
import com.yugao.dao_.domain.Actor;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestDAO {

    // 测试AvtorDAO 对actor表的CRUD操作
    @Test
    public void testActorDAO(){
        ActorDAO actorDAO = new ActorDAO();
        // 1.查询
        List<Actor> actors = actorDAO.queryMulti("select * from actor", Actor.class, null);
        for(Actor actor : actors){
            System.out.println(actor);
        }
        // 2.查询单行记录
        Actor actor = actorDAO.querySingle("select * from actor where id = ?", Actor.class, 1);
        System.out.println(actor);
        // 3.查询特定行特定列的数据
        Object s = actorDAO.queryScalar("select name from actor where id = ?",  1);
        System.out.println(s);
        // 4.dml操作 delete insert update
        actorDAO.update("update actor set name = ? where id = ?", "newName", "1");
        actorDAO.update("insert into actor values (?,?,?,?,?)", null,"insertName","女", "1111-11-11", "1111");
        actorDAO.update("delete from actor where name = ?", "testName5");
    }
}
