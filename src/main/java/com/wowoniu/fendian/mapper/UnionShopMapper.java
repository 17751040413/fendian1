package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.UnionShop;

public interface UnionShopMapper {
    int deleteByPrimaryKey(String id);

    int insert(UnionShop record);

    int insertSelective(UnionShop record);

    UnionShop selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UnionShop record);

    int updateByPrimaryKey(UnionShop record);
}