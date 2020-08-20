package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * 小程序Mapper
 */
public interface AppletMapper {

    /**
     * 条件搜索商家
     *
     * @param map
     * @return
     */
    List<UseUser> searchUseUser(Map<String, Object> map);

    /**
     * 条件搜索商家数量
     *
     * @param map
     * @return
     */
    int searchUseUserCount(Map<String, Object> map);

    /**
     * 条件搜索商家的商品
     *
     * @param map
     * @return
     */
    List<Wares> searchGoods(Map<String, Object> map);

    /**
     * 条件搜索商家的商品数量
     *
     * @param map
     * @return
     */
    int searchGoodsCount(Map<String, Object> map);

    /**
     * 商家ID获取商家信息
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM use_user WHERE id = #{{id}")
    UseUser getUseUserById(@Param("id") String id);

    /**
     * ID 获取商品分类
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM wares_sort_set WHERE sort_id = #{id} AND state = 'Y' ORDER BY top_row,row")
    List<WaresSortSet> getSortById(@Param("id") String id);

    /**
     * 商品ID获取商品信息
     *
     * @param waresId
     * @return
     */
    @Select("SELECT * FROM wares WHERE id = #{waresId}")
    Wares getWaresById(@Param("waresId") String waresId);

    /**
     * 获取同买家卖家的同件规格的同种商品
     *
     * @param waresCart
     * @return
     */
    @Select("SELECT * FROM waresCart WHERE user_id = #{userId} AND buyer_id = #{buyerId} AND wares_id = #{waresId} AND spec_detail_id = #{specDetailId}")
    WaresCart getWaresCartByWares(WaresCart waresCart);

    /**
     * 更新购物车的数量
     *
     * @param number
     * @param id
     * @return
     */
    @Update("UPDATE wares_cart SET number = #{number} WHERE id = #{id}")
    int updateWaresCart(@Param("number") Integer number, @Param("id") String id);

    /**
     * 购物车添加
     *
     * @param waresCart
     * @return
     */
    @Insert("INSERT INTO wares_cart (id,user_id,buyer_id,wares_id,spec_id,spec_detail_id,number) VALUES (id,userId,buyerId,waresId,specId,specDetailId,number)")
    int addWaresCart(WaresCart waresCart);

    /**
     * 买家ID获取购物车列表
     *
     * @param buyerId
     * @param userId
     * @return
     */
    @Select("SELECT * FROM wares_cart WHERE buyer_id = #{buyerId} AND user_id = #{userId} AND order_id IS NULL")
    List<WaresCart> getGoodsCartById(@Param("buyerId") String buyerId, @Param("userId") String userId);

    /**
     * 订单新增
     *
     * @param waresOrder
     * @return
     */
    @Insert("INSERT INTO wares_order (id,user_id,buyer_id,state,create_time,address_id,delivery_method,coupon_id,freight,price,self_name,self_phone) " +
            "VALUES (id,userId,buyerId,state,NOW(),addressId,deliveryMethod,couponId,freight,price,selfName,selfPhone)")
    int addWaresOrder(WaresOrder waresOrder);

    /**
     * 购物车货物添加订单ID
     *
     * @param ids
     * @param orderId
     * @return
     */
    int updateWaresCarByOrder(@Param("ids") List<String> ids, @Param("orderId") String orderId);
}
