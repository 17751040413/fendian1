package com.wowoniu.fendian.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.model.*;
import com.wowoniu.fendian.utils.PageUtil;
import com.wowoniu.fendian.utils.Result;

import java.util.List;
import java.util.Map;

/**
 * 活动设置接口
 *
 * @author
 * @date 2020-07-08
 */
public interface ActivitySetService {

    /**
     * 商家ID获取裂变
     *
     * @param userId
     * @param state
     * @return
     */
    Result getFissionSet(String userId, String state);

    /**
     * 新增/更新裂变
     *
     * @param param 裂变实体及详情
     * @return
     */
    boolean addOrUpdateFission(JSONObject param, String userId);


    /**
     * 商家ID获取返利及详情
     *
     * @param userId 商家ID
     * @param state  状态
     * @return
     */
    Result getRebateSet(String userId, String state);

    /**
     * 新增/更新返利
     *
     * @param param 返利实体
     * @return
     */
    boolean addOrUpdateRebate(JSONObject param, String userId);

    PageUtil<RebateRecord> getRebateRecord(Map<String, Object> map);

    PageUtil<DistributionRatioRecord> getDistributionRatioRecord(Map<String, Object> map);

    PageUtil<DistributionUser> getDistributionUser(Map<String, Object> map);

    /**
     * 商家ID获取分销及分销优惠券
     *
     * @param userId 商家ID
     * @param state
     * @return
     */
    Result getDistribution(String userId, String state);

    /**
     * 新增/更新分销
     *
     * @param distributionSet 分销实体
     * @return
     */
    boolean addOrUpdateDistribution(DistributionSet distributionSet, String userId);

    /**
     * 新增/更新返利详情
     *
     * @param distributionCoupon 返利详情实体
     * @return
     */
    boolean addOrUpdateDistributionCoupon(DistributionCoupon distributionCoupon);

    /**
     * 删除分销优惠券
     *
     * @param id 分销优惠券ID
     * @return
     */
    boolean updateDistributionCouponEndTime(String id);

    /**
     * 商家ID获取商城设置
     *
     * @param userId 商家ID
     * @param state
     * @return
     */
    Result getShoppingMallSet(String userId, String state);

    /**
     * 新增/更新商城设置
     *
     * @param shoppingMallSet
     * @return
     */
    boolean addOrUpdateShoppingMall(ShoppingMallSet shoppingMallSet, String userId);

    /**
     * 状态获取订单列表
     *
     * @param userId
     * @param state
     * @return
     */
    List<WaresOrder> getWaresOrderList(String userId, String state);

    /**
     * 商品分类启用/禁用 及获取分类列表
     *
     * @param userId
     * @param state
     * @return
     */
    Result getWaresShortSet(String userId, String state);

    /**
     * 分类详情ID获取商品分类
     *
     * @param id
     * @return
     */
    WaresSortDetail getWaresSortDetail(String id);

    /**
     * 商家ID获取商品分类列表
     *
     * @param userId
     * @return
     */
    List<WaresSortDetail> getWaresSortDetailListByUserId(String userId);

    /**
     * 商品分类新增/修改
     *
     * @param waresSortDetail
     * @return
     */
    boolean setWaresSortDetail(WaresSortDetail waresSortDetail);

    /**
     * 商品分类置顶设置
     *
     * @param id
     * @return
     */
    List<WaresSortDetail> setWaresSortDetailTop(String id);


    /**
     * 商品分类置顶移动
     *
     * @param id
     * @param move
     * @return
     */
    boolean setWaresSortDetailTopMove(String id, Integer move);

    /**
     * 商品列表条件查询
     *
     * @param userId
     * @param onShelf
     * @param time
     * @param sales
     * @return
     */
    List<Wares> getWaresList(String userId, String onShelf, String time, String sales, String sortId);

    /**
     * 发布商品新增
     *
     * @param wares
     * @return
     */
    boolean setWares(Wares wares, String userId);

    /**
     * 商品ID获取规格及规格详情
     *
     * @param waresId
     * @return
     */
    JSONArray getWaresSpecAndDetail(String waresId);

    /**
     * 商品规格及详情新增/修改
     *
     * @param param
     * @return
     */
    boolean setWaresSpecAndDetail(JSONObject param);

    /**
     * 删除商品规格及详情
     *
     * @param id
     * @return
     */
    boolean delWaresSpec(String id);

    /**
     * 删除商品规格详情
     *
     * @param id
     * @return
     */
    int delWaresSpecDetail(String id);

    /**
     * 订单发货确认
     *
     * @param id
     * @return
     */
    int sendWaresSure(String id, String code);

    /**
     * 订单取货确认
     *
     * @param id
     * @return
     */
    boolean takeWaresSure(String id, String code, String userId);

    /**
     * 抽奖ID获取抽奖设置及详情
     *
     * @param id
     * @return
     */
    JSONObject getLuckDrawSetAndDetail(String id);

    /**
     * 抽奖及详情新增/修改
     *
     * @param param
     * @param userId
     * @return
     */
    boolean setLuckDrawSetAndDetail(JSONObject param, String userId);

    /**
     * ID获取优惠券设置
     *
     * @return
     */
    CouponSet getCouponSet(String id);

    /**
     * 优惠券设置新增/修改
     *
     * @param couponSet
     * @return
     */
    boolean setCouponSet(CouponSet couponSet, String userId);

    /**
     * ID获取拼团设置
     *
     * @param id
     * @return
     */
    GroupBuying getGroupBuying(String id);

    /**
     * 拼团设置 新增/删除
     *
     * @param groupBuying
     * @param userId
     * @return
     */
    boolean setGroupBuying(GroupBuying groupBuying, String userId);

    /**
     * 推荐ID获取推荐信息
     *
     * @param id
     * @return
     */
    RecommendSet getRecommendSet(String id);

    /**
     * 推荐新增/修改
     *
     * @param recommendSet
     * @param userId
     * @return
     */
    boolean setRecommendSet(RecommendSet recommendSet, String userId);

    /**
     * 砍价ID获取设置信息
     *
     * @param id
     * @return
     */
    BargainingSet getBargainingSet(String id);

    /**
     * 砍价设置新增/修改
     *
     * @param bargainingSet
     * @param userId
     * @return
     */
    boolean setBargainingSet(BargainingSet bargainingSet, String userId);

    /**
     * 朋友圈模板列表
     *
     * @return
     */
    PageUtil<ShareFriends> getShareFriendList(Map<String, Object> map);

    /**
     * 朋友圈分享ID获取设置信息
     *
     * @param id
     * @return
     */
    ShareFriends getShareFriends(String id);

    /**
     * 朋友圈分享新增/修改
     *
     * @param shareFriends
     * @param userId
     * @return
     */
    boolean setShareFriends(ShareFriends shareFriends, String userId);

    /**
     * 朋友圈模板ID删除
     *
     * @param id
     * @return
     */
    int delShareFriends(String id);

    /**
     * 秒杀ID获取设置信息
     *
     * @param id
     * @return
     */
    SeckillSet getSeckillSet(String id);

    /**
     * 秒杀设置新增/修改
     *
     * @param seckillSet
     * @param userId
     * @return
     */
    boolean setSeckillSet(SeckillSet seckillSet, String userId);

    /**
     * 红包裂变ID获取设置信息
     *
     * @param id
     * @return
     */
    RedenvelopesSet getRedenvelopesSet(String id);

    /**
     * 红包裂变设置新增/修改
     *
     * @param redenvelopesSet
     * @param userId
     * @return
     */
    boolean setRedenvelopesSet(RedenvelopesSet redenvelopesSet, String userId);

    /**
     * 活动浏览次数增加
     *
     * @param id
     * @param type
     * @return
     */
    boolean addBrowse(String id, String type);

    /**
     * 活动领券次数增加
     *
     * @param id
     * @param type
     * @return
     */
    boolean addCoupon(String id, String type);

    /**
     * 活动用券次数增加
     *
     * @param id
     * @param type
     * @return
     */
    boolean addUse(String id, String type);

}

