package com.wowoniu.fendian.service;

import com.wowoniu.fendian.model.UnionInfo;

import java.util.Map;

public interface UnionService {
    /**
     * 根据盟主id获取联盟信息
     * @param leaderId
     * @return
     */
    Map getUnionInfo(String leaderId);
}
