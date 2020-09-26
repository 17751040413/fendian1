package com.wowoniu.fendian.service;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.model.User;
import com.wowoniu.fendian.utils.PageUtil;

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
    PageUtil<User> getUserList(Map<String, Object> map);

    /**
     * 活动列表
     *
     * @param map
     * @return
     */
    JSONObject getActivity(Map<String, Object> map);
}
