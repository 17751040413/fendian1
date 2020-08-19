package com.wowoniu.fendian.service;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.model.UseUser;
import com.wowoniu.fendian.model.Wares;
import com.wowoniu.fendian.model.WaresCart;
import com.wowoniu.fendian.model.WaresSortSet;
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
     */
    Boolean setGoodsCart(WaresCart waresCart);
}
