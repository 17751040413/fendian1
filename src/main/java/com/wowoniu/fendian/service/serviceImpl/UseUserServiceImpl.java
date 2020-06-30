package com.wowoniu.fendian.service.serviceImpl;

import com.wowoniu.fendian.mapper.UseUserMapper;
import com.wowoniu.fendian.mapper.UserLoginMapper;
import com.wowoniu.fendian.model.UseUser;
import com.wowoniu.fendian.model.UserLogin;
import com.wowoniu.fendian.model.pack.LoginPack;
import com.wowoniu.fendian.service.UseUserService;
import com.wowoniu.fendian.utils.JwtUtils;
import com.wowoniu.fendian.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Service
public class UseUserServiceImpl implements UseUserService {
    @Autowired
    UseUserMapper useUserMapper;
    @Autowired
    UserLoginMapper userLoginMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public LoginPack weChatLogin(String openId, String photo, String nickname, String identification) {
        //查询此微信号是否注册
        UseUser useUser = useUserMapper.queryUserByOpenId(openId);
        LoginPack loginPack = new LoginPack();
        if(useUser == null){

        }else {
            String token = JwtUtils.geneJsonToken(useUser);
            String id = StringUtils.getUuid();
            UserLogin userLogin = new UserLogin(id,useUser.getId(),identification,0,new Date(),null);
            //添加用户登录信息
            userLoginMapper.insertUserLogin(userLogin);
            int i = 0;
            if (StringUtils.isEmpity(useUser.getLoginName())) i = 1;
            loginPack.setFlg(i);
            loginPack.setToken(token);
            loginPack.setUseUser(useUser);
        }

        return loginPack;
    }
}
