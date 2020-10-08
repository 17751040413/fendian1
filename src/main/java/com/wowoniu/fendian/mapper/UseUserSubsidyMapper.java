package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.UseUserSubsidy;

import java.util.List;

public interface UseUserSubsidyMapper {
    int deleteByPrimaryKey(String id);

    int insert(UseUserSubsidy record);

    int insertSelective(UseUserSubsidy record);

    UseUserSubsidy selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UseUserSubsidy record);

    int updateByPrimaryKey(UseUserSubsidy record);

    List<UseUserSubsidy> querySubsidyByUserid(String userid);
}