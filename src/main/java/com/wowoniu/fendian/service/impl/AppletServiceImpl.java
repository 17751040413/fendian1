package com.wowoniu.fendian.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.config.Constants;
import com.wowoniu.fendian.mapper.ActivitySetMapper;
import com.wowoniu.fendian.mapper.AppletMapper;
import com.wowoniu.fendian.model.*;
import com.wowoniu.fendian.service.AppletService;
import com.wowoniu.fendian.utils.PageUtil;
import com.wowoniu.fendian.utils.StringUtils;
import com.wowoniu.fendian.utils.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 小程序Service实现
 *
 * @author yuany
 * @date 2020-08-19
 */
@Service
public class AppletServiceImpl implements AppletService {

    @Autowired
    private AppletMapper appletMapper;

    @Autowired
    private ActivitySetMapper activitySetMapper;

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
    public void addCounpon(String id, String openId, String openId1) {
        //获取活动
        RecommendSet recommendSet = activitySetMapper.getRecommendSet(id);
        CouponBuyer couponBuyer = new CouponBuyer();
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

    }
}
