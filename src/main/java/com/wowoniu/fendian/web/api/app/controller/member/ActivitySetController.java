package com.wowoniu.fendian.web.api.app.controller.member;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.model.*;
import com.wowoniu.fendian.service.ActivitySetService;
import com.wowoniu.fendian.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yuany
 * @date 2020-07-08
 */
@Api(value = "活动设置管理控制层", tags = "引流活动设置接口")
@RestController
@RequestMapping("/activitySet")
public class ActivitySetController {

    @Autowired
    private ActivitySetService activitySetService;

    /************************************************* 裂变 - START *********************************************************/

    @PostMapping("/getFission")
    @ApiOperation("获取裂变及详情 及启用/禁用")
    @ApiImplicitParams({@ApiImplicitParam(name = "state", value = "状态（N：关；Y：开； null：为Null时 设置过则返回数据，未设置则返回空数据）", dataType = "String", required = true)})
    public Object getFission(String state, @ApiIgnore HttpServletRequest request) {

        return activitySetService.getFissionSet((String) request.getAttribute("sysid"), state);
    }

    @ApiOperation("裂变活动设置(新增/修改)")
    @PostMapping("/setFission")
    public Object setFission(@RequestBody FissionSet fissionSet, @ApiIgnore HttpServletRequest request) {

        boolean result = activitySetService.addOrUpdateFission(fissionSet, (String) request.getAttribute("sysid"));
        if (result) {
            return new Result(200, true, "设置成功", null);
        }
        return new Result(204, false, "设置失败", null);
    }

    @ApiOperation("裂变详情设置(新增/修改)")
    @PostMapping("/setFissionDetail")
    public Object setFissionDetail(@RequestBody FissionSetDetail fissionSetDetail) {

        boolean result = activitySetService.addOrUpdateFissionDetail(fissionSetDetail);
        if (result) {
            return new Result(200, true, "设置成功", null);
        }
        return new Result(204, false, "设置失败", null);
    }

    @ApiOperation("删除裂变详情")
    @PostMapping("/deleteFissionDetail")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "裂变详情ID", dataType = "String", required = true),
            @ApiImplicitParam(name = "fissionId", value = "裂变ID", dataType = "String", required = true)})
    public Object deleteFissionDetail(String id, String fissionId) {

        boolean result = activitySetService.deleteFissionDetail(id, fissionId);
        if (result) {
            return new Result(200, true, "删除成功", null);
        }
        return new Result(204, false, "删除失败", null);
    }

    /************************************************* 裂变 - END *********************************************************/

    /************************************************* 返利 - START *********************************************************/
    @PostMapping("/getRebate")
    @ApiOperation("获取返利设置及详情 及启用/禁用")
    @ApiImplicitParams({@ApiImplicitParam(name = "state", value = "状态（N：关；Y：开； null：为Null时 设置过则返回数据，未设置则返回空数据）", dataType = "String", required = true)})
    public Object getRebate(String state, @ApiIgnore HttpServletRequest request) {

        return activitySetService.getRebate((String) request.getAttribute("sysid"), state);
    }

    @ApiOperation("返利活动设置(新增/修改)")
    @PostMapping("/setRebate")
    public Object setRebate(@RequestBody RebateSet rebateSet, @ApiIgnore HttpServletRequest request) {

        boolean result = activitySetService.addOrUpdateRebate(rebateSet, (String) request.getAttribute("sysid"));
        if (result) {
            return new Result(200, true, "设置成功", null);
        }
        return new Result(204, false, "设置失败", null);
    }

    @ApiOperation("返利详情设置(新增/修改)")
    @PostMapping("/setRebateDetail")
    public Object setRebateDetail(@RequestBody RebateSetDetail rebateSetDetail) {

        boolean result = activitySetService.addOrUpdateRebateSetDetail(rebateSetDetail);
        if (result) {
            return new Result(200, true, "设置成功", null);
        }
        return new Result(204, false, "设置失败", null);
    }

    @ApiOperation("删除返利详情")
    @PostMapping("/deleteRebateDetail")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "返利详情ID", dataType = "String", required = true),
            @ApiImplicitParam(name = "rebateId", value = "返利ID", dataType = "String", required = true)})
    public Object deleteRebateDetail(String id, String rebateId) {

        boolean result = activitySetService.deleteRebateDetail(id, rebateId);
        if (result) {
            return new Result(200, true, "删除成功", null);
        }
        return new Result(204, false, "删除失败", null);
    }
    /************************************************* 返利 - END *********************************************************/

    /************************************************* 分销 - START *********************************************************/
    @PostMapping("/getDistribution")
    @ApiOperation("获取返利及详情 及启用/禁用")
    @ApiImplicitParams({@ApiImplicitParam(name = "state", value = "状态（N：关；Y：开； null：为null时返回现有状态的数据）", dataType = "String", required = true)})
    public Object getDistribution(String state, @ApiIgnore HttpServletRequest request) {

        return activitySetService.getDistribution((String) request.getAttribute("sysid"), state);
    }

    @ApiOperation("分销活动设置(新增/修改)")
    @PostMapping("/setRebate")
    public Object setDistribution(@RequestBody DistributionSet distributionSet, @ApiIgnore HttpServletRequest request) {

        boolean result = activitySetService.addOrUpdateDistribution(distributionSet, (String) request.getAttribute("sysid"));
        if (result) {
            return new Result(200, true, "设置成功", null);
        }
        return new Result(204, false, "设置失败", null);
    }

    @ApiOperation("分销优惠券设置(新增/修改)")
    @PostMapping("/setDistributionCoupon")
    public Object setDistributionCoupon(@RequestBody DistributionCoupon distributionCoupon) {

        boolean result = activitySetService.addOrUpdateDistributionCoupon(distributionCoupon);
        if (result) {
            return new Result(200, true, "设置成功", null);
        }
        return new Result(204, false, "设置失败", null);
    }

    @ApiOperation("删除分销优惠券")
    @PostMapping("/deleteDistributionCoupon")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "返利详情ID", dataType = "String", required = true)})
    public Object deleteDistributionCoupon(String id) {

        boolean result = activitySetService.deleteDistributionCoupon(id);
        if (result) {
            return new Result(200, true, "删除成功", null);
        }
        return new Result(204, false, "删除失败", null);
    }
    /************************************************* 分销 - END *********************************************************/

    /************************************************* 商城 - END *********************************************************/
    @PostMapping("/getShoppingMallSet")
    @ApiOperation("商家ID获取商城设置")
    @ApiImplicitParams({@ApiImplicitParam(name = "state", value = "状态（N：关；Y：开； null：为Null时 设置过则返回数据，未设置则返回空数据）", dataType = "String", required = true)})
    public Object getShoppingMallSet(String state, @ApiIgnore HttpServletRequest request) {

        return activitySetService.getShoppingMallSet((String) request.getAttribute("sysid"), state);
    }

    @ApiOperation("商城设置(新增/修改)")
    @PostMapping("/setShoppingMall")
    public Object setShoppingMall(@RequestBody ShoppingMallSet shoppingMallSet, @ApiIgnore HttpServletRequest request) {

        boolean result = activitySetService.addOrUpdateShoppingMall(shoppingMallSet, (String) request.getAttribute("sysid"));
        if (result) {
            return new Result(200, true, "设置成功", null);
        }
        return new Result(204, false, "设置失败", null);
    }

    @ApiOperation("订单状态获取订单列表")
    @PostMapping("/getWaresOrder")
    @ApiImplicitParams({@ApiImplicitParam(name = "state", value = "订单状态（0：待付款；1：待发货；2：已发货；3：已完成；4：已关闭）", dataType = "String", required = true)})
    public Object getWaresOrder(String state, @ApiIgnore HttpServletRequest request) {

        List<WaresOrder> waresOrderList = activitySetService.getWaresOrderList((String) request.getAttribute("sysid"), state);
        if (CollectionUtils.isEmpty(waresOrderList)) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, true, "获取成功", waresOrderList);
    }

    @ApiOperation("商品分类启用/禁用设置 并返回分类列表")
    @PostMapping("/getWaresShortSet")
    @ApiImplicitParams({@ApiImplicitParam(name = "state", value = "分类N:禁用；Y:启用", dataType = "String", required = true)})
    public Object getWaresShortSet(String state, @ApiIgnore HttpServletRequest request) {

        return activitySetService.getWaresShortSet((String) request.getAttribute("sysid"), state);
    }

    @ApiOperation("商品分类ID获取分类信息")
    @PostMapping("/getWaresSortDetail")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "商品分类ID", dataType = "String", required = true)})
    public Object getWaresSortDetail(String id) {

        WaresSortDetail waresSortDetail = activitySetService.getWaresSortDetail(id);
        if (waresSortDetail == null) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, true, "获取成功", waresSortDetail);
    }

    @ApiOperation("商品分类新增/修改")
    @PostMapping("/setWaresSortDetail")
    public Object getWaresSortDetail(@RequestBody WaresSortDetail waresSortDetail) {

        boolean result = activitySetService.setWaresSortDetail(waresSortDetail);
        if (result) {
            return new Result(200, true, "设置成功", null);
        }
        return new Result(204, false, "设置失败", null);
    }

    @ApiOperation("商品分类置顶设置")
    @PostMapping("/setWaresSortDetailTop")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "商品分类ID", dataType = "String", required = true)})
    public Object setWaresSortDetailTop(String id) {

        List<WaresSortDetail> waresSortDetailList = activitySetService.setWaresSortDetailTop(id);
        if (CollectionUtils.isEmpty(waresSortDetailList)) {
            return new Result(204, false, "设置失败", null);
        }
        return new Result(200, true, "设置成功", waresSortDetailList);
    }

    @ApiOperation("商品分类置顶上移 下移")
    @PostMapping("/setWaresSortDetailTop")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "商品分类ID", dataType = "String", required = true),
            @ApiImplicitParam(name = "move", value = "移动（0：向上移动一位；1：向下移动一位）", dataType = "int", required = true)})
    public Object setWaresSortDetailTopMove(String id, Integer move) {

        boolean result = activitySetService.setWaresSortDetailTopMove(id, move);
        if (result) {
            return new Result(200, true, "设置成功", null);
        }
        return new Result(204, false, "设置失败", null);
    }

    @ApiOperation("商品分类列表")
    @PostMapping("/getWaresSortDetailList")
    public Object getWaresSortDetailList(@ApiIgnore HttpServletRequest request) {

        List<WaresSortDetail> waresSortDetailList = activitySetService.getWaresSortDetailListByUserId((String) request.getAttribute("sysid"));
        if (CollectionUtils.isEmpty(waresSortDetailList)) {
            return new Result(204, false, "获取失败", null);
        }

        return new Result(200, true, "获取成功", null);

    }

    @ApiOperation("商品列表条件查询")
    @PostMapping("/getWaresList")
    @ApiImplicitParams({@ApiImplicitParam(name = "state", value = "上架（Y：上架；N：下架;全部：0）", dataType = "String", required = true),
            @ApiImplicitParam(name = "time", value = "时间查询条件（1:；0：禁用）按时间由近到远排序", dataType = "String", required = true),
            @ApiImplicitParam(name = "sales", value = "销量查询条件（1：启用；0：禁用）按时间由多到排序", dataType = "String", required = true),
            @ApiImplicitParam(name = "sortDetailId", value = "商品分类详情(全部则为0)", dataType = "String", required = true)})
    public Object getWaresList(String state, String time, String sales, String sortDetailId, @ApiIgnore HttpServletRequest request) {

        List<Wares> waresList = activitySetService.getWaresList((String) request.getAttribute("sysid"), state, time, sales, sortDetailId);
        if (CollectionUtils.isEmpty(waresList)) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, true, "获取成功", waresList);

    }

    @ApiOperation("发布商品新增/修改")
    @PostMapping("/setWares")
    public Object setWares(@RequestBody Wares wares, @ApiIgnore HttpServletRequest request) {
        boolean result = activitySetService.setWares(wares, (String) request.getAttribute("sysid"));
        if (result) {
            return new Result(200, true, "发布成功", null);
        }
        return new Result(204, false, "发布失败", null);
    }

    @ApiOperation("商品ID获取规格及规格详情")
    @PostMapping("/getWaresSpecAndDetail")
    @ApiImplicitParams({@ApiImplicitParam(name = "waresId", value = "商品ID", dataType = "String", required = true)})
    public Object getWaresSpecAndDetail(String waresId) {

        JSONArray jsonArray = activitySetService.getWaresSpecAndDetail(waresId);
        if (jsonArray == null || jsonArray.size() == 0) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, true, "获取成功", jsonArray);
    }

    @ApiOperation("商品规格及详情新增/修改")
    @PostMapping("/setWaresSpec")
    public Object setWaresSpec(@RequestBody WaresSpec waresSpec, @RequestBody List<WaresSpecDetail> waresSpecDetailList) {
        boolean result = activitySetService.setWaresSpecAndDetail(waresSpec, waresSpecDetailList);
        if (result) {
            return new Result(200, true, "创建成功", null);
        }
        return new Result(204, false, "创建失败", null);
    }


    /************************************************* 商城 - END *********************************************************/

    /************************************************* 抽奖 -砸金蛋 转盘 - START *********************************************************/

    @ApiOperation("抽奖ID获取抽奖设置及详情")
    @PostMapping("/getLuckDrawSetAndDetail")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "抽奖设置ID", dataType = "String", required = true)})
    public Object getLuckDrawSetAndDetail(String id) {

        JSONObject jsonObject = activitySetService.getLuckDrawSetAndDetail(id);
        if (jsonObject == null || jsonObject.size() == 0) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, true, "获取成功", jsonObject);
    }

    @ApiOperation("抽奖及详情新增/修改")
    @PostMapping("/setWaresSpec")
    public Object setLuckDrawSet(@RequestBody LuckDrawSet luckDrawSet, @RequestBody List<LuckDrawDetail> luckDrawDetailList) {
        boolean result = activitySetService.setLuckDrawSetAndDetail(luckDrawSet, luckDrawDetailList);
        if (result) {
            return new Result(200, true, "创建成功", null);
        }
        return new Result(204, false, "创建成功", null);
    }
    /************************************************* 抽奖 - END *********************************************************/

    /************************************************* 优惠券 - START *********************************************************/

    @ApiOperation("优惠券ID获取设置信息")
    @PostMapping("/getCouponSet")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "优惠券设置ID", dataType = "String", required = true)})
    public Object getCouponSet(String id) {

        CouponSet couponSet = activitySetService.getCouponSet(id);
        if (couponSet == null) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, true, "获取成功", couponSet);
    }

    @ApiOperation("优惠券新增/修改")
    @PostMapping("/setCouponSet")
    public Object setCouponSet(@RequestBody CouponSet couponSet, @ApiIgnore HttpServletRequest request) {
        boolean result = activitySetService.setCouponSet(couponSet, (String) request.getAttribute("sysid"));
        if (result) {
            return new Result(200, true, "创建成功", null);
        }
        return new Result(204, false, "创建成功", null);
    }
    /************************************************* 优惠券 - END *********************************************************/

    /************************************************* 拼团 - START *********************************************************/

    @ApiOperation("拼团ID获取设置信息")
    @PostMapping("/getGroupBuying")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "拼团设置ID", dataType = "String", required = true)})
    public Object getGroupBuying(String id) {

        GroupBuying groupBuying = activitySetService.getGroupBuying(id);
        if (groupBuying == null) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, true, "获取成功", groupBuying);
    }

    @ApiOperation("优惠券新增/修改")
    @PostMapping("/setGroupBuying")
    public Object setGroupBuying(@RequestBody GroupBuying groupBuying, @ApiIgnore HttpServletRequest request) {
        boolean result = activitySetService.setGroupBuying(groupBuying, (String) request.getAttribute("sysid"));
        if (result) {
            return new Result(200, true, "创建成功", null);
        }
        return new Result(204, false, "创建成功", null);
    }
    /************************************************* 拼团 - END *********************************************************/

    /************************************************* 推荐 - START *********************************************************/

    @ApiOperation("推荐ID获取设置信息")
    @PostMapping("/getRecommendSet")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "推荐设置ID", dataType = "String", required = true)})
    public Object getRecommendSet(String id) {

        RecommendSet recommendSet = activitySetService.getRecommendSet(id);
        if (recommendSet == null) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, true, "获取成功", recommendSet);
    }

    @ApiOperation("优惠券新增/修改")
    @PostMapping("/setRecommendSet")
    public Object setRecommendSet(@RequestBody RecommendSet recommendSet, @ApiIgnore HttpServletRequest request) {
        boolean result = activitySetService.setRecommendSet(recommendSet, (String) request.getAttribute("sysid"));
        if (result) {
            return new Result(200, true, "创建成功", null);
        }
        return new Result(204, false, "创建成功", null);
    }
    /************************************************* 推荐 - END *********************************************************/
}
