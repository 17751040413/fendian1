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
}
