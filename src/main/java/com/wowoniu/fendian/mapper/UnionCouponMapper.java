package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.UnionCoupon;

import java.util.List;
import java.util.Map;

public interface UnionCouponMapper {
    int deleteByPrimaryKey(String id);

    int insert(UnionCoupon record);

    int insertSelective(UnionCoupon record);

    UnionCoupon selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UnionCoupon record);

    int updateByPrimaryKey(UnionCoupon record);

    /**
     * 根据条件获取所有优惠券
     * @param map
     * @return
     */
    List<UnionCoupon> queryUnionByParm(Map map);

    /**
     * 根据条件获取所有优惠券数量
     * @param map
     * @return
     */
    int queryUnionCountByParm(Map map);

}