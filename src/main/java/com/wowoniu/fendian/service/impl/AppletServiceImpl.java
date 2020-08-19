package com.wowoniu.fendian.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.config.Constants;
import com.wowoniu.fendian.mapper.ActivitySetMapper;
import com.wowoniu.fendian.mapper.AppletMapper;
import com.wowoniu.fendian.model.*;
import com.wowoniu.fendian.service.AppletService;
import com.wowoniu.fendian.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 小程序Service实现
 *
 * @author yuany
 * @date 2020-08-19
 */
@Service
public class AppletServiceImpl implements AppletService {

    @Autowired
    private AppletMapper appletMapper;

    @Autowired
    private ActivitySetMapper activitySetMapper;

    /**
     * 商铺分页条件查询
     *
     * @param map 参数
     * @return
     */
    @Override
    public PageUtil<UseUser> getSearchShops(Map<String, Object> map) {

        PageUtil<UseUser> pageUtil = new PageUtil();
        int count = appletMapper.searchUseUserCount(map);
        pageUtil.setTotalCount(count);
        pageUtil.setPageSize((Integer) map.get("pageSize"));
        pageUtil.setCurrentPage((Integer) map.get("pageSize"));
        map.put("pageSize", pageUtil.getPageSize());
        map.put("startRow", pageUtil.getStartRow());
        List<UseUser> useUsers = appletMapper.searchUseUser(map);
        pageUtil.setLists(useUsers);

        return pageUtil;
    }

    /**
     * 商家ID获取店铺信息
     *
     * @param id 商家ID
     * @return
     */
    @Override
    public UseUser getUseUserById(String id) {
        return appletMapper.getUseUserById(id);
    }

    /**
     * 获取商家的商品分类
     *
     * @param useUserId
     * @return
     */
    @Override
    public List<WaresSortSet> getSortByUseUserId(String useUserId) {
        WaresSortSet waresSortSet = activitySetMapper.getWaresSortSet(useUserId);
        //无分类  或 已禁用分类
        if (waresSortSet == null || waresSortSet.getState().equals(Constants.NO)) {
            return null;
        }
        return appletMapper.getSortById(waresSortSet.getId());
    }

    /**
     * 商家商品分页列表
     *
     * @param map 参数
     * @return
     */
    @Override
    public PageUtil<Wares> getGoodsPage(Map<String, Object> map) {
        PageUtil<Wares> pageUtil = new PageUtil();
        int count = appletMapper.searchGoodsCount(map);
        pageUtil.setTotalCount(count);
        pageUtil.setPageSize((Integer) map.get("pageSize"));
        pageUtil.setCurrentPage((Integer) map.get("pageSize"));
        map.put("pageSize", pageUtil.getPageSize());
        map.put("startRow", pageUtil.getStartRow());
        List<Wares> wares = appletMapper.searchGoods(map);
        pageUtil.setLists(wares);

        return pageUtil;
    }

    /**
     * 商品ID获取商品信息
     *
     * @param waresId
     * @return
     */
    @Override
    public Wares getWaresById(String waresId) {
        return appletMapper.getWaresById(waresId);
    }

    /**
     * 商品ID获取规格
     *
     * @param waresId
     * @return
     */
    @Override
    public JSONObject getWaresSpec(String waresId) {

        List<WaresSpec> waresSpecList = activitySetMapper.getWaresSpecList(waresId);
        JSONObject jsonObject = new JSONObject();
        for (WaresSpec waresSpec : waresSpecList) {
            List<WaresSpecDetail> waresSpecDetailList = activitySetMapper.getWaresSpecDetailList(waresSpec.getId());
            jsonObject.put(waresSpec.getSpec(), waresSpecDetailList);
        }
        return jsonObject;
    }

    /**
     * 购物车添加
     *
     * @param waresCart
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean setGoodsCart(WaresCart waresCart) {

        if (waresCart == null) {
            return false;
        }
        //获取同买家卖家的同件规格的同种商品
        WaresCart wc = appletMapper.getWaresCartByWares(waresCart);
        if (wc != null) {
            wc.setNumber(wc.getNumber() + waresCart.getNumber());
            appletMapper.updateWaresCart(wc.getNumber(), wc.getId());
            return true;
        } else {
            appletMapper.addWaresCart(waresCart);
            return true;
        }
    }
}
