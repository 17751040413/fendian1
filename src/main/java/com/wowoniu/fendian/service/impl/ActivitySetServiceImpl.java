package com.wowoniu.fendian.service.impl;

import com.alibaba.fastjson.JSONArray;
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
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
        //设置状态为空 或 裂变为禁用状态 直接返回null
        if (StringUtils.isEmpity(state) || (state.equals(Constants.NO) && Constants.NO.equals(fissionSet.getState()))) {
            return new Result(200, true, "禁用状态", null);
        }

        fissionSet.setState(state);
        activitySetMapper.setFissionState(userId, state);
        //禁用
        if (Constants.NO.equals(state)) {
            return new Result(200, true, "禁用成功", null);
        }
        List<FissionSetDetail> fissionSetDetailList = activitySetMapper.getFissionSetDetailList(fissionSet.getId());
        //启用-获取裂变详情并返回
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fission", fissionSet);
        jsonObject.put("detail", fissionSetDetailList);
        if (jsonObject == null || jsonObject.size() == 0) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, false, "获取成功", jsonObject);
    }

    /**
     * 新增/更新裂变
     *
     * @param param 裂变实体及详情
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addOrUpdateFission(JSONObject param, String userId) {
        FissionSet fissionSet = JSONObject.parseObject(param.getJSONObject("fissionSet").toJSONString(), FissionSet.class);
        List<FissionSetDetail> fissionSetDetailList = JSONArray.parseArray(param.getJSONArray("detail").toJSONString(), FissionSetDetail.class);
        if (fissionSet == null || CollectionUtils.isEmpty(fissionSetDetailList)) {
            return false;
        }

        //新增
        if (StringUtils.isEmpity(fissionSet.getId())) {
            fissionSet.setUserId(userId);
            fissionSet.setId(StringUtils.getUuid());
            activitySetMapper.addFissionSet(fissionSet);
            for (FissionSetDetail fissionSetDetail : fissionSetDetailList) {
                fissionSetDetail.setId(StringUtils.getUuid());
                fissionSetDetail.setLevel(activitySetMapper.getFissionSetDetailList(fissionSet.getId()).size() + 1);
                fissionSetDetail.setFissionId(fissionSet.getId());
                activitySetMapper.addFissionSetDetail(fissionSetDetail);
            }
        } else {
            //更新
            activitySetMapper.updateFissionSet(fissionSet);
            //新增的详情
            List<FissionSetDetail> adds = new ArrayList<>();
            //删除的详情
            List<FissionSetDetail> deletes = new ArrayList<>();
            //原有详情
            List<FissionSetDetail> olds = activitySetMapper.getFissionSetDetailList(fissionSet.getId());
            for (FissionSetDetail fissionSetDetail : fissionSetDetailList) {
                if (StringUtils.isEmpity(fissionSetDetail.getId())) {
                    fissionSetDetail.setId(StringUtils.getUuid());
                    fissionSetDetail.setFissionId(fissionSet.getId());
                    activitySetMapper.addFissionSetDetail(fissionSetDetail);
                } else {
                    activitySetMapper.updateFissionSetDetail(fissionSetDetail);
                }
            }
            for (FissionSetDetail old : olds) {
                boolean result = true;
                for (FissionSetDetail fissionSetDetail : fissionSetDetailList) {
                    if (old.getId().equals(fissionSetDetail.getId())) {
                        result = false;
                        break;
                    }
                }
                if (result) {
                    activitySetMapper.deleteFissionSetDetail(old.getId());
                }
            }

            //获取裂变集合 重新排序
            List<FissionSetDetail> detailList = activitySetMapper.getFissionSetDetailList(fissionSet.getId());
            if (CollectionUtils.isEmpty(detailList)) {
                return true;
            }
            int level = 0;
            for (FissionSetDetail fissionSetDetail : detailList) {
                level++;
                fissionSetDetail.setLevel(level);
                activitySetMapper.updateFissionSetDetail(fissionSetDetail);
            }

        }

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
    public Result getRebateSet(String userId, String state) {
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
        if (jsonObject == null || jsonObject.size() == 0) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, false, "获取成功", jsonObject);

    }

    /**
     * 新增/更新返利
     *
     * @param param 返利实体及详情
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addOrUpdateRebate(JSONObject param, String userId) {
        RebateSet rebateSet = JSONObject.parseObject(param.getJSONObject("rebateSet").toJSONString(), RebateSet.class);
        List<RebateSetDetail> rebateSetDetailList = JSONArray.parseArray(param.getJSONArray("detail").toJSONString(), RebateSetDetail.class);
        if (rebateSet == null || CollectionUtils.isEmpty(rebateSetDetailList)) {
            return false;
        }

        //新增
        if (StringUtils.isEmpity(rebateSet.getId())) {
            rebateSet.setUserId(userId);
            rebateSet.setId(StringUtils.getUuid());
            activitySetMapper.addRebateSet(rebateSet);
            for (RebateSetDetail rebateSetDetail : rebateSetDetailList) {
                rebateSetDetail.setId(StringUtils.getUuid());
                rebateSetDetail.setLevel(activitySetMapper.getFissionSetDetailList(rebateSet.getId()).size() + 1);
                rebateSetDetail.setRebateId(rebateSet.getId());
                activitySetMapper.addRebateSetDetail(rebateSetDetail);
            }
        } else {
            //更新
            activitySetMapper.updateRebateSet(rebateSet);
            //新增的详情
            List<RebateSetDetail> adds = new ArrayList<>();
            //删除的详情
            List<RebateSetDetail> deletes = new ArrayList<>();
            //原有详情
            List<RebateSetDetail> olds = activitySetMapper.getRebateSetDetailList(rebateSet.getId());
            for (RebateSetDetail rebateSetDetail : rebateSetDetailList) {
                if (StringUtils.isEmpity(rebateSetDetail.getId())) {
                    rebateSetDetail.setId(StringUtils.getUuid());
                    rebateSetDetail.setRebateId(rebateSet.getId());
                    activitySetMapper.addRebateSetDetail(rebateSetDetail);
                } else {
                    activitySetMapper.updateRebateSetDetail(rebateSetDetail);
                }
            }
            for (RebateSetDetail old : olds) {
                boolean result = true;
                for (RebateSetDetail rebateSetDetail : rebateSetDetailList) {
                    if (old.getId().equals(rebateSetDetail.getId())) {
                        result = false;
                        break;
                    }
                }
                if (result) {
                    activitySetMapper.deleteRebateSetDetail(old.getId());
                }
            }

            //返利裂变集合 重新排序
            List<RebateSetDetail> detailList = activitySetMapper.getRebateSetDetailList(rebateSet.getId());
            if (CollectionUtils.isEmpty(detailList)) {
                return true;
            }
            int level = 0;
            for (RebateSetDetail rebateSetDetail : detailList) {
                level++;
                rebateSetDetail.setLevel(level);
                activitySetMapper.updateRebateSetDetail(rebateSetDetail);
            }
        }

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
        if (jsonObject == null || jsonObject.size() == 0) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, false, "获取成功", jsonObject);
    }

    /**
     * 新增/更新分销
     *
     * @param distributionSet 分销实体
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addOrUpdateDistribution(DistributionSet distributionSet, String userId) {
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
            activitySetMapper.updateDistributionCouponList(distributionCoupon);
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
    public boolean updateDistributionCouponEndTime(String id) {
        if (StringUtils.isEmpity(id)) {
            return false;
        }
        activitySetMapper.updateDistributionCouponEndTime(id);

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
        if (StringUtils.isNoneEmpty(state)) {
            shoppingMallSet.setState(state);
            activitySetMapper.setShoppingMallSetState(userId, state);
        }
        //禁用
        if (Constants.NO.equals(state)) {
            return new Result(200, true, "禁用成功", null);
        }
        //启用-返回商城设置
        if (shoppingMallSet == null) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, true, "获取成功", shoppingMallSet);
    }

    /**
     * 新增/更新商城设置
     *
     * @param shoppingMallSet
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addOrUpdateShoppingMall(ShoppingMallSet shoppingMallSet, String userId) {
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
        if (jsonObject == null || jsonObject.size() == 0) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, false, "获取成功", jsonObject);
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

        return activitySetMapper.getWaresSortDetailListByUserId(Constants.YES, "1");
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
            List<WaresSortDetail> waresSortDetailList = activitySetMapper.getWaresSortDetailList(waresSortDetail.getSortId());
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
        List<WaresSortDetail> waresSortDetailList = activitySetMapper.getWaresSortDetailListByTop(waresSortDetail.getSortId());

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
        activitySetMapper.updateWaresSortDetail(waresSortDetail);
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
        activitySetMapper.updateWaresSortDetail(waresSortDetail);
        activitySetMapper.updateWaresSortDetail(waresSortDetail1TopRow);
        return true;
    }

    /**
     * 商品列表条件查询
     *
     * @param userId
     * @param onShelf
     * @param time
     * @param sales
     * @return
     */
    @Override
    public List<Wares> getWaresList(String userId, String onShelf, String time, String sales, String sortId) {

        return activitySetMapper.getWaresList(userId, onShelf, time, sales, sortId);
    }

    /**
     * 发布商品新增
     *
     * @param wares
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean setWares(Wares wares, String userId) {
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

    /**
     * 商品ID获取规格及规格详情
     *
     * @param waresId
     * @return
     */
    @Override
    public JSONArray getWaresSpecAndDetail(String waresId) {

        //规格集合
        List<WaresSpec> waresSpecList = activitySetMapper.getWaresSpecList(waresId);
        JSONArray jsonArray = new JSONArray();
        for (WaresSpec waresSpec : waresSpecList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("waresSpec", waresSpec);
            //规格详情集合
            List<WaresSpecDetail> waresSpecDetailList = activitySetMapper.getWaresSpecDetailList(waresSpec.getId());
            jsonObject.put("detail", waresSpecDetailList);
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }

    /**
     * 商品规格及详情新增/修改
     *
     * @param param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean setWaresSpecAndDetail(JSONObject param) {
        WaresSpec waresSpec = JSONObject.parseObject(param.getJSONObject("waresSpec").toJSONString(), WaresSpec.class);
        List<WaresSpecDetail> waresSpecDetailList = JSONArray.parseArray(param.getJSONArray("detail").toJSONString(), WaresSpecDetail.class);
        if (waresSpec == null || CollectionUtils.isEmpty(waresSpecDetailList)) {
            return false;
        }

        //新增
        if (StringUtils.isEmpity(waresSpec.getId())) {
            waresSpec.setId(StringUtils.getUuid());
            for (WaresSpecDetail waresSpecDetail : waresSpecDetailList) {
                waresSpecDetail.setId(StringUtils.getUuid());
                waresSpecDetail.setSpecId(waresSpec.getId());
            }
            activitySetMapper.addWaresSpec(waresSpec);
            activitySetMapper.addWaresSpecDetailBatch(waresSpecDetailList);
        } else {
            //修改
            activitySetMapper.updateWaresSpec(waresSpec);
            List<WaresSpecDetail> add = new ArrayList<>();
            List<WaresSpecDetail> update = new ArrayList<>();
            for (WaresSpecDetail waresSpecDetail : waresSpecDetailList) {
                if (StringUtils.isEmpity(waresSpecDetail.getId())) {
                    waresSpecDetail.setId(StringUtils.getUuid());
                    waresSpecDetail.setSpecId(waresSpec.getId());
                    add.add(waresSpecDetail);
                } else {
                    update.add(waresSpecDetail);
                }
            }
            if (!CollectionUtils.isEmpty(add)) {
                activitySetMapper.addWaresSpecDetailBatch(add);
            }
            if (!CollectionUtils.isEmpty(update)) {
                activitySetMapper.updateWaresSpecDetailBatch(update);
            }
        }
        return true;
    }

    /**
     * 删除商品规格及详情
     *
     * @param id
     * @return
     */
    @Override
    public boolean delWaresSpec(String id) {
        int count = activitySetMapper.delWaresSpec(id);
        if (count > 0) {
            activitySetMapper.delWaresSpecDetailBySpecId(id);
        } else {
            return false;
        }
        return true;
    }

    /**
     * 删除商品规格详情
     *
     * @param id
     * @return
     */
    @Override
    public int delWaresSpecDetail(String id) {
        return delWaresSpecDetail(id);
    }

    /**
     * 抽奖ID获取抽奖设置及详情
     *
     * @param id
     * @return
     */
    @Override
    public JSONObject getLuckDrawSetAndDetail(String id) {
        JSONObject jsonObject = new JSONObject();
        LuckDrawSet luckDrawSet = activitySetMapper.getLuckDrawSet(id);
        if (luckDrawSet == null) {
            return null;
        }
        jsonObject.put("luckDrawSet", luckDrawSet);
        jsonObject.put("detail", activitySetMapper.getLuckDrawDetailList(id));
        return jsonObject;
    }

    /**
     * 抽奖及详情新增/修改
     *
     * @param param
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean setLuckDrawSetAndDetail(JSONObject param, String userId) {
        LuckDrawSet luckDrawSet = JSONObject.parseObject(param.getJSONObject("luckDrawSet").toJSONString(), LuckDrawSet.class);
        List<LuckDrawDetail> luckDrawDetailList = JSONArray.parseArray(param.getJSONArray("detail").toJSONString(), LuckDrawDetail.class);
        if (luckDrawSet == null || CollectionUtils.isEmpty(luckDrawDetailList)) {
            return false;
        }

        //新增
        if (StringUtils.isEmpity(luckDrawSet.getId())) {
            luckDrawSet.setId(StringUtils.getUuid());
            for (LuckDrawDetail luckDrawDetail : luckDrawDetailList) {
                luckDrawDetail.setId(StringUtils.getUuid());
                luckDrawDetail.setLuckDrawId(luckDrawSet.getId());
            }
            activitySetMapper.addLuckDrawSet(luckDrawSet);
            activitySetMapper.addLuckDrawDetailBatch(luckDrawDetailList);
        } else {
            //修改
            activitySetMapper.updateLuckDrawSet(luckDrawSet);
            //新增的详情
            List<LuckDrawDetail> adds = new ArrayList<>();
            //删除的详情
            List<LuckDrawDetail> deletes = new ArrayList<>();
            //原有详情
            List<LuckDrawDetail> olds = activitySetMapper.getLuckDrawDetailList(luckDrawSet.getId());
            for (LuckDrawDetail luckDrawDetail : luckDrawDetailList) {
                if (StringUtils.isEmpity(luckDrawDetail.getId())) {
                    luckDrawDetail.setId(StringUtils.getUuid());
                    luckDrawDetail.setLuckDrawId(luckDrawSet.getId());
                    adds.add(luckDrawDetail);
                } else {
                    activitySetMapper.updateLuckDrawDetail(luckDrawDetail);
                }
            }
            for (LuckDrawDetail old : olds) {
                boolean result = true;
                for (LuckDrawDetail luckDrawDetail : luckDrawDetailList) {
                    if (old.getId().equals(luckDrawDetail.getId())) {
                        result = false;
                        break;
                    }
                }
                if (result) {
                    deletes.add(old);
                }
            }
            if (!CollectionUtils.isEmpty(adds)) {
                activitySetMapper.addLuckDrawDetailBatch(adds);
            }
            if (!CollectionUtils.isEmpty(deletes)) {
                activitySetMapper.deleteLuckDrawDetailBatch(deletes);
            }

        }
        return true;
    }

    /**
     * ID获取优惠券设置
     *
     * @return
     */
    @Override
    public CouponSet getCouponSet(String id) {
        return activitySetMapper.getCouponSet(id);
    }

    /**
     * 优惠券设置新增/修改
     *
     * @param couponSet
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean setCouponSet(CouponSet couponSet, String userId) {
        if (couponSet == null) {
            return false;
        }
        if (StringUtils.isEmpity(couponSet.getId())) {
            couponSet.setId(StringUtils.getUuid());
            couponSet.setUserId(userId);
            activitySetMapper.addCouponSet(couponSet);
        } else {
            activitySetMapper.updateCouponSet(couponSet);
        }
        return true;
    }

    /**
     * ID获取拼团设置
     *
     * @param id
     * @return
     */
    @Override
    public GroupBuying getGroupBuying(String id) {
        return activitySetMapper.getGroupBuying(id);
    }

    /**
     * 拼团设置 新增/删除
     *
     * @param groupBuying
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean setGroupBuying(GroupBuying groupBuying, String userId) {
        if (groupBuying == null) {
            return false;
        }
        if (StringUtils.isEmpity(groupBuying.getId())) {
            groupBuying.setId(StringUtils.getUuid());
            groupBuying.setUserId(userId);
            activitySetMapper.addGroupBuying(groupBuying);
        } else {
            activitySetMapper.updateGroupBuying(groupBuying);
        }
        return true;
    }

    /**
     * 推荐ID获取推荐信息
     *
     * @param id
     * @return
     */
    @Override
    public RecommendSet getRecommendSet(String id) {
        return activitySetMapper.getRecommendSet(id);
    }

    /**
     * 推荐新增/修改
     *
     * @param recommendSet
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean setRecommendSet(RecommendSet recommendSet, String userId) {
        if (recommendSet == null) {
            return false;
        }
        if (StringUtils.isEmpity(recommendSet.getId())) {
            recommendSet.setId(StringUtils.getUuid());
            recommendSet.setUserId(userId);
            activitySetMapper.addRecommendSet(recommendSet);
        } else {
            activitySetMapper.updateRecommendSet(recommendSet);
        }
        return true;
    }

    /**
     * 砍价ID获取设置信息
     *
     * @param id
     * @return
     */
    @Override
    public BargainingSet getBargainingSet(String id) {
        return activitySetMapper.getBargainingSet(id);
    }

    /**
     * 砍价设置新增/修改
     *
     * @param bargainingSet
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean setBargainingSet(BargainingSet bargainingSet, String userId) {

        if (bargainingSet == null) {
            return false;
        }
        if (StringUtils.isEmpity(bargainingSet.getId())) {
            bargainingSet.setId(StringUtils.getUuid());
            bargainingSet.setUserId(userId);
            activitySetMapper.addBargainingSet(bargainingSet);
        } else {
            activitySetMapper.updateBargainingSet(bargainingSet);
        }
        return true;
    }

    /**
     * 朋友圈分享ID获取设置信息
     *
     * @param id
     * @return
     */
    @Override
    public ShareFriends getShareFriends(String id) {
        return activitySetMapper.getShareFriends(id);
    }

    /**
     * 朋友圈分享新增/修改
     *
     * @param shareFriends
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean setShareFriends(ShareFriends shareFriends, String userId) {
        if (shareFriends == null) {
            return false;
        }
        if (StringUtils.isEmpity(shareFriends.getId())) {
            shareFriends.setId(StringUtils.getUuid());
            shareFriends.setUserId(userId);
            activitySetMapper.addShareFriends(shareFriends);
        } else {
            activitySetMapper.updateShareFriends(shareFriends);
        }
        return true;
    }

    /**
     * 秒杀ID获取设置信息
     *
     * @param id
     * @return
     */
    @Override
    public SeckillSet getSeckillSet(String id) {
        return activitySetMapper.getSeckillSet(id);
    }

    /**
     * 秒杀设置新增/修改
     *
     * @param seckillSet
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean setSeckillSet(SeckillSet seckillSet, String userId) {
        if (seckillSet == null) {
            return false;
        }
        if (StringUtils.isEmpity(seckillSet.getId())) {
            seckillSet.setId(StringUtils.getUuid());
            seckillSet.setUserId(userId);
            activitySetMapper.addSeckillSet(seckillSet);
        } else {
            activitySetMapper.updateSeckillSet(seckillSet);
        }
        return true;
    }

    /**
     * 红包裂变ID获取设置信息
     *
     * @param id
     * @return
     */
    @Override
    public RedenvelopesSet getRedenvelopesSet(String id) {
        return activitySetMapper.getRedenvelopesSet(id);
    }

    /**
     * 红包裂变设置新增/修改
     *
     * @param redenvelopesSet
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean setRedenvelopesSet(RedenvelopesSet redenvelopesSet, String userId) {
        if (redenvelopesSet == null) {
            return false;
        }
        if (StringUtils.isEmpity(redenvelopesSet.getId())) {
            redenvelopesSet.setId(StringUtils.getUuid());
            redenvelopesSet.setUserId(userId);
            activitySetMapper.addRedenvelopesSet(redenvelopesSet);
        } else {
            activitySetMapper.updateRedenvelopesSet(redenvelopesSet);
        }
        return true;
    }

    /**
     * 活动浏览次数增加
     *
     * @param id
     * @param type 活动类型（4：幸运转盘；5：发优惠券；6：推荐有礼；7：秒杀活动；8：拼团活动；9：砸金蛋抽奖；10：砍价大战；11：红包裂变券；12：朋友圈）
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addBrowse(String id, String type) {

        boolean result = true;

        switch (type) {
            //幸运转盘
            case Constants.TURNTABLE:
                activitySetMapper.luckDrawBrowse(id);
                break;
            //发优惠券
            case Constants.COUPON:
                activitySetMapper.couponBrowse(id);
                break;
            //推荐有礼
            case Constants.RECOMMEND:
                activitySetMapper.recommendBrowse(id);
                break;
            //秒杀活动
            case Constants.SECKILL:
                activitySetMapper.seckillBrowse(id);
                break;
            //拼团活动
            case Constants.GROUP:
                activitySetMapper.groupBrowse(id);
                break;
            //砸金蛋抽奖
            case Constants.LUCKDRAW:
                activitySetMapper.luckDrawBrowse(id);
                break;
            //砍价大战
            case Constants.BARGAINING:
                activitySetMapper.bargainingBrowse(id);
                break;
            //红包裂变券
            case Constants.REDENVELOPES:
                activitySetMapper.redenvelopesBrowse(id);
                break;
            default:
                result = false;
                break;
        }
        return result;
    }

    /**
     * 活动领券次数增加
     *
     * @param id
     * @param type 活动类型（4：幸运转盘；5：发优惠券；6：推荐有礼；7：秒杀活动；8：拼团活动；9：砸金蛋抽奖；10：砍价大战；11：红包裂变券；12：朋友圈）
     * @return
     */
    @Override
    public boolean addCoupon(String id, String type) {
        boolean result = true;

        switch (type) {
            //幸运转盘
            case Constants.TURNTABLE:
                activitySetMapper.luckDrawCoupon(id);
                break;
            //发优惠券
            case Constants.COUPON:
                activitySetMapper.couponCoupon(id);
                break;
            //推荐有礼
            case Constants.RECOMMEND:
                activitySetMapper.recommendCoupon(id);
                break;
            //秒杀活动
            case Constants.SECKILL:
                activitySetMapper.seckillCoupon(id);
                break;
            //拼团活动
            case Constants.GROUP:
                activitySetMapper.groupCoupon(id);
                break;
            //砸金蛋抽奖
            case Constants.LUCKDRAW:
                activitySetMapper.luckDrawCoupon(id);
                break;
            //砍价大战
            case Constants.BARGAINING:
                activitySetMapper.bargainingCoupon(id);
                break;
            //红包裂变券
            case Constants.REDENVELOPES:
                activitySetMapper.redenvelopesCoupon(id);
                break;
            default:
                result = false;
                break;
        }
        return result;
    }

    /**
     * 活动用券次数增加
     *
     * @param id
     * @param type 活动类型（4：幸运转盘；5：发优惠券；6：推荐有礼；7：秒杀活动；8：拼团活动；9：砸金蛋抽奖；10：砍价大战；11：红包裂变券；12：朋友圈）
     * @return
     */
    @Override
    public boolean addUse(String id, String type) {
        boolean result = true;

        switch (type) {
            //幸运转盘
            case Constants.TURNTABLE:
                activitySetMapper.luckDrawUse(id);
                break;
            //发优惠券
            case Constants.COUPON:
                activitySetMapper.couponUse(id);
                break;
            //推荐有礼
            case Constants.RECOMMEND:
                activitySetMapper.recommendUse(id);
                break;
            //秒杀活动
            case Constants.SECKILL:
                activitySetMapper.seckillUse(id);
                break;
            //拼团活动
            case Constants.GROUP:
                activitySetMapper.groupUse(id);
                break;
            //砸金蛋抽奖
            case Constants.LUCKDRAW:
                activitySetMapper.luckDrawUse(id);
                break;
            //砍价大战
            case Constants.BARGAINING:
                activitySetMapper.bargainingUse(id);
                break;
            //红包裂变券
            case Constants.REDENVELOPES:
                activitySetMapper.redenvelopesUse(id);
                break;
            //红包裂变券
            case Constants.SHAREFRIENDS:
                activitySetMapper.shareFriendsUse(id);
                break;
            default:
                result = false;
                break;
        }
        return result;
    }

    /**
     * 图片上传
     */
    public static String generateImage(String imgStr, String pk, HttpServletRequest request) {
        //对字节数组字符串进行Base64解码并生成图片
        System.out.print("已经收到了把字节码转化为图片的方法");
        //图像数据为空
        if (imgStr == null) {
            return "error";
        }
        //解析base64码，获取图片格式
        String str[] = imgStr.split(",");
        imgStr = str[1];
        String imgInfo = str[0];
        String imgExt = imgInfo.split("/")[1].split(";")[0];

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            String imgFilePath = "/SCApp/images/" + pk + "." + imgExt;//新生成的图片
            System.out.println(imgFilePath);
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return imgExt;
        } catch (Exception e) {
            return "error";
        }
    }


}
