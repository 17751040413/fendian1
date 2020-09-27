package com.wowoniu.fendian.web.api.app.controller.login;

import com.wowoniu.fendian.model.UseUser;
import com.wowoniu.fendian.model.pack.LoginPack;
import com.wowoniu.fendian.service.UseUserService;
import com.wowoniu.fendian.service.UserLoginService;
import com.wowoniu.fendian.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@Api(tags = "登录接口")
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
    public Result<LoginPack> wechatLogin(String openId, String photo, String nickname, String identification) throws IOException {


        return new Result(200,true,"登录成功",useUserService.weChatLogin(openId,photo,
                nickname,identification));
    }


    @PostMapping("smsLogin")
    @ApiOperation("验证码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "验证码",dataType = "String",required = true),
            @ApiImplicitParam(name = "phone",value = "手机号",dataType = "String",required = true),
            @ApiImplicitParam(name = "identification",value = "设备码",dataType = "String",required = true)

    })
    public Result<LoginPack> smsLogin(String code, String phone, String identification,
                                      @ApiIgnore HttpSession httpSession) throws IOException {

        return useUserService.smsLogin(code,phone,identification,httpSession);
    }




}
