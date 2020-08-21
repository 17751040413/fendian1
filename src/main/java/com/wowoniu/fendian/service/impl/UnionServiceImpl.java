package com.wowoniu.fendian.service.impl;

import com.wowoniu.fendian.mapper.*;
import com.wowoniu.fendian.model.*;
import com.wowoniu.fendian.service.UnionService;
import com.wowoniu.fendian.utils.Result;
import com.wowoniu.fendian.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    @Autowired
    UserPaySystemMapper userPaySystemMapper;
    @Autowired
    WriteOffMapper writeOffMapper;
    @Autowired
    UnionMemberMapper unionMemberMapper;

    @Override
    public Result getUnionInfo(String leaderId) {
        UseUser useUser = useUserMapper.selectByPrimaryKey(leaderId);
        UnionInfo unionInfo = unionInfoMapper.queryUnionInfoByLeadeid(leaderId);
        if (null == unionInfo){
            UnionInfo newUninfo = new UnionInfo();
            newUninfo.setId(StringUtils.getUuid());
            newUninfo.setUnionLeaderName(useUser.getNickName());
            newUninfo.setUnionName(useUser.getNickName()+"的联盟");
            newUninfo.setUnionLeaderId(useUser.getId());
            newUninfo.setUnionLeaderPhone(useUser.getLoginName());
            newUninfo.setIsOpen(0);
            unionInfoMapper.insertSelective(newUninfo);
            unionInfo = newUninfo;
        }


        Map map = new HashMap();
        map.put("unionName",unionInfo.getUnionName());
        map.put("unionLeader",unionInfo.getUnionLeaderName());
        map.put("unionId",unionInfo.getId());
        map.put("shopCount",unionShopMapper.queryShopCountByUnionId(unionInfo.getId()));
        map.put("customerCount",unionCustomerMapper.queryCustomerCountByUnionId(unionInfo.getId()));
        map.put("balance",useUser.getTakeMoney());
        map.put("totalRev",uoionSeparateLogMapper.queryUnionSepPriceByUserId(leaderId,unionInfo.getId()));
        return new Result(200,true,"获取成功",map);
    }

    @Override
    public Result getUnionLeaderMan(String id) {
        Map map = new HashMap();
        UseUser useUser = useUserMapper.selectByPrimaryKey(id);
        //获取我的商圈联盟
        UnionInfo unionInfo = unionInfoMapper.queryUnionInfoByLeadeid(id);
        Map myUnionMap = new HashMap();
        if (unionInfo != null){
            myUnionMap.put("unionName",unionInfo.getUnionName());
            myUnionMap.put("unionLeaderName",unionInfo.getUnionLeaderName());
            myUnionMap.put("totalRev",uoionSeparateLogMapper.queryUnionSepPriceByUserId(id,unionInfo.getId()));
            myUnionMap.put("balance",useUser.getTakeMoney());
            myUnionMap.put("expenditure",userPaySystemMapper.queryMoneyByPaid(id)/100.0);
            myUnionMap.put("orderCount",writeOffMapper.countByUserId(id));
        }
        map.put("myUnion",myUnionMap);
        //获取我加入的商圈联盟
        List<UnionMember> unionMembers = unionMemberMapper.queryMemberByUserId(id);
        List<Map> mapList = new ArrayList<>();
        for (UnionMember unionMember:unionMembers){
            Map map1 = new HashMap();
            map1.put("unionName",unionMember.getUnionName());
            map1.put("unionLeaderName",unionInfoMapper.selectByPrimaryKey(unionMember.getUnionId()).getUnionLeaderName());
            map1.put("totalRev",uoionSeparateLogMapper.queryUnionSepPriceByUserId(unionMember.getUserId(),unionMember.getUnionId()));
            mapList.add(map1);
        }

        map.put("unions",mapList);
        return new Result(200,true,"获取成功",map);
    }

    @Override
    public Result unionOrder(String userid,String unionId) {
        UnionInfo unionInfo = unionInfoMapper.selectByPrimaryKey(unionId);
        double waitEntryPrice = uoionSeparateLogMapper.queryUnionPriceByUnionAndIsEntry(unionId,0);
        double isEntryPrice = uoionSeparateLogMapper.queryUnionPriceByUnionAndIsEntry(unionId,1);
        List<WriteOff> writeOffs = writeOffMapper.queryWriteByUserId(unionId);
        Map map = new HashMap();
        map.put("unionName",unionInfo.getUnionName());
        map.put("unionLeaderName",unionInfo.getUnionLeaderName());
        map.put("waitEntryPrice",waitEntryPrice);
        map.put("isEntryPrice",isEntryPrice);
        map.put("writeOffs",writeOffs);

        return new Result(200,true,"获取成功",map);
    }

    @Override
    public Result unionCoupon(int couponType, String shopName,String unionId) {
        UnionInfo unionInfo = unionInfoMapper.selectByPrimaryKey(unionId);

        return null;
    }
}
