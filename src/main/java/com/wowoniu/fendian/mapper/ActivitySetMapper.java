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
     * @param fissionSetDetailList
     * @return
     */
    int updateFissionSetDetailList(@Param("fissionSetDetailList") List<FissionSetDetail> fissionSetDetailList);

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
    @Insert("INSERT INTO rebate_ret_detail(id,level,level_name,gain_factor,rebate_ratio,rebate_id) VALUES (id,level,levelName,gainFactor,rebateRatio,rebateId)")
    int addRebateSetDetail(RebateSetDetail rebateSetDetail);

    /**
     * 批量更新裂变详情
     *
     * @param rebateSetDetailList
     * @return
     */
    int updateRebateSetDetailList(@Param("rebateSetDetailList") List<RebateSetDetail> rebateSetDetailList);

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
    @Insert("INSERT INTO distribution_set(id,user_id,state,commission_ratio,prevent_brush) VALUES (id,userId,state,commissionRatio,preventBrush)")
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
            "VALUES (id,distributionId,phoneEnable,type,money,discount,threshold,effectiveType,effectiveDay,startTime,endTime,range)")
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
    @Delete("DELETE FROM distribution_coupon WHERE id = #{id}")
    int deleteDistributionCoupon(@Param("id") String id);

    /************************************** 分销 - END *************************************************/




    /**
     * 商家-客户订单列表(订单状态筛选)
     *
     * @param userId 商家ID
     * @param states 状态字符串数组 为NULL时 查询所有订单
     * @return
     */
    List<JSONObject> getWaresOrder(@Param("userId") String userId, @Param("states") String[] states);


}
