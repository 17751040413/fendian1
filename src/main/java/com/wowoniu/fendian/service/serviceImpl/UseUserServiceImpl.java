package com.wowoniu.fendian.service.serviceImpl;

import com.wowoniu.fendian.mapper.UseUserMapper;
import com.wowoniu.fendian.mapper.UserLoginMapper;
import com.wowoniu.fendian.model.UseUser;
import com.wowoniu.fendian.model.UserLogin;
import com.wowoniu.fendian.model.pack.LoginPack;
import com.wowoniu.fendian.service.UseUserService;
import com.wowoniu.fendian.utils.ImgByUrl;
import com.wowoniu.fendian.utils.JwtUtils;
import com.wowoniu.fendian.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Map;

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
}
