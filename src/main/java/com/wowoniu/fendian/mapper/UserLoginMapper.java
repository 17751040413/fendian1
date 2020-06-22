package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.UserLogin;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserLoginMapper {

    /**
     * 查询用户最近一次登录信息
     * @param id
     * @return
     */
    @Select("select * from user_login where user_id = #{id} order by login_time desc limit 1")
    UserLogin queryUserLoginById(String id);

    @Update("update user_login set connection_time = #{connectionTime} where id = #{id}")
    int updateConTimeUserLoginById(UserLogin userLogin);
}
