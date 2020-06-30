package com.wowoniu.fendian.web.api.app.controller;

import com.wowoniu.fendian.service.UseUserService;
import com.wowoniu.fendian.service.UserLoginService;
import com.wowoniu.fendian.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/login")
public class LoginController {
    @Autowired
    UserLoginService userLoginService;
    @Autowired
    UseUserService useUserService;

    @PostMapping("wechatLogin")
    @ApiOperation("微信登录")
    @ApiImplicitParams({ @ApiImplicitParam(name = "openId", value = "微信开放id", dataType = "String", required = true),
            @ApiImplicitParam(name = "photo", value = "头像", dataType = "String", required = true),
            @ApiImplicitParam(name = "nickname", value = "昵称", dataType = "String", required = true),
            @ApiImplicitParam(name = "identification", value = "设备码", dataType = "String", required = true) })
    public Result wechatLogin(String openId, String photo, String nickname, String identification){


        return new Result(200,true,"登录成功",useUserService.weChatLogin(openId,photo,
                nickname,identification));
    }
}
