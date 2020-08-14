package com.wowoniu.fendian.service.impl;

import com.wowoniu.fendian.mapper.AppletMapper;
import com.wowoniu.fendian.model.UseUser;
import com.wowoniu.fendian.service.AppletService;
import com.wowoniu.fendian.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("appletService")
public class AppletServiceImpl implements AppletService {

    @Autowired
    private AppletMapper appletMapper;

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
        List<UseUser> goods = appletMapper.searchUseUser(map);
        pageUtil.setLists(goods);

        return pageUtil;
    }
}
