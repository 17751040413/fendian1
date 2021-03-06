package com.wowoniu.fendian.service;

import com.wowoniu.fendian.model.ShopCase;
import com.wowoniu.fendian.model.ShopIndustry;
import com.wowoniu.fendian.model.ShopType;
import com.wowoniu.fendian.utils.PageUtil;
import com.wowoniu.fendian.utils.Result;

import java.util.List;

public interface ShopService {
    /**
     * 获取所有店铺分类
     * @return
     */
    List<ShopType> getShopTypes();

    /**
     * 获取所有行业
     * @return
     */
    List<ShopIndustry> getShopIndustry();

    /**
     * 根据行业id获取所有
     * @param currentPage
     * @param pageSize
     * @param industyId
     * @return
     */
    PageUtil<ShopCase> getShopCasePage(int currentPage,int pageSize,String industyId);

    /**
     * 根据行业id和关键字查询店铺
     * @param industyId
     * @param keyWords
     * @return
     */
    List<ShopCase> getShopCase(String inid,String keyWords);


    /**
     * 排队免单详情
     * @param userid
     * @return
     */
    Result freeDetail(String userid,int type);

    /**
     * 排队免单人员
     * @param keyWord
     * @param freeId
     * @return
     */
    Result freePeople(String keyWord,String freeId);

    /**
     * 结束活动
     * @param freeId
     * @return
     */
    Result endFree(String freeId);

    Result shopHome(String userid);
}
