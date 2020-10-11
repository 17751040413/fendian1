package com.wowoniu.fendian.web.api.app.controller.member;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.mapper.ActivityMapper;
import com.wowoniu.fendian.mapper.ActivitySetMapper;
import com.wowoniu.fendian.model.*;
import com.wowoniu.fendian.service.ActivitySetService;
import com.wowoniu.fendian.utils.Result;
import com.wowoniu.fendian.utils.StringUtils;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private ActivitySetMapper activitySetMapper;

    /************************************************* True 裂变 - START *********************************************************/

    @PostMapping("/getFission")
    @ApiOperation("裂变--获取裂变及详情 及启用/禁用")
    @ApiImplicitParams({@ApiImplicitParam(name = "state", value = "关闭：N 开启：Y ", dataType = "String", required = true)})
    public Object getFission(String state, @ApiIgnore HttpServletRequest request) {

        return activitySetService.getFissionSet((String) request.getAttribute("sysid"), state);
    }

    @ApiOperation("裂变--裂变活动设置及详情(新增/修改)")
    @PostMapping("/setFissionSet")
    @ApiImplicitParams({@ApiImplicitParam(name = "param", value = " 裂变活动设置FissionSet实体 FissionSetDetail详情", dataType = "JSONObject", required = true)})
    public Object setFissionSet(@RequestBody JSONObject param, @ApiIgnore HttpServletRequest request) {

        boolean result = activitySetService.addOrUpdateFission(param, (String) request.getAttribute("sysid"));
        if (result) {
            return new Result(200, true, "操作成功", null);
        }
        return new Result(204, false, "操作失败", null);
    }


    /************************************************* True 裂变 - END *********************************************************/

    /************************************************* True 返利 - START *********************************************************/
    @PostMapping("/getRebateSet")
    @ApiOperation("返利--获取返利设置及详情 及启用/禁用")
    @ApiImplicitParams({@ApiImplicitParam(name = "state", value = "状态（N：关；Y：开； null：为Null时 设置过则返回数据，未设置则返回空数据）", dataType = "String", required = true)})
    public Object getRebateSet(String state, @ApiIgnore HttpServletRequest request) {

        return activitySetService.getRebateSet((String) request.getAttribute("sysid"), state);
    }

    @ApiOperation("返利--返利活动设置及详情(新增/修改)")
    @PostMapping("/setRebateSet")
    @ApiImplicitParams({@ApiImplicitParam(name = "param", value = " RebateSet实体及RebateSetDetail集合数据", dataType = "JSONObject", required = true)})
    public Object setRebateSet(@RequestBody JSONObject param, @ApiIgnore HttpServletRequest request) {

        boolean result = activitySetService.addOrUpdateRebate(param, (String) request.getAttribute("sysid"));
        if (result) {
            return new Result(200, true, "操作成功", null);
        }
        return new Result(204, false, "操作失败", null);
    }

    @ApiOperation("返利--返利记录")
    @PostMapping("/getRebateRecord")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int", required = true),
            @ApiImplicitParam(name = "startRow", value = "起始行", dataType = "int", required = true),
            @ApiImplicitParam(name = "name", value = "昵称", dataType = "String", required = true)})
    public Object getRebateRecord(@ApiIgnore HttpServletRequest request, int pageSize, int startRow, String name) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("pageSize", pageSize);
        map.put("startRow", startRow);
        map.put("userId", request.getAttribute("sysid"));
        map.put("name", name);

        return new Result(200, true, "获取成功", activitySetService.getRebateRecord(map));
    }

    /************************************************* True 返利 - END *********************************************************/

    /************************************************* True 分销 - START *********************************************************/
    @PostMapping("/getDistribution")
    @ApiOperation("分销--获取分销及详情 及启用/禁用")
    @ApiImplicitParams({@ApiImplicitParam(name = "state", value = "状态（N：关；Y：开； null：为null时返回现有状态的数据）", dataType = "String", required = true)})
    public Object getDistribution(String state, @ApiIgnore HttpServletRequest request) {

        return activitySetService.getDistribution((String) request.getAttribute("sysid"), state);
    }

    @ApiOperation("分销--分销活动设置(新增/修改)")
    @PostMapping("/setDistribution")
    public Object setDistribution(@RequestBody DistributionSet distributionSet, @ApiIgnore HttpServletRequest request) {

        boolean result = activitySetService.addOrUpdateDistribution(distributionSet, (String) request.getAttribute("sysid"));
        if (result) {
            return new Result(200, true, "操作成功", null);
        }
        return new Result(204, false, "操作失败", null);
    }

    @ApiOperation("分销--分销优惠券设置(新增/修改)")
    @PostMapping("/setDistributionCoupon")
    public Object setDistributionCoupon(@RequestBody DistributionCoupon distributionCoupon) {

        boolean result = activitySetService.addOrUpdateDistributionCoupon(distributionCoupon);
        if (result) {
            return new Result(200, true, "操作成功", null);
        }
        return new Result(204, false, "操作失败", null);
    }

    @ApiOperation("分销--删除(禁用)分销优惠券")
    @PostMapping("/updateDistributionCouponEndTime")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "返利详情ID", dataType = "String", required = true)})
    public Object updateDistributionCouponEndTime(String id) {

        boolean result = activitySetService.updateDistributionCouponEndTime(id);
        if (result) {
            return new Result(200, true, "禁用成功", null);
        }
        return new Result(204, false, "禁用失败", null);
    }

    @ApiOperation("分销--分销比例修改记录")
    @PostMapping("/getDistributionRatioRecord")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int", required = true),
            @ApiImplicitParam(name = "startRow", value = "起始行", dataType = "int", required = true)})
    public Object getDistributionRatioRecord(@ApiIgnore HttpServletRequest request, int pageSize, int startRow) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("pageSize", pageSize);
        map.put("startRow", startRow);
        map.put("userId", request.getAttribute("sysid"));

        return new Result(200, true, "获取成功", activitySetService.getDistributionRatioRecord(map));
    }

    @ApiOperation("分销--分销商名单")
    @PostMapping("/getDistributionUser")
    @ApiImplicitParams({@ApiImplicitParam(name = "search", value = "搜索条件（昵称/手机号）", dataType = "int", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int", required = true),
            @ApiImplicitParam(name = "startRow", value = "起始行", dataType = "int", required = true)})
    public Object getDistributionUser(@ApiIgnore HttpServletRequest request, String search, int pageSize, int startRow) {
        String phone = null;
        String name = null;
        if (StringUtils.isNotEmpty(search)) {
            if (StringUtils.isNumeric(search)) {
                phone = search;
            } else {
                name = search;
            }
        }
        Map<String, Object> map = new HashMap<>(8);
        map.put("pageSize", pageSize);
        map.put("startRow", startRow);
        map.put("phone", phone);
        map.put("name", name);
        map.put("userId", request.getAttribute("sysid"));

        return new Result(200, true, "获取成功", activitySetService.getDistributionUser(map));
    }

    @ApiOperation("分销--分销商详情")
    @PostMapping("/getDistributionUserById")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "分销商ID", dataType = "int", required = true)})
    public Object getDistributionUserById(String id) {
        return new Result(200, true, "获取成功", activitySetService.getDistributionUserById(id));
    }
    /************************************************* True 分销 - END *********************************************************/

    /************************************************* True 商城 - END *********************************************************/
    @PostMapping("/getShoppingMallSet")
    @ApiOperation("商城--商家ID获取商城设置")
    @ApiImplicitParams({@ApiImplicitParam(name = "state", value = "状态（N：关；Y：开； null：为Null时 设置过则返回数据，未设置则返回空数据）", dataType = "String", required = true)})
    public Object getShoppingMallSet(String state, @ApiIgnore HttpServletRequest request) {

        return activitySetService.getShoppingMallSet((String) request.getAttribute("sysid"), state);
    }

    @ApiOperation("商城--商城设置(新增/修改)")
    @PostMapping("/setShoppingMall")
    public Object setShoppingMall(@RequestBody ShoppingMallSet shoppingMallSet, @ApiIgnore HttpServletRequest request) {

        boolean result = activitySetService.addOrUpdateShoppingMall(shoppingMallSet, (String) request.getAttribute("sysid"));
        if (result) {
            return new Result(200, true, "操作成功", null);
        }
        return new Result(204, false, "操作失败", null);
    }

    @ApiOperation("商城--订单状态获取订单列表")
    @PostMapping("/getWaresOrder")
    @ApiImplicitParams({@ApiImplicitParam(name = "state", value = "状态（0：待付款；1：已付款，2：待发货；3：已发货；4：已完成；5：已关闭）", dataType = "String", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int", required = true),
            @ApiImplicitParam(name = "startRow", value = "起始行", dataType = "int", required = true)})
    public Object getWaresOrder(@ApiIgnore HttpServletRequest request, String state, int pageSize, int startRow) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("pageSize", pageSize);
        map.put("startRow", startRow);
        map.put("state", state);
        map.put("userId", request.getAttribute("sysid"));

        return new Result(200, true, "获取成功", activitySetService.getWaresOrderList(map));
    }

    @ApiOperation("商城--商品分类启用/禁用设置 并返回分类列表")
    @PostMapping("/getWaresShortSet")
    @ApiImplicitParams({@ApiImplicitParam(name = "state", value = "分类N:禁用；Y:启用", dataType = "String", required = true)})
    public Object getWaresShortSet(String state, @ApiIgnore HttpServletRequest request) {

        return activitySetService.getWaresShortSet((String) request.getAttribute("sysid"), state);
    }

    @ApiOperation("商城--商品分类ID获取分类信息")
    @PostMapping("/getWaresSortDetail")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "商品分类ID", dataType = "String", required = true)})
    public Object getWaresSortDetail(String id) {

        WaresSortDetail waresSortDetail = activitySetService.getWaresSortDetail(id);
        if (waresSortDetail == null) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, true, "获取成功", waresSortDetail);
    }

    @ApiOperation("商城--商品分类新增/修改")
    @PostMapping("/setWaresSortDetail")
    public Object setWaresSortDetail(@RequestBody WaresSortDetail waresSortDetail) {

        boolean result = activitySetService.setWaresSortDetail(waresSortDetail);
        if (result) {
            return new Result(200, true, "操作成功", null);
        }
        return new Result(204, false, "操作失败", null);
    }

    @ApiOperation("商城--商品分类置顶设置")
    @PostMapping("/setWaresSortDetailTop")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "商品分类ID", dataType = "String", required = true)})
    public Object setWaresSortDetailTop(String id) {

        List<WaresSortDetail> waresSortDetailList = activitySetService.setWaresSortDetailTop(id);
        if (CollectionUtils.isEmpty(waresSortDetailList)) {
            return new Result(204, false, "操作失败", null);
        }
        return new Result(200, true, "操作成功", waresSortDetailList);
    }

    @ApiOperation("商城--商品分类置顶上移 下移")
    @PostMapping("/setWaresSortDetailTopMove")
    public Object setWaresSortDetailTopMove(String param) {

        JSONArray jsonArray = JSONArray.parseArray(param);
        boolean result = activitySetService.setWaresSortDetailTopMove(jsonArray);
        if (result) {
            return new Result(200, true, "操作成功", null);
        }
        return new Result(204, false, "操作失败", null);
    }

    @ApiOperation("商城--商品分类列表")
    @PostMapping("/getWaresSortDetailList")
    public Object getWaresSortDetailList(@ApiIgnore HttpServletRequest request) {

        List<WaresSortDetail> waresSortDetailList = activitySetService.getWaresSortDetailListByUserId((String) request.getAttribute("sysid"));
        if (CollectionUtils.isEmpty(waresSortDetailList)) {
            return new Result(204, false, "获取失败", null);
        }

        return new Result(200, true, "获取成功", waresSortDetailList);

    }

    @ApiOperation("商城--商品列表条件查询")
    @PostMapping("/getWaresList")
    @ApiImplicitParams({@ApiImplicitParam(name = "onShelf", value = "上架（Y：上架；N：下架;全部：null）", dataType = "String", required = true),
            @ApiImplicitParam(name = "time", value = "时间查询条件（1:启用；0：禁用）按时间由近到远排序", dataType = "String", required = true),
            @ApiImplicitParam(name = "sales", value = "销量查询条件（1：启用；0：禁用）按时间由多到排序", dataType = "String", required = true),
            @ApiImplicitParam(name = "sortId", value = "商品分类详情Id(全部则为0)", dataType = "String", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int", required = true),
            @ApiImplicitParam(name = "startRow", value = "起始行", dataType = "int", required = true)})
    public Object getWaresList(String onShelf, String time, String sales, String sortId, int pageSize, int startRow, @ApiIgnore HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("pageSize", pageSize);
        map.put("startRow", startRow);
        map.put("userId", request.getAttribute("sysid"));
        map.put("onShelf", onShelf);
        map.put("time", time);
        map.put("sales", sales);
        map.put("sortId", sortId);

        return new Result(200, true, "获取成功", activitySetService.getWaresList(map));

    }

    @ApiOperation("商城--发布商品新增/修改")
    @PostMapping("/setWares")
    public Object setWares(@RequestBody Wares wares, @ApiIgnore HttpServletRequest request) {
        boolean result = activitySetService.setWares(wares, (String) request.getAttribute("sysid"));
        if (result) {
            return new Result(200, true, "操作成功", null);
        }
        return new Result(204, false, "操作失败", null);
    }

    @ApiOperation("商城--商品ID获取规格及规格详情列表")
    @PostMapping("/getWaresSpecAndDetail")
    @ApiImplicitParams({@ApiImplicitParam(name = "waresId", value = "商品ID", dataType = "String", required = true)})
    public Object getWaresSpecAndDetail(String waresId) {

        return new Result(200, true, "获取成功", activitySetService.getWaresSpecAndDetail(waresId));
    }

    @ApiOperation("商城--规格ID获取规格及规格详情")
    @PostMapping("/getWaresSpecById")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "规格id", dataType = "String", required = true)})
    public Object getWaresSpecById(String id) {

        return new Result(200, true, "获取成功", activitySetService.getWaresSpecById(id));
    }

    @ApiOperation("商城--商品规格及详情新增/修改")
    @PostMapping("/setWaresSpec")
    public Object setWaresSpec(@RequestBody JSONObject param) {
        boolean result = activitySetService.setWaresSpecAndDetail(param);
        if (result) {
            return new Result(200, true, "操作成功", null);
        }
        return new Result(204, false, "操作失败", null);
    }

    @ApiOperation("商城--删除商品规格及详情")
    @PostMapping("/delWaresSpec")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "商品规格ID", dataType = "String", required = true)})
    public Object delWaresSpec(@RequestBody String id) {
        boolean result = activitySetService.delWaresSpec(id);
        if (result) {
            return new Result(200, true, "删除成功", null);
        }
        return new Result(204, false, "删除失败", null);
    }

    @ApiOperation("商城--删除商品规格详情")
    @PostMapping("/delWaresSpecDetail")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "商品规格详情ID", dataType = "String", required = true)})
    public Object delWaresSpecDetail(@RequestBody String id) {
        int count = activitySetService.delWaresSpecDetail(id);
        if (count > 0) {
            return new Result(200, true, "删除成功", null);
        }
        return new Result(204, false, "删除失败", null);
    }

    @ApiOperation("商城--订单发货确认")
    @PostMapping("/sendWaresSure")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "订单ID", dataType = "String", required = true),
            @ApiImplicitParam(name = "code", value = "快递单号", dataType = "String", required = true)})
    public Object sendWaresSure(String id, String code) {
        int count = activitySetService.sendWaresSure(id, code);
        if (count > 0) {
            return new Result(200, true, "确认成功", null);
        }
        return new Result(204, false, "确认失败", null);
    }

    @PostMapping("/delWares")
    public Object delWares(String id){

        activitySetMapper.delWares(id);

        return new Result(200,true,"删除成功");
    }

    @ApiOperation("商城--取货码确认")
    @PostMapping("/takeWaresSure")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "订单ID", dataType = "long", required = true),
            @ApiImplicitParam(name = "code", value = "取货码", dataType = "String", required = true)})
    public Object takeWaresSure(String id, String code, @ApiIgnore HttpServletRequest request) {
        boolean result = activitySetService.takeWaresSure(id, code, (String) request.getAttribute("sysid"));
        if (result) {
            return new Result(200, true, "确认成功", null);
        }
        return new Result(204, false, "确认失败", null);
    }

    @ApiOperation("商城--订单ID获取订单信息")
    @PostMapping("/getWaresOrderById")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "订单ID", dataType = "long", required = true)})
    public Object getWaresOrderById(String id, @ApiIgnore HttpServletRequest request) {
        return new Result(200, true, "获取成功", activitySetService.getWaresOrderById(id, (String) request.getAttribute("sysid")));
    }


    /************************************************* True 商城 - END *********************************************************/

    /************************************************* True 抽奖 -砸金蛋 转盘 - START *********************************************************/

    @ApiOperation("抽奖（砸金蛋 转盘）--抽奖ID获取抽奖设置及详情")
    @PostMapping("/getLuckDrawSetAndDetail")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "抽奖设置ID", dataType = "String", required = true)})
    public Object getLuckDrawSetAndDetail(String id) {

        JSONObject jsonObject = activitySetService.getLuckDrawSetAndDetail(id);
        if (jsonObject == null || jsonObject.size() == 0) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, true, "获取成功", jsonObject);
    }

    @ApiOperation("抽奖（砸金蛋 转盘）--抽奖及详情新增/修改")
    @PostMapping("/setLuckDrawSet")
    @ApiImplicitParams({@ApiImplicitParam(name = "param", value = " LuckDrawSet实体及LuckDrawDetail集合数据", dataType = "JSONObject", required = true)})
    public Object setLuckDrawSet(@RequestBody JSONObject param, @ApiIgnore HttpServletRequest request) {

        boolean result = activitySetService.setLuckDrawSetAndDetail(param, (String) request.getAttribute("sysid"));
        if (result) {
            return new Result(200, true, "操作成功", null);
        }
        return new Result(204, false, "操作失败", null);
    }
    /************************************************* True 抽奖 - END *********************************************************/

    /************************************************* True 优惠券 - START *********************************************************/

    @ApiOperation("优惠券--优惠券ID获取设置信息")
    @PostMapping("/getCouponSet")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "优惠券设置ID", dataType = "String", required = true)})
    public Object getCouponSet(String id) {

        CouponSet couponSet = activitySetService.getCouponSet(id);
        if (couponSet == null) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, true, "获取成功", couponSet);
    }

    @ApiOperation("优惠券--优惠券新增/修改 （有id参数为修改 无id参数为新增）")
    @PostMapping("/setCouponSet")
    public Object setCouponSet(@RequestBody CouponSet couponSet, @ApiIgnore HttpServletRequest request) {
        boolean result = activitySetService.setCouponSet(couponSet, (String) request.getAttribute("sysid"));
        if (result) {
            return new Result(200, true, "操作成功", null);
        }
        return new Result(204, false, "操作失败", null);
    }
    /************************************************* True 优惠券 - END *********************************************************/

    /************************************************* True 拼团 - START *********************************************************/

    @ApiOperation("拼团--拼团ID获取设置信息")
    @PostMapping("/getGroupBuying")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "拼团设置ID", dataType = "String", required = true)})
    public Object getGroupBuying(String id) {

        GroupBuying groupBuying = activitySetService.getGroupBuying(id);
        if (groupBuying == null) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, true, "获取成功", groupBuying);
    }

    @ApiOperation("拼团--拼团新增/修改 （有id参数为修改 无id参数为新增）")
    @PostMapping("/setGroupBuying")
    public Object setGroupBuying(@RequestBody GroupBuying groupBuying, @ApiIgnore HttpServletRequest request) {
        boolean result = activitySetService.setGroupBuying(groupBuying, (String) request.getAttribute("sysid"));
        if (result) {
            return new Result(200, true, "操作成功", null);
        }
        return new Result(204, false, "操作失败", null);
    }
    /************************************************* True 拼团 - END *********************************************************/

    /************************************************* True 推荐 - START *********************************************************/

    @ApiOperation("推荐--推荐ID获取设置信息 ")
    @PostMapping("/getRecommendSet")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "推荐设置ID", dataType = "String", required = true)})
    public Object getRecommendSet(String id) {

        return new Result(200, true, "获取成功", activitySetService.getRecommendSet(id));
    }

    @ApiOperation("推荐--优惠券新增/修改（有id参数为修改 无id参数为新增）")
    @PostMapping("/setRecommendSet")
    public Object setRecommendSet(@RequestBody RecommendSet recommendSet, @ApiIgnore HttpServletRequest request) {
        boolean result = activitySetService.setRecommendSet(recommendSet, (String) request.getAttribute("sysid"));
        if (result) {
            return new Result(200, true, "操作成功", null);
        }
        return new Result(204, false, "操作失败", null);
    }
    /************************************************* True 推荐 - END *********************************************************/


    /************************************************* True 砍价 - START *********************************************************/
    @ApiOperation("砍价--砍价ID获取设置信息")
    @PostMapping("/getBargainingSet")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "砍价设置ID", dataType = "String", required = true)})
    public Object getBargainingSet(String id) {

        BargainingSet bargainingSet = activitySetService.getBargainingSet(id);
        if (bargainingSet == null) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, true, "获取成功", bargainingSet);
    }

    @ApiOperation("砍价--砍价设置新增/修改 （有id参数为修改 无id参数为新增）")
    @PostMapping("/setBargainingSet")
    public Object setBargainingSet(@RequestBody BargainingSet bargainingSet, @ApiIgnore HttpServletRequest request) {
        boolean result = activitySetService.setBargainingSet(bargainingSet, (String) request.getAttribute("sysid"));
        if (result) {
            return new Result(200, true, "操作成功", null);
        }
        return new Result(204, false, "操作失败", null);
    }
    /************************************************* True 砍价 - END *********************************************************/

    /************************************************* True 朋友圈分享 - START *********************************************************/
    @ApiOperation("朋友圈分享--朋友圈模板列表")
    @PostMapping("/getShareFriendList")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int", required = true),
            @ApiImplicitParam(name = "startRow", value = "起始行", dataType = "int", required = true)})
    public Object getShareFriendList(@ApiIgnore HttpServletRequest request, int pageSize, int startRow) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("pageSize", pageSize);
        map.put("startRow", startRow);
        map.put("userId", request.getAttribute("sysid"));

        return new Result(200, true, "获取成功", activitySetService.getShareFriendList(map));
    }

    @ApiOperation("朋友圈分享--朋友圈分享ID获取设置信息")
    @PostMapping("/getShareFriends")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "朋友圈模板ID", dataType = "String", required = true)})
    public Object getShareFriends(String id) {

        ShareFriends shareFriends = activitySetService.getShareFriends(id);
        if (shareFriends == null) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, true, "获取成功", shareFriends);
    }

    @ApiOperation("朋友圈分享--设置新增/修改 （有id参数为修改 无id参数为新增）")
    @PostMapping("/setShareFriends")
    public Object setShareFriends(@RequestBody ShareFriends shareFriends, @ApiIgnore HttpServletRequest request) {
        boolean result = activitySetService.setShareFriends(shareFriends, (String) request.getAttribute("sysid"));
        if (result) {
            return new Result(200, true, "操作成功", null);
        }
        return new Result(204, false, "操作失败", null);
    }

    @ApiOperation("朋友圈分享--删除")
    @PostMapping("/delShareFriends")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "朋友圈模板ID", dataType = "String", required = true)})
    public Object delShareFriends(String id) {
        int count = activitySetService.delShareFriends(id);
        if (count > 0) {
            return new Result(200, true, "操作成功", null);
        }
        return new Result(204, false, "操作失败", null);
    }
    /************************************************* True 朋友圈 - END *********************************************************/

    /*************************************************  True 秒杀 - START *********************************************************/

    @ApiOperation("秒杀--秒杀ID获取设置信息")
    @PostMapping("/getSeckillSet")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "砍价设置ID", dataType = "String", required = true)})
    public Object getSeckillSet(String id) {

        SeckillSet seckillSet = activitySetService.getSeckillSet(id);
        if (seckillSet == null) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, true, "获取成功", seckillSet);
    }

    @ApiOperation("秒杀--秒杀设置新增/修改 （有id参数为修改 无id参数为新增）")
    @PostMapping("/setSeckillSet")
    public Object setSeckillSet(@RequestBody SeckillSet seckillSet, @ApiIgnore HttpServletRequest request) {
        boolean result = activitySetService.setSeckillSet(seckillSet, (String) request.getAttribute("sysid"));
        if (result) {
            return new Result(200, true, "操作成功", null);
        }
        return new Result(204, false, "操作失败", null);
    }
    /************************************************* True 秒杀 - END *********************************************************/

    /************************************************* True 红包裂变 - START *********************************************************/

    @ApiOperation("红包裂变--红包裂变ID获取设置信息")
    @PostMapping("/getRedenvelopesSet")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "砍价设置ID", dataType = "String", required = true)})
    public Object getRedenvelopesSet(String id) {

        RedenvelopesSet redenvelopesSet = activitySetService.getRedenvelopesSet(id);
        if (redenvelopesSet == null) {
            return new Result(204, false, "获取失败", null);
        }
        return new Result(200, true, "获取成功", redenvelopesSet);
    }

    @ApiOperation("红包裂变--红包裂变设置新增/修改 （有id参数为修改 无id参数为新增）")
    @PostMapping("/setRedenvelopesSet")
    public Object setRedenvelopesSet(@RequestBody RedenvelopesSet redenvelopesSet, @ApiIgnore HttpServletRequest request) {
        boolean result = activitySetService.setRedenvelopesSet(redenvelopesSet, (String) request.getAttribute("sysid"));
        if (result) {
            return new Result(200, true, "操作成功", null);
        }
        return new Result(204, false, "操作失败", null);
    }
    /************************************************* True 红包裂变 - END *********************************************************/

    /************************************************* True 活动计数 - START *********************************************************/

    @ApiOperation("活动计数--活动浏览次数增加")
    @PostMapping("/addBrowse")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "活动ID", dataType = "String", required = true),
            @ApiImplicitParam(name = "type", value = "活动类型（4：幸运转盘；5：发优惠券；6：推荐有礼；7：秒杀活动；8：拼团活动；9：砸金蛋抽奖；10：砍价大战；11：红包裂变券；12：朋友圈）", dataType = "String", required = true)})
    public Object addBrowse(String id, String type) {
        boolean result = activitySetService.addBrowse(id, type);
        if (result) {
            return new Result(200, true, "增加成功", null);
        }
        return new Result(204, false, "增加失败", null);
    }

    @ApiOperation("活动计数--活动领券次数增加")
    @PostMapping("/addCoupon")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "活动ID", dataType = "String", required = true),
            @ApiImplicitParam(name = "type", value = "活动类型（4：幸运转盘；5：发优惠券；6：推荐有礼；7：秒杀活动；8：拼团活动；9：砸金蛋抽奖；10：砍价大战；11：红包裂变券；12：朋友圈）", dataType = "String", required = true)})
    public Object addCoupon(String id, String type) {
        boolean result = activitySetService.addCoupon(id, type);
        if (result) {
            return new Result(200, true, "增加成功", null);
        }
        return new Result(204, false, "增加失败", null);
    }

    @ApiOperation("活动计数--活动用券次数增加")
    @PostMapping("/addUse")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "活动ID", dataType = "String", required = true),
            @ApiImplicitParam(name = "type", value = "活动类型（4：幸运转盘；5：发优惠券；6：推荐有礼；7：秒杀活动；8：拼团活动；9：砸金蛋抽奖；10：砍价大战；11：红包裂变券；12：朋友圈）", dataType = "String", required = true)})
    public Object addUse(String id, String type) {
        boolean result = activitySetService.addUse(id, type);
        if (result) {
            return new Result(200, true, "增加成功", null);
        }
        return new Result(204, false, "增加失败", null);
    }

    /************************************************* True 活动计数 - END *********************************************************/
}
