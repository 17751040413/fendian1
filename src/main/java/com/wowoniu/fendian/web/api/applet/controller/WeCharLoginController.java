package com.wowoniu.fendian.web.api.applet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.common.GlobalResult;
import com.wowoniu.fendian.mapper.UserMapper;
import com.wowoniu.fendian.model.User;
import com.wowoniu.fendian.utils.StringUtils;
import com.wowoniu.fendian.utils.WechatUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author
 * @date 2020-09-01
 */
@Api(value = "微信登陆", tags = "小程序-微信登陆")
@RestController
@RequestMapping("/weChar")
public class WeCharLoginController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 微信用户登录详情
     */
    @ApiOperation("微信登陆验证 ")
    @PostMapping("/login")
    @ApiImplicitParams({@ApiImplicitParam(name = "code", value = "code ", dataType = "String", required = true),
            @ApiImplicitParam(name = "rawData", value = "用户非敏感信息：rawData【nickName：昵称；avatarUrl：头像地址；gender：性别；city：市；country：国家；province：省；】 ", dataType = "String"),
            @ApiImplicitParam(name = "signature", value = "签名：signature ", dataType = "String"),
            @ApiImplicitParam(name = "encrypteData", value = "encrypteData比rowData多了appid和openid（备用 暂时用不到） ", dataType = "String"),
            @ApiImplicitParam(name = "iv", value = "偏移（备用 暂时用不到） ", dataType = "String")})
    public GlobalResult user_login(String code, String rawData, String signature, String encrypteData, String iv) {
        // 用户非敏感信息：rawData
        // 签名：signature
        JSONObject rawDataJson = JSON.parseObject(rawData);
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        // 3.接收微信接口服务 获取返回的参数
        String openid = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");

        // 4.校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
        String signature2 = DigestUtils.sha1Hex(rawData + sessionKey);
        if (!signature.equals(signature2)) {
            return GlobalResult.build(500, "签名校验失败", null);
        }
        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新最新登录时间
        User user = this.userMapper.selectById(openid);
        // uuid生成唯一key，用于维护微信小程序用户与服务端的会话
        String skey = UUID.randomUUID().toString();
        if (user == null) {
            // 用户信息入库
            String nickName = rawDataJson.getString("nickName");
            String avatarUrl = rawDataJson.getString("avatarUrl");
            String gender = rawDataJson.getString("gender");
            String city = rawDataJson.getString("city");
            String country = rawDataJson.getString("country");
            String province = rawDataJson.getString("province");

            user = new User();
            user.setOpenId(openid);
            user.setSkey(skey);
            user.setCreateTime(new Timestamp(System.currentTimeMillis()));
            user.setLastVisitTime(new Timestamp(System.currentTimeMillis()));
            user.setSessionKey(sessionKey);
            user.setCity(city);
            user.setProvince(province);
            user.setCountry(country);
            user.setAvatarUrl(avatarUrl);
            user.setGender(Integer.parseInt(gender));
            user.setNickName(nickName);

            this.userMapper.addUser(user);
        } else {
            // 已存在，更新用户登录时间
            user.setLastVisitTime(new Timestamp(System.currentTimeMillis()));
            // 重新设置会话skey
            user.setSkey(skey);
            this.userMapper.updateById(user);
        }
        //encrypteData比rowData多了appid和openid
//        JSONObject userInfo = WechatUtil.getUserInfo(encrypteData, sessionKey, iv);
        //6. 把新的skey返回给小程序
        GlobalResult result = GlobalResult.build(200, null, skey);
        return result;
    }

    @ApiOperation("获取skey")
    @PostMapping("/getSkey")
    @ApiImplicitParams({@ApiImplicitParam(name = "code", value = "code ", dataType = "String", required = true)})
    public GlobalResult getSkey(String code) {
        if (StringUtils.isEmpity(code)) {
            return GlobalResult.build(204, "未获code取参数", null);
        }
        // 开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        //接收微信接口服务 获取返回的参数
        String openid = SessionKeyOpenId.getString("openid");
        if (openid == null) {
            return GlobalResult.build(204, "未找到用户信息", null);
        }
        User user = userMapper.selectByOpenId(openid);
        if (user == null) {
            return GlobalResult.build(204, "未找到用户信息", null);
        }
        return GlobalResult.build(200, "获取成功", user.getSkey());
    }

    @ApiOperation("退出登陆")
    @PostMapping("/signOut")
    @ApiImplicitParams({@ApiImplicitParam(name = "code", value = "code ", dataType = "String", required = true)})
    public GlobalResult signOut(String code) {
        if (StringUtils.isEmpity(code)) {
            return GlobalResult.build(204, "未获code取参数", null);
        }
        // 开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        String openid = SessionKeyOpenId.getString("openid");
        userMapper.signOut(openid);
        return GlobalResult.build(200, "退出成功", null);
    }
}

