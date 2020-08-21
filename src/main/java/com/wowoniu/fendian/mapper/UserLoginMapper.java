package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.UserLogin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserLoginMapper {

    /**
     * 查询用户最近一次登录信息
     * @param id
     * @return
     */
    @Select("select * from user_login where user_id = #{id} order by login_time desc limit 1")
    UserLogin queryUserLoginById(String id);

    /**
     * 修改用户最后一次访问时间
     * @param userLogin
     * @return
     */
    @Update("update user_login set connection_time = #{connectionTime} where id = #{id}")
    int updateConTimeUserLoginById(UserLogin userLogin);

    /**
     * 添加用户登录信息
     * @param userLogin
     * @return
     */
    @Insert("insert into user_login (id,user_id,identification,login_type,login_time) values (#{id},#{userId}," +
            "#{identification},#{loginType},#{loginTime})")
    int insertUserLogin(UserLogin userLogin);
}
