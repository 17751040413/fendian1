package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.Member;
import com.wowoniu.fendian.model.MemberConsume;
import com.wowoniu.fendian.model.MemberStatistic;
import com.wowoniu.fendian.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 会员统计DAO层
 *
 * @author
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
     * @param map
     * @return
     */
    List<Member> getUserList(Map<String, Object> map);

    /**
     * 条件搜索用户数量
     *
     * @param map
     * @return
     */
    int searchUserCount(Map<String, Object> map);

    List<MemberConsume> getMemberConsumeList(Map<String, Object> map);

    int searchMemberConsume(Map<String, Object> map);
}
