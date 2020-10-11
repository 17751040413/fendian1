package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.FreeCharge;

public interface FreeChargeMapper {
    int deleteByPrimaryKey(String id);

    int insert(FreeCharge record);

    int insertSelective(FreeCharge record);

    FreeCharge selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FreeCharge record);

    int updateByPrimaryKey(FreeCharge record);
}