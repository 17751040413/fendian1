package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.FreeCharge;
import org.apache.ibatis.annotations.Param;

public interface FreeChargeMapper {
    int deleteByPrimaryKey(String id);

    int insert(FreeCharge record);

    int insertSelective(FreeCharge record);

    FreeCharge selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FreeCharge record);

    int updateByPrimaryKey(FreeCharge record);

    /**
     * 获取最近的一次排队免单活动根据用户id
     * @param userid
     * @return
     */
    FreeCharge queryFreeChargeByUserid(String userid);


}