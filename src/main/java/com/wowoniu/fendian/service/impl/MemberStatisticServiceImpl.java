package com.wowoniu.fendian.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.config.Constants;
import com.wowoniu.fendian.mapper.ActivitySetMapper;
import com.wowoniu.fendian.mapper.MemberStatisticMapper;
import com.wowoniu.fendian.model.*;
import com.wowoniu.fendian.service.MemberStatisticService;
import com.wowoniu.fendian.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
                object.put("activity", activitySetMapper.getFissionSetDetail(userId));
                break;
            //会员返利
            case Constants.REBATE:
                object.put("activity", activitySetMapper.getRebateSetList(userId));
                break;
            //店铺分销
            case Constants.DISTRIBUTION:
                DistributionSet distributionSet = activitySetMapper.getDistributionSet(userId);
                distributionSet.setDistributionCouponList(activitySetMapper.getDistributionCoupon(userId));
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
     * @param map 分页
     * @return
     */
    @Override
    public PageUtil<User> getUserList(Map<String, Object> map) {
        PageUtil<User> pageUtil = new PageUtil();
        int count = memberStatisticMapper.searchUserCount(map);
        pageUtil.setTotalCount(count);
        pageUtil.setPageSize((Integer) map.get("pageSize"));
        pageUtil.setCurrentPage((Integer) map.get("pageSize"));
        List<User> userList = memberStatisticMapper.getUserList(map);
        pageUtil.setLists(userList);
        return pageUtil;
    }

    /**
     * 活动列表
     *
     * @param map
     * @return
     */
    @Override
    public JSONObject getActivity(Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject();
        int count;
        switch ((String) map.get("type")) {
            //会员裂变
            case Constants.FISSION:
                PageUtil<FissionSetDetail> pageUtil1 = new PageUtil();
                pageUtil1.setPageSize((Integer) map.get("pageSize"));
                pageUtil1.setCurrentPage((Integer) map.get("pageSize"));
                count = activitySetMapper.searchFissionSetDetail(map);
                List<FissionSetDetail> fissionSetDetailList = activitySetMapper.getFissionSetDetailByUserId(map);
                pageUtil1.setTotalCount(count);
                pageUtil1.setLists(fissionSetDetailList);
                jsonObject.put("fissionDetail", pageUtil1);
                break;
            //会员返利
            case Constants.REBATE:
                RebateSet readable = activitySetMapper.getRebateSets(map.get("userId").toString());
                List<RechargeDetail> rechargeDetailList = activitySetMapper.getRechargeDetailList(readable.getId());
                List<RebateSetDetail> rebateSetDetailList = activitySetMapper.getRebateSetDetailList(readable.getId());
                jsonObject.put("rebate", readable);
                jsonObject.put("detail", rebateSetDetailList);
                jsonObject.put("recharge", rechargeDetailList);
                break;
            //店铺分销
            case Constants.DISTRIBUTION:
                PageUtil<DistributionCoupon> pageUtil3 = new PageUtil();
                pageUtil3.setPageSize((Integer) map.get("pageSize"));
                pageUtil3.setCurrentPage((Integer) map.get("pageSize"));
                count = activitySetMapper.searchDistributionCoupon(map);
                List<DistributionCoupon> distributionCouponList = activitySetMapper.getDistributionCouponList(map);
                pageUtil3.setTotalCount(count);
                pageUtil3.setLists(distributionCouponList);
                jsonObject.put("distributionDetail", pageUtil3);
                break;
            default:
                break;
        }

        return jsonObject;
    }
}
