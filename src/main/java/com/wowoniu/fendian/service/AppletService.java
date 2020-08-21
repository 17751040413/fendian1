package com.wowoniu.fendian.service;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.model.*;
import com.wowoniu.fendian.utils.PageUtil;

import java.util.List;
import java.util.Map;

/**
 * 小程序Sevcie
 *
 * @author yuay
 * @date 2020-08-14
 */
public interface AppletService {

    /**
     * 商铺分页条件查询
     *
     * @param map 参数
     * @return
     */
    PageUtil<UseUser> getSearchShops(Map<String, Object> map);

    /**
     * 商家ID获取店铺信息
     *
     * @param id
     * @return
     */
    UseUser getUseUserById(String id);

    /**
     * 商家ID获取商品分类
     *
     * @param useUserId
     * @return
     */
    List<WaresSortSet> getSortByUseUserId(String useUserId);

    /**
     * 商家商品分页列表
     *
     * @param map 参数
     * @return
     */
    PageUtil<Wares> getGoodsPage(Map<String, Object> map);

    /**
     * 商品ID获取信息
     *
     * @param waresId
     * @return
     */
    Wares getWaresById(String waresId);

    /**
     * 商品ID获取规格
     *
     * @param waresId
     * @return
     */
    JSONObject getWaresSpec(String waresId);

    /**
     * 购物车添加
     *
     * @param waresCart
     * @return
     */
    Boolean setGoodsCart(WaresCart waresCart);

    /**
     * 买家ID获取购物车列表
     *
     * @param buyerId
     * @return
     */
    List<WaresCart> getGoodsCartById(String buyerId, String userId);

    /**
     * 订单结算
     *
     * @param waresOrder
     * @return
     */
    boolean settlementOrder(WaresOrder waresOrder);

    /**
     * 收货地址列表
     *
     * @param buyerId
     * @return
     */
    List<ShippingAddress> getShippingAddressList(String buyerId);

    /**
     * 收货地址新增
     *
     * @param shippingAddress
     * @return
     */
    boolean setShippingAddress(ShippingAddress shippingAddress);

    /**
     * 当前店铺的订单列表
     *
     * @param buyerId
     * @param userId
     * @return
     */
    JSONObject getWaresOrderList(String buyerId, String userId);

    /**
     * 订单ID获取订单明细
     *
     * @param id
     * @return
     */
    JSONObject getWaresOrderById(String id);
}
