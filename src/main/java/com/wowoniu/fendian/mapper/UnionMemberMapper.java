package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.UnionMember;

import java.util.List;

public interface UnionMemberMapper {
    int deleteByPrimaryKey(String id);

    int insert(UnionMember record);

    int insertSelective(UnionMember record);

    UnionMember selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UnionMember record);

    int updateByPrimaryKey(UnionMember record);

    /**
     * 根据会员id查联盟
     * @param userid
     * @return
     */
    List<UnionMember> queryMemberByUserId(String userid);

    /**
     * 查询联盟未开通会员
     * @param unionId
     * @return
     */
    List<UnionMember> queryMemberByUnionId(String unionId);
}