package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.WriteOff;

import java.util.List;

public interface WriteOffMapper {
    int deleteByPrimaryKey(String id);

    int insert(WriteOff record);

    int insertSelective(WriteOff record);

    WriteOff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WriteOff record);

    int updateByPrimaryKey(WriteOff record);

    /**
     * 根据商店或联盟id查询数量
     * @param userid
     * @return
     */
    int countByUserId(String userid);

    /**
     * 根据店铺id查询订单
     * @return
     */
    List<WriteOff> queryWriteByUserId(String userId);

    /**
     * 根据用户id查询
     * @param customerId
     * @return
     */
    List<WriteOff> queryWriteByCustomerId(String customerId);

    /**
     * 个人中心我的收益用
     * @param userid
     * @return
     */
    List<WriteOff> queryWriteByuserid(String userid);
}