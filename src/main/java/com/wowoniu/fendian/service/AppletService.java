package com.wowoniu.fendian.service;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.model.*;
import com.wowoniu.fendian.utils.PageUtil;

import java.util.List;
import java.util.Map;

/**
 * 小程序Sevcie
 *
 * @author yuay
 * @date 2020-08-14
 */
public interface AppletService {

    /**
     * 商铺分页条件查询
     *
     * @param map 参数
     * @return
     */
    PageUtil<UseUser> getSearchShops(Map<String, Object> map);

    /**
     * 商家ID获取店铺信息
     *
     * @param id
     * @return
     */
    UseUser getUseUserById(String id, String openId);

    /**
     * 商家ID获取商品分类
     *
     * @param useUserId
     * @return
     */
    List<WaresSortDetail> getSortByUseUserId(String useUserId);

    /**
     * 商家商品分页列表
     *
     * @param map 参数
     * @return
     */
    PageUtil<Wares> getGoodsPage(Map<String, Object> map);

    /**
     * 商品ID获取信息
     *
     * @param waresId
     * @return
     */
    Wares getWaresById(String waresId);

    /**
     * 商品ID获取规格
     *
     * @param waresId
     * @return
     */
    JSONObject getWaresSpec(String waresId);

    /**
     * 购物车添加
     *
     * @param waresCart
     * @return
     */
    Boolean setGoodsCart(WaresCart waresCart);

    /**
     * 买家ID获取购物车列表
     *
     * @param buyerId
     * @return
     */
    List<WaresCart> getGoodsCartById(String buyerId, String userId);

    /**
     * 订单结算
     *
     * @param waresOrder
     * @return
     */
    String settlementOrder(WaresOrder waresOrder);

    /**
     * 收货地址列表
     *
     * @param buyerId
     * @return
     */
    List<ShippingAddress> getShippingAddressList(String buyerId);

    /**
     * 收货地址新增
     *
     * @param shippingAddress
     * @return
     */
    boolean setShippingAddress(ShippingAddress shippingAddress);

    /**
     * 当前店铺的订单列表
     *
     * @param buyerId
     * @param userId
     * @return
     */
    JSONObject getWaresOrderList(String buyerId, String userId);

    /**
     * 订单ID获取订单明细
     *
     * @param id
     * @return
     */
    JSONObject getWaresOrderById(String id);

    /**
     * 订单ID获取取货码
     *
     * @param id
     * @return
     */
    String getTakeCodeById(String id);

    /**
     * 订单状态更新
     *
     * @param id
     * @return
     */
    boolean updateOrderState(String id, String state, String courierNumber);

    /**
     * 买家优惠券列表
     *
     * @param id
     * @return
     */
    List<CouponBuyer> getCouponBuyerList(String id,String state);

    /**
     * 优惠券详情
     *
     * @param id
     * @return
     */
    CouponBuyer getCouponBuyerById(String id);

    /**
     * 参与的拼团活动
     *
     * @param buyerId
     * @param state
     * @return
     */
    List<GroupBuyer> groupParticipate(String buyerId, int state);

    /**
     * 我的奖券
     *
     * @param buyerId
     * @return
     */
    List<LuckBuyer> luckWinning(String buyerId, int state);

    /**
     * 券详情
     *
     * @param id
     * @return
     */
    LuckBuyer luckWinningDetail(String id);

    /**
     * 参与的砍价活动
     *
     * @param buyerId
     * @param state
     * @return
     */
    List<BargainBuyer> bargainParticipate(String buyerId, int state);

    /**
     * ID获取用户信息
     *
     * @param openId
     * @return
     */
    User getUserByOpenId(String openId);

    /**
     * 当前用户所有订单
     *
     * @param openId
     * @return
     */
    List<JSONObject> getWaresOrderAll(String openId);

    /**
     * ID获取订单详情
     *
     * @param id
     * @return
     */
    WaresOrder getOrderById(String id);

    /**
     * 浏览过的店铺
     *
     * @param openId
     * @return
     */
    List<ShopRecord> getShopRecordList(String openId);

    /**
     * 商家ID获取推荐好友活动
     *
     * @param id
     * @return
     */
    RecommendSet getRecommend(String id);

    List<RecommendSet> getRecommendList(String userId);

    /**
     * 添加推荐优惠券
     *
     * @return
     */
    String addCounpon(String id, String openId, String openId1);

    /**
     * 砸金蛋 / 幸运大抽奖
     *
     * @param userId
     * @param type
     * @return
     */
    JSONObject lottery(String userId, String type);

    /**
     * 参与抽奖人添加
     *
     * @param id
     * @param openId
     */
    void addLuckUser(String id, String openId);

    /**
     * 查看中奖券
     *
     * @param id
     * @return
     */
    String checkLuckCoupon(String id, String openId);

    /**
     * 活动ID获取参与人
     *
     * @param id
     * @return
     */
    JSONObject luckUserList(String id);

    /**
     * 商家ID获取秒杀活动列表
     *
     * @param userId
     * @return
     */
    List<SeckillSet> spike(String userId);

    /**
     * 活动ID获取秒杀活动详情
     *
     * @param id
     * @return
     */
    SeckillSet spikeById(String id);

    /**
     * 查看券
     *
     * @param id
     * @return
     */
    CouponBuyer couponById(String id);

    /**
     * 获取优惠券
     *
     * @param id
     * @param openId
     * @return
     */
    String getSpike(String id, String openId);

    /**
     * 获取ID获取领取人记录
     *
     * @param map
     * @return
     */
    PageUtil<CouponUser> couponUser(Map<String,Object> map);

    /**
     * 优惠券信息
     *
     * @param id
     * @return
     */
    CouponSet couponInfo(String id);

    /**
     * 领取优惠券
     *
     * @param id
     * @param openId
     * @return
     */
    String getCoupon(String id, String openId);

    /**
     * 商家ID获取拼团活动
     *
     * @param userId
     * @return
     */
    List<GroupBuying> groupBuy(String userId);

    /**
     * 活动ID获取拼团活动详情
     *
     * @param id
     * @return
     */
    GroupBuying groupBuyById(String id);

    /**
     * 一键开团
     *
     * @param openId
     * @return
     */
    String startGroup(String openId, String id);

    /**
     * 活动ID获取开团信息
     *
     * @param id
     * @return
     */
    GroupBuyer getGroupBuyer(String id);

    /**
     * 参团
     *
     * @param openId
     * @param id
     */
    void joinGroup(String openId, String id);

    /**
     * 检查当前人是否参与拼团活动
     *
     * @param openId
     * @param id
     * @return
     */
    boolean checkGroup(String openId, String id);

    /**
     * 拼团券
     *
     * @param openId
     * @param id
     * @return
     */
    CouponBuyer lookGroupCoupon(String openId, String id);

    /**
     * 商家ID获取砍价活动信息
     *
     * @param userId
     * @return
     */
    List<BargainingSet> bargain(String userId);

    /**
     * 砍价ID获取信息
     *
     * @param id
     * @return
     */
    BargainingSet bargainById(String id);

    /**
     * 发起砍价
     *
     * @param openId
     * @param id
     * @return
     */
    String startBargain(String openId, String id);

    /**
     * 帮砍价
     *
     * @param openId
     * @param id
     */
    JSONObject joinBargain(String openId, String id);

    /**
     * 砍价活动ID获取助力人
     *
     * @param bargainId
     * @return
     */
    JSONObject bargainUserListByBargainId(String bargainId);

    /**
     * 商家ID获取裂变活动详情
     *
     * @param userId
     * @return
     */
    JSONObject fission(String userId);

    /**
     * 添加会员
     *
     * @param member
     * @return
     */
    int member(Member member);

    /**
     * 获取会员信息
     *
     * @param userId
     * @param openId
     * @return
     */
    Member getMember(String userId, String openId);

    /**
     * 充值
     *
     * @param id
     * @param price
     * @return
     */
    void price(String id, int price);

    List<MemberConsume> getConsume(String id);

    /**
     * 好友到店消费记录
     *
     * @param id
     * @param openId
     * @return
     */
    List<CouponUser> getFriendConsume(String userId, String openId);

}
