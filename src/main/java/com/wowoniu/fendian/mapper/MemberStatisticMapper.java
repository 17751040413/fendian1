package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.MemberStatistic;
import com.wowoniu.fendian.model.UseUser;
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
     * 根据父级用户ID获取会员用户集合 以团队人数倒叙 limit 取数据量
     *
     * @param parentId 父级用户ID
     * @param limit    数据量
     * @return
     */
    List<UseUser> getUseUserList(@Param("parentId") String parentId, @Param("limit") Integer limit);
}
