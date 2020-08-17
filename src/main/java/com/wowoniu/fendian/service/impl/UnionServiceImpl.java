package com.wowoniu.fendian.service.impl;

import com.wowoniu.fendian.mapper.*;
import com.wowoniu.fendian.model.UnionInfo;
import com.wowoniu.fendian.model.UseUser;
import com.wowoniu.fendian.service.UnionService;
import com.wowoniu.fendian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UnionServiceImpl implements UnionService {
    @Autowired
    UnionInfoMapper unionInfoMapper;
    @Autowired
    UnionShopMapper unionShopMapper;
    @Autowired
    UnionCustomerMapper unionCustomerMapper;
    @Autowired
    UseUserMapper useUserMapper;
    @Autowired
    UoionSeparateLogMapper uoionSeparateLogMapper;
    @Override
    public Result getUnionInfo(String leaderId) {

        UnionInfo unionInfo = unionInfoMapper.queryUnionInfoByLeadeid(leaderId);
        UseUser useUser = useUserMapper.selectByPrimaryKey(leaderId);

        Map map = new HashMap();
        map.put("unionName",unionInfo.getUnionName());
        map.put("unionLeader",unionInfo.getUnionLeaderName());
        map.put("unionId",unionInfo.getId());
        map.put("shopCount",unionShopMapper.queryShopCountByUnionId(unionInfo.getId()));
        map.put("customerCount",unionCustomerMapper.queryCustomerCountByUnionId(unionInfo.getId()));
        map.put("balance",useUser.getTakeMoney());
        map.put("totalRev",uoionSeparateLogMapper.queryUnionSepPriceByUserId(leaderId));
        return new Result(200,true,"获取成功",map);
    }
}
