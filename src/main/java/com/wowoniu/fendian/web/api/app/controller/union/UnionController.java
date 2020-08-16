package com.wowoniu.fendian.web.api.app.controller.union;


import com.wowoniu.fendian.utils.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "商圈联盟")
@RestController
@RequestMapping("app/union")
public class UnionController {

    @ApiOperation("获取我的商圈联盟信息")
    @PostMapping("getUnionInfo")
    public Result getUnionInfo(@ApiIgnore HttpServletRequest request){
        String id = (String) request.getAttribute("id");
        return new Result();
    }

    @ApiOperation("盟主管理")
    @PostMapping("getUnionLeaderMan")
    public Result getUnionLeaderMan(@ApiIgnore HttpServletRequest request){

        return new Result();
    }

    @ApiOperation("联盟订单--盟主")
    @PostMapping("unionOrder")
    public Result unionOrder(@ApiIgnore HttpServletRequest request){

        return new Result();
    }

    @ApiOperation("所有联盟券 -- 盟主")
    @PostMapping("unionCoupon")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "couponType",value = "优惠券类型 0--待审核 1--派发中 2--已下架",dataType = "int",required = true),
            @ApiImplicitParam(name = "shopName",value = "店铺名",dataType = "String",required = true)
    })
    public Result unionCoupon(@ApiIgnore HttpServletRequest request,int couponType,String shopName){

        return new Result();
    }

    @ApiOperation("联盟券详情--盟主")
    @PostMapping("unionCouponInfo")
    @ApiImplicitParam(name = "unionCouponId",value = "联盟券id",dataType = "String",required = true)
    public Result unionCouponInfo(String unionCouponId){

        return new Result();
    }

    @ApiOperation("联盟券审核--盟主")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "unionCouponId",value = "联盟券id",dataType = "String",required = true),
            @ApiImplicitParam(name = "examType",value = "审核状态 0--审核成功 1--审核失败",dataType = "int",required = true)
    })
    @PostMapping("unionCouponExamine")
    public Result unionCouponExamine(String unionCouponId,int examType){

        return new Result();
    }

    @ApiOperation("商圈联盟设置--盟主")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "unionName",value = "联盟名",dataType = "String",required = true),
            @ApiImplicitParam(name = "leaderPhone",value = "盟主手机号",dataType = "String",required = true)
    })
    @PostMapping("unionSet")
    public Result unionSet(@ApiIgnore HttpServletRequest request,String unionName,String leaderPhone){

        return new Result();

    }

    @ApiOperation("联盟规则设置--盟主")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "brief",value = "简介",dataType = "String",required = true),
            @ApiImplicitParam(name = "rule",value = "规则",dataType = "String",required = true)
    })
    @PostMapping("unionRule")
    public Result unionRule(@ApiIgnore HttpServletRequest request,String brief,String rule){
        return new Result();
    }

    @ApiOperation("联盟位置设置--盟主")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lng",value = "double",dataType = "double",required = true),
            @ApiImplicitParam(name = "lat",value = "double",dataType = "double",required = true)
    })
    @PostMapping("unionPositionSet")
    public Result unionPositionSet(@ApiIgnore HttpServletRequest request,double lat,double lng){

        return new Result();
    }

    @ApiOperation("领取规则设置--盟主")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type",value = "0--无条件 1--加微信",dataType = "int",required = true)
    })
    @PostMapping("unionMemberReceive")
    public Result unionMemberReceive(@ApiIgnore HttpServletRequest request,int type){

        return new Result();
    }

    @ApiOperation("联盟商家列表")
    @ApiImplicitParam(name = "unionId",value = "联盟id",dataType = "String",required = true)
    @PostMapping("unionShops")
    public Result unionShops(String unionId){

        return new Result();
    }

    @ApiOperation("联盟店铺详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "unionShopId",value = "联盟店铺id",dataType = "String",required = true),
            @ApiImplicitParam(name = "grantType",value = "派券 0--店铺帮联盟 1--联盟帮店铺",dataType = "int",required = true)
    })

    @PostMapping("unionShopInfo")
    public Result unionShopInfo(String unionShopId,int grantType){

        return new Result();
    }

    @ApiOperation("锁定店铺")
    @ApiImplicitParam(name = "unionShopId",value = "联盟店铺id",dataType = "String",required = true)
    @PostMapping("lockShop")
    public Result lockShop(String unionShopId){

        return new Result();
    }

    @PostMapping("unionCouponLog")
    @ApiOperation("联盟优惠券记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "unionId",value = "联盟id",dataType = "String",required = true),
            @ApiImplicitParam(name = "grantType",value = "派券 0--店铺帮联盟 1--联盟帮店铺",dataType = "int",required = true)
    })
    public Result unionCouponLog(String unionId,int grantType){

        return new Result();
    }

    @ApiOperation("商圈顾客列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "unionId",value = "联盟id",dataType = "String",required = true),
            @ApiImplicitParam(name = "keyords",value = "关键字",dataType = "String",required = true)
    })
    @PostMapping("unionCustomers")
    public Result unionCustomers(String unionId,String keyWords){

        return new Result();
    }

    @PostMapping("unionCustomerInfo")
    @ApiImplicitParam(name = "unionCusId",value = "商圈顾客id",dataType = "String",required = true)
    @ApiOperation("商圈顾客详情")
    public Result unionCustomerInfo(String unionCusId){

        return new Result();
    }

    @PostMapping("unionCashMoney")
    @ApiOperation("我的提货金")
    public Result unionCashMoney(@ApiIgnore HttpServletRequest request){
        return new Result();

    }

    @PostMapping("goOpenService")
    @ApiOperation("为客户开通服务页面")
    public Result goOpenService(@ApiIgnore HttpServletRequest request){


        return new Result();
    }

    @ApiOperation("手机号开通服务验证手机号")
    @PostMapping("phoneOpenService")
    public Result phoneOpenService(String phone){

        return new Result();
    }

    @ApiOperation("开通服务")
    @PostMapping("openService")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid",value = "用户id",dataType = "String",required = true),
            @ApiImplicitParam(name = "serviceIds",value = "服务id,用逗号隔开",dataType = "String",required = true)
    })
    public Result openService(String userid,String serviceIds){

        return new Result();
    }

    @ApiOperation("开通记录")
    @PostMapping("openServiceLog")
    public Result openServiceLog(@ApiIgnore HttpServletRequest request){

        return new Result();
    }

    @ApiOperation("提货金充值")
    @PostMapping("cashMoneyRec")
    public Result cashMoneyRec(@ApiIgnore HttpServletRequest request){

        return new Result();
    }

    @ApiOperation("提货金使用记录")
    @PostMapping("cashMoneyLog")
    @ApiImplicitParam(name = "type",value = "提货金记录 0--去不 1--支出 2--充值",dataType = "int",required = true)
    public Result cashMoneyLog(int type){

        return new Result();

    }



    @ApiOperation("设置优惠券引流界面")
    @PostMapping("toUnionCouponSet")
    public Result toUnionCouponSet(@ApiIgnore HttpServletRequest request){

        return new Result();
    }



    @ApiOperation("设置优惠券")
    @PostMapping("unionCouponSet")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "couponType",value = "优惠券类型 0--代金券 1--折扣券 2--兑换券",dataType = "int",required = true),
            @ApiImplicitParam(name = "disPrice",value = "优惠金额 couponType为0必填 ",dataType = "double",required = false),
            @ApiImplicitParam(name = "disMan",value = "使用门槛 couponType为0必填 ",dataType = "double",required = false),
            @ApiImplicitParam(name = "discount",value = "折扣 couponType为1必填 ",dataType = "double",required = false),
            @ApiImplicitParam(name = "giftName",value = "礼物名 couponType为2必填 ",dataType = "String",required = false),
            @ApiImplicitParam(name = "tremDateType",value = "有效期类型 0--指定日期 1--有效天数 ",dataType = "int",required = true),
            @ApiImplicitParam(name = "termDate",value = "有效天数 tremDateType为1必填 ",dataType = "int",required = false),
            @ApiImplicitParam(name = "startTime",value = "开始日期 tremDateType为0必填 ",dataType = "String",required = false),
            @ApiImplicitParam(name = "endTime",value = "结束日期 tremDateType为0必填 ",dataType = "String",required = false),
            @ApiImplicitParam(name = "limitCount",value = "每人限领张数 ",dataType = "int",required = true),
            @ApiImplicitParam(name = "remarks",value = "适用范围 ",dataType = "String",required = true),
            @ApiImplicitParam(name = "yongType",value = "返佣方式 0--按比例 1--固定金额 ",dataType = "int",required = true),
            @ApiImplicitParam(name = "yongPro",value = "比例 yongType为0必填 ",dataType = "double",required = false),
            @ApiImplicitParam(name = "yongPrice",value = "固定金额 yongType为1必填 ",dataType = "double",required = false),
    })
    public Result unionCouponSet(@ApiIgnore HttpServletRequest request,int couponType,double disPrice,
                                 double disMan,double discount,String giftName,int tremDateType,
                                 int termDate,String startTime,String endTime,int limitCount,String remarks,
                                 int yongType,double yongPrice,double yongPro){

        return new Result();
    }

    @ApiOperation("优惠券领取记录")
    @PostMapping("couponReciveLog")
    @ApiImplicitParam(name = "unionId",value = "联盟id",dataType = "String",required = true)
   public Result couponReciveLog(String unionId){

        return new Result();
    }


}
