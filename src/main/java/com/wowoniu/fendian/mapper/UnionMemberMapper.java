package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.UnionMember;

public interface UnionMemberMapper {
    int deleteByPrimaryKey(String id);

    int insert(UnionMember record);

    int insertSelective(UnionMember record);

    UnionMember selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UnionMember record);

    int updateByPrimaryKey(UnionMember record);
}