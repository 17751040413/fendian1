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
    List<FissionSetDetail> getFissionSetDetail(@Param("fissionId") String fissionId);

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

    /**
     * 用户ID获取返利设置
     *
     * @param userId
     * @return
     */
    @Select("SELECT * FROM rebate_set WHERE user_id = #{userId}")
    List<RebateSet> getRebateSetList(@Param("userId") String userId);

    /**
     * 用户ID获取分销设置
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
     * 商家-客户订单列表(订单状态筛选)
     *
     * @param userId 商家ID
     * @param states 状态字符串数组 为NULL时 查询所有订单
     * @return
     */
    List<JSONObject> getWaresOrder(@Param("userId") String userId, @Param("states") String[] states);


}
