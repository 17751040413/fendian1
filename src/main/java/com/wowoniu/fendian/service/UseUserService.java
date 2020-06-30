package com.wowoniu.fendian.service;

import com.wowoniu.fendian.model.pack.LoginPack;

import java.util.Map;

public interface UseUserService {
    /**
     * 微信登录
     * @return
     */
    LoginPack weChatLogin(String openId, String photo, String nickname, String identification);
}
