package com.wowoniu.fendian.service;

import com.wowoniu.fendian.model.pack.LoginPack;
import com.wowoniu.fendian.model.pack.UserInfoPack;
import com.wowoniu.fendian.utils.Result;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

public interface UseUserService {
    /**
     * 微信登录
     * @return
     */
    LoginPack weChatLogin(String openId, String photo, String nickname, String identification) throws IOException;

    /**
     * 验证码登录
     * @param code
     * @param phone
     * @param identification
     * @param httpSession
     * @return
     */
    Result smsLogin(String code, String phone, String identification, HttpSession httpSession);

    /**
     * 绑定手机号
     * @param phone
     * @param code
     * @param httpSession
     * @return
     */
    Result bindPhone(String phone, String code,HttpSession httpSession,String userId);

    /**
     * 绑定微信
     * @param openId
     * @param photo
     * @param nickname
     * @param userId
     * @return
     */
    Result bindWeChat(String openId, String photo, String nickname,String userId) throws MalformedURLException;

    /**
     * 获取个人中心
     * @param userid
     * @return
     */
    UserInfoPack getUserInfo(String userid);
}
