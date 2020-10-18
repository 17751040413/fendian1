package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.UserSystem;
import org.apache.ibatis.annotations.Select;

public interface UserSystemMapper {

    @Select("select * from user_system where user_id = #{userid}")
    UserSystem queryUserSystemByUserId(String userid);
}
