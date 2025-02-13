package com.yugao.mybatis_proxy_practice;

import com.yugao.mybatis_proxy_practice.domain.Brand;
import com.yugao.mybatis_proxy_practice.mapper.BrandMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    @Test
    public void TestSelectedALL() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = brandMapper.selectAll();
        for (Brand brand : brands) {
            System.out.println(brand);
        }

        System.out.println("----------------------------------");
        int temp_id = 1;
        Brand brand = brandMapper.selectById(temp_id);
        System.out.println(brand);
        System.out.println("----------------------------------");

        brands = brandMapper.selectByCondition(1, "", "");
        for (Brand brand1 : brands) {
            System.out.println(brand1);
        }
        System.out.println("----------------------------------");

        Brand t = new Brand();
        t.setCompanyName("%公司");
        brands = brandMapper.selectByConditionSingle(t);
        for (Brand brand1 : brands) {
            System.out.println(brand1);
        }
        sqlSession.close();
    }
}
