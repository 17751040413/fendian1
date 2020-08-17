package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.UoionSeparateLog;

public interface UoionSeparateLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(UoionSeparateLog record);

    int insertSelective(UoionSeparateLog record);

    UoionSeparateLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UoionSeparateLog record);

    int updateByPrimaryKey(UoionSeparateLog record);

    /**
     * 根据分账人id查询分账总金额
     * @param userid
     * @return
     */
    double queryUnionSepPriceByUserId(String userid);
}