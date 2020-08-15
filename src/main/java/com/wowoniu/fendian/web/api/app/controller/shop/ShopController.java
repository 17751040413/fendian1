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
import org.apache.ibatis.annotations.Case;
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
            @ApiImplicitParam(name = "industyrId",value = "店铺分类id 全部传空",dataType = "String",required = false),
            @ApiImplicitParam(name = "currentPage",value = "页码",dataType = "int",required = true)
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
                Activity activity = activityService.getActivity(s);
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
    @ApiResponses({
            @ApiResponse(code = 200,message = "行业",response = ShopIndustry.class),
            @ApiResponse(code = 200,message = "店铺",response = ShopCase.class)
    })
    public Result getShopType(String keyWords){
        //获取所有店铺
        List<Map<ShopIndustry,List>> shopList = new ArrayList<>();
        //获取所有行业
        List<ShopIndustry> shopIndustries = shopService.getShopIndustry();
        for (ShopIndustry shopIndustry:shopIndustries){
            Map map = new HashMap();
            List<ShopCase> shopCases = shopService.getShopCase(shopIndustry.getId(),keyWords);
            map.put("shopIndustrie",shopIndustry);
            map.put("shopCases",shopCases);
            shopList.add(map);
        }

        return new Result(200,true,"获取成功",shopList);
    }

    @ApiOperation("获取策划师二维码")
    @PostMapping("getCeHuaImg")
    public Result getCeHuaImg(){


        return new Result(200,true,"获取成功",Constants.CEHUAIMG);
    }

    @ApiOperation("获取招商经理二维码")
    @PostMapping("getAttract")
    public Result getAttract(){

        return new Result(200,true,"获取成功",Constants.ATTACTIMG);
    }

    @ApiOperation("获取所有活动")
    @PostMapping("getActiveAll")
    @ApiResponse(code = 200,message = "活动详情",response = Activity.class)
    public Result getActiveAll(){

        return new Result(200,true,"获取成功",activityService.getAll());
    }


    @ApiOperation("核销页面")
    @PostMapping("goWriteOff")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activeId",value = "活动id",dataType = "String",required = true),
            @ApiImplicitParam(name = "activeLogId",value = "活动详情id",dataType = "String",required = true)
    })
    public Result goWriteOff(String activeId,String activeLogId){
        //砍价
        if (activeId.equals(Constants.BARGAINING)){

            //拼团
        }else if(activeId.equals(Constants.GROUP)){

            //秒杀
        }else if(activeId.equals(Constants.SECKILL)){

            //转盘
        }else if(activeId.equals(Constants.TURNTABLE)){

            //砸金蛋
        }else if(activeId.equals(Constants.LUCKDRAW)){

            //推荐有礼
        }else if(activeId.equals(Constants.RECOMMEND)){

            //联盟
        }else if(activeId.equals(Constants.UNION)){

        }

        return new Result();
    }

    @ApiOperation("核销")
    @PostMapping("writeOff")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activeId",value = "活动id",dataType = "String",required = true),
            @ApiImplicitParam(name = "activeLogId",value = "活动详情id",dataType = "String",required = true),
            @ApiImplicitParam(name = "price",value = "原价",dataType = "double",required = true)
    })
    public Result writeOff(double price,String activeId,String activeLogId){
        //砍价
        if (activeId.equals(Constants.BARGAINING)){

            //拼团
        }else if(activeId.equals(Constants.GROUP)){

            //秒杀
        }else if(activeId.equals(Constants.SECKILL)){

            //转盘
        }else if(activeId.equals(Constants.TURNTABLE)){

            //砸金蛋
        }else if(activeId.equals(Constants.LUCKDRAW)){

            //推荐有礼
        }else if(activeId.equals(Constants.RECOMMEND)){

            //联盟
        }else if(activeId.equals(Constants.UNION)){

        }

        return new Result();
    }

    @ApiOperation("核销记录")
    @PostMapping("getWriteOffLog")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone",value = "手机号",dataType = "String",required = true),
            @ApiImplicitParam(name = "nickName",value = "昵称",dataType = "String",required = true)
    })
    public Result getWriteOffLog(String phone,String nickName){

        return new Result();
    }



}
