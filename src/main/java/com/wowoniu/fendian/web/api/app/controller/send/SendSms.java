package com.wowoniu.fendian.web.api.app.controller.send;

import com.wowoniu.fendian.utils.AliSms;
import com.wowoniu.fendian.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

@RestController
@Api(value = "短信接口",tags = "短信接口")
@RequestMapping("api/send")
public class SendSms {


    @Autowired
    AliSms aliSms;
    @PostMapping("sendCode")
    @ApiOperation("发送验证码接口")
    @ApiImplicitParam(name = "phone",value = "手机号",dataType = "String",required = true)
    public Result sendCode(String phone, @ApiIgnore HttpSession httpSession){

        //发送验证码
        return (Result) aliSms.smsCode(phone,httpSession);

    }
}
