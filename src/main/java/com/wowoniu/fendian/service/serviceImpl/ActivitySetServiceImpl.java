package com.wowoniu.fendian.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.config.Constants;
import com.wowoniu.fendian.mapper.ActivitySetMapper;
import com.wowoniu.fendian.model.*;
import com.wowoniu.fendian.service.ActivitySetService;
import com.wowoniu.fendian.utils.Result;
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
     * 商家ID获取裂变
     *
     * @param userId
     * @param state
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result getFissionSet(String userId, String state) {
        //获取裂变
        FissionSet fissionSet = activitySetMapper.getFissionSet(userId);
        if (fissionSet == null) {
            return new Result(200, true, "无裂变设置", null);
        }
        //设置状态为空 且裂变为禁用状态 直接返回null
        if (StringUtils.isEmpity(state) && Constants.NO.equals(fissionSet.getState())) {
            return new Result(200, true, "禁用状态", null);
        }
        if (!StringUtils.isEmpity(state)) {
            fissionSet.setState(state);
            activitySetMapper.setFissionState(userId, state);
        }
        //禁用
        if (Constants.NO.equals(state)) {
            return new Result(200, true, "禁用成功", null);
        }
        List<FissionSetDetail> fissionSetDetailList = activitySetMapper.getFissionSetDetailList(fissionSet.getId());
        //启用-获取裂变详情并返回
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fission", fissionSet);
        jsonObject.put("detail", fissionSetDetailList);
        if (jsonObject.isEmpty()) {
            return new Result(204, false, "获取成功", jsonObject);
        }
        return new Result(204, false, "获取失败", null);
    }

    /**
     * 新增/更新裂变
     *
     * @param fissionSet 裂变实体
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addOrUpdateFission(FissionSet fissionSet,String userId) {
        if (fissionSet == null) {
            return false;
        }
        //新增
        if (StringUtils.isEmpity(fissionSet.getId())) {
            fissionSet.setUserId(userId);
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
        if (StringUtils.isEmpity(fissionSetDetail.getId())) {
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
     * @param state  状态
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result getRebate(String userId, String state) {
        //获取返利
        RebateSet rebateSet = activitySetMapper.getRebateSet(userId);
        if (rebateSet == null) {
            return new Result(200, true, "无返利设置", null);
        }
        //设置状态为空 且返利为禁用状态 直接返回null
        if (StringUtils.isEmpity(state) && Constants.NO.equals(rebateSet.getState())) {
            return new Result(200, true, "禁用状态", null);
        }
        if (!StringUtils.isEmpity(state)) {
            rebateSet.setState(state);
            activitySetMapper.setRebateState(userId, state);
        }
        //禁用
        if (Constants.NO.equals(state)) {
            return new Result(200, true, "禁用成功", null);
        }
        List<RebateSetDetail> rebateSetDetailList = activitySetMapper.getRebateSetDetailList(rebateSet.getId());
        //启用-获取返利详情并返回
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rebate", rebateSet);
        jsonObject.put("detail", rebateSetDetailList);
        if (jsonObject.isEmpty()) {
            return new Result(204, false, "获取成功", jsonObject);
        }
        return new Result(204, false, "获取失败", null);
    }

    /**
     * 新增/更新返利
     *
     * @param rebateSet 返利实体
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addOrUpdateRebate(RebateSet rebateSet,String userId) {
        if (rebateSet == null) {
            return false;
        }
        //新增
        if (StringUtils.isEmpity(rebateSet.getId())) {
            rebateSet.setUserId(userId);
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
    @Transactional(rollbackFor = Exception.class)
    public boolean addOrUpdateRebateSetDetail(RebateSetDetail rebateSetDetail) {
        if (rebateSetDetail == null) {
            return false;
        }
        //新增
        if (StringUtils.isEmpity(rebateSetDetail.getId())) {
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
     * @param state
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result getDistribution(String userId, String state) {
        //获取分销
        DistributionSet distributionSet = activitySetMapper.getDistributionSet(userId);
        if (distributionSet == null) {
            return new Result(200, true, "无分销设置", null);
        }
        if (!StringUtils.isEmpity(state)) {
            distributionSet.setState(state);
            activitySetMapper.setDistributionSetState(userId, state);
        }
        List<DistributionCoupon> distributionCouponList = activitySetMapper.getDistributionCouponList(distributionSet.getId());
        //启用-获取分销详情并返回
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("distribution", distributionSet);
        jsonObject.put("detail", distributionCouponList);
        if (jsonObject.isEmpty()) {
            return new Result(204, false, "获取成功", jsonObject);
        }
        return new Result(204, false, "获取失败", null);
    }

    /**
     * 新增/更新分销
     *
     * @param distributionSet 分销实体
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addOrUpdateDistribution(DistributionSet distributionSet,String userId) {
        if (distributionSet == null) {
            return false;
        }
        //新增
        if (StringUtils.isEmpity(distributionSet.getId())) {
            distributionSet.setUserId(userId);
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
    @Transactional(rollbackFor = Exception.class)
    public boolean addOrUpdateDistributionCoupon(DistributionCoupon distributionCoupon) {
        if (distributionCoupon == null) {
            return false;
        }
        //新增
        if (StringUtils.isEmpity(distributionCoupon.getId())) {
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
     * @param id 分销优惠券ID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteDistributionCoupon(String id) {
        if (StringUtils.isEmpity(id)) {
            return false;
        }
        activitySetMapper.deleteDistributionCoupon(id);

        return true;
    }

    /**
     * 商家ID获取商城设置
     *
     * @param userId 商家ID
     * @param state
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result getShoppingMallSet(String userId, String state) {

        //获取商城设置
        ShoppingMallSet shoppingMallSet = activitySetMapper.getShoppingMallSet(userId);
        if (shoppingMallSet == null) {
            return new Result(200, true, "无裂变设置", null);
        }
        //设置状态为空 且裂变为禁用状态 直接返回null
        if (StringUtils.isEmpity(state) && Constants.NO.equals(shoppingMallSet.getState())) {
            return new Result(200, true, "禁用状态", null);
        }
        if (!StringUtils.isEmpity(state)) {
            shoppingMallSet.setState(state);
            activitySetMapper.setShoppingMallSetState(userId, state);
        }
        //禁用
        if (Constants.NO.equals(state)) {
            return new Result(200, true, "禁用成功", null);
        }
        //启用-返回商城设置
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shoppingMall", shoppingMallSet);
        if (jsonObject.isEmpty()) {
            return new Result(204, false, "获取成功", jsonObject);
        }
        return new Result(204, false, "获取失败", null);
    }

    /**
     * 新增/更新商城设置
     *
     * @param shoppingMallSet
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addOrUpdateShoppingMall(ShoppingMallSet shoppingMallSet,String userId) {
        if (shoppingMallSet == null) {
            return false;
        }
        //新增
        if (StringUtils.isEmpity(shoppingMallSet.getId())) {
            shoppingMallSet.setUserId(userId);
            shoppingMallSet.setId(StringUtils.getUuid());
            activitySetMapper.addShoppingMallSet(shoppingMallSet);
        } else {
            //更新
            activitySetMapper.updateShoppingMallSet(shoppingMallSet);
        }
        return true;
    }

    /**
     * 状态获取订单列表
     *
     * @param userId
     * @param state
     * @return
     */
    @Override
    public List<WaresOrder> getWaresOrderList(String userId, String state) {

        return activitySetMapper.getWaresOrderList(userId, state);
    }

    /**
     * 商品分类启用/禁用 及获取分类列表
     *
     * @param userId
     * @param state
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result getWaresShortSet(String userId, String state) {
        //获取商城设置
        WaresSortSet waresSortSet = activitySetMapper.getWaresSortSet(userId);
        if (waresSortSet == null) {
            if (StringUtils.isEmpity(state)) {
                return new Result(200, true, "无裂变设置", null);
            } else {
                waresSortSet = new WaresSortSet();
                waresSortSet.setId(StringUtils.getUuid());
                waresSortSet.setState(state);
                waresSortSet.setUserId(userId);
                activitySetMapper.addWaresSortSet(waresSortSet);
            }
        } else {
            waresSortSet.setState(state);
            activitySetMapper.setWaresSortSetState(userId, state);
        }

        //分类列表
        List<WaresSortDetail> waresSortDetailList = activitySetMapper.getWaresSortDetailList(waresSortSet.getId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("waresSortSet", waresSortSet);
        jsonObject.put("detail", waresSortDetailList);
        if (jsonObject.isEmpty()) {
            return new Result(204, false, "获取成功", jsonObject);
        }
        return new Result(204, false, "获取失败", null);
    }

    /**
     * 分类详情ID获取商品分类
     *
     * @param id
     * @return
     */
    @Override
    public WaresSortDetail getWaresSortDetail(String id) {
        return activitySetMapper.getWaresSortDetail(id);
    }

    /**
     * 商家ID获取商品分类列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<WaresSortDetail> getWaresSortDetailListByUserId(String userId) {

        return activitySetMapper.getWaresSortDetailListByUserId(userId, Constants.YES);
    }

    /**
     * 商品分类新增/修改
     *
     * @param waresSortDetail
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean setWaresSortDetail(WaresSortDetail waresSortDetail) {
        if (waresSortDetail == null) {
            return false;
        }
        if (StringUtils.isEmpity(waresSortDetail.getId())) {
            //获取已存在的商品分类
            List<WaresSortDetail> waresSortDetailList = activitySetMapper.getWaresSortDetailList(waresSortDetail.getSordId());
            waresSortDetail.setId(StringUtils.getUuid());
            waresSortDetail.setRow(waresSortDetailList.size() + 1);
            activitySetMapper.addWaresSortDetail(waresSortDetail);
        } else {
            activitySetMapper.updateWaresSortDetail(waresSortDetail);
        }
        return true;
    }

    /**
     * 商品分类置顶设置
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<WaresSortDetail> setWaresSortDetailTop(String id) {
        WaresSortDetail waresSortDetail = activitySetMapper.getWaresSortDetail(id);
        //获取置顶的商品分类
        List<WaresSortDetail> waresSortDetailList = activitySetMapper.getWaresSortDetailListByTop(waresSortDetail.getSordId());

        //重新排序
        int topRow = 0;
        boolean result = true;
        for (WaresSortDetail detail : waresSortDetailList) {
            if (detail.getId().equals(waresSortDetail.getId())) {
                detail.setTopRow(0);
                result = false;
            } else {
                topRow++;
                detail.setTopRow(topRow);
            }
        }
        if (result) {
            waresSortDetail.setTopRow(0);
            waresSortDetailList.add(waresSortDetail);
        }

        //更新并返回列表
        activitySetMapper.updateWaresSortDetailBatch(waresSortDetailList);
        return waresSortDetailList;
    }

    /**
     * 商品分类置顶移动
     *
     * @param id
     * @param move
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean setWaresSortDetailTopMove(String id, Integer move) {
        WaresSortDetail waresSortDetail = activitySetMapper.getWaresSortDetail(id);
        //首位向上移动 则不变
        if (waresSortDetail.getTopRow() == 0 && move.equals(0)) {
            return true;
        }
        WaresSortDetail waresSortDetail1TopRow;
        //向上
        if (move.equals(0)) {
            //获取交换的分类
            waresSortDetail1TopRow = activitySetMapper.getWaresSortDetailByTopRow(waresSortDetail.getTopRow() - 1);
        } else {
            //向下
            waresSortDetail1TopRow = activitySetMapper.getWaresSortDetailByTopRow(waresSortDetail.getTopRow() + 1);
        }
        //交换位置
        List<WaresSortDetail> waresSortDetailList = new ArrayList<>();
        Integer interim = waresSortDetail.getTopRow();
        waresSortDetail.setTopRow(waresSortDetail1TopRow.getTopRow());
        waresSortDetail1TopRow.setTopRow(interim);
        waresSortDetailList.add(waresSortDetail);
        waresSortDetailList.add(waresSortDetail1TopRow);
        activitySetMapper.updateWaresSortDetailBatch(waresSortDetailList);
        return true;
    }

    /**
     * 商品列表条件查询
     *
     * @param userId
     * @param state
     * @param time
     * @param sales
     * @return
     */
    @Override
    public List<Wares> getWaresList(String userId, String state, String time, String sales, String sortDetailId) {

        return activitySetMapper.getWaresList(userId, state, time, sales, sortDetailId);
    }

    /**
     * 发布商品新增
     *
     * @param wares
     * @return
     */
    @Override
    public boolean setWares(Wares wares,String userId) {
        if (wares == null) {
            return false;
        }
        if (StringUtils.isEmpity(wares.getId())) {
            wares.setId(StringUtils.getUuid());
            activitySetMapper.addWares(wares);
        } else {
            activitySetMapper.updateWares(wares);
        }
        return true;
    }


}
