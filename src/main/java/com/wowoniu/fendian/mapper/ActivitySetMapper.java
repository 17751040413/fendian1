package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.DistributionCoupon;
import com.wowoniu.fendian.model.DistributionSet;
import com.wowoniu.fendian.model.FissionSetDetail;
import com.wowoniu.fendian.model.RebateSet;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 会员活动设置汇总DAO层
 *
 * @author yuany
 * @date 2020-06-28
 */
public interface ActivitySetMapper {

    /**
     * 用户ID获取裂变详情
     *
     * @param userId
     * @return
     */
    @Select("SELECT * FROM fission_set_detail WHERE id = (SELECT id FROM fission_set WHERE user_id = #{userId}) ORDER BY level")
    List<FissionSetDetail> getFissionSetDetail(@Param("userId") String userId);

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
}
