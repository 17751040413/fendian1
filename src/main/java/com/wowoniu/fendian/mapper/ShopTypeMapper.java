package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.ShopType;

import java.util.List;

public interface ShopTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(ShopType record);

    int insertSelective(ShopType record);

    ShopType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ShopType record);

    int updateByPrimaryKey(ShopType record);

    /**
     * 查询所有店铺分类
     * @return
     */
    List<ShopType> queryAll();
}