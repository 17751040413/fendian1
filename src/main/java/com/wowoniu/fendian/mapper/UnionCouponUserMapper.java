package com.wowoniu.fendian.mapper;

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
}