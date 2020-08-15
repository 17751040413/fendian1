package com.wowoniu.fendian.web.api.applet.controller;

import com.wowoniu.fendian.config.StaticConfig;
import com.wowoniu.fendian.service.AppletService;
import com.wowoniu.fendian.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuany
 * @date 2020-08-14
 */
@Api(value = "小程序", tags = "小程序接口")
@RestController
@RequestMapping("/applet")
public class AppletController {

    @Autowired
    private AppletService appletService;

    /**************************************** 5-1-1-1 小程序活动 *************************************************/
    @PostMapping("/nearbyShop")
    @ApiOperation("附近商铺")
    @ApiImplicitParams({@ApiImplicitParam(name = "lat", value = "纬度 ", dataType = "Double", required = true),
            @ApiImplicitParam(name = "lng", value = "经度 ", dataType = "Double", required = true),
            @ApiImplicitParam(name = "type", value = "根据店铺类别查询，当此字段为空时为查所有：店铺类别（0：服饰；1：零食；2：餐饮；3：水果；4：生鲜） ", dataType = "String", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int", required = true),
            @ApiImplicitParam(name = "startRow", value = "起始行", dataType = "int", required = true)})
    public Object nearbyShop(Double lat, Double lng, String type, int pageSize, int startRow) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("lat", lat);
        map.put("lng", lng);
        map.put("type", type);
        map.put("pageSize", pageSize);
        map.put("startRow", startRow);
        map.put("range", StaticConfig.getShopRange());
        return new Result<>(200, true, "获取成功", appletService.getSearchShops(map));
    }

    /**************************************** 5-2-1 *************************************************/
    @PostMapping("/getUseUserById")
    @ApiOperation("店铺ID获取店铺信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "店铺ID ", dataType = "String", required = true)})
    public Object getUseUserById(String id) {
        return new Result<>(200, true, "获取成功", appletService.getUseUserById(id));
    }

    /**************************************** 5-2-2 *************************************************/
    @PostMapping("/getSortByUseUserId")
    @ApiOperation("商家ID获取商品分类")
    @ApiImplicitParams({@ApiImplicitParam(name = "useUserId", value = "商家ID ", dataType = "String", required = true)})
    public Object getSortByUseUserId(String id) {
        return new Result<>(200, true, "获取成功", appletService.getSortByUseUserId(id));
    }

    @PostMapping("/getGoodsPage")
    @ApiOperation("店铺商品分页查询")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "商家ID ", dataType = "String", required = true),
            @ApiImplicitParam(name = "priceSort", value = "价格排序（0：默认；1：由低到高 ；-1：由高到底） ", dataType = "int", required = true),
            @ApiImplicitParam(name = "sortId", value = "分类ID【空值则为查询全部】", dataType = "String", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int", required = true),
            @ApiImplicitParam(name = "startRow", value = "起始行", dataType = "int", required = true)})
    public Object getGoodsPage(String userId, int priceSort, String sortId, int pageSize, int startRow) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("userId", userId);
        map.put("priceSort", priceSort);
        map.put("sortId", sortId);
        map.put("pageSize", pageSize);
        map.put("startRow", startRow);
        return new Result<>(200, true, "获取成功", appletService.getGoodsPage(map));
    }

    /**************************************** 5-2-3 *************************************************/
    @PostMapping("/getGoodsById")
    @ApiOperation("商品ID获取商品信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "waresId", value = "商品ID ", dataType = "String", required = true)})
    public Object getGoodsPage(String waresId) {
        return new Result<>(200, true, "获取成功", appletService.getWaresById(waresId));
    }

}
