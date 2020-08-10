package com.wowoniu.fendian.service;

import com.wowoniu.fendian.model.ShopCase;
import com.wowoniu.fendian.model.ShopIndustry;
import com.wowoniu.fendian.model.ShopType;
import com.wowoniu.fendian.utils.PageUtil;

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
}
