package com.wowoniu.fendian.service;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.model.*;

/**
 * 活动设置接口
 *
 * @author yuany
 * @date 2020-07-08
 */
public interface ActivitySetService {

    /**
     * 商家ID获取裂变及详情
     *
     * @param userId 商家ID
     * @return
     */
    JSONObject getFissionAndDetail(String userId);

    /**
     * 新增/更新裂变
     *
     * @param fissionSet 裂变实体
     * @return
     */
    boolean addOrUpdateFission(FissionSet fissionSet);

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
     * @return
     */
    JSONObject getRebateAndDetail(String userId);

    /**
     * 新增/更新返利
     *
     * @param rebateSet 返利实体
     * @return
     */
    boolean addOrUpdateRebate(RebateSet rebateSet);

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
     * @return
     */
    JSONObject getDistributionAndDetail(String userId);

    /**
     * 新增/更新分销
     *
     * @param distributionSet 分销实体
     * @return
     */
    boolean addOrUpdateDistribution(DistributionSet distributionSet);

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
     * @param id             分销优惠券ID
     * @return
     */
    boolean deleteDistributionCoupon(String id);
}
