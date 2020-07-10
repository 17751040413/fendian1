package com.wowoniu.fendian.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.config.Constants;
import com.wowoniu.fendian.mapper.ActivitySetMapper;
import com.wowoniu.fendian.model.*;
import com.wowoniu.fendian.service.ActivitySetService;
import com.wowoniu.fendian.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动设置接口实现
 *
 * @author yuany
 * @date 2020-07-08
 */
@Service("activitySetService")
public class ActivitySetServiceImpl implements ActivitySetService {

    @Autowired
    private ActivitySetMapper activitySetMapper;

    /**
     * 商家ID获取裂变及详情
     *
     * @param userId 商家ID
     * @return
     */
    @Override
    public JSONObject getFissionAndDetail(String userId) {
        JSONObject jsonObject = new JSONObject();
        FissionSet fissionSet = activitySetMapper.getFissionSet(userId);
        if (fissionSet == null || Constants.NO.equals(fissionSet.getState())) {
            return null;
        }
        jsonObject.put("fission", fissionSet);
        jsonObject.put("detail", activitySetMapper.getFissionSetDetailList(fissionSet.getId()));
        return jsonObject;
    }

    /**
     * 新增/更新裂变
     *
     * @param fissionSet 裂变实体
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addOrUpdateFission(FissionSet fissionSet) {
        if (fissionSet == null) {
            return false;
        }
        //新增
        if (fissionSet.getId() == null) {
            fissionSet.setId(StringUtils.getUuid());
            activitySetMapper.addFissionSet(fissionSet);
        } else {
            //更新
            activitySetMapper.updateFissionSet(fissionSet);
        }
        return true;
    }

    /**
     * 新增/更新裂变详情
     *
     * @param fissionSetDetail 裂变详情实体
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addOrUpdateFissionDetail(FissionSetDetail fissionSetDetail) {
        if (fissionSetDetail == null) {
            return false;
        }
        //新增
        if (fissionSetDetail.getId() == null) {
            fissionSetDetail.setId(StringUtils.getUuid());
            fissionSetDetail.setLevel(activitySetMapper.getFissionSetDetailList(fissionSetDetail.getFissionId()).size() + 1);
            activitySetMapper.addFissionSetDetail(fissionSetDetail);
        } else {
            //更新
            List<FissionSetDetail> fissionSetDetailList = new ArrayList<>();
            fissionSetDetailList.add(fissionSetDetail);
            activitySetMapper.updateFissionSetDetailList(fissionSetDetailList);
        }
        return true;
    }

    /**
     * 删除裂变详情
     *
     * @param id        裂变详情ID
     * @param fissionId 裂变ID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteFissionDetail(String id, String fissionId) {
        if (StringUtils.isEmpity(id)) {
            return false;
        }
        activitySetMapper.deleteFissionSetDetail(id);
        //获取裂变集合 重新排序
        List<FissionSetDetail> fissionSetDetailList = activitySetMapper.getFissionSetDetailList(fissionId);
        if (CollectionUtils.isEmpty(fissionSetDetailList)) {
            return true;
        }
        int level = 0;
        for (FissionSetDetail fissionSetDetail : fissionSetDetailList) {
            level++;
            fissionSetDetail.setLevel(level);
        }
        activitySetMapper.updateFissionSetDetailList(fissionSetDetailList);

        return true;
    }

    /**
     * 商家ID获取返利及详情
     *
     * @param userId 商家ID
     * @return
     */
    @Override
    public JSONObject getRebateAndDetail(String userId) {
        JSONObject jsonObject = new JSONObject();
        RebateSet rebateSet = activitySetMapper.getRebateSet(userId);
        if (rebateSet == null || Constants.NO.equals(rebateSet.getState())) {
            return null;
        }
        jsonObject.put("rebate", rebateSet);
        jsonObject.put("detail", activitySetMapper.getRebateSetDetailList(rebateSet.getId()));
        return jsonObject;
    }

    /**
     * 新增/更新裂变
     *
     * @param rebateSet 返利实体
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addOrUpdateRebate(RebateSet rebateSet) {
        if (rebateSet == null) {
            return false;
        }
        //新增
        if (rebateSet.getId() == null) {
            rebateSet.setId(StringUtils.getUuid());
            activitySetMapper.addRebateSet(rebateSet);
        } else {
            //更新
            activitySetMapper.updateRebateSet(rebateSet);
        }
        return true;
    }

    /**
     * 新增/更新返利详情
     *
     * @param rebateSetDetail 返利详情实体
     * @return
     */
    @Override
    public boolean addOrUpdateRebateSetDetail(RebateSetDetail rebateSetDetail) {
        if (rebateSetDetail == null) {
            return false;
        }
        //新增
        if (rebateSetDetail.getId() == null) {
            rebateSetDetail.setId(StringUtils.getUuid());
            rebateSetDetail.setLevel(activitySetMapper.getRebateSetDetailList(rebateSetDetail.getRebateId()).size() + 1);
            activitySetMapper.addRebateSetDetail(rebateSetDetail);
        } else {
            //更新
            List<RebateSetDetail> rebateSetDetailList = new ArrayList<>();
            rebateSetDetailList.add(rebateSetDetail);
            activitySetMapper.updateRebateSetDetailList(rebateSetDetailList);
        }
        return true;
    }

    /**
     * 删除返利详情
     *
     * @param id       返利详情ID
     * @param rebateId 返利ID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteRebateDetail(String id, String rebateId) {
        if (StringUtils.isEmpity(id)) {
            return false;
        }
        activitySetMapper.deleteRebateSetDetail(id);
        //获取裂变集合 重新排序
        List<RebateSetDetail> rebateSetDetailList = activitySetMapper.getRebateSetDetailList(rebateId);
        if (CollectionUtils.isEmpty(rebateSetDetailList)) {
            return true;
        }
        int level = 0;
        for (RebateSetDetail rebateSetDetail : rebateSetDetailList) {
            level++;
            rebateSetDetail.setLevel(level);
        }
        activitySetMapper.updateRebateSetDetailList(rebateSetDetailList);

        return true;
    }

    /**
     * 商家ID获取分销及分销优惠券
     *
     * @param userId 商家ID
     * @return
     */
    @Override
    public JSONObject getDistributionAndDetail(String userId) {
        JSONObject jsonObject = new JSONObject();
        DistributionSet distributionSet = activitySetMapper.getDistributionSet(userId);
        if (distributionSet == null || Constants.NO.equals(distributionSet.getState())) {
            return null;
        }
        jsonObject.put("rebate", distributionSet);
        jsonObject.put("detail", activitySetMapper.getDistributionCouponList(distributionSet.getId()));
        return jsonObject;
    }

    /**
     * 新增/更新分销
     *
     * @param distributionSet 分销实体
     * @return
     */
    @Override
    public boolean addOrUpdateDistribution(DistributionSet distributionSet) {
        if (distributionSet == null) {
            return false;
        }
        //新增
        if (distributionSet.getId() == null) {
            distributionSet.setId(StringUtils.getUuid());
            activitySetMapper.addDistributionSet(distributionSet);
        } else {
            //更新
            activitySetMapper.updateDistributionSet(distributionSet);
        }
        return true;
    }

    /**
     * 新增/更新返利详情
     *
     * @param distributionCoupon 返利详情实体
     * @return
     */
    @Override
    public boolean addOrUpdateDistributionCoupon(DistributionCoupon distributionCoupon) {
        if (distributionCoupon == null) {
            return false;
        }
        //新增
        if (distributionCoupon.getId() == null) {
            distributionCoupon.setId(StringUtils.getUuid());
            activitySetMapper.addDistributionCoupon(distributionCoupon);
        } else {
            //更新
            List<DistributionCoupon> distributionCouponList = new ArrayList<>();
            distributionCouponList.add(distributionCoupon);
            activitySetMapper.updateDistributionCouponList(distributionCouponList);
        }
        return true;
    }

    /**
     * 删除分销优惠券
     *
     * @param id             分销优惠券ID
     * @return
     */
    @Override
    public boolean deleteDistributionCoupon(String id) {
        if (StringUtils.isEmpity(id)) {
            return false;
        }
        activitySetMapper.deleteDistributionCoupon(id);

        return true;
    }


}
