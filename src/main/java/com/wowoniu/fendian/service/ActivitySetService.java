package com.wowoniu.fendian.service;

import com.alibaba.fastjson.JSONArray;
import com.wowoniu.fendian.model.*;
import com.wowoniu.fendian.utils.Result;

import java.util.List;

/**
 * 活动设置接口
 *
 * @author yuany
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
     * @param fissionSet 裂变实体
     * @return
     */
    boolean addOrUpdateFission(FissionSet fissionSet, String userId);

    /**
     * 新增/更新裂变详情
     *
     * @param fissionSetDetail 裂变详情实体
     * @return
     */
    boolean addOrUpdateFissionDetail(FissionSetDetail fissionSetDetail);

    /**
     * 删除裂变详情
     *
     * @param id        裂变详情ID
     * @param fissionId 裂变ID
     * @return
     */
    boolean deleteFissionDetail(String id, String fissionId);

    /**
     * 商家ID获取返利及详情
     *
     * @param userId 商家ID
     * @param state  状态
     * @return
     */
    Result getRebate(String userId, String state);

    /**
     * 新增/更新返利
     *
     * @param rebateSet 返利实体
     * @return
     */
    boolean addOrUpdateRebate(RebateSet rebateSet, String userId);

    /**
     * 新增/更新返利详情
     *
     * @param rebateSetDetail 返利详情实体
     * @return
     */
    boolean addOrUpdateRebateSetDetail(RebateSetDetail rebateSetDetail);

    /**
     * 删除返利详情
     *
     * @param id       返利详情ID
     * @param rebateId 返利ID
     * @return
     */
    boolean deleteRebateDetail(String id, String rebateId);

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
    boolean deleteDistributionCoupon(String id);

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
     * @param state
     * @param time
     * @param sales
     * @return
     */
    List<Wares> getWaresList(String userId, String state, String time, String sales, String sortDetailId);

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
     * @param waresSpec
     * @param waresSpecDetailList
     * @return
     */
    boolean setWaresSpecAndDetail(WaresSpec waresSpec, List<WaresSpecDetail> waresSpecDetailList);

}

