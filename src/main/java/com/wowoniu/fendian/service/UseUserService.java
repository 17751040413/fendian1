package com.wowoniu.fendian.service;

import com.wowoniu.fendian.model.pack.LoginPack;
import com.wowoniu.fendian.utils.Result;

import javax.servlet.http.HttpSession;
import java.io.IOException;
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
}
