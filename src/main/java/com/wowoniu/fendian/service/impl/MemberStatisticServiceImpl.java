package com.wowoniu.fendian.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.config.Constants;
import com.wowoniu.fendian.mapper.ActivitySetMapper;
import com.wowoniu.fendian.mapper.AppletMapper;
import com.wowoniu.fendian.mapper.MemberStatisticMapper;
import com.wowoniu.fendian.model.*;
import com.wowoniu.fendian.service.MemberStatisticService;
import com.wowoniu.fendian.utils.DateUtils;
import com.wowoniu.fendian.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员统计Service实现
 *
 * @author
 * @date 2020-06-28
 */
@Service
public class MemberStatisticServiceImpl implements MemberStatisticService {

    @Autowired
    private MemberStatisticMapper memberStatisticMapper;

    @Autowired
    private ActivitySetMapper activitySetMapper;

    @Autowired
    private AppletMapper appletMapper;

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
    public PageUtil<Member> getUserList(Map<String, Object> map) {
        PageUtil<Member> pageUtil = new PageUtil();
        int count = memberStatisticMapper.searchUserCount(map);
        pageUtil.setTotalCount(count);
        pageUtil.setPageSize((Integer) map.get("pageSize"));
        pageUtil.setCurrentPage((Integer) map.get("pageSize"));
        List<Member> userList = memberStatisticMapper.getUserList(map);
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

    /**
     * 会员ID获取数据 会员余额变动记录
     *
     * @param map
     * @return
     */
    @Override
    public Object getMemberPrice(Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject();
        Member member = appletMapper.getMemberById(map.get("id").toString());
        List<MemberConsume> memberConsumeList = appletMapper.getConsume(map.get("id").toString());
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //总额
        double price = member.getPrice() * 0.01;
        //今日增
        int todayPlus = 0;
        //今日减
        int todayLess = 0;
        //总加
        int countPlus = 0;
        //总减
        int countLess = 0;
        for (MemberConsume memberConsume : memberConsumeList) {
            if (memberConsume.getType().equals("0") || memberConsume.getType().equals("2")) {
                countPlus = countPlus + memberConsume.getActual();
            }
            if (memberConsume.getType().equals("1")) {
                countLess = countLess + memberConsume.getActual();
            }
            if (sdf.format(memberConsume.getTime()).equals(DateUtils.getDay())) {
                if (memberConsume.getType().equals("0") || memberConsume.getType().equals("2")) {
                    todayPlus = todayPlus + memberConsume.getActual();
                }
                if (memberConsume.getType().equals("1")) {
                    todayLess = todayLess + memberConsume.getActual();
                }
            }
        }
        jsonObject.put("price", price);
        jsonObject.put("todayPlus", todayPlus * 0.01);
        jsonObject.put("todayLess", todayLess * 0.01);
        jsonObject.put("countPlus", countPlus * 0.01);
        jsonObject.put("countLess", countLess * 0.01);

        PageUtil<MemberConsume> pageUtil = new PageUtil();
        int count = memberStatisticMapper.searchMemberConsume(map);
        pageUtil.setTotalCount(count);
        pageUtil.setPageSize((Integer) map.get("pageSize"));
        pageUtil.setCurrentPage((Integer) map.get("pageSize"));
        List<MemberConsume> memberConsumes = memberStatisticMapper.getMemberConsumeList(map);
        pageUtil.setLists(memberConsumes);
        jsonObject.put("list", pageUtil);
        return jsonObject;
    }

    /**
     * 会员ID获取会员信息
     *
     * @param id
     * @return
     */
    @Override
    public Member getMember(String id) {
        return appletMapper.getMemberById(id);
    }

    /**
     * 更新会员信息
     *
     * @param member
     * @return
     */
    @Override
    public int updateMember(Member member) {
        return appletMapper.updateMember(member);
    }

    /**
     * 获取会员等级
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> getLevel(String userId) {
        List<FissionSetDetail> fissionSetDetailList = activitySetMapper.getLevel(userId);
        if (CollectionUtils.isEmpty(fissionSetDetailList)){
            return null;
        }
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (FissionSetDetail fissionSetDetail : fissionSetDetailList){
            Map<String,Object> map = new HashMap<>();
            map.put("level",fissionSetDetail.getLevel());
            map.put("levelName", fissionSetDetail.getLevelName());
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 获取记录 消费记录 /余额记录
     *
     * @param map
     * @return
     */
    @Override
    public PageUtil<MemberConsume> getMemberRecord(Map<String, Object> map) {
        PageUtil<MemberConsume> pageUtil = new PageUtil();
        pageUtil.setPageSize((Integer) map.get("pageSize"));
        pageUtil.setCurrentPage((Integer) map.get("pageSize"));
        List<MemberConsume> memberConsumeList = null;
        int count = 0;
        String type = map.get("type").toString();
        if ("0".equals(type)) {
            count = appletMapper.searchMemberRecord1(map);
            memberConsumeList = appletMapper.getMemberRecord1(map);
        } else if ("1".equals(type)) {
            count = appletMapper.searchMemberRecord2(map);
            memberConsumeList = appletMapper.getMemberRecord2(map);
        }
        pageUtil.setTotalCount(count);
        pageUtil.setLists(memberConsumeList);
        return pageUtil;
    }

    /**
     * 邀请记录
     *
     * @param map
     * @return
     */
    @Override
    public PageUtil<LinkMemberUser> getMemberInviter(Map<String, Object> map) {

        PageUtil<LinkMemberUser> pageUtil = new PageUtil();
        int count = appletMapper.searchMemberInviter(map);
        pageUtil.setTotalCount(count);
        pageUtil.setPageSize((Integer) map.get("pageSize"));
        pageUtil.setCurrentPage((Integer) map.get("pageSize"));
        List<LinkMemberUser> linkMemberUserList = appletMapper.getMemberInviter(map);
        pageUtil.setLists(linkMemberUserList);
        return pageUtil;
    }
}
