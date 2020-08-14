package com.wowoniu.fendian.service;

import com.wowoniu.fendian.model.UseUser;
import com.wowoniu.fendian.utils.PageUtil;

import java.util.Map;

/**
 * 小程序Sevcie
 *
 * @author yuay
 * @date 2020-08-14
 */
public interface AppletService {

    /**
     * 商铺分页条件查询
     *
     * @param map 参数
     * @return
     */
    PageUtil<UseUser> getSearchShops(Map<String, Object> map);
}
