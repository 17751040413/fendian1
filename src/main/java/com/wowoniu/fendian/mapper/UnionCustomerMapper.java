package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.UnionCustomer;

public interface UnionCustomerMapper {
    int deleteByPrimaryKey(String id);

    int insert(UnionCustomer record);

    int insertSelective(UnionCustomer record);

    UnionCustomer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UnionCustomer record);

    int updateByPrimaryKey(UnionCustomer record);
}