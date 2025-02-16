package com.yugao.screen;


import com.yugao.screen.dao.UserMapper;
import com.yugao.screen.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestQ {

    @Test
    public void testQuery() throws IOException {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //User user = userMapper.checkExistance("user1", "111111");
        User user2 = new User(12, "user5", "55555");
        userMapper.insertUser(user2);
        sqlSession.commit();
        //System.out.println(user);
        sqlSession.close();
    }
}
