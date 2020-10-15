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

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
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
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "商家ID ", dataType = "String", required = true),
            @ApiImplicitParam(name = "skey", value = "skey ", dataType = "String", required = true)})
    public Object getUseUserById(String userId, String skey) {
        String openId = userMapper.selectBySkey(skey).getOpenId();
        return new Result<>(200, true, "获取成功", appletService.getUseUserById(userId, openId));
    }

    @PostMapping("/getSortByUseUserId")
    @ApiOperation("5-2-2 商家ID获取商品分类")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "商家ID ", dataType = "String", required = true)})
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
    @ApiImplicitParams({@ApiImplicitParam(name = "skey", value = "买家skey", dataType = "String", required = true),
            @ApiImplicitParam(name = "state", value = "Y :未使用 N：已使用 X：过期", dataType = "String", required = true)})
    public Object couponChoice(String skey,String state) {
        String buyerId = userMapper.selectBySkey(skey).getOpenId();
        return new Result<>(200, true, "获取成功", appletService.getCouponBuyerList(buyerId,state));
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

    @PostMapping("/recommendByUserId")
    @ApiOperation("8-2-4-1 推荐好友 -活动列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "商家ID ", dataType = "String", required = true)})
    public Object recommendByUserId(String userId) {
        return new Result<>(200, true, "获取成功", appletService.getRecommendList(userId));
    }

    @PostMapping("/recommendById")
    @ApiOperation("8-2-4-2面对面弹窗")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "活动ID ", dataType = "String", required = true)})
    public Object recommendById(String id) {
        return new Result<>(200, true, "获取成功", appletService.getRecommend(id));
    }

    @PostMapping("/getRecommendById")
    @ApiOperation("8-2-4-2 立即领取")
    @ApiImplicitParams({@ApiImplicitParam(name = "skey", value = "当前用户的skey ", dataType = "String", required = true),
            @ApiImplicitParam(name = "id", value = "推荐活动ID 返回值为优惠券ID 若返回空值则获取失败 ", dataType = "String", required = true),
            @ApiImplicitParam(name = "skey1", value = "赠送人的skey ", dataType = "String", required = true)})
    public Object getRecommendById(String id, String skey, String skey1) {
        String openId = userMapper.selectBySkey(skey).getOpenId();
        String openId1 = userMapper.selectBySkey(skey1).getOpenId();
        return new Result<>(200, true, "获取成功", appletService.addCounpon(id, openId, openId1));
    }

    @PostMapping("/lottery")
    @ApiOperation("8-2-1 8-2-6-1 砸金蛋 / 幸运大抽奖")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "商家ID", dataType = "String", required = true),
            @ApiImplicitParam(name = "type", value = "类型0：转盘；1：砸金蛋", dataType = "String", required = true)})
    public Object lottery(String userId, String type) {

        return new Result<>(200, true, "获取成功", appletService.lottery(userId, type));
    }

    @PostMapping("/luckUser")
    @ApiOperation("8-2-1 中奖/未中奖  添加参与人记录")
    @ApiImplicitParams({@ApiImplicitParam(name = "skey", value = "skey", dataType = "String", required = true),
            @ApiImplicitParam(name = "id", value = "抽奖活动ID", dataType = "String", required = true)})
    public Object luckUser(String skey, String id) {
        String openId = userMapper.selectBySkey(skey).getOpenId();
        appletService.addLuckUser(id, openId);
        return new Result<>(200, true, "添加成功", null);
    }

    @PostMapping("/luckUserList")
    @ApiOperation("8-2-1 参加名单")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "抽奖活动ID", dataType = "String", required = true)})
    public Object luckUserList(String id) {

        return new Result<>(200, true, "获取成功", appletService.luckUserList(id));
    }

    @PostMapping("/checkLuckCoupon")
    @ApiOperation("8-2-7-1 8-2-1 查看中奖券（中奖添加后领取奖券）")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "中奖详情ID 返回值为优惠券ID 若返回空值则获取失败", dataType = "String", required = true),
            @ApiImplicitParam(name = "skey", value = "skey", dataType = "String", required = true)})
    public Object checkLuckCoupon(String id, String skey) {
        String openId = userMapper.selectBySkey(skey).getOpenId();
        return new Result<>(200, true, "获取成功", appletService.checkLuckCoupon(id, openId));
    }

    @PostMapping("/spike")
    @ApiOperation("8-2-6-1 商家ID获取秒杀活动列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "商家ID", dataType = "String", required = true)})
    public Object spike(String userId) {
        return new Result<>(200, true, "获取成功", appletService.spike(userId));
    }

    @PostMapping("/spikeById")
    @ApiOperation("8-2-6-1 活动ID获取秒杀活动详情")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "活动ID", dataType = "String", required = true)})
    public Object spikeById(String id) {
        return new Result<>(200, true, "获取成功", appletService.spikeById(id));
    }

    @PostMapping("/getSpike")
    @ApiOperation("8-2-6-1 秒杀活动弹窗(秒杀成功)")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "秒杀活动ID 返回值为优惠券ID 若返回空值则获取失败", dataType = "String", required = true),
            @ApiImplicitParam(name = "skey", value = "skey", dataType = "String", required = true)})
    public Object getSpike(String id, String skey) {
        String openId = userMapper.selectBySkey(skey).getOpenId();
        return new Result<>(200, true, "获取成功", appletService.getSpike(id, openId));
    }


    @PostMapping("/couponById")
    @ApiOperation("8-2-6-1 查看券-所有的通过券ID单独查看某张券的通用接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "券ID ", dataType = "String", required = true)})
    public Object couponById(String id) {

        return new Result<>(200, true, "获取成功", appletService.couponById(id));
    }

    @PostMapping("/couponUser")
    @ApiOperation("8-2-2 领取优惠券-领取人记录")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "活动ID", dataType = "String", required = true),
            @ApiImplicitParam(name = "userId", value = "商家ID", dataType = "String", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int", required = true),
            @ApiImplicitParam(name = "startRow", value = "起始行", dataType = "int", required = true)})
    public Object couponUser(int pageSize, int startRow,String id,String userId) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("pageSize", pageSize);
        map.put("startRow", startRow);
        map.put("id",id);
        map.put("userId",userId);
        return new Result<>(200, true, "获取成功", appletService.couponUser(map));
    }

    @PostMapping("/couponInfo")
    @ApiOperation("8-2-2 领取优惠券-优惠券信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "活动ID", dataType = "String", required = true)})
    public Object couponInfo(String id) {

        return new Result<>(200, true, "获取成功", appletService.couponInfo(id));
    }

    @PostMapping("/getCoupon")
    @ApiOperation("8-2-2-2 领取优惠券-领取")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "券ID 返回值为优惠券ID 若返回空值则获取失败", dataType = "String", required = true),
            @ApiImplicitParam(name = "skey", value = "skey", dataType = "String", required = true)})
    public Object getCoupon(String id, String skey) {
        String openId = userMapper.selectBySkey(skey).getOpenId();
        return new Result<>(200, true, "获取成功", appletService.getCoupon(id, openId));
    }

    @PostMapping("/groupBuy")
    @ApiOperation("8-2-3 商家ID获取所有拼团活动")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "商家ID", dataType = "String", required = true)})
    public Object groupBuy(String userId) {
        return new Result<>(200, true, "获取成功", appletService.groupBuy(userId));
    }

    @PostMapping("/groupBuyById")
    @ApiOperation("8-2-3 活动ID获取拼团活动详情")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "活动ID", dataType = "String", required = true)})
    public Object groupBuyById(String id) {
        return new Result<>(200, true, "获取成功", appletService.groupBuyById(id));
    }

    @PostMapping("/startGroup")
    @ApiOperation("8-2-3 一键开团")
    @ApiImplicitParams({@ApiImplicitParam(name = "skey", value = "skey", dataType = "String", required = true),
            @ApiImplicitParam(name = "id", value = "活动ID 组团成功返回组团ID 否则返回空值", dataType = "String", required = true)})
    public Object startGroup(String skey, String id) {
        String openId = userMapper.selectBySkey(skey).getOpenId();
        return new Result<>(200, true, "获取成功", appletService.startGroup(openId, id));
    }

    @PostMapping("/getGroupBuyer")
    @ApiOperation("8-2-3 活动ID获取成团的信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "活动ID ", dataType = "String", required = true)})
    public Object getGroupBuyer(String id) {
        return new Result<>(200, true, "获取成功", appletService.getGroupBuyer(id));
    }

    @PostMapping("/joinGroup")
    @ApiOperation("8-2-3 参加团")
    @ApiImplicitParams({@ApiImplicitParam(name = "skey", value = "skey", dataType = "String", required = true),
            @ApiImplicitParam(name = "id", value = "活动ID ", dataType = "String", required = true)})
    public Object joinGroup(String skey, String id) {
        String openId = userMapper.selectBySkey(skey).getOpenId();
        appletService.joinGroup(openId, id);
        return new Result<>(200, true, "获取成功", null);
    }

    @PostMapping("/checkGroup")
    @ApiOperation("8-2-3 检查是否有当前拼团活动未完成的团")
    @ApiImplicitParams({@ApiImplicitParam(name = "skey", value = "skey", dataType = "String", required = true),
            @ApiImplicitParam(name = "id", value = "活动ID  返回值为true 有未完成的拼团  false 相反", dataType = "String", required = true)})
    public Object checkGroup(String skey, String id) {
        String openId = userMapper.selectBySkey(skey).getOpenId();
        return new Result<>(200, true, "获取成功", appletService.checkGroup(openId, id));
    }

    @PostMapping("/lookGroupCoupon")
    @ApiOperation("8-2-3 8-2-5-1查看券")
    @ApiImplicitParams({@ApiImplicitParam(name = "skey", value = "skey", dataType = "String", required = true),
            @ApiImplicitParam(name = "id", value = "活动ID ", dataType = "String", required = true)})
    public Object lookGroupCoupon(String skey, String id) {
        String openId = userMapper.selectBySkey(skey).getOpenId();
        return new Result<>(200, true, "获取成功", appletService.lookGroupCoupon(openId, id));
    }

    @PostMapping("/bargain")
    @ApiOperation("8-2-5-1 砍价")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "商家ID ", dataType = "String", required = true)})
    public Object bargain(String userId) {
        return new Result<>(200, true, "获取成功", appletService.bargain(userId));
    }

    @PostMapping("/bargainById")
    @ApiOperation("8-2-5-1 砍价")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "活动ID ", dataType = "String", required = true)})
    public Object bargainById(String id) {
        return new Result<>(200, true, "获取成功", appletService.bargainById(id));
    }

    @PostMapping("/startBargain")
    @ApiOperation("8-2-5-2 发起砍价")
    @ApiImplicitParams({@ApiImplicitParam(name = "skey", value = "skey", dataType = "String", required = true),
            @ApiImplicitParam(name = "id", value = "活动ID ", dataType = "String", required = true)})
    public Object startBargain(String skey, String id) {
        String openId = userMapper.selectBySkey(skey).getOpenId();
        return new Result<>(200, true, "获取成功", appletService.startBargain(openId, id));
    }

    @PostMapping("/joinBargain")
    @ApiOperation("8-2-5-4 帮砍价")
    @ApiImplicitParams({@ApiImplicitParam(name = "skey", value = "skey", dataType = "String", required = true),
            @ApiImplicitParam(name = "id", value = "活动ID 返回值price为砍掉的价格 result 为true时砍价成功 最低价 ", dataType = "String", required = true)})
    public Object joinBargain(String skey, String id) {
        String openId = userMapper.selectBySkey(skey).getOpenId();
        return new Result<>(200, true, "获取成功", appletService.joinBargain(openId, id));
    }

    @PostMapping("/bargainUser")
    @ApiOperation("8-2-5-4 参与砍价")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "活动ID ", dataType = "String", required = true)})
    public Object bargainUser(String id) {
        return new Result<>(200, true, "获取成功", appletService.bargainUserListByBargainId(id));
    }

    @PostMapping("/fission")
    @ApiOperation("8-3-1 领券激活会员-会员权益列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "商家ID ", dataType = "String", required = true)})
    public Object fission(String userId) {
        return new Result<>(200, true, "获取成功", appletService.fission(userId));
    }

    @PostMapping("/member")
    @ApiOperation("8-2-8-1 领取会员权益")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "商家ID ", dataType = "String", required = true),
            @ApiImplicitParam(name = "skey", value = "skey ", dataType = "String", required = true),
            @ApiImplicitParam(name = "price", value = "余额 ", dataType = "int", required = true),
            @ApiImplicitParam(name = "endTime", value = "到期时间 ", dataType = "String", required = true),
            @ApiImplicitParam(name = "level", value = "等级 ", dataType = "String", required = true),
            @ApiImplicitParam(name = "levelName", value = "等级名称 ", dataType = "String", required = true),
            @ApiImplicitParam(name = "lastBuy", value = "最后消费 ", dataType = "String", required = true)})
    public Object member(String userId, String skey, int price, Timestamp endTime,Integer level,String levelName,String lastBuy) {
        Member member = new Member();
        member.setUserId(userId);
        member.setSkey(skey);
        member.setPrice(price);
        member.setEndTime(endTime);
        member.setLevel(level);
        member.setLevelName(levelName);
        member.setLastBuy(lastBuy);
        int count = appletService.member(member);
        if (count==1){
            return new Result<>(200, true, "添加成功", null);
        }
        return new Result<>(204, true, "添加失败", null);
    }

    @PostMapping("/getMember")
    @ApiOperation("8-2-8-1 获取会员卡信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "商家ID ", dataType = "String", required = true),
            @ApiImplicitParam(name = "skey", value = "skey ", dataType = "String", required = true)})
    public Object getMember(String userId, String skey) {
        String openId = userMapper.selectBySkey(skey).getOpenId();
        return new Result<>(200, true, "获取成功", appletService.getMember(userId, openId));
    }

    @PostMapping("/price")
    @ApiOperation("8-2-8-2 充值")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "会员卡ID ", dataType = "String", required = true),
            @ApiImplicitParam(name = "price", value = "金额 ", dataType = "int", required = true)})
    public Object price(String id, int price) {
        appletService.price(id, price);
        return new Result<>(200, true, "充值成功", null);
    }

    @PostMapping("/getConsume")
    @ApiOperation("8-2-8-2 余额记录")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "会员卡ID ", dataType = "String", required = true)})
    public Object getConsume(String id) {
        return new Result<>(200, true, "获取成功", appletService.getConsume(id));
    }

    @PostMapping("/getFriendConsume")
    @ApiOperation("8-2-9-1 会员卡详情 - 邀请好友消费记录")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "商家ID ", dataType = "String", required = true),
            @ApiImplicitParam(name = "skey", value = "skey ", dataType = "String", required = true)})
    public Object getFriendConsume(String userId, String skey) {
        String openId = userMapper.selectBySkey(skey).getOpenId();
        return new Result<>(200, true, "获取成功", appletService.getFriendConsume(userId,openId));
    }


}
