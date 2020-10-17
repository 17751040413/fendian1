package com.wowoniu.fendian.service.impl;

import com.wowoniu.fendian.mapper.*;
import com.wowoniu.fendian.model.*;
import com.wowoniu.fendian.service.ShopService;
import com.wowoniu.fendian.utils.PageUtil;
import com.wowoniu.fendian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return null;
    }
}
