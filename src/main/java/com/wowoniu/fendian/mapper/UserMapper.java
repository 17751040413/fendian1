package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    /**
     * openId 获取微信用户
     *
     * @param openId
     * @return
     */
    @Select("SELECT * FROM user WHERE open_id = #{openId}")
    User selectById(@Param("openId") String openId);

    /**
     * skey 获取微信用户
     *
     * @param skey
     * @return
     */
    @Select("SELECT * FROM user WHERE skey = #{skey}")
    User selectBySkey(@Param("skey") String skey);

    /**
     * openId 获取微信用户
     * @param openId
     * @return
     */
    @Select("SELECT * FROM user WHERE opend = #{openId}")
    User selectByOpenId(@Param("openId") String openId);

    /**
     * 登陆退出 清除skey
     * @param openId
     * @return
     */
    @Update("UPDATE user SET skey = null WHERE open_id = #{openId}")
    int signOut(@Param("openId") String openId);

    /**
     * 微信用户新增
     */
    @Insert("INSERT INTO `user` (open_id,skey,create_time,last_visit_time,session_key,city,province,country,avatar_url,gender,nick_name) " +
            "VALUES (#{openId},#{skey},#{createTime},#{lastVisitTime},#{sessionKey},#{city},#{province},#{country},#{avatarUrl},#{gender},#{nickName})")
    void addUser(User user);

    /**
     * 微信用户更新
     *
     * @param user
     */
    @Update("UPDATE `user` SET last_visit_time = #{lastVisitTime} ,skey = #{skey} WHERE open_id = #{openId}")
    void updateById(User user);
}
