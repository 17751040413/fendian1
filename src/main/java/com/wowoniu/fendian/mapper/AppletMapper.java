package com.wowoniu.fendian.mapper;

import com.alibaba.fastjson.JSONObject;
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
    @Select("SELECT * FROM use_user WHERE id = #{id}")
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
    @Select("SELECT * FROM wares_cart WHERE user_id = #{userId} AND buyer_id = #{buyerId} AND wares_id = #{waresId} AND spec_detail_id = #{specDetailId}")
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
    @Insert("INSERT INTO wares_cart (id,user_id,buyer_id,wares_id,spec_id,spec_detail_id,number) " +
            "VALUES (#{id},#{userId},#{buyerId},#{waresId},#{specId},#{specDetailId},#{number})")
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
     * 获取最大订单编码
     *
     * @return
     */
    @Select("SELECT MAX(order_code) FROM wares_order")
    String getMaxOrderCode();

    /**
     * 订单新增
     *
     * @param waresOrder
     * @return
     */
    @Insert("INSERT INTO wares_order (id,user_id,buyer_id,state,create_time,address_id,delivery_method,coupon_id,freight,price,self_name,self_phone,cart_id) " +
            "VALUES (#{id},#{userId},#{buyerId},#{state},NOW(),#{addressId},#{deliveryMethod},#{couponId},#{freight},#{price},#{selfName},#{selfPhone},#{cartId})")
    int addWaresOrder(WaresOrder waresOrder);

    /**
     * 购物车货物添加订单ID
     *
     * @param ids
     * @param orderId
     * @return
     */
    int updateWaresCarByOrder(@Param("ids") List<String> ids, @Param("orderId") String orderId);

    /**
     * 收货地址列表
     *
     * @param buyerId
     * @return
     */
    @Select("SELECT * FROM shipping_address WHERE buyer_id = #{buyerId}")
    List<ShippingAddress> getShippingAddressList(String buyerId);

    /**
     * 收货地址新增
     *
     * @param shippingAddress
     * @return
     */
    @Insert("INSERT INTO shipping_address (id,buyer_id,name,phone,address,tab) VALUES (#{id},#{buyerId},#{name},#{phone},#{address},#{tab})")
    int addShippingAddress(ShippingAddress shippingAddress);

    /**
     * 收货地址更新
     *
     * @param shippingAddress
     * @return
     */
    @Update("UPDATE shipping_address SET name = #{name},phone = #{phone},address = #{address} WHERE id = #{id}")
    int updateShippingAddress(ShippingAddress shippingAddress);

    /**
     * 更改去除默认地址
     *
     * @return
     */
    @Update("UPDATE shipping_address SET tab = 'N' WHERE tab = 'Y'")
    int updateAddressDefaultN();

    /**
     * 当前店铺的订单列表
     *
     * @param buyerId
     * @param userId
     * @return
     */
    @Select("SELECT * FROM wares_order w LEFT JOIN use_user uu ON w.user_id = uu.id  WHERE w.buyer_id = #{buyerId} AND w.user_id = #{userId}")
    JSONObject getWaresOrderList(@Param("buyerId") String buyerId, @Param("userId") String userId);

    /**
     * 订单ID获取购物车数据
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM wares_cart c LEFT JOIN use_user uu ON c.user_id = uu.id LEFT JOIN `user` u ON c.buyer_id = u.open_id " +
            "LEFT JOIN wares w ON c.wares_id = w.id LEFT JOIN wares_spec ws ON ws.id = c.wares_id LEFT JOIN wares_spec_detail wsd ON wsd.id = c.spec_detail_id " +
            "LEFT JOIN wares_order wo ON wo.id = c.order_id  WHERE order_id = #{id}")
    JSONObject getWaresCartByOrderId(@Param("id") String id);

    /**
     * 订单ID获取订单信息
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM wares_order wo LEFT JOIN use_user uu ON wo.user_id = uu.id LEFT JOIN `user` u ON wo.buyer_id = u.open_id " +
            "LEFT JOIN shipping_address sa ON sa.id = wo.address_id LEFT JOIN coupon_set cs ON wo.coupon_id = cs.id  WHERE wo.id = #{id} ")
    JSONObject getWaresOrderById(@Param("id") String id);

    /**
     * 订单ID获取取货码
     *
     * @param id
     * @return
     */
    @Select("SELECT take_code FROM wares_order WHERE id = #{id}")
    String getTakeCodeById(@Param("id") String id);

    /**
     * 取件码更新
     *
     * @param id
     */
    @Update("UPDATE wares_order SET take_code = #{takeCode} WHERE id = #{id}")
    void updateOrderTakeCode(@Param("id") String id, @Param("takeCode") String takeCode);

    /**
     * 取件码更新
     *
     * @param id
     * @param courierNumber
     */
    @Update("UPDATE wares_order SET courier_number = #{courierNumber} WHERE id = #{id}")
    void updateOrderCourierNumber(@Param("id") String id, @Param("courierNumber") String courierNumber);

    /**
     * 订单状态更新
     *
     * @param id
     */
    @Update("UPDATE wares_order SET state = #{state} WHERE id = #{id}")
    void updateOrderState(@Param("id") String id, @Param("state") String state);

    /**
     * 买家优惠券列表
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM coupon_buyer WHERE buyer_id = #{id} ORDER BY effective DESC")
    List<CouponBuyer> getCouponBuyerList(String id);

    /**
     * 参与的拼团活动
     *
     * @param buyerId
     * @param state
     * @return
     */
    @Select("SELECT gb.*,uu.shop_name FROM group_buyer gb " +
            "LEFT JOIN group_buying g ON g.id = gb.group_id LEFT JOIN use_user uu ON uu.id = g.user_id " +
            "WHERE gb.users LIKE CONCAT('%',#{buyerId},'%') AND gb.state = #{state}")
    List<GroupBuyer> groupParticipate(@Param("buyerId") String buyerId, @Param("state") int state);

    /**
     * 我的奖券
     *
     * @param buyerId
     * @param state
     * @return
     */
    @Select("SELECT b.*,uu.shop_name,uu.shop_address,ldd.`name`,ldd.`range`,ldd.prize_name,ldd.picture_url FROM luck_buyer b " +
            "LEFT JOIN luck_draw_detail ldd ON b.luck_id = ldd.id LEFT JOIN luck_draw_set lds ON lds.id = ldd.luck_draw_id " +
            "LEFT JOIN use_user uu ON uu.id = lds.user_id  WHERE b.buyer_id = #{buyerId} AND b.state = #{state}")
    List<LuckBuyer> luckWinning(@Param("buyerId") String buyerId, @Param("state") int state);

    /**
     * 券详情
     *
     * @param id
     * @return
     */
    @Select("SELECT b.*,uu.shop_name,uu.shop_address,ldd.`name`,ldd.`range`,ldd.prize_name,ldd.picture_url FROM luck_buyer b " +
            "LEFT JOIN luck_draw_detail ldd ON b.luck_id = ldd.id LEFT JOIN luck_draw_set lds ON lds.id = ldd.luck_draw_id " +
            "LEFT JOIN use_user uu ON uu.id = lds.user_id  WHERE b.id = #{id}")
    LuckBuyer luckWinningDetail(String id);

    /**
     * 参与的砍价活动
     *
     * @param buyerId
     * @param state
     * @return
     */
    @Select("SELECT bb.*,uu.shop_name FROM bargain_buyer bb " +
            "LEFT JOIN bargaining_set bs ON bs.id = bb.bargain_id LEFT JOIN use_user uu ON uu.id = bs.user_id " +
            "WHERE bb.buyer_id = #{buyerId} AND bb.state = #{state}")
    List<BargainBuyer> bargainParticipate(String buyerId, int state);
}
