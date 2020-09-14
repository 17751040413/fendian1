package com.wowoniu.fendian.web.api.applet.controller;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.config.Constants;
import com.wowoniu.fendian.config.StaticConfig;
import com.wowoniu.fendian.mapper.UserMapper;
import com.wowoniu.fendian.model.*;
import com.wowoniu.fendian.service.AppletService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuany
 * @date 2020-08-14
 */
@Api(value = "小程序", tags = "小程序接口")
@RestController
@RequestMapping("/applet")
public class AppletController {

    @Autowired
    private AppletService appletService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/nearbyShop")
    @ApiOperation("5-1-1-1 附近商铺 /返回 use_user实体列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "lat", value = "纬度 ", dataType = "Double", required = true),
            @ApiImplicitParam(name = "lng", value = "经度 ", dataType = "Double", required = true),
            @ApiImplicitParam(name = "type", value = "根据店铺类别查询，当此字段为空时为查所有：店铺类别（0：服饰；1：零食；2：餐饮；3：水果；4：生鲜） ", dataType = "String", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int", required = true),
            @ApiImplicitParam(name = "startRow", value = "起始行", dataType = "int", required = true)})
    public Object nearbyShop(Double lat, Double lng, String type, int pageSize, int startRow) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("lat", lat);
        map.put("lng", lng);
        map.put("type", type);
        map.put("pageSize", pageSize);
        map.put("startRow", startRow);
        map.put("range", StaticConfig.getShopRange());
        return new Result<>(200, true, "获取成功", appletService.getSearchShops(map));
    }

    @PostMapping("/getUseUserById")
    @ApiOperation("5-2-1  店铺ID获取店铺信息 (5-2-9 联系店主)")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "店铺ID ", dataType = "String", required = true)})
    public Object getUseUserById(String id) {
        return new Result<>(200, true, "获取成功", appletService.getUseUserById(id));
    }

    @PostMapping("/getSortByUseUserId")
    @ApiOperation("5-2-2 商家ID获取商品分类")
    @ApiImplicitParams({@ApiImplicitParam(name = "useUserId", value = "商家ID ", dataType = "String", required = true)})
    public Object getSortByUseUserId(String id) {
        return new Result<>(200, true, "获取成功", appletService.getSortByUseUserId(id));
    }

    @PostMapping("/getGoodsPage")
    @ApiOperation("5-2-2 店铺商品分页查询")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "商家ID ", dataType = "String", required = true),
            @ApiImplicitParam(name = "priceSort", value = "价格排序（0：默认；1：由低到高 ；-1：由高到底） ", dataType = "int", required = true),
            @ApiImplicitParam(name = "sortId", value = "分类ID【空值则为查询全部】", dataType = "String", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int", required = true),
            @ApiImplicitParam(name = "startRow", value = "起始行", dataType = "int", required = true)})
    public Object getGoodsPage(String userId, int priceSort, String sortId, int pageSize, int startRow) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("userId", userId);
        map.put("priceSort", priceSort);
        map.put("sortId", sortId);
        map.put("pageSize", pageSize);
        map.put("startRow", startRow);
        return new Result<>(200, true, "获取成功", appletService.getGoodsPage(map));
    }

    @PostMapping("/getGoodsById")
    @ApiOperation("5-2-3 商品ID获取商品信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "waresId", value = "商品ID ", dataType = "String", required = true)})
    public Object getGoodsById(String waresId) {
        return new Result<>(200, true, "获取成功", appletService.getWaresById(waresId));
    }

    @PostMapping("/getGoodsSpec")
    @ApiOperation("5-2-4 商品ID获取商品规格")
    @ApiImplicitParams({@ApiImplicitParam(name = "waresId", value = "商品ID ", dataType = "String", required = true)})
    public Object getGoodsSpec(String waresId) {
        return new Result<>(200, true, "获取成功", appletService.getWaresSpec(waresId));
    }

    @PostMapping("/setGoodsCart")
    @ApiOperation("5-2-4 商品添加购物车")
    public Object setGoodsCart(@RequestBody WaresCart waresCart) {
        waresCart.setBuyerId(userMapper.selectBySkey(waresCart.getSkey()).getOpenId());
        boolean result = appletService.setGoodsCart(waresCart);
        if (result) {
            return new Result<>(200, true, "添加成功", null);
        }
        return new Result<>(204, false, "添加失败", null);
    }

    @PostMapping("/getGoodsCartById")
    @ApiOperation("5-2-5 买家skey获取购物车列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "buyerId", value = "买家skey ", dataType = "String", required = true),
            @ApiImplicitParam(name = "userId", value = "商家ID ", dataType = "String", required = true)})
    public Object getGoodsCartById(String skey, String userId) {
        String buyerId = userMapper.selectBySkey(skey).getOpenId();
        List<WaresCart> waresCartList = appletService.getGoodsCartById(buyerId, userId);
        if (CollectionUtils.isEmpty(waresCartList)) {
            return new Result<>(204, false, "获取失败", null);
        }
        return new Result<>(200, true, "获取成功", waresCartList);
    }

    @PostMapping("/settlementOrder")
    @ApiOperation("5-2-6 订单结算 生成订单-调用本接口后返回订单ID 此时订单状态为0：待付款， 付款后调用5-2-9的付款更新订单状态接口")
    public Object settlementOrder(@RequestBody WaresOrder waresOrder) {
        waresOrder.setBuyerId(userMapper.selectBySkey(waresOrder.getSkey()).getOpenId());
        waresOrder.setState(Constants.ORDER_STATE_PENDING_PAYMENT);
        String id = appletService.settlementOrder(waresOrder);
        if (StringUtils.isNotEmpty(id)) {
            return new Result<>(200, true, "结算成功", id);
        }
        return new Result<>(204, false, "结算失败", null);
    }

    @PostMapping("/getShippingAddressList")
    @ApiOperation("5-2-8 收货地址列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "skey", value = "买家skey ", dataType = "String", required = true)})
    public Object getShippingAddressList(String skey) {
        String buyerId = userMapper.selectBySkey(skey).getOpenId();
        List<ShippingAddress> shippingAddressList = appletService.getShippingAddressList(buyerId);
        if (CollectionUtils.isEmpty(shippingAddressList)) {
            return new Result<>(204, false, "获取失败", null);
        }
        return new Result<>(200, true, "获取成功", shippingAddressList);
    }

    @PostMapping("/setShippingAddress")
    @ApiOperation("5-2-8 收货地址新增/修改（无ID为新增，有ID为修改）")
    public Object setShippingAddress(@RequestBody ShippingAddress shippingAddress) {
        shippingAddress.setBuyerId(userMapper.selectBySkey(shippingAddress.getSkey()).getOpenId());
        boolean result = appletService.setShippingAddress(shippingAddress);
        if (result) {
            return new Result<>(200, true, "添加成功", null);
        }
        return new Result<>(204, false, "添加失败", null);
    }

    @PostMapping("/getWaresOrderList")
    @ApiOperation("5-2-9 当前店铺订单列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "skey", value = "买家skey ", dataType = "String", required = true),
            @ApiImplicitParam(name = "userId", value = "商家ID ", dataType = "String", required = true)})
    public Object getWaresOrderList(String skey, String userId) {
        String buyerId = userMapper.selectBySkey(skey).getOpenId();
        JSONObject jsonObject = appletService.getWaresOrderList(buyerId, userId);
        if (jsonObject == null || jsonObject.size() <= 0) {
            return new Result<>(204, false, "获取失败", null);
        }
        return new Result<>(200, true, "获取成功", jsonObject);
    }

    @PostMapping("/getWaresOrderById")
    @ApiOperation("5-2-9 订单ID获取订单详情")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "订单ID ", dataType = "String", required = true)})
    public Object getWaresOrderById(String id) {

        JSONObject jsonObject = appletService.getWaresOrderById(id);
        if (jsonObject == null || jsonObject.size() <= 0) {
            return new Result<>(204, false, "获取失败", null);
        }
        return new Result<>(200, true, "获取成功", jsonObject);
    }

    @PostMapping("/getTakeCodeById")
    @ApiOperation("5-2-9 订单ID获取取货码")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "订单ID ", dataType = "String", required = true)})
    public Object getTakeCodeById(String id) {

        String code = appletService.getTakeCodeById(id);
        if (StringUtils.isEmpity(code)) {
            return new Result<>(204, false, "获取失败", null);
        }
        return new Result<>(200, true, "获取成功", code);
    }

    @PostMapping("/updateOrderState")
    @ApiOperation("5-2-9 5-2-10 订单ID更新订单状态 待付款订单付款成功后调用")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "订单ID ", dataType = "String", required = true),
            @ApiImplicitParam(name = "state", value = " 订单状态（0：待付款；1：已付款；2：代发货；3：已发货；4：已完成；5：已关闭）", dataType = "String", required = true),
            @ApiImplicitParam(name = "courierNumber", value = "快递单号(state 状态为3已发货时 必填  其余状态码时不传参)", dataType = "String", required = true)})
    public Object updateOrderState(String id, String state, String courierNumber) {

        boolean result = appletService.updateOrderState(id, state, courierNumber);
        if (result) {
            return new Result<>(200, true, "更新成功", null);
        }
        return new Result<>(204, false, "更新失败", null);
    }

    @PostMapping("/groupParticipate")
    @ApiOperation("5-3-1 我的拼团")
    @ApiImplicitParams({@ApiImplicitParam(name = "skey", value = "买家skey", dataType = "String", required = true),
            @ApiImplicitParam(name = "state", value = "状态：0拼团中；1成功；2失败", dataType = "int", required = true)})
    public Object groupParticipate(String skey, int state) {
        String buyerId = userMapper.selectBySkey(skey).getOpenId();
        List<GroupBuyer> groupBuyerList = appletService.groupParticipate(buyerId, state);

        return new Result<>(200, true, "获取成功", groupBuyerList);
    }

    @PostMapping("/luckWinning")
    @ApiOperation("5-3-1 我的奖券")
    @ApiImplicitParams({@ApiImplicitParam(name = "skey", value = "买家skey", dataType = "String", required = true),
            @ApiImplicitParam(name = "state", value = "状态：0未兑换；1已兑换;2过期", dataType = "int", required = true)})
    public Object luckWinning(String skey, int state) {
        String buyerId = userMapper.selectBySkey(skey).getOpenId();
        List<LuckBuyer> luckBuyerList = appletService.luckWinning(buyerId, state);

        return new Result<>(200, true, "获取成功", luckBuyerList);
    }

    @PostMapping("/luckWinningDetail")
    @ApiOperation("5-3-2 券详情")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "券ID", dataType = "String", required = true)})
    public Object luckWinningDetail(String id) {
        LuckBuyer LuckBuyer = appletService.luckWinningDetail(id);

        return new Result<>(200, true, "获取成功", LuckBuyer);
    }

    @PostMapping("/bargainParticipate")
    @ApiOperation("5-3-2 我的砍价")
    @ApiImplicitParams({@ApiImplicitParam(name = "skey", value = "买家skey", dataType = "String", required = true),
            @ApiImplicitParam(name = "state", value = "状态：0砍价中；1成功；2失败", dataType = "int", required = true)})
    public Object bargainParticipate(String skey, int state) {
        String buyerId = userMapper.selectBySkey(skey).getOpenId();
        List<BargainBuyer> bargainBuyerList = appletService.bargainParticipate(buyerId, state);

        return new Result<>(200, true, "获取成功", bargainBuyerList);
    }

    @PostMapping("/couponChoice")
    @ApiOperation("5-2-11 6-1-1优惠券列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "skey", value = "买家skey", dataType = "String", required = true)})
    public Object couponChoice(String skey) {
        String buyerId = userMapper.selectBySkey(skey).getOpenId();
        List<CouponBuyer> couponBuyerList = appletService.getCouponBuyerList(buyerId);
        if (CollectionUtils.isEmpty(couponBuyerList)) {
            return new Result<>(204, false, "获取失败", null);
        }
        return new Result<>(200, true, "获取成功", couponBuyerList);
    }

    @PostMapping("/couponDetail")
    @ApiOperation("6-1-2 8-2-3 8-2-4-4 7-1-2-2 7-1-2-3 优惠券详情")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "优惠券ID ", dataType = "String", required = true)})
    public Object couponDetail(String id) {
        CouponBuyer couponBuyer = appletService.getCouponBuyerById(id);
        if (couponBuyer == null) {
            return new Result<>(204, false, "获取失败", null);
        }
        return new Result<>(200, true, "获取成功", couponBuyer);
    }

    @PostMapping("/getUserBySkey")
    @ApiOperation("7-1-1 个人中心")
    @ApiImplicitParams({@ApiImplicitParam(name = "skey", value = "Skey ", dataType = "String", required = true)})
    public Object getUserBySkey(String skey) {
        String openId = userMapper.selectBySkey(skey).getOpenId();
        User user = appletService.getUserByOpenId(openId);
        if (user == null) {
            return new Result<>(204, false, "获取失败", null);
        }
        return new Result<>(200, true, "获取成功", user);
    }

    @PostMapping("/getWaresOrderAll")
    @ApiOperation("7-1-2 我的订单")
    @ApiImplicitParams({@ApiImplicitParam(name = "skey", value = "Skey ", dataType = "String", required = true)})
    public Object getWaresOrderAll(String skey) {
        String openId = userMapper.selectBySkey(skey).getOpenId();
        List<JSONObject> orders = appletService.getWaresOrderAll(openId);
        if (CollectionUtils.isEmpty(orders)) {
            return new Result<>(204, false, "获取失败", null);
        }
        return new Result<>(200, true, "获取成功", orders);
    }

    @PostMapping("/getOrderById")
    @ApiOperation("7-1-2-2 7-1-2-3 订单详情 （调用 couponDetail 接口 传参：coupon_id 获取优惠券及活动数据）)")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "订单ID ", dataType = "String", required = true)})
    public Object getOrderById(String id) {
        JSONObject order = appletService.getOrderById(id);
        if (order == null) {
            return new Result<>(204, false, "获取失败", null);
        }
        return new Result<>(200, true, "获取成功", order);
    }

    @PostMapping("/getShopRecordList")
    @ApiOperation("7-1-4 浏览过的店铺")
    @ApiImplicitParams({@ApiImplicitParam(name = "skey", value = "Skey ", dataType = "String", required = true)})
    public Object getShopRecordList(String skey) {
        String openId = userMapper.selectBySkey(skey).getOpenId();
        List<ShopRecord> shopRecordList = appletService.getShopRecordList(openId);
        if (CollectionUtils.isEmpty(shopRecordList)) {
            return new Result<>(204, false, "获取失败", null);
        }
        return new Result<>(200, true, "获取成功", shopRecordList);
    }

}
