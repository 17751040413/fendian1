package com.wowoniu.fendian.service.impl;

import com.wowoniu.fendian.config.Constants;
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
    @Autowired
    UnionCouponMapper unionCouponMapper;
    @Autowired
    UnionCouponUserMapper unionCouponUserMapper;

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
            myUnionMap.put("unionId",unionInfo.getId());
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
        map.put("unionId",unionInfo.getId());
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
        Map map = new HashMap();
        map.put("unionId",unionId);
        map.put("isState",couponType);
        map.put("shopName",shopName);
        List list =unionCouponMapper.queryUnionByParm(map);
        int count = unionCouponMapper.queryUnionCountByParm(map);
        Map reMap = new HashMap();
        reMap.put("count",count);
        reMap.put("list",list);
        return new Result(200,true,"获取成功",reMap);
    }

    @Override
    public Result endDisCoupon(String id) {
        UnionCoupon unionCoupon = new UnionCoupon();
        unionCoupon.setId(id);
        unionCoupon.setIsState(2);
        unionCouponMapper.updateByPrimaryKeySelective(unionCoupon);

        return new Result(200,true,"结束成功");
    }

    @Override
    public Result unionCouponInfo(String id) {
        return new Result(200,true,"获取成功",unionCouponMapper.selectByPrimaryKey(id));
    }

    @Override
    public Result unionCouponExamine(String id, int exmType) {
        int isState;
        if (exmType == 0){
            isState = 1;
        }else {
            isState = -1;
        }
        UnionCoupon unionCoupon = new UnionCoupon();
        unionCoupon.setId(id);
        unionCoupon.setIsState(isState);
        unionCouponMapper.updateByPrimaryKeySelective(unionCoupon);
        return new Result(200,true,"操作成功");
    }

    @Override
    public Result unionSet(String id, String unionName, String leaderPhone) {
        UnionInfo unionInfo = new UnionInfo();
        unionInfo.setId(id);
        unionInfo.setUnionLeaderPhone(leaderPhone);
        unionInfo.setUnionName(unionName);
        unionInfoMapper.updateByPrimaryKeySelective(unionInfo);
        return new Result(200,true,"保存成功");
    }

    @Override
    public Result unionRule(String brief, String rule,String id) {
        UnionInfo unionInfo = new UnionInfo();
        unionInfo.setId(id);
        unionInfo.setBrief(brief);
        unionInfo.setRule(rule);
        unionInfoMapper.updateByPrimaryKeySelective(unionInfo);
        return new Result(200,true,"修改成功");
    }

    @Override
    public Result unionPositionSet(double lng, double lat, String id) {
        UnionInfo unionInfo = new UnionInfo();
        unionInfo.setId(id);
        unionInfo.setLng(lng);
        unionInfo.setLat(lat);
        unionInfoMapper.updateByPrimaryKeySelective(unionInfo);
        return new Result(200,true,"修改成功");
    }

    @Override
    public Result unionMemberReceive(int type, String id) {
        UnionInfo unionInfo = new UnionInfo();
        unionInfo.setId(id);
        unionInfo.setReceiveType(type);
        unionInfoMapper.updateByPrimaryKeySelective(unionInfo);
        return new Result(200,true,"设置成功");
    }

    @Override
    public Result unionShops(String id, String shopName) {
        List<UnionShop> unionShops = unionShopMapper.queryUnionShops(id,shopName);
        for (UnionShop unionShop:unionShops){
            UnionCoupon unionCoupon = unionCouponMapper.queryUnionCouponByShopAndUnionIdLimit1(id,unionShop.getShopId());
            unionShop.setCouponType(unionCoupon.getCouponType());
        }


        return new Result(200,true,"获取成功",unionShops);
    }

    @Override
    public Result unionShopInfo(String unionShopId, int grantType) {
        //发放
        int sendCount;
        //使用
        int useCount;
        //佣金
        double commission;

        int wechatCount;

        //联盟店铺
        UnionShop unionShop = unionShopMapper.selectByPrimaryKey(unionShopId);
        wechatCount = unionShop.getLeaderWechatCount();
        //联盟信息
        UnionInfo unionInfo = unionInfoMapper.selectByPrimaryKey(unionShop.getUnionId());

        if(grantType == 0){
           sendCount = unionCouponUserMapper.queryShopUnionCount(unionInfo.getId(),unionShop.getShopId(),2);
           useCount = unionCouponUserMapper.queryShopUnionCount(unionInfo.getId(),unionShop.getShopId(),1);
           commission = uoionSeparateLogMapper.queryUnionSepPriceByUserId(unionShop.getShopId(),unionInfo.getId());
        }else {
            sendCount = unionCouponUserMapper.queryUnionShopCount(unionInfo.getId(),unionShop.getShopId(),2);
            useCount = unionCouponUserMapper.queryUnionShopCount(unionInfo.getId(),unionShop.getShopId(),1);
            commission = uoionSeparateLogMapper.queryUnionSepPriceByUserId(unionInfo.getUnionLeaderId(),unionInfo.getId());
        }

        Map map = new HashMap();
        map.put("sendCount",sendCount);
        map.put("useCount",useCount);
        map.put("commission",commission);
        map.put("wechatCount",wechatCount);
        map.put("isLock",unionShop.getIsLock());
        return new Result(200,true,"获取成功",map);
    }

    @Override
    public Result lockShop(String unionShopId) {

        UnionShop unionShop = unionShopMapper.selectByPrimaryKey(unionShopId);

        unionShopMapper.updateLock(unionShop.getId(),unionShop.getIsLock());

        return new Result(200,true,"修改成功");
    }

    @Override
    public Result unionCouponLog(String unionId, int grantType) {
        //发放
        int sendCount;
        //使用
        int useCount;
        //佣金
        double commission;
        sendCount = unionCouponUserMapper.queryUnionCount(unionId,2);
        useCount = unionCouponUserMapper.queryUnionCount(unionId,1);
        UnionInfo unionInfo = unionInfoMapper.selectByPrimaryKey(unionId);
        commission = uoionSeparateLogMapper.queryUnionSepPriceByUserId(unionInfo.getUnionLeaderId(),unionInfo.getId());
        List<UnionShop> unionShops = unionShopMapper.queryUnionShops(unionId,null);
        //返回集合
        List reList = new ArrayList();
        for (UnionShop unionShop:unionShops){
            Map map = new HashMap();
            map.put("shopId",unionShop.getShopId());
            map.put("shopName",unionShop.getShopName());
            map.put("shopLogo",unionShop.getShopLogo());
            map.put("sendCount",unionCouponUserMapper.queryShopUnionCount(unionInfo.getId(),unionShop.getShopId(),2));
            map.put("useCount",unionCouponUserMapper.queryShopUnionCount(unionInfo.getId(),unionShop.getShopId(),1));
            map.put("commission",uoionSeparateLogMapper.queryUnionSepPriceByUserId(unionShop.getShopId(),unionInfo.getId()));
            reList.add(map);

        }
        Map map = new HashMap();
        map.put("sendCount",sendCount);
        map.put("useCount",useCount);
        map.put("commission",commission);
        map.put("shopList",reList);

        return new Result(200,true,"获取成功",map);
    }

    @Override
    public Result unionCustomers(String unionId, String keyWords) {
        List<UnionCustomer> customers = unionCustomerMapper.queryUnionCustomer(unionId,keyWords);
        for (UnionCustomer customer:customers){

            UnionCouponUser reCoupon = unionCouponUserMapper.queryLatelyReUnion(customer.getUserId());
            if (reCoupon != null){
                customer.setReceiveTime(reCoupon.getReceiveTime());
            }
            UnionCouponUser useCoupon = unionCouponUserMapper.queryLatelyUseUnion(customer.getUserId());
            if (useCoupon != null){
                customer.setIsUseTime(useCoupon.getIsUseTime());
            }
        }

        return new Result(200,true,"获取成功",customers);
    }

    @Override
    public Result unionCustomerInfo(String unionCusId) {
        //用户信息
        UnionCustomer unionCustomer = unionCustomerMapper.selectByPrimaryKey(unionCusId);
        //消费信息
        List<WriteOff> writeOffs = writeOffMapper.queryWriteByCustomerId(unionCustomer.getUserId());
        Map map = new HashMap();
        map.put("customer",unionCustomer);
        map.put("writeOffs",writeOffs);
        return new Result(200,true,"获取成功",map);
    }

    @Override
    public Result unionCashMoney(String userid) {
        UseUser useUser = useUserMapper.selectByPrimaryKey(userid);

        return new Result(200,true,"获取成功",useUser);
    }

    @Override
    public Result goOpenService(String id) {
        //获取未开通服务的商圈用户
        UnionInfo unionInfo = unionInfoMapper.queryUnionInfoByLeadeid(id);
        List<UnionMember> unionMembers = unionMemberMapper.queryMemberByUnionId(unionInfo.getId());

        return new Result(200,true,"获取成功",unionMembers);
    }

    @Override
    public Result phoneOpenService(String phone,String id) {
        UseUser useUser = useUserMapper.queryUserByLoginName(phone);
        if (!useUser.getRoleId1().equals("0")){
            return new Result(200,true,"当前账号会员未过期，请勿重复开通");
        }
        UseUser kaiUser = useUserMapper.selectByPrimaryKey(id);
        //if (kaiUser.getBalance() < Constants.MEMBERPRICE){}
        return null;
    }

    @Override
    public Result updatebeingjing(String img, String unionId) {
        UnionInfo unionInfo = unionInfoMapper.selectByPrimaryKey(unionId);
        unionInfo.setBackimg(img);
        unionInfoMapper.updateByPrimaryKeySelective(unionInfo);
        return new Result(200,true,"修改成功");
    }
}
