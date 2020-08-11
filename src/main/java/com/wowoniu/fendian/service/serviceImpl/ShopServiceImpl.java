package com.wowoniu.fendian.service.serviceImpl;

import com.wowoniu.fendian.mapper.ShopCaseMapper;
import com.wowoniu.fendian.mapper.ShopIndustryMapper;
import com.wowoniu.fendian.mapper.ShopTypeMapper;
import com.wowoniu.fendian.model.ShopCase;
import com.wowoniu.fendian.model.ShopIndustry;
import com.wowoniu.fendian.model.ShopType;
import com.wowoniu.fendian.service.ShopService;
import com.wowoniu.fendian.utils.PageUtil;
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
}
