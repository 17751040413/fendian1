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
     * 商家ID和用户ID获取浏览记录
     *
     * @param userId
     * @param buyerId
     * @return
     */
    @Select("SELECT * FROM shop_record WHERE user_id = #{userId} AND buyer_id = #{buyerId}")
    ShopRecord getShopRecordByUserId(@Param("userId") String userId, @Param("buyerId") String buyerId);

    /**
     * 新增浏览记录
     *
     * @param shopRecord
     */
    @Insert("INSERT INTO shop_record (id,user_id,buyer_id,shop_name,shop_logo,last_time) " +
            "VALUES (#{id},#{userId},#{buyerId},#{shopName},#{shopLogo},now())")
    void addShopRecord(ShopRecord shopRecord);

    /**
     * ID更新最后浏览时间
     *
     * @param id
     */
    @Update("UPDATE shop_record SET last_time = now() WHERE id = #{id}")
    void updateShopRecordLastTime(@Param("id") String id);

    /**
     * ID 获取商品分类
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM wares_sort_detail WHERE `sort_id` = #{id} AND state = 'Y' ORDER BY top_row,`row`")
    List<WaresSortDetail> getSortById(@Param("id") String id);

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
    List<CouponBuyer> getCouponBuyerList(@Param("id") String id,@Param("state") String state);

    /**
     * 优惠券详情
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM coupon_buyer WHERE id = #{id}")
    CouponBuyer getCouponBuyerById(String id);

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
    List<BargainBuyer> bargainParticipate(@Param("buyerId") String buyerId, @Param("state") int state);

    /**
     * ID获取用户信息
     *
     * @param openId
     * @return
     */
    @Select("SELECT * FROM user WHERE open_id = #{openId}")
    User getUserByOpenId(String openId);

    /**
     * 当前用户所有订单
     *
     * @param openId
     * @return
     */
    @Select("SELECT wo.id,wo.create_time,wo.state,wo.price,wo.actual_payment,uu.shop_name,uu.shop_logo FROM wares_order wo LEFT JOIN use_user uu ON uu.id = wo.user_id WHERE wo.buyer_id = #{openId}")
    List<JSONObject> getWaresOrderAll(@Param("openId") String openId);

    /**
     * ID获取订单详情
     *
     * @param id
     * @return
     */
    @Select("SELECT wo.*,uu.shop_name,uu.shop_logo,uu.shop_address,cb.discount_amount FROM wares_order wo " +
            "LEFT JOIN use_user uu ON uu.id = wo.user_id LEFT JOIN coupon_buyer cb ON cb.id = wo.coupon_id  WHERE wo.id = #{id}")
    WaresOrder getOrderById(String id);

    /**
     * 浏览过的店铺
     *
     * @param openId
     * @return
     */
    @Select("SELECT * FROM shop_record WHERE buyer_id = #{openId} ORDER BY last_time DESC")
    List<ShopRecord> getShopRecordList(@Param("openId") String openId);

    /**
     * 优惠券新增
     *
     * @param couponBuyer
     * @return
     */
    @Insert("INSERT INTO coupon_buyer (id,buyer_id,donor_id,user_id,create_time,start_time,end_time,discount,discount_amount," +
            "activity_id,activity_type,`condition`,`range`,exchange_number,price,activity_price,pay_price,activity_name,activity_url) " +
            "VALUES (#{id},#{buyerId},#{donorId},#{userId},#{createTime},#{startTime},#{endTime},#{discount},#{discountAmount}," +
            "#{activityId},#{activityType},#{condition},#{range},#{exchangeNumber},#{price},#{activityPrice},#{payPrice},#{activityName},#{activityUrl})")
    int addCouponBuyer(CouponBuyer couponBuyer);

    /**
     * 添加参与抽奖活动的用户
     *
     * @param luckUser
     */
    @Insert("INSERT INTO luck_user (id,buyer_id,name,create_time,activity_id,avatar_url) VALUES (#{id},#{buyerId},#{name},now(),#{activityId},#{avatarUrl})")
    void addLuckUser(LuckUser luckUser);

    /**
     * 活动ID获取参与人
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM luck_user WHERE activity_id = #{id}")
    List<LuckUser> luckUserList(String id);

    /**
     * 商家ID获取秒杀活动列表
     *
     * @param userId
     * @return
     */
    @Select("SELECT * FROM seckill_set WHERE user_id = #{userId}")
    List<SeckillSet> spike(String userId);

    /**
     * ID查看券
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM coupon_buyer WHERE id = #{id}")
    CouponBuyer couponById(String id);

    /**
     * 活动ID获取领取人记录
     *
     * @param map
     * @return
     */
    List<CouponUser> getCouponUserList(Map<String,Object> map);
    int searchCouponUser(Map<String,Object> map);

    /**
     * 新增组团
     *
     * @param groupBuyer
     */
    @Insert("INSERT INTO group_buyer (id,group_id,buyer_id,users,number,create_time,end_time) VALUES (#{id},#{groupId},#{users},#{users},#{number},NOW(),#{endTime}) ")
    void addGroupBuyer(GroupBuyer groupBuyer);

    /**
     * 活动ID获取组团信息
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM group_buyer WHERE group_id = #{id}")
    GroupBuyer getGroupBuyer(String id);

    /**
     * 参团
     *
     * @param openId
     * @param id
     */
    @Update("UPDATE group_buyer SET number = number -1, users = CONCAT(users,';',#{openId}) WHERE group_id = #{id} ")
    void joinGroup(@Param("openId") String openId, @Param("id") String id);

    /**
     * 修改拼团状态
     */
    @Update("UPDATE group_buyer SET state =1 WHERE number =0")
    void updateGroupBuyerByNumber();

    /**
     * 修改拼团状态
     */
    @Update("UPDATE group_buyer SET state =2 WHERE end_time < NOW()")
    void updateGroupBuyerByTime();

    @Select("SELECT * FROM coupon_buyer WHERE activity_id = #{id} AND buyer_id = #{openId} AND effective ='N'")
    CouponBuyer lookGroupCoupon(@Param("openId") String openId, @Param("id") String id);

    /**
     * 新增发起砍价
     *
     * @param bargainBuyer
     */
    @Insert("INSERT INTO bargain_buyer (id,buyer_id,bargain_id,number,create_time,end_time,users) VALUES (#{id},#{buyerId},#{bargainId},#{number},NOW(),#{endTime},#{users}) ")
    void addBargainBuyer(BargainBuyer bargainBuyer);

    /**
     * 砍价助力人
     *
     * @param bargainUser
     */
    @Insert("INSERT INTO bargain_user (id,buyer_id,name,url,price,bargain_id) VALUES (#{id},#{buyerId},#{name},#{url},#{price},#{bargainId}) ")
    void addBargainUser(BargainUser bargainUser);

    /**
     * 帮砍价
     *
     * @param openId
     * @param id
     */
    @Update("UPDATE bargain_buyer SET number = number -1, users = CONCAT(users,';',#{openId}) WHERE id = #{id} ")
    void joinBargain(@Param("openId") String openId, @Param("id") String id);

    /**
     * ID获取创建的砍价
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM bargain_buyer WHERE id = #{id}")
    BargainBuyer getBargainBuyer(String id);

    /**
     * 砍价活动ID 获取助力人
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM bargain_user WHERE bargain_id = #{id}")
    List<BargainUser> getBargainUserListByBargainId(String id);

    /**
     * 修改砍价状态
     */
    @Update("UPDATE bargain_buyer SET state =1 WHERE number =0")
    void updateBargainBuyerByNumber();

    /**
     * 修改拼团状态
     */
    @Update("UPDATE bargain_buyer SET state =2 WHERE end_time < NOW()")
    void updateBargainBuyerByTime();

    /**
     * 新增
     *
     * @param member
     * @return
     */
    @Insert("INSERT INTO member (id,user_id,buyer_id,phone,price,create_time,name,url,number,level,level_name,end_time,remark,nick_name,gender) " +
            "VALUES (#{id},#{userId},#{buyerId},#{phone},#{price},now(),#{name},#{url},0,#{level},#{levelName},#{endTime},#{remark},#{nickName},#{gender})")
    int addMember(Member member);

    /**
     * 获取会员信息
     *
     * @param userId
     * @param openId
     * @return
     */
    @Select("SELECT * FROM member WHERE user_id = #{userId} AND buyer_id = #{openId}")
    Member getMember(@Param("userId") String userId, @Param("openId") String openId);

    @Select("SELECT * FROM member WHERE id = #{id}")
    Member getMemberById(@Param("id") String id);

    @Select("SELECT * FROM member WHERE user_id = #{userId}")
    List<Member> getMemberByUserId(@Param("userId") String userId);

    /**
     * 充值
     *
     * @param id
     * @param price
     * @return
     */
    @Select("UPDATE member SET price = price + #{price} WHERE id = #{id}")
    void price(@Param("id") String id, @Param("price") int price);

    /**
     * 添加会员消费记录
     *
     * @param memberConsume
     */
    @Insert("INSERT INTO member_consume (member_id,consume,time,type,price,actual,nick_name,url) VALUES (#{memberId},#{consume},now(),#{type},#{price},#{actual},#{nickName},#{url})")
    void addMemberConsume(MemberConsume memberConsume);

    /**
     * 消费记录
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM member_consume WHERE member_id = #{id} ORDER BY time DESC")
    List<MemberConsume> getConsume(String id);

    /**
     * 消费记录 同一家店铺的
     *
     * @param userId
     * @return
     */
    @Select("SELECT * FROM member_consume WHERE member_id IN (SELECT id FROM member WHERE user_id = #{userId} ) ORDER BY time DESC")
    List<MemberConsume> getConsumeByUserId(String userId);

    /**
     * 邀请好友到店消费记录
     *
     * @param id
     * @param openId
     * @return
     */
    @Select("SELECT * FROM coupon_user WHERE buyer_id IN (SELECT buyer_id FROM coupon_buyer WHERE effective = 'N' AND user_id = #{userId} AND donor_id = #{openId} ORDER BY receive_time DESC)")
    List<CouponUser> getFriendConsume(String userId, String openId);

    /**
     * 修改会员信息
     *
     * @param member
     * @return
     */
    int updateMember(Member member);


    /**
     * 会员消费记录
     *
     * @param map
     * @return
     */
    List<MemberConsume> getMemberRecord1(Map<String, Object> map);
    int searchMemberRecord1(Map<String, Object> map);

    /**
     * 会员余额记录
     *
     * @param map
     * @return
     */
    List<MemberConsume> getMemberRecord2(Map<String, Object> map);
    int searchMemberRecord2(Map<String, Object> map);

    /**
     * 邀请人获取邀请记录
     *
     * @param map
     * @return
     */
    List<LinkMemberUser> getMemberInviter(Map<String, Object> map);

    /**
     * 邀请人获取邀请记录总数
     *
     * @param map
     * @return
     */
    int searchMemberInviter(Map<String, Object> map);

}
