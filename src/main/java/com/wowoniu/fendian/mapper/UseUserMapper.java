package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.UseUser;
import org.apache.ibatis.annotations.Select;

public interface UseUserMapper {

    /**
     * 根据openid查询用户
     * @param openId
     * @return
     */
    @Select("SELECT * FROM use_user WHERE wechat_id = #{openId} AND state = 0")
    UseUser queryUserByOpenId(String openId);

}
