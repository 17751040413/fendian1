package com.wowoniu.fendian.web.api.app.controller.shop;

import com.wowoniu.fendian.config.Constants;
import com.wowoniu.fendian.model.Activity;
import com.wowoniu.fendian.model.ShopCase;
import com.wowoniu.fendian.model.ShopIndustry;
import com.wowoniu.fendian.model.ShopType;
import com.wowoniu.fendian.service.ActivityService;
import com.wowoniu.fendian.service.ShopService;
import com.wowoniu.fendian.utils.PageUtil;
import com.wowoniu.fendian.utils.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Api(tags = "首页--店铺案例")
@RestController
@RequestMapping("app/shop")
public class ShopController {
    @Autowired
    ShopService shopService;
    @Autowired
    ActivityService activityService;

    @ApiOperation("获取店铺首页信息")
    @PostMapping("getHomePage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "industyrId",value = "店铺分类id 全部传空",dataType = "String",required = true),
            @ApiImplicitParam(name = "currentPage",value = "页码",dataType = "int",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "ok",response = ShopIndustry.class),
            @ApiResponse(code = 200,message = "ok",response = ShopCase.class)
    })
    public Result getHomePage(String industyrId,@RequestParam(defaultValue = "1") int currentPage){
        //获取所有店铺行业
        List<ShopIndustry> shopIndustries = shopService.getShopIndustry();
        //获取该店铺id的所有案例
        PageUtil<ShopCase> pageUtil = shopService.getShopCasePage(currentPage, Constants.PAGESIZE,industyrId);
        Map map = new HashMap();
        List<ShopCase> caseList = pageUtil.getLists();
        //查询对应活动
        for (ShopCase shopCase:caseList){
            String acts = shopCase.getContent();
            List<String> acList = Arrays.asList(acts.split(","));//根据逗号分隔转化为list
            List<Activity> activities = new ArrayList<>();
            for (String s:acList){
                Activity activity = activityService.getActivity(Integer.parseInt(s));
                activities.add(activity);
            }
            shopCase.setActivityList(activities);

        }
        map.put("shopIndustries",shopIndustries);
        map.put("cases",pageUtil.getLists());
        return new Result(200,true,"获取成功",map);
    }

    @ApiOperation("获取所有店铺类型 关键字查询")
    @PostMapping("getShopType")
    @ApiImplicitParam(name = "keyWords",value = "关键字",dataType = "String",required = false)
    public Result getShopType(String keyWords){


        return new Result();
    }



}
