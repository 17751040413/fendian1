package com.wowoniu.fendian.service;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.model.LinkMemberUser;
import com.wowoniu.fendian.model.Member;
import com.wowoniu.fendian.model.MemberConsume;
import com.wowoniu.fendian.utils.PageUtil;

import java.util.List;
import java.util.Map;

/**
 * 会员统计Service
 *
 * @author
 * @date 2020-06-28
 */
public interface MemberStatisticService {

    /**
     * 根据用户ID获取其会员总数居及当日数据，及活动列表
     *
     * @param userId 会员ID
     * @return
     */
    JSONObject getTotalDataAndActivity(String userId, String type);

    /**
     * 根据父级用户ID获取会员用户集合 以团队人数倒叙 limit 取数据量
     *
     * @param map 分页
     * @return
     */
    PageUtil<Member> getUserList(Map<String, Object> map);

    /**
     * 活动列表
     *
     * @param map
     * @return
     */
    JSONObject getActivity(Map<String, Object> map);

    /**
     * 会员ID获取数据 会员余额变动记录
     *
     * @param map
     * @return
     */
    Object getMemberPrice(Map<String, Object> map);

    /**
     * ID获取会员信息
     *
     * @param id
     * @return
     */
    Member getMember(String id);

    /**
     * 更新会员信息
     *
     * @param member
     * @return
     */
    int updateMember(Member member);

    List<Map<String,Object>>  getLevel(String userId);

    /**
     * 获取记录 消费记录 /余额记录
     *
     * @param map
     * @return
     */
    PageUtil<MemberConsume> getMemberRecord(Map<String, Object> map);

    /**
     * 邀请记录
     *
     * @param map
     * @return
     */
    PageUtil<LinkMemberUser> getMemberInviter(Map<String, Object> map);

}
