package com.wowoniu.fendian.service.serviceImpl;

import com.wowoniu.fendian.mapper.MemberStatisticMapper;
import com.wowoniu.fendian.service.MemberStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 会员统计Service实现
 *
 * @author yuany
 * @date 2020-06-28
 */
@Service("memberStatisticService")
public class MemberStatisticServiceImpl implements MemberStatisticService {

    @Autowired
    private MemberStatisticMapper memberStatisticMapper;

    /**
     * 根据用户ID获取其会员总数居及当日数据
     *
     * @param userId 会员ID
     * @return
     */
    @Override
    public Object getMemberTotalData(String userId) {

        return memberStatisticMapper.getMemberTotalData(userId);
    }

    /**
     * 根据父级用户ID获取会员用户集合 以团队人数倒叙 limit 取数据量
     *
     * @param userId 用户ID
     * @param limit  数据量
     * @return
     */
    @Override
    public Object getMemberList(String userId, Integer limit) {

        return memberStatisticMapper.getMemberList(userId, limit);
    }
}
