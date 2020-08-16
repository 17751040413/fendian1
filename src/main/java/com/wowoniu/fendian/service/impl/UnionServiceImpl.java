package com.wowoniu.fendian.service.impl;

import com.wowoniu.fendian.mapper.UnionInfoMapper;
import com.wowoniu.fendian.model.UnionInfo;
import com.wowoniu.fendian.service.UnionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UnionServiceImpl implements UnionService {
    @Autowired
    UnionInfoMapper unionInfoMapper;

    @Override
    public Map getUnionInfo(String leaderId) {

        UnionInfo unionInfo = unionInfoMapper.queryUnionInfoByLeadeid(leaderId);


        return null;
    }
}
