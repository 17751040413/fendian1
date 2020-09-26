package com.wowoniu.fendian.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.config.Constants;
import com.wowoniu.fendian.mapper.ActivitySetMapper;
import com.wowoniu.fendian.mapper.AppletMapper;
import com.wowoniu.fendian.mapper.UserMapper;
import com.wowoniu.fendian.model.*;
import com.wowoniu.fendian.service.AppletService;
import com.wowoniu.fendian.utils.PageUtil;
import com.wowoniu.fendian.utils.StringUtils;
import com.wowoniu.fendian.utils.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 小程序Service实现
 *
 * @author
 * @date 2020-08-19
 */
@Service
public class AppletServiceImpl implements AppletService {

    @Autowired
    private AppletMapper appletMapper;

    @Autowired
    private ActivitySetMapper activitySetMapper;

    @Autowired
    private UserMapper userMapper;


    /**
     * 商铺分页条件查询
     *
     * @param map 参数
     * @return
     */
    @Override
    public PageUtil<UseUser> getSearchShops(Map<String, Object> map) {

        PageUtil<UseUser> pageUtil = new PageUtil();
        int count = appletMapper.searchUseUserCount(map);
        pageUtil.setTotalCount(count);
        pageUtil.setPageSize((Integer) map.get("pageSize"));
        pageUtil.setCurrentPage((Integer) map.get("pageSize"));
        List<UseUser> useUsers = appletMapper.searchUseUser(map);
        pageUtil.setLists(useUsers);

        return pageUtil;
    }

    /**
     * 商家ID获取店铺信息
     *
     * @param id 商家ID
     * @return
     */
    @Override
    public UseUser getUseUserById(String id, String openId) {
        //添加浏览记录
        UseUser useUser = appletMapper.getUseUserById(id);
        ShopRecord shopRecord = appletMapper.getShopRecordByUserId(id, openId);
        if (shopRecord == null) {
            shopRecord = new ShopRecord();
            shopRecord.setBuyerId(openId);
            shopRecord.setUserId(id);
            shopRecord.setId(StringUtils.getUuid());
            shopRecord.setLastTime(new Timestamp(System.currentTimeMillis()));
            shopRecord.setShopLogo(useUser.getShopLogo());
            shopRecord.setShopName(useUser.getShopName());
            appletMapper.addShopRecord(shopRecord);
        } else {
            appletMapper.updateShopRecordLastTime(shopRecord.getId());
        }

        return useUser;
    }

    /**
     * 获取商家的商品分类
     *
     * @param useUserId
     * @return
     */
    @Override
    public List<WaresSortSet> getSortByUseUserId(String useUserId) {
        WaresSortSet waresSortSet = activitySetMapper.getWaresSortSet(useUserId);
        //无分类  或 已禁用分类
        if (waresSortSet == null || waresSortSet.getState().equals(Constants.NO)) {
            return null;
        }
        return appletMapper.getSortById(waresSortSet.getId());
    }

    /**
     * 商家商品分页列表
     *
     * @param map 参数
     * @return
     */
    @Override
    public PageUtil<Wares> getGoodsPage(Map<String, Object> map) {
        PageUtil<Wares> pageUtil = new PageUtil();
        int count = appletMapper.searchGoodsCount(map);
        pageUtil.setTotalCount(count);
        pageUtil.setPageSize((Integer) map.get("pageSize"));
        pageUtil.setCurrentPage((Integer) map.get("pageSize"));
        List<Wares> wares = appletMapper.searchGoods(map);
        pageUtil.setLists(wares);

        return pageUtil;
    }

    /**
     * 商品ID获取商品信息
     *
     * @param waresId
     * @return
     */
    @Override
    public Wares getWaresById(String waresId) {
        return appletMapper.getWaresById(waresId);
    }

    /**
     * 商品ID获取规格
     *
     * @param waresId
     * @return
     */
    @Override
    public JSONObject getWaresSpec(String waresId) {

        List<WaresSpec> waresSpecList = activitySetMapper.getWaresSpecList(waresId);
        JSONObject jsonObject = new JSONObject();
        for (WaresSpec waresSpec : waresSpecList) {
            List<WaresSpecDetail> waresSpecDetailList = activitySetMapper.getWaresSpecDetailList(waresSpec.getId());
            jsonObject.put(waresSpec.getSpec(), waresSpecDetailList);
        }
        return jsonObject;
    }

    /**
     * 购物车添加
     *
     * @param waresCart
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean setGoodsCart(WaresCart waresCart) {

        if (waresCart == null) {
            return false;
        }
        //获取同买家卖家的同件规格的同种商品
        WaresCart wc = appletMapper.getWaresCartByWares(waresCart);
        if (wc != null) {
            wc.setNumber(wc.getNumber() + waresCart.getNumber());
            appletMapper.updateWaresCart(wc.getNumber(), wc.getId());
            return true;
        } else {
            waresCart.setId(StringUtils.getUuid());
            appletMapper.addWaresCart(waresCart);
            return true;
        }
    }

    /**
     * 买家ID获取购物车列表
     *
     * @param buyerId
     * @return
     */
    @Override
    public List<WaresCart> getGoodsCartById(String buyerId, String userId) {
        return appletMapper.getGoodsCartById(buyerId, userId);
    }

    /**
     * 订单结算
     *
     * @param waresOrder
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String settlementOrder(WaresOrder waresOrder) {
        if (waresOrder == null) {
            return null;
        }
        //保存订单
        waresOrder.setId(StringUtils.getUuid());
        waresOrder.setOrderCode(getOrderMaxCode());
        appletMapper.addWaresOrder(waresOrder);
        List<String> cartIds = Arrays.asList(waresOrder.getCartId().split(","));
        //购物车数据添加订单ID
        appletMapper.updateWaresCarByOrder(cartIds, waresOrder.getId());

        return waresOrder.getId();
    }

    /**
     * 获取最大订单编码
     *
     * @return
     */
    private String getOrderMaxCode() {
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String today = sdf.format(new Date());
        //获取当前最大收货单编码
        String maxCode = appletMapper.getMaxOrderCode();
        //当前最大收获编码若为空 则返回DT-日期-00000001,否则递增
        if (StringUtils.isEmpty(maxCode)) {
            return "DT-" + today + "-00000001";
        } else {
            //序列递增，8位，例：00000001
            String format = String.format("%08d", Integer.valueOf(maxCode.split("-")[2]) + 1);
            return String.format("DT-" + today + "-" + format);
        }
    }

    /**
     * 收货地址列表
     *
     * @param buyerId
     * @return
     */
    @Override
    public List<ShippingAddress> getShippingAddressList(String buyerId) {
        return appletMapper.getShippingAddressList(buyerId);
    }

    /**
     * 收货地址新增/修改
     *
     * @param shippingAddress
     * @return
     */
    @Override
    public boolean setShippingAddress(ShippingAddress shippingAddress) {
        if (shippingAddress == null) {
            return false;
        }
        if (shippingAddress.getId() == null) {
            shippingAddress.setId(StringUtils.getUuid());
            appletMapper.addShippingAddress(shippingAddress);
        } else {
            appletMapper.updateShippingAddress(shippingAddress);
        }
        if (shippingAddress.getTab().equals(Constants.YES)) {
            //去除原有默认地址
            appletMapper.updateAddressDefaultN();
        }
        return true;
    }

    /**
     * 当前店铺的订单列表
     *
     * @param buyerId
     * @param userId
     * @return
     */
    @Override
    public JSONObject getWaresOrderList(String buyerId, String userId) {
        return appletMapper.getWaresOrderList(buyerId, userId);
    }

    /**
     * 订单ID获取订单明细
     *
     * @param id
     * @return
     */
    @Override
    public JSONObject getWaresOrderById(String id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("detail", appletMapper.getWaresCartByOrderId(id));
        jsonObject.put("order", appletMapper.getWaresOrderById(id));
        return jsonObject;
    }

    /**
     * 订单ID获取取货码
     *
     * @param id
     * @return
     */
    @Override
    public String getTakeCodeById(String id) {

        return appletMapper.getTakeCodeById(id);
    }

    /**
     * 订单状态更新
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateOrderState(String id, String state, String courierNumber) {
        if (state.equals(Constants.ORDER_STATE_PAID) || state.equals(Constants.ORDER_STATE_NOT_SHIPPED)) {
            appletMapper.updateOrderTakeCode(id, VerifyCodeUtil.generateVerifyCode(6));
        } else if (state.equals(Constants.ORDER_STATE_SHIPPED)) {
            if (StringUtils.isEmpity(courierNumber)) {
                return false;
            }
            appletMapper.updateOrderCourierNumber(id, courierNumber);
        }
        appletMapper.updateOrderState(id, state);

        return true;
    }

    /**
     * 买家优惠券列表
     *
     * @param id
     * @return
     */
    @Override
    public List<CouponBuyer> getCouponBuyerList(String id) {
        return appletMapper.getCouponBuyerList(id);
    }

    /**
     * 优惠券详情
     *
     * @param id
     * @return
     */
    @Override
    public CouponBuyer getCouponBuyerById(String id) {
        return appletMapper.getCouponBuyerById(id);
    }

    /**
     * 参与的拼团活动
     *
     * @param buyerId
     * @param state
     * @return
     */
    @Override
    public List<GroupBuyer> groupParticipate(String buyerId, int state) {
        return appletMapper.groupParticipate(buyerId, state);
    }

    /**
     * 我的奖券
     *
     * @param buyerId
     * @return
     */
    @Override
    public List<LuckBuyer> luckWinning(String buyerId, int state) {
        return appletMapper.luckWinning(buyerId, state);
    }

    /**
     * 券详情
     *
     * @param id
     * @return
     */
    @Override
    public LuckBuyer luckWinningDetail(String id) {
        return appletMapper.luckWinningDetail(id);
    }

    /**
     * 参与的砍价活动
     *
     * @param buyerId
     * @param state
     * @return
     */
    @Override
    public List<BargainBuyer> bargainParticipate(String buyerId, int state) {
        return appletMapper.bargainParticipate(buyerId, state);
    }

    /**
     * ID获取用户信息
     *
     * @param openId
     * @return
     */
    @Override
    public User getUserByOpenId(String openId) {
        return appletMapper.getUserByOpenId(openId);
    }

    /**
     * 当前用户所有订单
     *
     * @param openId
     * @return
     */
    @Override
    public List<JSONObject> getWaresOrderAll(String openId) {
        return appletMapper.getWaresOrderAll(openId);
    }

    /**
     * ID获取订单详情
     *
     * @param id
     * @return
     */
    @Override
    public JSONObject getOrderById(String id) {
        return appletMapper.getOrderById(id);
    }

    /**
     * 浏览过的店铺
     *
     * @param openId
     * @return
     */
    @Override
    public List<ShopRecord> getShopRecordList(String openId) {
        return appletMapper.getShopRecordList(openId);
    }

    /**
     * ID获取推荐好友活动
     *
     * @param id
     * @return
     */
    @Override
    public RecommendSet getRecommend(String id) {
        return activitySetMapper.getRecommendSet(id);
    }

    /**
     * 商家ID获取推荐好友活动
     *
     * @param userId
     * @return
     */
    @Override
    public List<RecommendSet> getRecommendList(String userId) {
        return activitySetMapper.getRecommendSetList(userId);
    }

    /**
     * 添加推荐优惠券
     *
     * @param id
     * @param openId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addCounpon(String id, String openId, String openId1) {
        //获取活动
        RecommendSet recommendSet = activitySetMapper.getRecommendSet(id);
        CouponBuyer couponBuyer = new CouponBuyer();
        couponBuyer.setId(StringUtils.getUuid());
        couponBuyer.setBuyerId(openId1);
        couponBuyer.setDonorId(openId);
        couponBuyer.setUserId(recommendSet.getUserId());
        couponBuyer.setStartTime(recommendSet.getRecommendStartTime());
        couponBuyer.setEndTime(recommendSet.getRecommendEndTime());
        couponBuyer.setCreateTime(new Timestamp(System.currentTimeMillis()));
        couponBuyer.setActivityId(recommendSet.getId());
        couponBuyer.setActivityName(recommendSet.getTitle());
        couponBuyer.setCondition(recommendSet.getRecommendThreshold().toString());
        couponBuyer.setRange(recommendSet.getRecommendRange());
        couponBuyer.setExchangeNumber(recommendSet.getRecommendGiftNumber());
        couponBuyer.setExchangeContent(recommendSet.getRecommendGiftName());
        couponBuyer.setDiscount(recommendSet.getRecommendDiscount());
        couponBuyer.setActivityType(Constants.RECOMMEND);
        String[] url = recommendSet.getPictureUrl().split(";");
        couponBuyer.setActivityUrl(url[0]);
        couponBuyer.setDiscountAmount(recommendSet.getRecommendMoney().toString());
        appletMapper.addCouponBuyer(couponBuyer);

        couponBuyer = new CouponBuyer();
        couponBuyer.setId(StringUtils.getUuid());
        couponBuyer.setBuyerId(openId);
        couponBuyer.setUserId(recommendSet.getUserId());
        couponBuyer.setStartTime(recommendSet.getRecommendedStartTime());
        couponBuyer.setEndTime(recommendSet.getRecommendedEndTime());
        couponBuyer.setCreateTime(new Timestamp(System.currentTimeMillis()));
        couponBuyer.setActivityId(recommendSet.getId());
        couponBuyer.setActivityName(recommendSet.getTitle());
        couponBuyer.setCondition(recommendSet.getRecommendedThreshold().toString());
        couponBuyer.setRange(recommendSet.getRecommendedRange());
        couponBuyer.setExchangeNumber(recommendSet.getRecommendedGiftNumber());
        couponBuyer.setExchangeContent(recommendSet.getRecommendedGiftName());
        couponBuyer.setDiscount(recommendSet.getRecommendedDiscount());
        couponBuyer.setActivityType(Constants.RECOMMEND);
        couponBuyer.setActivityUrl(url[0]);
        couponBuyer.setDiscountAmount(recommendSet.getRecommendedMoney().toString());
        appletMapper.addCouponBuyer(couponBuyer);
        return couponBuyer.getId();
    }

    /**
     * 砸金蛋 / 幸运大抽奖
     *
     * @param userId
     * @param type
     * @return
     */
    @Override
    public JSONObject lottery(String userId, String type) {
        JSONObject jsonObject = new JSONObject();
        LuckDrawSet luckDrawSet = activitySetMapper.getLuckDrawSetByUserId(userId, type);
        if (luckDrawSet != null) {
            List<LuckDrawDetail> luckDrawDetailList = activitySetMapper.getLuckDrawDetailList(luckDrawSet.getId());
            jsonObject.put("luck", luckDrawSet);
            jsonObject.put("detail", luckDrawDetailList);
        }

        return jsonObject;
    }

    /**
     * 参与抽奖人添加
     *
     * @param id
     * @param openId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addLuckUser(String id, String openId) {
        LuckUser luckUser = new LuckUser();
        User user = appletMapper.getUserByOpenId(openId);
        luckUser.setActivityId(id);
        luckUser.setBuyerId(openId);
        luckUser.setId(StringUtils.getUuid());
        luckUser.setName(user.getNickName());
        luckUser.setAvatarUrl(user.getAvatarUrl());
        appletMapper.addLuckUser(luckUser);
    }

    /**
     * 查看中奖券
     *
     * @param id
     * @return
     */
    @Override
    public String checkLuckCoupon(String id, String openId) {
        LuckDrawDetail luckDrawDetail = activitySetMapper.getLuckDrawDetailById(id);
        LuckDrawSet luckDrawSet = activitySetMapper.getLuckDrawSet(luckDrawDetail.getLuckDrawId());
        //放入用户券包
        CouponBuyer couponBuyer = new CouponBuyer();
        couponBuyer.setId(StringUtils.getUuid());
        couponBuyer.setBuyerId(openId);
        couponBuyer.setUserId(luckDrawSet.getUserId());
        couponBuyer.setStartTime(luckDrawDetail.getStartTime());
        couponBuyer.setEndTime(luckDrawDetail.getEndTime());
        couponBuyer.setCreateTime(new Timestamp(System.currentTimeMillis()));
        couponBuyer.setActivityId(luckDrawDetail.getId());
        couponBuyer.setActivityName(luckDrawSet.getTitle());
        couponBuyer.setRange(luckDrawDetail.getRange());
        couponBuyer.setExchangeNumber(1);
        couponBuyer.setExchangeContent(luckDrawDetail.getPrizeName());
        couponBuyer.setDiscount(luckDrawDetail.getDiscount());
        if ("0".equals(luckDrawSet.getType())) {
            couponBuyer.setActivityType(Constants.TURNTABLE);
        } else {
            couponBuyer.setActivityType(Constants.LUCKDRAW);
        }
        couponBuyer.setActivityUrl(luckDrawDetail.getPictureUrl());
        couponBuyer.setDiscountAmount(luckDrawDetail.getPreferential().toString());
        appletMapper.addCouponBuyer(couponBuyer);

        return couponBuyer.getId();
    }

    /**
     * 活动ID获取参与人
     *
     * @param id
     * @return
     */
    @Override
    public JSONObject luckUserList(String id) {
        JSONObject jsonObject = new JSONObject();
        List<LuckUser> luckUserList = appletMapper.luckUserList(id);
        jsonObject.put("luckUser", luckUserList);
        jsonObject.put("num", luckUserList.size());
        return jsonObject;
    }

    /**
     * 商家ID获取秒杀活动列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<SeckillSet> spike(String userId) {
        return appletMapper.spike(userId);
    }

    /**
     * 活动ID获取秒杀活动详情
     *
     * @param id
     * @return
     */
    @Override
    public SeckillSet spikeById(String id) {
        return activitySetMapper.getSeckillSet(id);
    }

    /**
     * 查看券
     *
     * @param id
     * @return
     */
    @Override
    public CouponBuyer couponById(String id) {
        return appletMapper.couponById(id);
    }

    /**
     * 获取优惠券
     *
     * @param id
     * @param openId
     * @return
     */
    @Override
    public String getSpike(String id, String openId) {
        SeckillSet seckillSet = activitySetMapper.getSeckillSet(id);
        if (seckillSet == null) {
            return null;
        }
        //放入用户券包
        CouponBuyer couponBuyer = new CouponBuyer();
        couponBuyer.setId(StringUtils.getUuid());
        couponBuyer.setBuyerId(openId);
        couponBuyer.setUserId(seckillSet.getUserId());
        couponBuyer.setStartTime(seckillSet.getStartTime());
        couponBuyer.setEndTime(seckillSet.getEndTime());
        couponBuyer.setCreateTime(new Timestamp(System.currentTimeMillis()));
        couponBuyer.setActivityId(seckillSet.getId());
        couponBuyer.setActivityName(seckillSet.getTitle());
        couponBuyer.setType(Constants.FAVORABLE);
        couponBuyer.setExchangeNumber(1);
        couponBuyer.setExchangeContent(seckillSet.getTitle());
        couponBuyer.setPrice(seckillSet.getOriginalPrice());
        couponBuyer.setActivityType(Constants.SECKILL);
        couponBuyer.setActivityUrl(seckillSet.getHeadPictureUrl());
        couponBuyer.setActivityPrice(seckillSet.getSeckillPrice().toString());
        couponBuyer.setPayPrice(seckillSet.getPayAdvance().toString());
        appletMapper.addCouponBuyer(couponBuyer);
        return couponBuyer.getId();
    }

    /**
     * ID获取订单详情
     *
     * @param id
     * @return
     */
    @Override
    public List<CouponUser> couponUser(String id) {
        return appletMapper.getUnionCouponUserByActivityId(id);
    }

    /**
     * ID获取优惠券信息
     *
     * @param id
     * @return
     */
    @Override
    public CouponSet couponInfo(String id) {
        return activitySetMapper.getCouponSet(id);
    }

    /**
     * 领取优惠券
     *
     * @param id
     * @param openId
     * @return
     */
    @Override
    public String getCoupon(String id, String openId) {
        CouponSet couponSet = activitySetMapper.getCouponSet(id);
        if (couponSet == null) {
            return null;
        }
        //放入用户券包
        CouponBuyer couponBuyer = new CouponBuyer();
        couponBuyer.setId(StringUtils.getUuid());
        couponBuyer.setBuyerId(openId);
        couponBuyer.setUserId(couponSet.getUserId());
        couponBuyer.setCreateTime(new Timestamp(System.currentTimeMillis()));
        couponBuyer.setActivityId(couponSet.getId());
        couponBuyer.setActivityType(Constants.COUPON);
        appletMapper.addCouponBuyer(couponBuyer);
        return couponBuyer.getId();
    }

    /**
     * 商家ID获取拼团活动
     *
     * @param userId
     * @return
     */
    @Override
    public List<GroupBuying> groupBuy(String userId) {
        appletMapper.updateGroupBuyerByTime();
        return activitySetMapper.getGroupBuyingList(userId);
    }

    /**
     * 活动ID获取拼团活动详情
     *
     * @param id
     * @return
     */
    @Override
    public GroupBuying groupBuyById(String id) {
        appletMapper.updateGroupBuyerByTime();
        return activitySetMapper.getGroupBuying(id);
    }

    /**
     * 一键开团
     *
     * @param openId
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String startGroup(String openId, String id) {
        GroupBuying groupBuying = activitySetMapper.getGroupBuying(id);
        if (groupBuying == null) {
            return null;
        }
        GroupBuyer groupBuyer = new GroupBuyer();
        groupBuyer.setId(StringUtils.getUuid());
        groupBuyer.setGroupId(id);
        groupBuyer.setBuyerId(openId);
        groupBuyer.setUsers(openId);
        groupBuyer.setNumber(groupBuying.getGroupNumber() - 1);
        groupBuyer.setEndTime(groupBuying.getGroupTime());
        appletMapper.addGroupBuyer(groupBuyer);
        return groupBuyer.getId();
    }

    /**
     * 活动ID 获取开团信息
     *
     * @param id
     * @return
     */
    @Override
    public GroupBuyer getGroupBuyer(String id) {
        return appletMapper.getGroupBuyer(id);
    }

    /**
     * 参团
     *
     * @param openId
     * @param id
     */
    @Override
    public void joinGroup(String openId, String id) {
        appletMapper.joinGroup(openId, id);
        appletMapper.updateGroupBuyerByNumber();
        GroupBuyer groupBuyer = appletMapper.getGroupBuyer(id);
        GroupBuying groupBuying = activitySetMapper.getGroupBuying(groupBuyer.getGroupId());
        String[] openIds = groupBuyer.getUsers().split(";");
        for (int i = 0; i < openIds.length; i++) {
            CouponBuyer couponBuyer = new CouponBuyer();
            couponBuyer.setId(StringUtils.getUuid());
            couponBuyer.setActivityType(Constants.GROUP);
            couponBuyer.setActivityId(id);
            couponBuyer.setBuyerId(openIds[i]);
            couponBuyer.setUserId(groupBuying.getUserId());
            couponBuyer.setCreateTime(new Timestamp(System.currentTimeMillis()));
            appletMapper.addCouponBuyer(couponBuyer);
        }
    }

    /**
     * 检查当前人是否参与拼团活动
     *
     * @param openId
     * @param id
     * @return
     */
    @Override
    public boolean checkGroup(String openId, String id) {

        GroupBuyer groupBuyer = appletMapper.getGroupBuyer(id);
        //进行中
        if (groupBuyer.getState().equals(Constants.ZERO)) {
            return groupBuyer.getUsers().contains(openId);
        }

        return false;
    }

    /**
     * 查看拼团券
     *
     * @param openId
     * @param id
     * @return
     */
    @Override
    public CouponBuyer lookGroupCoupon(String openId, String id) {
        return appletMapper.lookGroupCoupon(openId, id);
    }

    /**
     * 商家ID获取砍价信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<BargainingSet> bargain(String userId) {
        appletMapper.updateBargainBuyerByTime();
        return activitySetMapper.getBargainingSetList(userId);
    }

    /**
     * 商家ID获取砍价信息
     *
     * @param id
     * @return
     */
    @Override
    public BargainingSet bargainById(String id) {
        appletMapper.updateBargainBuyerByTime();
        return activitySetMapper.getBargainingSet(id);
    }

    /**
     * 发起砍价
     *
     * @param openId
     * @param id
     * @return
     */
    @Override
    public String startBargain(String openId, String id) {
        BargainingSet bargainingSet = activitySetMapper.getBargainingSet(id);
        BargainBuyer bargainBuyer = new BargainBuyer();
        bargainBuyer.setId(StringUtils.getUuid());
        bargainBuyer.setBargainId(id);
        bargainBuyer.setBargainId(openId);
        bargainBuyer.setUsers(openId);
        bargainBuyer.setNumber(bargainingSet.getBargainingFrequency() - 1);
        bargainBuyer.setEndTime(bargainingSet.getEndTime());
        appletMapper.addBargainBuyer(bargainBuyer);

        BargainUser bargainUser = new BargainUser();
        bargainUser.setId(StringUtils.getUuid());
        bargainUser.setBuyerId(openId);
        User user = appletMapper.getUserByOpenId(openId);
        bargainUser.setName(user.getNickName());
        bargainUser.setUrl(user.getAvatarUrl());
        bargainUser.setBargainId(id);
        //差价
        Integer agio = bargainingSet.getOriginalPrice() - bargainingSet.getFloorPrice();
        //砍价比例
        DecimalFormat df = new DecimalFormat("0.00");
        Double proportion = Double.valueOf(df.format((bargainingSet.getBargainingFrequency() - bargainBuyer.getNumber()) / (bargainingSet.getBargainingFrequency() * 1.0)));
        //砍价金额
        int price = (int) (agio * proportion);
        bargainUser.setPrice(price);
        appletMapper.addBargainUser(bargainUser);
        return bargainBuyer.getId();
    }

    /**
     * 帮砍价
     *
     * @param openId
     * @param id
     */
    @Override
    public JSONObject joinBargain(String openId, String id) {
        appletMapper.joinBargain(openId, id);
        appletMapper.updateBargainBuyerByNumber();
        BargainBuyer bargainBuyer = appletMapper.getBargainBuyer(id);
        BargainingSet bargainingSet = activitySetMapper.getBargainingSet(bargainBuyer.getBargainId());
        User user = appletMapper.getUserByOpenId(openId);
        BargainUser bargainUser = new BargainUser();
        bargainUser.setId(StringUtils.getUuid());
        bargainUser.setBuyerId(openId);
        bargainUser.setName(user.getNickName());
        bargainUser.setUrl(user.getAvatarUrl());
        bargainUser.setBargainId(id);

        //是否结束
        boolean result = false;
        //差价
        Integer agio = bargainingSet.getOriginalPrice() - bargainingSet.getFloorPrice();
        //砍价金额
        int price;
        if (bargainingSet.getBargainingFrequency() - bargainBuyer.getNumber() != 1) {
            //砍价比例
            DecimalFormat df = new DecimalFormat("0.00");
            Double proportion = Double.valueOf(df.format((bargainingSet.getBargainingFrequency() - bargainBuyer.getNumber()) / (bargainingSet.getBargainingFrequency() * 1.0)));
            //砍价金额
            price = (int) (agio * proportion);
        } else {
            //最后一人 砍所有
            List<BargainUser> bargainUserList = appletMapper.getBargainUserListByBargainId(bargainBuyer.getBargainId());
            //砍价总数
            int count = 0;
            for (BargainUser bu : bargainUserList) {
                count = count + bu.getPrice();
            }
            price = agio - count;
            result = true;
        }
        bargainUser.setPrice(price);
        appletMapper.addBargainUser(bargainUser);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("price", price);
        jsonObject.put("result", result);
        return jsonObject;
    }

    /**
     * 砍价活动ID获取助力人
     *
     * @param bargainId
     * @return
     */
    @Override
    public JSONObject bargainUserListByBargainId(String bargainId) {
        JSONObject jsonObject = new JSONObject();
        List<BargainUser> bargainUserList = appletMapper.getBargainUserListByBargainId(bargainId);
        //砍价总数
        int count = 0;
        for (BargainUser bu : bargainUserList) {
            count = count + bu.getPrice();
        }
        //差价
        BargainingSet bargainingSet = activitySetMapper.getBargainingSet(bargainId);
        Integer agio = bargainingSet.getOriginalPrice() - bargainingSet.getFloorPrice();
        int price = agio - count;
        jsonObject.put("list", bargainUserList);
        //距离最低价剩余
        jsonObject.put("price", price);
        return jsonObject;
    }

    /**
     * 商家ID获取裂变会员活动详情
     *
     * @param userId
     * @return
     */
    @Override
    public JSONObject fission(String userId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fission", activitySetMapper.getFissionSet(userId));
        jsonObject.put("detail", activitySetMapper.getFissionSetDetail(userId));
        return jsonObject;
    }

    /**
     * 添加会员
     *
     * @param member
     * @return
     */
    @Override
    public int member(Member member) {
        member.setId(StringUtils.getUuid());
        User user = userMapper.selectBySkey(member.getSkey());
        member.setNickName(user.getNickName());
        member.setUrl(user.getAvatarUrl());
        member.setGender(user.getGender());
        member.setPhone(user.getPhone());

        return appletMapper.addMember(member);
    }

    /**
     * 获取会员信息
     *
     * @param userId
     * @param skey
     * @return
     */
    @Override
    public Member getMember(String userId, String openId) {
        return appletMapper.getMember(userId, openId);
    }

    /**
     * 充值
     *
     * @param id
     * @param price
     * @return
     */
    @Override
    public void price(String id, int price) {

        //元 转成 分
        price = price * 100;
        appletMapper.price(id, price);
        MemberConsume memberConsume = new MemberConsume();
        memberConsume.setConsume(price);
        memberConsume.setMemberId(id);
        appletMapper.addMemberConsume(memberConsume);
    }

    /**
     * 消费记录
     *
     * @param id
     * @return
     */
    @Override
    public List<MemberConsume> getConsume(String id) {
        return appletMapper.getConsume(id);
    }

    /**
     * 邀请好友消费记录
     *
     * @param id
     * @param openId
     * @return
     */
    @Override
    public List<CouponUser> getFriendConsume(String userId, String openId) {
        return appletMapper.getFriendConsume(userId, openId);
    }


}
