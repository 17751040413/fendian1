package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.UseUser;


public interface UseUserMapper {

    /**
     * 根据openid查询用户
     * @param openId
     * @return
     */
    UseUser queryUserByOpenId(String openId);


    int deleteByPrimaryKey(String id);

    int insert(UseUser record);

    int insertSelective(UseUser record);

    UseUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UseUser record);

    int updateByPrimaryKey(UseUser record);

    /**
     * 根据用户名查询
     * @param loginName
     * @return
     */
    UseUser queryUserByLoginName(String loginName);

}
