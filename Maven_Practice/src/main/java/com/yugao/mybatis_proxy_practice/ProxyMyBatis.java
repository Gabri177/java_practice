package com.yugao.mybatis_proxy_practice;

import com.yugao.mybatis_proxy_practice.domain.Admin;
import com.yugao.mybatis_proxy_practice.mapper.AdminMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProxyMyBatis {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        List<Admin> admins = adminMapper.selectAll();
        for (Admin admin : admins) {
            System.out.println(admin);
        }
        sqlSession.close();
    }
}
