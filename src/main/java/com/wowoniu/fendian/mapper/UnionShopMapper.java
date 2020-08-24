package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.UnionShop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UnionShopMapper {
    int deleteByPrimaryKey(String id);

    int insert(UnionShop record);

    int insertSelective(UnionShop record);

    UnionShop selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UnionShop record);

    int updateByPrimaryKey(UnionShop record);

    /**
     * 根据联盟id查询店铺数
     * @param unionId
     * @return
     */
    int queryShopCountByUnionId(String unionId);

    /**
     * 获取联盟店铺
     * @param unionId
     * @param shopName
     * @return
     */
    List<UnionShop> queryUnionShops(@Param("unionId") String unionId, @Param("shopName") String shopName);
}