package com.wowoniu.fendian.service.serviceImpl;

import com.wowoniu.fendian.config.Constants;
import com.wowoniu.fendian.mapper.ActivitySetMapper;
import com.wowoniu.fendian.mapper.MemberStatisticMapper;
import com.wowoniu.fendian.service.MemberStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private ActivitySetMapper activitySetMapper;

    /**
     * 根据用户ID获取其会员总数居及当日数据,及活动列表
     *
     * @param userId 会员ID
     * @param type   引流类型
     * @return
     */
    @Override
    public Object getTotalDataAndActivity(String userId, String type) {
        Map<String, Object> map = new HashMap<>();
        map.put("total", memberStatisticMapper.getMemberTotalData(userId,type));
        switch (type) {
            case Constants.FISSION:
                map.put("activity",activitySetMapper.getFissionSetDetail(userId));
                break;
            case Constants.REBATE:
                break;
            case Constants.DISTRIBUTION:
                break;
            case Constants.SHOPPINGMALL:
                break;
            case Constants.TURNTABLE:
                break;
            case Constants.COUPON:
                break;
            case Constants.RECOMMEND:
                break;
            case Constants.SECKILL:
                break;
            case Constants.SPELL:
                break;
            case Constants.LUCKDRAW:
                break;
            case Constants.BARGAINING:
                break;
            case Constants.REDENVELOPES:
                break;
            default:
                break;
        }
        return map;
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
