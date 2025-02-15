package com.yugao.mybatis_proxy_practice.mapper;


import com.yugao.mybatis_proxy_practice.domain.Admin;
import com.yugao.mybatis_proxy_practice.domain.Brand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface BrandMapper {

    // 查询所有条目
    List<Brand> selectAll();
    // 查看特定条目的详细信息
    Brand selectById(Integer id);
    // 用注解的方式实现下面的方法
    @Select("select * from tb_brand where id = #{id}")
    Brand selectById2(int id);
    // 多条件查询
    List<Brand> selectByCondition(@Param("status") int status,
                                  @Param("companyName") String companyName,
                                  @Param("brandName") String brandName);

    List<Brand> selectByCondition(Brand brand);
    List<Brand> selectByCondition(Map<?,?> map);
    // 单条件查询
    List<Brand> selectByConditionSingle(Brand brand);

    // 添加功能
    void add(Brand brand);

    // 修改功能
    int update(Brand brand);

    // 删除功能
    void deleteById(Integer id);
    // 批量删除
    void deleteByIds(@Param("ids") int[] ids);
}
