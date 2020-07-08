package com.wowoniu.fendian.service;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.model.FissionSet;
import com.wowoniu.fendian.model.FissionSetDetail;

/**
 * 活动设置接口
 *
 * @author yuany
 * @date 2020-07-08
 */
public interface ActivitySetService {

    /**
     * 商家ID获取裂变及详情
     *
     * @param userId 商家ID
     * @return
     */
    JSONObject getFissionAndDetail(String userId);

    /**
     * 新增/更新裂变
     *
     * @param fissionSet 裂变实体
     * @return
     */
    boolean addOrUpdateFission(FissionSet fissionSet);

    /**
     * 新增/更新裂变详情
     *
     * @param fissionSetDetail 裂变详情实体
     * @return
     */
    boolean addOrUpdateFissionDetail(FissionSetDetail fissionSetDetail);

    /**
     * 删除裂变详情
     *
     * @param id        裂变详情ID
     * @param fissionId 裂变ID
     * @return
     */
    boolean deleteFissionDetail(String id, String fissionId);
}
