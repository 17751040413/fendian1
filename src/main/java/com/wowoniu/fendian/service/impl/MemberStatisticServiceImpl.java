package com.wowoniu.fendian.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.config.Constants;
import com.wowoniu.fendian.mapper.ActivitySetMapper;
import com.wowoniu.fendian.mapper.MemberStatisticMapper;
import com.wowoniu.fendian.model.DistributionSet;
import com.wowoniu.fendian.service.MemberStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 会员统计Service实现
 *
 * @author yuany
 * @date 2020-06-28
 */
@Service
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
    public JSONObject getTotalDataAndActivity(String userId, String type) {
        JSONObject object = new JSONObject();
        //统计数据-类型区分
        object.put("total", memberStatisticMapper.getMemberTotalData(userId, type));
        switch (type) {
            //会员裂变
            case Constants.FISSION:
                object.put("activity", activitySetMapper.getFissionSetDetailByUserId(userId));
                break;
            //会员返利
            case Constants.REBATE:
                object.put("activity", activitySetMapper.getRebateSetList(userId));
                break;
            //店铺分销
            case Constants.DISTRIBUTION:
                DistributionSet distributionSet = activitySetMapper.getDistributionSet(userId);
                distributionSet.setDistributionCouponList(activitySetMapper.getDistributionCouponList(userId));
                object.put("activity", distributionSet);
                break;
            //在线商城
            case Constants.SHOPPINGMALL:
                //查询状态非未付款和已取消的订单
                String[] states = {Constants.ORDER_STATE_PAID, Constants.ORDER_STATE_NOT_SHIPPED, Constants.ORDER_STATE_SHIPPED, Constants.ORDER_STATE_COMPLETE};
                object.put("activity", activitySetMapper.getWaresOrder(userId, states));
                break;
            //幸运转盘
            case Constants.TURNTABLE:
                break;
            //发优惠券
            case Constants.COUPON:
                break;
            //推荐有礼
            case Constants.RECOMMEND:
                break;
            //秒杀活动
            case Constants.SECKILL:
                break;
            //拼团活动
            case Constants.GROUP:
                break;
            //砸金蛋抽奖
            case Constants.LUCKDRAW:
                break;
            //砍价大战
            case Constants.BARGAINING:
                break;
            //红包裂变券
            case Constants.REDENVELOPES:
                break;
            default:
                break;
        }
        return object;
    }

    /**
     * 根据父级用户ID获取会员用户集合 以团队人数倒叙 limit 取数据量
     *
     * @param userId 用户ID
     * @param limit  数据量
     * @return
     */
    @Override
    public Object getUserList(String userId, Integer limit) {

        return memberStatisticMapper.getUserList(userId, limit);
    }

    /**
     * 活动列表
     *
     * @param userId
     * @return
     */
    @Override
    public JSONObject getActivity(String userId, int type) {

        String t = type+"";
        JSONObject jsonObject = new JSONObject();
        switch (t) {
            //会员裂变
            case Constants.FISSION:
                //裂变
                jsonObject.put("fissionDetail", activitySetMapper.getFissionSetDetailByUserId(userId));
                break;
            //会员返利
            case Constants.REBATE:
                //返利
                jsonObject.put("rebate", activitySetMapper.getRebateSet(userId));
                //分销
                break;
            //店铺分销
            case Constants.DISTRIBUTION:
                jsonObject.put("distributionDetail", activitySetMapper.getDistributionCouponList(userId));
                break;
            default:
                break;
        }

        return jsonObject;
    }
}
