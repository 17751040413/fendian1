package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.UseUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UseUserMapper {

    /**
     * 根据openid查询用户
     * @param openId
     * @return
     */
    @Select("SELECT * FROM use_user WHERE wechat_id = #{openId} AND state = 0")
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
