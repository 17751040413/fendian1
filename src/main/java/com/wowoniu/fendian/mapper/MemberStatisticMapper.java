package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.MemberStatistic;
import com.wowoniu.fendian.model.UseUser;
import com.wowoniu.fendian.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 会员统计DAO层
 *
 * @author yuany
 * @date 2020-06-28
 */
public interface MemberStatisticMapper {

    /**
     * 根据用户ID获取其会员总数居及当日数据
     *
     * @param userId 用户ID
     * @return
     */
    @Select("SELECT * FROM member_statistic WHERE user_id = #{userId} AND type = #{type}")
    MemberStatistic getMemberTotalData(@Param("userId") String userId, @Param("type") String type);

    /**
     * 商家ID获取会员 limit 取数据量
     *
     * @param userId 商家ID
     * @param limit    数据量
     * @return
     */
    List<User> getUserList(@Param("userId") String userId, @Param("limit") Integer limit);
}
