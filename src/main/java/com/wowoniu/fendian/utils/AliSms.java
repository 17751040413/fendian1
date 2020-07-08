package com.wowoniu.fendian.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import com.wowoniu.fendian.config.AliSmsConfig;
import com.wowoniu.fendian.config.AuthCodeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Component
public class AliSms {
    @Autowired
    AliSmsConfig aliSmsConfig;
    public  Object smsCode(String phone,@ApiIgnore HttpSession httpSession){
        String code = RandomUtil.getRandom();

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliSmsConfig.accessKeyId, aliSmsConfig.accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", aliSmsConfig.signName);
        request.putQueryParameter("TemplateCode", aliSmsConfig.tempId);
        request.putQueryParameter("TemplateParam", "{\"code\":"+code+"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        AuthCodeConfig authCodeConfig = new AuthCodeConfig(code,new Date(),phone);
        httpSession.setAttribute("authCodeConfig",authCodeConfig);

        return new Result(200,true,"发送成功");
    }


}
