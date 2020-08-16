package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.UnionCoupon;

public interface UnionCouponMapper {
    int deleteByPrimaryKey(String id);

    int insert(UnionCoupon record);

    int insertSelective(UnionCoupon record);

    UnionCoupon selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UnionCoupon record);

    int updateByPrimaryKey(UnionCoupon record);
}