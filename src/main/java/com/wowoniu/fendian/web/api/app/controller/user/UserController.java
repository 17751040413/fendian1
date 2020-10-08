package com.wowoniu.fendian.web.api.app.controller.user;

import com.wowoniu.fendian.service.UseUserService;
import com.wowoniu.fendian.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

@RestController
@Api(tags = "账号管理/用户信息")
@RequestMapping("app/v1/us")
public class UserController {
    @Autowired
    UseUserService useUserService;

    @PostMapping("bindPhone")
    @ApiOperation("绑定手机号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone",value = "手机号",dataType = "String",required = true),
                @ApiImplicitParam(name = "code",value = "验证码",dataType = "String",required = true)
    })
    public Result bindPhone(String phone, String code, @ApiIgnore HttpSession httpSession,
                            @ApiIgnore HttpServletRequest request){
        String userid = (String) request.getAttribute("sysid");

        return useUserService.bindPhone(phone,code,httpSession,userid);
    }

    @PostMapping("bindWeChat")
    @ApiOperation("绑定微信")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "微信开放id", dataType = "String", required = true),
            @ApiImplicitParam(name = "photo", value = "头像", dataType = "String", required = true),
            @ApiImplicitParam(name = "nickname", value = "昵称", dataType = "String", required = true)

    })
    public Result bindWeChat(String openId, String photo, String nickname,@ApiIgnore HttpServletRequest request) throws MalformedURLException {

        String userid = (String) request.getAttribute("id");

        return useUserService.bindWeChat(openId,photo,nickname,userid);

    }



    @PostMapping("getUserInfo")
    @ApiOperation("个人中心")
    public Result getUserInfo(@ApiIgnore HttpServletRequest request){

        String userid = (String) request.getAttribute("sysid");
        return new Result(200,true,"获取成功",useUserService.getUserInfo(userid));
    }

    @PostMapping("getTeam")
    @ApiOperation("我的团队")
    public Result getTeam(@ApiIgnore HttpServletRequest request){
        String userid = (String) request.getAttribute("sysid");

        return useUserService.getTeam(userid);

    }

    @PostMapping("getMessage")
    @ApiOperation("我的消息")
    public Result getMessage(@ApiIgnore HttpServletRequest request){
        String userid = (String) request.getAttribute("sysid");
        return useUserService.getMessage(userid);
    }
    @PostMapping("updateNickName")
    @ApiOperation("修改昵称")
    public Result updateNickName(@ApiIgnore HttpServletRequest request,String nickName){
        String userid = (String) request.getAttribute("sysid");
        return useUserService.updateNickName(userid,nickName);
    }


    @PostMapping("updateHeadImg")
    @ApiOperation("修改头像")
    public Result updateHeadImg(@ApiIgnore HttpServletRequest request,@RequestParam("img") MultipartFile img){
        String userid = (String) request.getAttribute("sysid");



        return useUserService.updateHeadImg(userid,img,request);
    }

    @PostMapping("profit")
    @ApiOperation("获取佣金收益")
    public Result profit(@ApiIgnore HttpServletRequest request){
        String userid = (String) request.getAttribute("sysid");

        return useUserService.profit(userid);
    }

    @PostMapping("toBuTie")
    @ApiOperation("补贴计划")
    public Result toBuTie(@ApiIgnore HttpServletRequest request){
        String userid = (String) request.getAttribute("sysid");
        return useUserService.toBuTie(userid);
    }

    @PostMapping("lingquBu")
    @ApiOperation("领取补贴")
    public Result lingquBu(String butieId){
        return useUserService.lingquBu(butieId);

    }

}
