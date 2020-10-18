package com.wowoniu.fendian.service.impl;

import com.wowoniu.fendian.mapper.*;
import com.wowoniu.fendian.model.*;
import com.wowoniu.fendian.service.ActivityService;
import com.wowoniu.fendian.service.ActivitySetService;
import com.wowoniu.fendian.service.ShopService;
import com.wowoniu.fendian.utils.PageUtil;
import com.wowoniu.fendian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopTypeMapper shopTypeMapper;
    @Autowired
    ShopIndustryMapper shopIndustryMapper;
    @Autowired
    ShopCaseMapper shopCaseMapper;
    @Autowired
    UseUserMapper useUserMapper;
    @Autowired
    FreeChargeMapper freeChargeMapper;
    @Autowired
    FreeChargeDetailMapper freeChargeDetailMapper;
    @Autowired
    WriteOffMapper writeOffMapper;
    @Autowired
    UserSystemMapper userSystemMapper;
    @Autowired
    ActivitySetService activitySetService;
    @Autowired
    ActivitySetMapper activitySetMapper;
    @Override
    public List<ShopType> getShopTypes() {
        return shopTypeMapper.queryAll();
    }

    @Override
    public List<ShopIndustry> getShopIndustry() {
        return shopIndustryMapper.queryAll();
    }

    @Override
    public PageUtil<ShopCase> getShopCasePage(int currentPage, int pageSize, String industyId) {
        PageUtil<ShopCase> pageUtil = new PageUtil();
        Map map = new HashMap(8);
        map.put("industyId",industyId);
        int count = shopCaseMapper.queryCaseCountByInid(map);
        pageUtil.setTotalCount(count);
        pageUtil.setPageSize(pageSize);
        pageUtil.setCurrentPage(currentPage);
        int startRow = pageUtil.getStartRow();
        map.put("pageSize", pageUtil.getPageSize());
        map.put("startRow", pageUtil.getStartRow());
        List<ShopCase> shopCases = shopCaseMapper.queryCaseByInid(map);
        pageUtil.setLists(shopCases);
        return pageUtil;
    }

    @Override
    public List<ShopCase> getShopCase(String inid,String keyWords) {
        return shopCaseMapper.queryCaseByKeyWord(keyWords,inid);
    }

    @Override
    public Result freeDetail(String userid,int type) {

        UseUser useUser = useUserMapper.selectByPrimaryKey(userid);

        FreeCharge freeCharge = freeChargeMapper.queryFreeChargeByUserid(userid);
        if (null == freeCharge){
            return new Result(204,true,"您当前没有排队免单活动");
        }
        Map map = new HashMap();
        //浏览次数
        int browse;
        //排队人数
        int lineNumber;
        //免单人数
        int freeNumber;
        //免单人列表
        List<FreeChargeDetail> freeChargeDetails;

        lineNumber = freeChargeDetailMapper.queryFreeChargeCountByFreeId(freeCharge.getId(),type,1);
        freeNumber = freeChargeDetailMapper.queryFreeChargeCountByFreeId(freeCharge.getId(),type,2);
        if (type == 0){
            //浏览次数
            browse = freeCharge.getBrowse();

        }else {
            browse = freeCharge.getTodayBrowse();
        }
        freeChargeDetails = freeChargeDetailMapper.queryAllByFreeId(freeCharge.getId(),type);

        map.put("browse",browse);
        map.put("lineNumber",lineNumber);
        map.put("freeNumber",freeNumber);
        map.put("freeChargeDetails",freeChargeDetails);
        map.put("freeCharge",freeCharge);
        return new Result(200,true,"获取成功",map);
    }

    @Override
    public Result freePeople(String keyWord, String freeId) {

        //已免
        double yimian = freeChargeDetailMapper.queryYiMian(freeId);

        //到几号
        int number = freeChargeDetailMapper.queryNumber(freeId);

        Map map = new HashMap();
        map.put("yimian",yimian);
        map.put("number",number);
        map.put("freeDetails",freeChargeDetailMapper.queryAllByParms(keyWord,freeId));
        return new Result(200,true,"获取成功",map);
    }

    @Override
    public Result endFree(String freeId) {
        FreeCharge freeCharge = freeChargeMapper.selectByPrimaryKey(freeId);
        freeCharge.setType(0);
        freeChargeMapper.updateByPrimaryKeySelective(freeCharge);
        return new Result(200,true,"修改成功");
    }


    @Override
    public Result shopHome(String userid) {
        UseUser useUser = useUserMapper.selectByPrimaryKey(userid);
        Map map = new HashMap();
        //粉丝数
        int fans = useUser.getFans();
        //订单数
        int orderCount = writeOffMapper.countByUserId(userid);
        //余额
        double balance = useUser.getBalance();
        //店铺名
        String shopName = useUser.getShopName();
        //系统过期时间
        Date overDate = new Date();
        if (userSystemMapper.queryUserSystemByUserId(userid) != null){
            overDate = userSystemMapper.queryUserSystemByUserId(userid).getExpireTime();
        }
        List actList = new ArrayList();
        //列变
        Object fis = activitySetMapper.getFissionSet(userid);
        Map fisMap = new HashMap();
        if (fis != null){
            fisMap.put("acName","裂变系统");
            fisMap.put("shopAcName","我的裂变活动");
            fisMap.put("type",0);
            fisMap.put("id",((FissionSet) fis).getId());
            fisMap.put("todayData",2);
            fisMap.put("sumData",7);
            actList.add(fisMap);
        }

        //返利
        //获取返利
        RebateSet rebateSet = activitySetMapper.getRebateSet(userid);
        //获取分销
        DistributionSet distributionSet = activitySetMapper.getDistributionSet(userid);
//        actList.add(fis);
//        actList.add(rebateSet);
//        actList.add(distributionSet);
        map.put("fans",fans);
        map.put("orderCount",orderCount);
        map.put("balance",balance);
        map.put("shopName",shopName);
        map.put("overDate",overDate);
        map.put("acList",actList);
        return new Result(200,true,"获取成功",map);
    }
}
