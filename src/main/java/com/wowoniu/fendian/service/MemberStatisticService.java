package com.wowoniu.fendian.service;

/**
 * 会员统计Service
 *
 * @author yuany
 * @date 2020-06-28
 */
public interface MemberStatisticService {

    /**
     * 根据用户ID获取其会员总数居及当日数据
     *
     * @param userId 会员ID
     * @return
     */
    Object getMemberTotalData(String userId);

    /**
     * 根据父级用户ID获取会员用户集合 以团队人数倒叙 limit 取数据量
     *
     * @param userId 用户ID
     * @param limit  数据量
     * @return
     */
    Object getMemberList(String userId, Integer limit);
}
