package com.wowoniu.fendian.service.serviceImpl;

import com.wowoniu.fendian.config.AuthCodeConfig;
import com.wowoniu.fendian.mapper.UseUserMapper;
import com.wowoniu.fendian.mapper.UserLoginMapper;
import com.wowoniu.fendian.model.UseUser;
import com.wowoniu.fendian.model.UserLogin;
import com.wowoniu.fendian.model.pack.LoginPack;
import com.wowoniu.fendian.service.UseUserService;
import com.wowoniu.fendian.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

@Service
public class UseUserServiceImpl implements UseUserService {

    @Autowired
    UseUserMapper useUserMapper;
    @Autowired
    UserLoginMapper userLoginMapper;
    //上传地址
    @Value("${headimg.path}")
    String headImgPath;
    //访问路径
    @Value("${headimg.visit}")
    String headImgVisit;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public LoginPack weChatLogin(String openId, String photo, String nickname, String identification) throws IOException {
        //查询此微信号是否注册
        UseUser useUser = useUserMapper.queryUserByOpenId(openId);
        LoginPack loginPack = new LoginPack();
        //初次注册
        if (useUser == null) {
            UseUser newUser = new UseUser();
            newUser.setId(StringUtils.getUuid());
            newUser.setNickName(nickname);
            newUser.setWechatId(openId);
            URL url = new URL(photo);
            File file = new File(url.getFile());
            String fe = file.getName();
            String suffix = fe.substring(fe.lastIndexOf(".") + 1);// 获取后缀
            String fileName = StringUtils.getUuid() + "." + suffix;// 获取文件名

            //上传头像到服务器
            ImgByUrl.getImageByUrl(photo, headImgPath, fileName);
            //获取头像访问地址
            String imgVisit = headImgVisit+fileName;
            newUser.setHeadImg(imgVisit);
            newUser.setHierarchy(1);
            newUser.setTeamNumber(0);
            newUser.setSystemNumber(0);
            newUser.setTodayTermNumber(0);
            newUser.setOpenSystemFlg(0);
            newUser.setPromotionIncome(0);
            newUser.setBalance(0);
            newUser.setState(0);
            newUser.setCreateTime(new Date());

            useUserMapper.insertSelective(newUser);
            String token = JwtUtils.geneJsonToken(newUser);
            String id = StringUtils.getUuid();
            UserLogin userLogin = new UserLogin(id, newUser.getId(), identification, 0, new Date(), null);
            //添加用户登录信息
            userLoginMapper.insertUserLogin(userLogin);
            loginPack.setFlg(1);
            loginPack.setToken(token);
            loginPack.setUseUser(newUser);

        }
        //推荐用户首次登录
        else if(StringUtils.isEmpity(useUser.getNickName())){



        }
        else {
            String token = JwtUtils.geneJsonToken(useUser);
            String id = StringUtils.getUuid();
            UserLogin userLogin = new UserLogin(id, useUser.getId(), identification, 0, new Date(), null);
            //添加用户登录信息
            userLoginMapper.insertUserLogin(userLogin);
            int i = 0;
            //判断是否已经绑定手机号
            if (StringUtils.isEmpity(useUser.getLoginName())) i = 1;
            loginPack.setFlg(i);
            loginPack.setToken(token);
            loginPack.setUseUser(useUser);
        }

        return loginPack;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result smsLogin(String code, String phone, String identification, HttpSession httpSession) {
        //通过账号查询用户
        UseUser useUser = useUserMapper.queryUserByLoginName(phone);
        LoginPack loginPack = new LoginPack();
        Result result;
        //获取验证码信息
        AuthCodeConfig authCodeConfig = (AuthCodeConfig) httpSession.getAttribute("authCodeConfig");
        if (authCodeConfig == null){
            return new  Result(204,true,"请重新获取验证码");
        }
        String authCode = authCodeConfig.getAuthcode();
        Date sendDate = authCodeConfig.getDate();
        String iphone = authCodeConfig.getPhone();

        Date nowDate = new Date();


        if (!(phone.equals(iphone))) {
            result = new Result(204, true, "手机号不一致");
        } else if (sendDate != null && DateUtils.minutie(nowDate, sendDate) > 10) {
            result = new Result(204, true, "验证码超时，请重新获取");
        } else if (authCode == null || !(authCode.equals(code))) {
            result = new Result(204, true, "验证码错误");
        }else {
            if (useUser == null){
                UseUser registerUser = new UseUser();
                registerUser.setId(StringUtils.getUuid());
                registerUser.setLoginName(phone);
                registerUser.setCreateTime(new Date());
                useUserMapper.insertSelective(registerUser);

                //添加登录信息
                UserLogin userLogin = new UserLogin(StringUtils.getUuid(),registerUser.getId(),identification, 0,new Date(),new Date());
                userLoginMapper.insertUserLogin(userLogin);

                String token = JwtUtils.geneJsonToken(registerUser);
                loginPack.setFlg(1);
                loginPack.setToken(token);
                loginPack.setUseUser(registerUser);

                result = new Result(200,true,"登录成功",loginPack);

            }else {

                String token = JwtUtils.geneJsonToken(useUser);
                //添加登录信息
                UserLogin usersLogin = new UserLogin(StringUtils.getUuid(),useUser.getId(),identification,
                         0,new Date(),new Date());
                userLoginMapper.insertUserLogin(usersLogin);

                int i = 0;
                //判断是否已经绑定微信号
                if (StringUtils.isEmpity(useUser.getWechatId())) i = 1;
                loginPack.setFlg(i);
                loginPack.setToken(token);
                loginPack.setUseUser(useUser);
                result = new Result(200,true,"登录成功",loginPack);
            }

        }

        return result;
    }


}
