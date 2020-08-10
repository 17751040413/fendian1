package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.ShopIndustry;

import java.util.List;

public interface ShopIndustryMapper {
    int deleteByPrimaryKey(String id);

    int insert(ShopIndustry record);

    int insertSelective(ShopIndustry record);

    ShopIndustry selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ShopIndustry record);

    int updateByPrimaryKey(ShopIndustry record);

    /**
     * 获取所有行业
     * @return
     */
    List<ShopIndustry> queryAll();
}