package com.yugao.mybatis_proxy_practice.mapper;


import com.yugao.mybatis_proxy_practice.domain.Admin;
import com.yugao.mybatis_proxy_practice.domain.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BrandMapper {

    // 查询所有条目
    List<Brand> selectAll();
    // 查看特定条目的详细信息
    Brand selectById(Integer id);
    // 多条件查询
    List<Brand> selectByCondition(@Param("status") int status,
                                  @Param("companyName") String companyName,
                                  @Param("brandName") String brandName);

    List<Brand> selectByCondition(Brand brand);
    List<Brand> selectByCondition(Map<?,?> map);
    // 单条件查询
    List<Brand> selectByConditionSingle(Brand brand);
}
