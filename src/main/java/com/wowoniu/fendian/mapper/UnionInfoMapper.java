package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.UnionInfo;

public interface UnionInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(UnionInfo record);

    int insertSelective(UnionInfo record);

    UnionInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UnionInfo record);

    int updateByPrimaryKey(UnionInfo record);

    /**
     * 根据盟主id获取信息
     * @param leaderId
     * @return
     */
    UnionInfo queryUnionInfoByLeadeid(String leaderId);
}