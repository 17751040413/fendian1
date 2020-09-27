package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.UnionCoupon;
import com.wowoniu.fendian.model.UnionCouponUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UnionCouponUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(UnionCouponUser record);

    int insertSelective(UnionCouponUser record);

    UnionCouponUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UnionCouponUser record);

    int updateByPrimaryKey(UnionCouponUser record);

    /**
     * 店铺帮联盟派券数
     * @param unionId
     * @param unionShopId
     * @return
     */
    int queryShopUnionCount(@Param("unionId") String unionId,
                            @Param("unionShopId") String unionShopId, @Param("isUse") int isUse);

    /**
     * 联盟帮店铺派券数
     * @param unionId
     * @param unionShopId
     * @return
     */
    int queryUnionShopCount(@Param("unionId") String unionId,
                            @Param("unionShopId") String unionShopId, @Param("isUse") int isUse);

    /**
     * 查询联盟发券数
     * @param unionId
     * @param isUse
     * @return
     */
    int queryUnionCount(@Param("unionId") String unionId,@Param("isUse") int isUse);

    /**
     * 查询用户最近领取的优惠券
     * @param userId
     * @return
     */
    UnionCouponUser queryLatelyReUnion(String userId);

    /**
     * 查询用户最近使用的优惠券
     * @param userId
     * @return
     */
    UnionCouponUser queryLatelyUseUnion(String userId);
}