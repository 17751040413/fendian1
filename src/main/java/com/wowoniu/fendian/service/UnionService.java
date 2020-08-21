package com.wowoniu.fendian.service;

import com.wowoniu.fendian.model.UnionInfo;
import com.wowoniu.fendian.utils.Result;

import java.util.Map;

public interface UnionService {
    /**
     * 根据盟主id获取联盟信息
     * @param leaderId
     * @return
     */
    Result getUnionInfo( String leaderId);

    /**
     * 商圈3-1
     * @param id
     * @return
     */
    Result getUnionLeaderMan(String id);

    /**
     * 商圈订单 3-2-1
     * @return
     */
    Result unionOrder(String userid,String unionId);

    /**
     * 所有联盟券 3-3-1
     * @param couponType
     * @return
     */
    Result unionCoupon(int couponType,String shopName,String unionId);
}
