package com.wowoniu.fendian.service;

import com.wowoniu.fendian.model.pack.LoginPack;
import com.wowoniu.fendian.model.pack.UserInfoPack;
import com.wowoniu.fendian.utils.Result;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
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
    Result<LoginPack> smsLogin(String code, String phone, String identification, HttpSession httpSession) throws IOException;

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

    /**
     * 我的团队
     * @param id
     * @return
     */
    Result getTeam(String id);

    /**
     * 获取我的消息
     * @param id
     * @return
     */
    Result getMessage(String id);

    /**
     * 修改昵称
     * @param id
     * @return
     */
    Result updateNickName(String id,String nickName);


    Result updateHeadImg(String userId, MultipartFile img,HttpServletRequest request);

    /**
     * 获取我的收益
     * @param userid
     * @return
     */
    Result profit(String userid);

    /**
     * 前往补贴页面
     * @return
     */
    Result toBuTie(String userid);

    /**
     * 领取补贴
     * @param butieId
     * @return
     */
    Result lingquBu(String butieId);
}
