package com.wowoniu.fendian.mapper;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 会员活动设置汇总DAO层
 *
 * @author yuany
 * @date 2020-06-28
 */
public interface ActivitySetMapper {

    /************************************** 裂变 - START *************************************************/
    /**
     * 用户ID获取裂变详情
     *
     * @param userId
     * @return
     */
    @Select("SELECT * FROM fission_set_detail WHERE fission_id = (SELECT id FROM fission_set WHERE user_id = #{userId}) ORDER BY level")
    List<FissionSetDetail> getFissionSetDetailByUserId(@Param("userId") String userId);

    /**
     * 设置裂变启用/禁用
     *
     * @param userId
     * @param state
     * @return
     */
    @Update("UPDATE fission_set SET state = #{state} WHERE user_id = #{userId}")
    int setFissionState(@Param("userId") String userId, @Param("state") String state);

    /**
     * 商家ID获取裂变设置
     *
     * @param userId
     * @return
     */
    @Select("SELECT * FROM fission_set WHERE user_id = #{userId}")
    FissionSet getFissionSet(@Param("userId") String userId);

    /**
     * 裂变ID获取裂变详情
     *
     * @param fissionId
     * @return
     */
    @Select("SELECT * FROM fission_set_detail WHERE fission_id = #{fissionId} ORDER BY level")
    List<FissionSetDetail> getFissionSetDetailList(@Param("fissionId") String fissionId);

    /**
     * 新增裂变
     *
     * @param fissionSet
     * @return
     */
    @Insert("INSERT INTO fission_set(id,user_id,coupon_reach_day,effective_time,phone_enable,state,prevent_brush) " +
            "VALUES (#{id},#{userId},#{couponReachDay},#{effectiveTime},#{phoneEnable},#{state},#{preventBrush}) ")
    int addFissionSet(FissionSet fissionSet);

    /**
     * 新增裂变详情
     *
     * @param fissionSetDetail
     * @return
     */
    @Insert("INSERT INTO fission_set_detail(id,fission_id,level,level_name,equity,coupon_type,discount_money," +
            "use_threshold,month_number,use_range,exchange_coupon_name,discount) " +
            "VALUES (#{id},#{fissionId},#{level},#{levelName},#{equity},#{couponType},#{discountMoney},#{useThreshold}," +
            "#{monthNumber},#{useRange},#{exchangeCouponName},#{discount}) ")
    int addFissionSetDetail(FissionSetDetail fissionSetDetail);

    /**
     * 更新裂变
     *
     * @param fissionSet
     * @return
     */
    @Update("UPDATE fission_set SET coupon_reach_day=#{couponReachDay},effective_time=#{effectiveTime},phone_enable=#{phoneEnable}," +
            "state=#{state},prevent_brush=#{preventBrush} WHERE id =#{id} ")
    int updateFissionSet(FissionSet fissionSet);

    /**
     * 批量更新裂变详情
     *
     * @param fissionSetDetail
     * @return
     */
    @Update(" UPDATE fission_set_detail SET level_name=#{levelName},equity=#{equity},coupon_type=#{couponType}, discount_money=#{discountMoney}," +
            "use_threshold=#{useThreshold},month_number=#{monthNumber},use_range=#{useRange}, exchange_coupon_name=#{exchangeCouponName},discount=#{discount} WHERE id =#{id}")
    int updateFissionSetDetail(FissionSetDetail fissionSetDetail);

    /**
     * ID删除裂变详情
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM fission_set_detail WHERE id = #{id}")
    int deleteFissionSetDetail(@Param("id") String id);

    /************************************** 裂变 - END *************************************************/


    /************************************** 返利 - START *************************************************/
    /**
     * 用户ID获取返利设置
     *
     * @param userId
     * @return
     */
    @Select("SELECT * FROM rebate_set WHERE user_id = #{userId}")
    List<RebateSet> getRebateSetList(@Param("userId") String userId);

    /**
     * 设置返利启用/禁用
     *
     * @param userId
     * @param state
     * @return
     */
    @Update("UPDATE rebate_set SET state = #{state} WHERE user_id = #{userId}")
    int setRebateState(@Param("userId") String userId, @Param("state") String state);

    /**
     * 商家ID获取返利设置
     *
     * @param userId
     * @return
     */
    @Select("SELECT * FROM rebate_set WHERE user_id = #{userId}")
    RebateSet getRebateSet(@Param("userId") String userId);

    /**
     * 返利ID获取返利详情
     *
     * @param rebateId
     * @return
     */
    @Select("SELECT * FROM rebate_set_detail WHERE rebate_id = #{rebateId} ORDER BY level")
    List<RebateSetDetail> getRebateSetDetailList(@Param("rebateId") String rebateId);

    /**
     * 新增返利
     *
     * @param rebateSet
     * @return
     */
    @Insert("INSERT INTO rebate_set(id,user_id,state,phone_enable,new_user_enable,rebate_enable,old_user_enable,recharge,recharge_rebate_enable," +
            "prevent_brush,effective_time,proportion,new_user_proportion,fission_help_number,first_help_money,old_help_money,rule) " +
            "VALUES (#{id},#{userId},#{state},#{phoneEnable},#{newUserEnable},#{rebateEnable},#{oldUserEnable},#{recharge},#{rechargeRebateEnable}," +
            "#{preventBrush},#{effectiveTime},#{proportion},#{newUserProportion},#{fissionHelpNumber},#{firstHelpMoney},#{oldHelpMoney},#{rule}) ")
    int addRebateSet(RebateSet rebateSet);

    /**
     * 更新返利
     *
     * @param rebateSet
     * @return
     */
    @Update("UPDATE rebate_set SET state=#{state},phone_enable=#{phoneEnable},new_user_enable=#{newUserEnable},rebate_enable=#{rebateEnable}," +
            "old_user_enable=#{oldUserEnable},recharge=#{recharge},recharge_rebate_enable=#{rechargeRebateEnable},prevent_brush=#{preventBrush}," +
            "effective_time=#{effectiveTime},proportion=#{proportion},new_user_proportion=#{newUserProportion},fission_help_number=#{fissionHelpNumber}," +
            "first_help_money=#{firstHelpMoney},old_help_money=#{oldHelpMoney},rule=#{rule} WHERE id =#{id} ")
    int updateRebateSet(RebateSet rebateSet);

    /**
     * 新增返利详情
     *
     * @param rebateSetDetail
     * @return
     */
    @Insert("INSERT INTO rebate_ret_detail(id,level,level_name,gain_factor,rebate_ratio,rebate_id) VALUES (#{id},#{level},#{levelName},#{gainFactor},#{rebateRatio},#{rebateId})")
    int addRebateSetDetail(RebateSetDetail rebateSetDetail);

    /**
     * 更新裂变详情
     *
     * @param rebateSetDetail
     * @return
     */
    @Update("UPDATE rebate_set_detail SET level_name=#{item.levelName},gain_factor=#{item.gainFactor},rebate_ratio=#{item.rebateRatio},rebate_id=#{item.rebateId} WHERE id =#{id}")
    int updateRebateSetDetail(RebateSetDetail rebateSetDetail);

    /**
     * ID删除返利详情
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM rebate_set_detail WHERE id = #{id}")
    int deleteRebateSetDetail(@Param("id") String id);

    /************************************** 返利 - END *************************************************/


    /************************************** 分销 - START *************************************************/
    /**
     * 商家ID获取分销设置
     *
     * @param userId
     * @return
     */
    @Select("SELECT * FROM distribution_set WHERE user_id = #{userId}")
    DistributionSet getDistributionSet(@Param("userId") String userId);

    /**
     * 设置分销启用/禁用
     *
     * @param userId
     * @param state
     * @return
     */
    @Update("UPDATE distribution_set SET state = #{state} WHERE user_id = #{userId}")
    int setDistributionSetState(@Param("userId") String userId, @Param("state") String state);

    /**
     * 用户ID获取分销优惠券
     *
     * @param userId
     * @return
     */
    @Select("SELECT * FROM distribution_coupon WHERE distribution_id = (SELECT id FROM distribution_set WHERE user_id = #{userId})")
    List<DistributionCoupon> getDistributionCouponList(@Param("userId") String userId);

    /**
     * 新增分销
     *
     * @param distributionSet
     * @return
     */
    @Insert("INSERT INTO distribution_set(id,user_id,state,commission_ratio,prevent_brush) VALUES (#{id},#{userId},#{state},#{commissionRatio},#{preventBrush})")
    int addDistributionSet(DistributionSet distributionSet);

    /**
     * 更新分销
     *
     * @param distributionSet
     * @return
     */
    @Update("UPDATE distribution_set SET state = #{state} ,commission_ratio = #{commissionRatio},prevent_brush = #{preventBrush} WHERE id =#{id} ")
    int updateDistributionSet(DistributionSet distributionSet);

    /**
     * 新增分销优惠券
     *
     * @param distributionCoupon
     * @return
     */
    @Insert("INSERT INTO distribution_coupon(id,distribution_id,phone_enable,type,money,discount,threshold,effective_type,effective_day,start_time,end_time,range) " +
            "VALUES (#{id},#{distributionId},#{phoneEnable},#{type},#{money},#{discount},#{threshold},#{effectiveType},#{effectiveDay},#{startTime},#{endTime},#{range})")
    int addDistributionCoupon(DistributionCoupon distributionCoupon);

    /**
     * 批量更新分销优惠券
     *
     * @param distributionCouponList
     * @return
     */
    int updateDistributionCouponList(@Param("distributionCouponList") List<DistributionCoupon> distributionCouponList);

    /**
     * ID删除返利详情
     *
     * @param id
     * @return
     */
    @Delete("UPDATE distribution_coupon SET end_time = now() WHERE id = #{id}")
    int updateDistributionCouponEndTime(@Param("id") String id);

    /************************************** 分销 - END *************************************************/

    /************************************** 商城 - START *************************************************/
    /**
     * 商家ID获取商城设置
     *
     * @param userId
     * @return
     */
    @Select("SELECT * FROM shopping_mall_set WHERE user_id = #{userId}")
    ShoppingMallSet getShoppingMallSet(@Param("userId") String userId);

    /**
     * 设置商城启用/禁用
     *
     * @param userId
     * @param state
     * @return
     */
    @Update("UPDATE shopping_mall_set SET state = #{state} WHERE user_id = #{userId}")
    int setShoppingMallSetState(@Param("userId") String userId, @Param("state") String state);

    /**
     * 新增商城设置
     *
     * @param shoppingMallSet
     * @return
     */
    @Insert("INSERT INTO shopping_mall_set(id,user_id,state,self_raising,distribution,freight,recommend,prevent_brush) " +
            "VALUES (#{id},#{userId},#{state},#{selfRaising},#{distribution},#{freight},#{ecommend},#{preventBrush})")
    int addShoppingMallSet(ShoppingMallSet shoppingMallSet);

    /**
     * 更新商城设置
     *
     * @param shoppingMallSet
     * @return
     */
    @Update("UPDATE shopping_mall_set SET state = #{state} ,self_raising = #{selfRaising},distribution = #{distribution},freight=#{freight},recommend=#{recommend},prevent_brush=#{preventBrush} WHERE id =#{id} ")
    int updateShoppingMallSet(ShoppingMallSet shoppingMallSet);

    /**
     * 状态获取订单列表
     *
     * @param userId
     * @param state
     * @return
     */
    @Select("SELECT * FROM wares_order WHERE user_id = #{userId} AND state = #{state}")
    List<WaresOrder> getWaresOrderList(@Param("userId") String userId, @Param("state") String state);

    @Select("SELECT * FROM wares_sort_set WHERE user_id = #{userId}")
    WaresSortSet getWaresSortSet(String userId);

    /**
     * 新增商品分类管理启用/禁用标识
     *
     * @param waresSortSet
     * @return
     */
    @Insert("INSERT INTO wares_sort_set (user_id,state) VALUES (#{userId},#{state})")
    int addWaresSortSet(WaresSortSet waresSortSet);

    /**
     * 修改商品分类管理启用/禁用标识
     *
     * @param userId
     * @param state
     * @return
     */
    @Update("UPDATE wares_sort_set SET state = #{state} WHERE id = #{id} AND user_id #{userId}")
    int setWaresSortSetState(@Param("userId") String userId, @Param("state") String state);

    /**
     * 分类ID获取分类列表
     *
     * @param sortId
     * @return
     */
    @Select("SELECT * FROM wares_sort_detail WHERE sort_id = #{sortId} ORDER BY top_row,state,id ASC")
    List<WaresSortDetail> getWaresSortDetailList(@Param("sortId") String sortId);

    /**
     * 分类ID获取置顶分类列表
     *
     * @param sortId
     * @return
     */
    @Select("SELECT * FROM wares_sort_detail WHERE sort_id = #{sortId} AND top = #{top} ORDER BY top_row ASC")
    List<WaresSortDetail> getWaresSortDetailListByTop(@Param("sortId") String sortId);

    /**
     * 分类详情ID获取商品分类
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM WHERE wares_sort_detail id = #{id}")
    WaresSortDetail getWaresSortDetail(@Param("id") String id);

    /**
     * 新增商品分类
     *
     * @param waresSortDetail
     * @return
     */
    @Insert("INSERT INTO wares_sort_detail(id,state,name,top_row,sort_id) VALUES (#{id},#{state},#{name},#{topRow},#{sortId})")
    int addWaresSortDetail(WaresSortDetail waresSortDetail);

    /**
     * 修改商品分类
     *
     * @param waresSortDetail
     * @return
     */
    @Update("UPDATE wares_sort_detail SET state = #{state},name=#{name},top_row=#{topRow},sort_id=#{sortId} WHERE id = #{id}")
    int updateWaresSortDetail(WaresSortDetail waresSortDetail);

    /**
     * 商品分类置顶
     *
     * @param waresSortDetail
     * @return
     */
    int updateWaresSortDetailBatch(@Param("waresSortDetail") List<WaresSortDetail> waresSortDetail);

    /**
     * 置顶排序获取分类
     *
     * @param topRow
     * @return
     */
    @Select("SELECT * FROM wares_sort_detail WHERE top_row = #{topRow}")
    WaresSortDetail getWaresSortDetailByTopRow(@Param("topRow") Integer topRow);

    /**
     * 用户ID获取分类列表
     *
     * @param state
     * @param userId
     * @return
     */
    @Select("SELECT * FROM wares_sort_detail WHERE sort_id = (SELECT id FROM wares_sort_set WHERE user_id = #{userId}) " +
            "AND state =#{state} ORDER BY top_row,state,id ASC")
    List<WaresSortDetail> getWaresSortDetailListByUserId(@Param("state") String state, @Param("userId") String userId);

    /**
     * 商品列表条件查询
     *
     * @param userId
     * @param state
     * @param time
     * @param sales
     * @param sortDetailId
     * @return
     */
    List<Wares> getWaresList(@Param("userId") String userId, @Param("state") String state, @Param("time") String time,
                             @Param("sales") String sales, @Param("sortDetailId") String sortDetailId);

    /**
     * 新增商品
     *
     * @param wares
     * @return
     */
    @Insert("INSERT INTO wares(id,title,price,stock,freight,sort_id,distribution_commission,hide,picture_url,on_shelf,sales_volume,shelf_time,user_id) " +
            "VALUES (#{id},#{title},#{price},#{stock},#{freight},#{sortId},#{distributionCommission},#{hide},#{pictureUrl},#{onShelf},#{salesVolume},#{shelfTime},#{userId})")
    int addWares(Wares wares);

    /**
     * 更新商品
     *
     * @param wares
     * @return
     */
    @Update("UPDATE wares SET title = #{title},price=#{price},stock=#{stock},freight=#{freight},sort_id=#{sortId}," +
            "distribution_commission=#{distributionCommission},hide=#{hide},picture_url=#{pictureUrl},on_shelf=#{onShelf}，" +
            "sales_volume =#{salesVolume},shelf_time=#{shelfTime} WHERE id = #{id}")
    int updateWares(Wares wares);

    /**
     * 商品ID获取规格
     *
     * @param waresId
     * @return
     */
    @Select("SELECT * FROM wares_spec WHERE wares_id = #{waresId}")
    List<WaresSpec> getWaresSpecList(String waresId);

    /**
     * 规格ID获取规格详情
     *
     * @param waresSpecList
     * @return
     */
    List<WaresSpecDetail> getWaresSpecDetailList(@Param("waresSpecList") List<WaresSpec> waresSpecList);

    /**
     * 新增规格
     */
    @Insert("INSERT INTO wares_spec (id,spec,wares_id) VALUES (#{id},#{spec},#{waresId})")
    int addWaresSpec(WaresSpec waresSpec);

    /**
     * 修改规格
     */
    @Insert("UPDATE wares_spec SET spec = #{spec} WHERE id = #{id}")
    int updateWaresSpec(WaresSpec waresSpec);

    /**
     * 新增规格详情-批量
     */
    int addWaresSpecDetailBatch(@Param("waresSpecDetailList") List<WaresSpecDetail> waresSpecDetailList);

    /**
     * 修改规格详情-批量
     */
    int updateWaresSpecDetailBatch(@Param("waresSpecDetailList") List<WaresSpecDetail> waresSpecDetailList);

    /************************************** 商城 - END *************************************************/

    /************************************** 抽奖 - START *************************************************/

    /**
     * 抽奖ID获取抽奖信息
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM luck_draw_set WHERE id = #{id} ")
    LuckDrawSet getLuckDrawSet(@Param("id") String id);

    /**
     * 抽奖ID获取抽奖详情
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM luck_draw_detail WHERE luck_draw_id = #{id} ")
    List<LuckDrawDetail> getLuckDrawDetailList(@Param("id") String id);

    /**
     * 新增抽奖
     *
     * @param luckDrawSet
     * @return
     */
    @Insert("INSERT INTO luck_draw_set (id,user_id,title,start_time,end_time,phone_enable,person_day,person_frequency,probability," +
            "increase_times,`describe`,picture_url,rule,prevent_brush,music_id,type) " +
            "VALUES (#{id},#{userId},#{title},#{startTime},#{endTime},#{phoneEnable},#{personDay},#{personFrequency},#{probability}," +
            "#{increaseTimes},#{describe},#{pictureUrl},#{rule},#{preventBrush},#{musicId},#{type})")
    int addLuckDrawSet(LuckDrawSet luckDrawSet);

    /**
     * 修改抽奖
     *
     * @param luckDrawSet
     * @return
     */
    @Insert("UPDATE luck_draw_set SET title = #{title},start_time=#{startTime},end_time=#{endTime},phone_enable=#{phoneEnable},person_day=#{personDay}," +
            "person_frequency=#{personFrequency},probability=#{probability},increase_times=#{increaseTimes},`describe`=#{describe},picture_url=#{pictureUrl}," +
            "prevent_brush=#{preventBrush},music_id=#{musicId} WHERE id = #{id}")
    int updateLuckDrawSet(LuckDrawSet luckDrawSet);

    /**
     * 新增抽奖详情-批量
     */
    int addLuckDrawDetailBatch(@Param("luckDrawDetailList") List<LuckDrawDetail> luckDrawDetailList);

    /**
     * 修改抽奖详情
     */
    @Update("UPDATE luck_draw_detail SET `name` = #{name},`type`  = #{type},coupon_type = #{couponType},preferential= #{preferential},satisfy = #{satisfy},`range` = #{range},effective_type = #{effectiveType},effective_day = #{effectiveDay},prize_number = #{prizeNumber},day_max = #{dayMax},start_time = #{startTime},end_time = #{endTime},discount = #{discount},prize_name = #{prizeName},picture_url = #{pictureUrl} WHERE id = #{id}")
    int updateLuckDrawDetail(LuckDrawDetail luckDrawDetail);


    /**
     * 删除抽奖详情-批量
     */
    int deleteLuckDrawDetailBatch(@Param("luckDrawDetailList") List<LuckDrawDetail> luckDrawDetailList);

    /************************************** 抽奖 - END *************************************************/

    /************************************** 优惠券 - START *************************************************/

    /**
     * ID 获取优惠券设置
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM coupon_set WHERE id = #{id}")
    CouponSet getCouponSet(@Param("id") String id);

    /**
     * 新增优惠券设置
     */
    @Insert("INSERT INTO coupon_set (id,order_coupon,new_customers,phone_enable,type,money,discount,threshold,effective_type,effective_day," +
            "start_time,end_time,`range`,coupon_number,`describe`,picture_url,prevent_brush,music_url,user_id) " +
            "VALUES (#{id},#{orderCoupon},#{newCustomers},#{phoneEnable},#{type},#{money},#{discount},#{threshold},#{effectiveType},#{effectiveDay}," +
            "#{startTime},#{endTime},#{range},#{couponNumber},#{describe},#{pictureUrl},#{preventBrush},#{musicUrl},#{userId}) ")
    int addCouponSet(CouponSet couponSet);

    /**
     * 修改优惠券设置
     */
    @Update("UPDATE coupon_set SET order_coupon = #{orderCoupon},new_customers = #{newCustomers},phone_enable = #{phoneEnable},type = #{type},money = #{money}," +
            "discount = #{discount},threshold = #{threshold},effective_type = #{effectiveType},effective_day = #{effectiveDay},start_time = #{startTime}," +
            "end_time = #{endTime},`range` = #{range},coupon_number = #{couponNumber},`describe` = #{describe},picture_url = #{pictureUrl}," +
            "prevent_brush = #{preventBrush},music_url = #{musicUrl} WHERE id = #{id}")
    int updateCouponSet(CouponSet couponSet);

    /************************************** 优惠券 - END *************************************************/

    /************************************** 拼团 - START *************************************************/
    /**
     * ID获取拼团设置
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM group_buying WHERE id = #{id}")
    GroupBuying getGroupBuying(@Param("id") String id);

    /**
     * 新增优惠券设置
     */
    @Insert("INSERT INTO group_buying (id,head_picture_url,title,start_time,end_time,phone_enable,stock,original_price,group_price,payment_type," +
            "pay_advance,group_number,group_time,time_unit,automatic_group,`describe`,picture_url,rule,prevent_brush,music_url,user_id) " +
            "VALUES (#{id},#{headPictureUrl},#{title},#{startTime},#{endTime},#{phoneEnable},#{stock},#{originalPrice},#{groupPrice},#{paymentType}," +
            "#{payAdvance},#{groupNumber},#{groupTime},#{timeUnit},#{automaticGroup},#{describe},#{pictureUrl},#{rule},#{preventBrush},#{musicUrl},#{userId})")
    int addGroupBuying(GroupBuying groupBuying);

    /**
     * 修改优惠券设置
     */
    @Update("UPDATE group_buying SET head_picture_url = #{headPictureUrl},title = #{title},start_time = #{startTime},end_time = #{endTime},phone_enable = #{phoneEnable}," +
            "stock = #{stock},original_price = #{originalPrice},group_price = #{groupPrice},payment_type = #{paymentType},pay_advance = #{payAdvance}," +
            "group_number = #{groupNumber},group_time = #{groupTime},time_unit = #{timeUnit},automatic_group = #{automaticGroup},`describe` = #{describe}," +
            "picture_url = #{pictureUrl}, rule = #{rule},prevent_brush = #{preventBrush},music_url = #{musicUrl} WHERE id = #{id}")
    int updateGroupBuying(GroupBuying groupBuying);
    /************************************** 拼团 - END *************************************************/

    /************************************** 推荐 - START *************************************************/
    /**
     * 推荐ID获取推荐信息
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM recommend_set WHERE id = #{id}")
    RecommendSet getRecommendSet(@Param("id") String id);

    /**
     * 推荐新增
     */
    @Insert("INSERT INTO recommend_set (id,title,start_time,end_time,phone_enable,prevent_brush,music_url,user_id,recommend_gift_type," +
            "recommend_money,recommend_threshold,recommend_effective_type,recommend_effective_day,recommend_start_time,recommend_end_time," +
            "recommend_range,recommend_gift_number,recommend_discount,recommend_gift_name,`describe`,picture_url,rule,recommended_gift_type," +
            "recommended_money,recommended_threshold,recommended_effective_type,recommended_effective_day,recommended_start_time,recommended_end_time," +
            "recommended_range,recommended_gift_number,recommended_discount,recommended_gift_name,recommended_new_customers) " +
            "VALUES (#{id},#{title},#{startTime},#{endTime},#{phoneEnable},#{preventBrush},#{musicUrl},#{userId},#{recommendGiftType},#{recommendMoney}," +
            "#{recommendThreshold},#{recommendEffectiveType},#{recommendEffectiveDay},#{recommendStartTime},#{recommendEndTime},#{recommendRange},#{recommendGiftNumber}," +
            "#{recommendDiscount},#{recommendGiftName},#{describe},#{pictureUrl},#{rule},#{recommendedGiftType},#{recommendedMoney},#{recommendedThreshold}," +
            "#{recommendedEffectiveType},#{recommendedEffectiveDay},#{recommendedStartTime},#{recommendedEndTime},#{recommendedRange},#{recommendedGiftNumber}," +
            "#{recommendedDiscount},#{recommendedGiftName},#{recommendedNewCustomers})")
    int addRecommendSet(RecommendSet recommendSet);

    /**
     * 推荐修改
     */
    @Update("UPDATE recommend_set SET title = #{title},start_time = #{startTime},end_time = #{endTime},phone_enable = #{phoneEnable},prevent_brush = #{preventBrush}," +
            "music_url = #{musicUrl},recommend_gift_type = #{recommendGiftType},recommend_money = #{recommendMoney},recommend_threshold = #{recommendThreshold}," +
            "recommend_effective_type = #{recommendEffectiveType},recommend_effective_day = #{recommendEffectiveDay},recommend_start_time = #{recommendStartTime}," +
            "recommend_end_time = #{recommendEndTime},recommend_range = #{recommendRange},recommend_gift_number = #{recommendGiftNumber}," +
            "recommend_discount = #{recommendDiscount},recommend_gift_name = #{recommendGiftName},`describe`  = #{describe},picture_url = #{pictureUrl}," +
            "rule = #{rule},recommended_gift_type = #{recommendedGiftType},recommended_money = #{recommendedMoney},recommended_threshold = #{recommendedThreshold}," +
            "recommended_effective_type = #{recommendedEffectiveType},recommended_effective_day = #{recommendedEffectiveDay},recommended_start_time = #{recommendedStartTime}," +
            "recommended_end_time = #{recommendedEndTime},recommended_range = #{recommendedRange},recommended_gift_number = #{recommendedGiftNumber}," +
            "recommended_discount = #{recommendedDiscount},recommended_gift_name = #{recommendedGiftName},recommended_new_customers = #{recommendedNewCustomers} " +
            "WHERE id = #{id}")
    int updateRecommendSet(RecommendSet recommendSet);
    /************************************** 推荐 - END *************************************************/

    /************************************** 砍价 - START *************************************************/
    /**
     * 砍价ID获取设置信息
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM bargaining_set WHERE id = #{id}")
    BargainingSet getBargainingSet(String id);

    /**
     * 砍价设置新增
     *
     * @param bargainingSet
     * @return
     */
    @Insert("INSERT INTO bargaining_set (id,head_picture_url,title,start_time,end_time,phone_enable,stock,original_price,floor_price,bargaining_frequency," +
            "floor_price_enable,pay_type,`describe`,picture_url,rule,prevent_brush,music_url,user_id) VALUES (#{id},#{headPictureUrl},#{title},#{startTime}," +
            "#{endTime},#{phoneEnable},#{stock},#{originalPrice},#{floorPrice},#{bargainingFrequency},#{floorPriceEnable},#{payType},#{describe},#{pictureUrl}," +
            "#{rule},#{preventBrush},#{musicUrl},#{userId}) ")
    int addBargainingSet(BargainingSet bargainingSet);

    /**
     * 砍价设置修改
     *
     * @param bargainingSet
     * @return
     */
    @Update("UPDATE bargaining_set SET head_picture_url = #{headPictureUrl},title = #{title},start_time = #{startTime},end_time = #{endTime}," +
            "phone_enable = #{phoneEnable},stock = #{stock},original_price = #{originalPrice},floor_price = #{floorPrice},bargaining_frequency = #{bargainingFrequency}," +
            "floor_price_enable = #{floorPriceEnable},pay_type = #{payType},`describe` = #{describe},picture_url = #{pictureUrl},rule = #{rule}," +
            "prevent_brush = #{preventBrush},music_url = #{musicUrl} WHERE id = #{id}")
    int updateBargainingSet(BargainingSet bargainingSet);

    /************************************** 砍价 - END *************************************************/

    /************************************** 朋友圈分享 - START *************************************************/

    /**
     * 朋友圈分享ID获取设置信息
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM share_friends WHERE id = #{id}")
    ShareFriends getShareFriends(String id);

    /**
     * 朋友圈分享新增
     */
    @Insert("INSERT INTO share_friends (id,copywriting,picture_url,user_id) VALUES (#{id},#{copywriting},#{pictureUrl},#{userId})")
    int addShareFriends(ShareFriends shareFriends);

    /**
     * 朋友圈分享修改
     */
    @Update("UPDATE share_friends SET copywriting = #{copywriting},picture_url = #{pictureUrl} WHERE id = #{id}")
    int updateShareFriends(ShareFriends shareFriends);

    /************************************** 朋友圈分享 - END *************************************************/

    /************************************** 秒杀 - START *************************************************/

    /**
     * 秒杀ID获取设置信息
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM seckill_set WHERE id = #{id}")
    SeckillSet getSeckillSet(String id);

    /**
     * 秒杀新增
     *
     * @return
     */
    @Insert("INSERT INTO seckill_set (id,head_picture_url,title,start_time,end_time,phone_enable,stock,original_price,seckill_price," +
            "frequency_personal,payment_type,pay_advance,`describe`,picture_url,rule,prevent_brush,music_url,user_id) VALUES (#{id}," +
            "#{headPictureUrl},#{title},#{startTime},#{endTime},#{phoneEnable},#{stock},#{originalPrice},#{seckillPrice},#{frequencyPersonal}," +
            "#{paymentType},#{payAdvance},#{describe},#{pictureUrl},#{rule},#{preventBrush},#{musicUrl},#{userId})")
    int addSeckillSet(SeckillSet seckillSet);

    /**
     * 秒杀修改
     *
     * @return
     */
    @Update("UPDATE seckill_set SET head_picture_url = #{headPictureUrl},title = #{title},start_time = #{startTime},end_time = #{endTime}," +
            "phone_enable = #{phoneEnable},stock = #{stock},original_price = #{originalPrice},seckill_price = #{seckillPrice}," +
            "frequency_personal = #{frequencyPersonal},payment_type = #{paymentType},pay_advance = #{payAdvance},`describe` = #{describe}," +
            "picture_url = #{pictureUrl},rule = #{rule},prevent_brush = #{preventBrush},music_url = #{musicUrl} WHERE id = #{id}")
    int updateSeckillSet(SeckillSet seckillSet);

    /************************************** 秒杀 - END *************************************************/

    /************************************** 红包裂变 - START *************************************************/

    /**
     * 红包裂变ID获取设置信息
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM redenvelopes_set WHERE id = #{id}")
    RedenvelopesSet getRedenvelopesSet(String id);

    /**
     * 红包裂变新增
     *
     * @param redenvelopesSet
     * @return
     */
    @Insert("INSERT INTO redenvelopes_set (id,title,start_time,end_time,phone_enable,new_customers,type,money,discount,threshold,effective_type,effective_day," +
            "coupon_start_time,coupon_end_time,`range`,reward_peoples,reward_condition,total,`describe`,picture_url,prevent_brush,user_id) " +
            "VALUES (#{id},#{title},#{startTime},#{endTime},#{phoneEnable},#{newCustomers},#{type},#{money},#{discount},#{threshold},#{effectiveType}," +
            "#{effectiveDay},#{couponStartTime},#{couponEndTime},#{range},#{rewardPeoples},#{rewardCondition},#{total},#{describe},#{pictureUrl},#{preventBrush},#{userId})")
    int addRedenvelopesSet(RedenvelopesSet redenvelopesSet);

    /**
     * 红包裂变修改
     *
     * @param redenvelopesSet
     * @return
     */
    @Update("UPDATE redenvelopes_set SET title = #{title},start_time = #{startTime},end_time = #{endTime},phone_enable = #{phoneEnable},new_customers = #{newCustomers}," +
            "type = #{type},money = #{money},discount = #{discount},threshold = #{threshold},effective_type = #{effectiveType},effective_day = #{effectiveDay}," +
            "coupon_start_time = #{couponStartTime},coupon_end_time = #{couponEndTime},`range` = #{range},reward_peoples = #{rewardPeoples}," +
            "reward_condition = #{rewardCondition},total = #{total},`describe` = #{describe},picture_url = #{pictureUrl}," +
            "prevent_brush = #{preventBrush} WHERE id = #{id}")
    int updateRedenvelopesSet(RedenvelopesSet redenvelopesSet);

    /************************************** 红包裂变 - END *************************************************/

    /************************************************* 活动计数 - START *********************************************************/

    /**
     * 抽奖浏览数量增加
     *
     * @param id
     * @return
     */
    @Update("UPDATE luck_draw_set SET today_browse = today_browse + 1,browse = browse + 1 WHERE id = #{id}")
    int luckDrawBrowse(@Param("id") String id);

    /**
     * 优惠券浏览数量增加
     *
     * @param id
     * @return
     */
    @Update("UPDATE coupon_set SET today_browse = today_browse + 1,browse = browse + 1 WHERE id = #{id}")
    int couponBrowse(@Param("id") String id);

    /**
     * 推荐浏览数量增加
     *
     * @param id
     * @return
     */
    @Update("UPDATE recommend_set SET today_browse = today_browse + 1,browse = browse + 1 WHERE id = #{id}")
    int recommendBrowse(@Param("id") String id);

    /**
     * 秒杀浏览数量增加
     *
     * @param id
     * @return
     */
    @Update("UPDATE seckill_set SET today_browse = today_browse + 1,browse = browse + 1 WHERE id = #{id}")
    int seckillBrowse(@Param("id") String id);

    /**
     * 拼团浏览数量增加
     *
     * @param id
     * @return
     */
    @Update("UPDATE group_buying SET today_browse = today_browse + 1,browse = browse + 1 WHERE id = #{id}")
    int groupBrowse(@Param("id") String id);

    /**
     * 砍价浏览数量增加
     *
     * @param id
     * @return
     */
    @Update("UPDATE bargaining_set SET today_browse = today_browse + 1,browse = browse + 1 WHERE id = #{id}")
    int bargainingBrowse(@Param("id") String id);

    /**
     * 红包浏览数量增加
     *
     * @param id
     * @return
     */
    @Update("UPDATE redenvelopes_set SET today_browse = today_browse + 1,browse = browse + 1 WHERE id = #{id}")
    int redenvelopesBrowse(@Param("id") String id);

    /**
     * 抽奖领券数量增加
     *
     * @param id
     * @return
     */
    @Update("UPDATE luck_draw_set SET today_receive = today_receive + 1,receive = receive + 1 WHERE id = #{id}")
    int luckDrawCoupon(@Param("id") String id);

    /**
     * 优惠券领券数量增加
     *
     * @param id
     * @return
     */
    @Update("UPDATE coupon_set SET today_receive = today_receive + 1,receive = receive + 1 WHERE id = #{id}")
    int couponCoupon(@Param("id") String id);

    /**
     * 推荐领券数量增加
     *
     * @param id
     * @return
     */
    @Update("UPDATE recommend_set SET today_receive = today_receive + 1,receive = receive + 1 WHERE id = #{id}")
    int recommendCoupon(@Param("id") String id);

    /**
     * 秒杀领券数量增加
     *
     * @param id
     * @return
     */
    @Update("UPDATE seckill_set SET today_receive = today_receive + 1,receive = receive + 1 WHERE id = #{id}")
    int seckillCoupon(@Param("id") String id);

    /**
     * 拼团领券数量增加
     *
     * @param id
     * @return
     */
    @Update("UPDATE group_buying SET today_receive = today_receive + 1,receive = receive + 1 WHERE id = #{id}")
    int groupCoupon(@Param("id") String id);

    /**
     * 砍价领券数量增加
     *
     * @param id
     * @return
     */
    @Update("UPDATE bargaining_set SET today_receive = today_receive + 1,receive = receive + 1 WHERE id = #{id}")
    int bargainingCoupon(@Param("id") String id);

    /**
     * 红包领券数量增加
     *
     * @param id
     * @return
     */
    @Update("UPDATE redenvelopes_set SET today_receive = today_receive + 1,receive = receive + 1 WHERE id = #{id}")
    int redenvelopesCoupon(@Param("id") String id);

    /**
     * 抽奖使用数量增加
     *
     * @param id
     * @return
     */
    @Update("UPDATE luck_draw_set SET today_use = today_use + 1,`use` = `use` + 1 WHERE id = #{id}")
    int luckDrawUse(@Param("id") String id);

    /**
     * 优惠券使用数量增加
     *
     * @param id
     * @return
     */
    @Update("UPDATE coupon_set SET today_use = today_use + 1,`use` = `use` + 1 WHERE id = #{id}")
    int couponUse(@Param("id") String id);

    /**
     * 推荐使用数量增加
     *
     * @param id
     * @return
     */
    @Update("UPDATE recommend_set SET today_use = today_use + 1,`use` = `use` + 1 WHERE id = #{id}")
    int recommendUse(@Param("id") String id);

    /**
     * 秒杀使用数量增加
     *
     * @param id
     * @return
     */
    @Update("UPDATE seckill_set SET today_use = today_use + 1,`use` = `use` + 1 WHERE id = #{id}")
    int seckillUse(@Param("id") String id);

    /**
     * 拼团使用数量增加
     *
     * @param id
     * @return
     */
    @Update("UPDATE group_buying SET today_use = today_use + 1,`use` = `use` + 1 WHERE id = #{id}")
    int groupUse(@Param("id") String id);

    /**
     * 砍价使用数量增加
     *
     * @param id
     * @return
     */
    @Update("UPDATE bargaining_set SET today_use = today_use + 1,`use` = `use` + 1 WHERE id = #{id}")
    int bargainingUse(@Param("id") String id);

    /**
     * 红包使用数量增加
     *
     * @param id
     * @return
     */
    @Update("UPDATE redenvelopes_set SET today_use = today_use + 1,`use` = `use` + 1 WHERE id = #{id}")
    int redenvelopesUse(@Param("id") String id);

    /**
     * 朋友圈参与数量增加
     *
     * @param id
     * @return
     */
    @Update("UPDATE share_friends SET today_use = today_use + 1,`use` = `use` + 1 WHERE id = #{id}")
    int shareFriendsUse(@Param("id") String id);

    /************************************************* 活动计数 - END *********************************************************/

    /**
     * 商家-客户订单列表(订单状态筛选)
     *
     * @param userId 商家ID
     * @param states 状态字符串数组 为NULL时 查询所有订单
     * @return
     */
    List<JSONObject> getWaresOrder(@Param("userId") String userId, @Param("states") String[] states);


}
