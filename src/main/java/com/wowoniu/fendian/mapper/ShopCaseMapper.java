package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.ShopCase;

import java.util.List;
import java.util.Map;

public interface ShopCaseMapper {
    int deleteByPrimaryKey(String id);

    int insert(ShopCase record);

    int insertSelective(ShopCase record);

    ShopCase selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ShopCase record);

    int updateByPrimaryKey(ShopCase record);

    /**
     * 根据行业id获取数量
     * @param inId
     * @return
     */
    int queryCaseCountByInid(Map map);

    /**
     * 根据行业id查询方案
     * @param map
     * @return
     */
    List<ShopCase> queryCaseByInid(Map map);
}