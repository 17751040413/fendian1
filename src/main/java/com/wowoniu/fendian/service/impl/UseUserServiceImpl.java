package com.wowoniu.fendian.service.impl;

import ch.qos.logback.core.spi.LogbackLock;
import com.alibaba.fastjson.JSON;
import com.wowoniu.fendian.config.AuthCodeConfig;
import com.wowoniu.fendian.mapper.*;
import com.wowoniu.fendian.model.*;
import com.wowoniu.fendian.model.pack.LoginPack;
import com.wowoniu.fendian.model.pack.UserInfoPack;
import com.wowoniu.fendian.service.UseUserService;
import com.wowoniu.fendian.utils.*;
import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    UoionSeparateLogMapper uoionSeparateLogMapper;
    @Autowired
    WriteOffMapper writeOffMapper;
    @Autowired
    UseUserSubsidyMapper useUserSubsidyMapper;


    private String fileSavePath = "C:/images/";


    /**
     * 时间格式化
     */
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");
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
//            URL url = new URL(photo);
//            File file = new File(url.getFile());
//            String fe = file.getName();
//            String suffix = fe.substring(fe.lastIndexOf(".") + 1);// 获取后缀
//            //临时
//            String fileName = StringUtils.getUuid() + "." + "jpg";// 获取文件名
//
//            //上传头像到服务器
//            ImgByUrl.getImageByUrl(photo, headImgPath, fileName);
//            //获取头像访问地址
//            String imgVisit = headImgVisit+fileName;
            newUser.setHeadImg(photo);
            newUser.setHierarchy(1);
            newUser.setTeamNumber(0);
            newUser.setSystemNumber(0);
            newUser.setTodayTermNumber(0);
            newUser.setOpenSystemFlg(0);
            newUser.setPromotionIncome(0);
            newUser.setBalance(0);
            newUser.setState(0);
            newUser.setIdentification(identification);
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


            String fileName = "登录数据--"+DateUtils.format(new Date());
            String loginData = JSON.toJSONString(loginPack);
            CreateTxt.writeToText(loginData,fileName);
        }

        else {
            useUser.setIdentification(identification);
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

            String fileName = "登录数据--"+DateUtils.format(new Date());
            String loginData = JSON.toJSONString(loginPack);
            CreateTxt.writeToText(loginData,fileName);
        }

        return loginPack;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result smsLogin(String code, String phone, String identification, HttpSession httpSession) throws IOException {
        //通过账号查询用户
        UseUser useUser = useUserMapper.queryUserByLoginName(phone);
        LoginPack loginPack = new LoginPack();
        Result result;
        //获取验证码信息
        AuthCodeConfig authCodeConfig = (AuthCodeConfig) httpSession.getAttribute("authCodeConfig");
        if (authCodeConfig == null){
            return new  Result(204,true,"请重新获取验证码");
        }
        //临时
        //String authCode = authCodeConfig.getAuthcode();
        String authCode = "1234";
        Date sendDate = authCodeConfig.getDate();
        String iphone = authCodeConfig.getPhone();

        Date nowDate = new Date();

        if (!(phone.equals(iphone))) {
            result = new Result(204, true, "手机号不一致");
        } else if (sendDate != null && DateUtils.minutie(nowDate, sendDate) > 10) {
            result = new Result(204, true, "验证码超时，请重新获取");
            //临时 测试
        } else if (authCode == null || !(authCode.equals(code))) {
            result = new Result(204, true, "验证码错误");
        }else {
            if (useUser == null){
                UseUser registerUser = new UseUser();
                registerUser.setId(StringUtils.getUuid());
                registerUser.setLoginName(phone);
                registerUser.setCreateTime(new Date());
                registerUser.setIdentification(identification);
                useUserMapper.insertSelective(registerUser);

                //添加登录信息
                UserLogin userLogin = new UserLogin(StringUtils.getUuid(),registerUser.getId(),identification, 0,new Date(),new Date());
                userLoginMapper.insertUserLogin(userLogin);

                String token = JwtUtils.geneJsonToken(registerUser);
                loginPack.setFlg(1);
                loginPack.setToken(token);
                loginPack.setUseUser(registerUser);

                String fileName = "登录数据--"+DateUtils.format(new Date());
                String loginData = JSON.toJSONString(loginPack);
                CreateTxt.writeToText(loginData,fileName);

                result = new Result(200,true,"登录成功",loginPack);

            }else {
                useUser.setIdentification(identification);
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

                String fileName = "登录数据--"+DateUtils.format(new Date());
                String loginData = JSON.toJSONString(loginPack);
                CreateTxt.writeToText(loginData,fileName);
                result = new Result(200,true,"登录成功",loginPack);
            }

        }

        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result bindPhone(String phone, String code, HttpSession httpSession,String userId) {

        Result result;
        //获取验证码信息
        AuthCodeConfig authCodeConfig = (AuthCodeConfig) httpSession.getAttribute("authCodeConfig");
        if (authCodeConfig == null){
            return new  Result(204,true,"请重新获取验证码");
        }
        //临时
        //String authCode = authCodeConfig.getAuthcode();
        String authCode = "1234";
        Date sendDate = authCodeConfig.getDate();
        String iphone = authCodeConfig.getPhone();

        Date nowDate = new Date();

        UseUser us = useUserMapper.queryUserByLoginName(phone);
        if (us != null){
            return new Result(204,true,"当前手机已绑定其他账号,请更换");
        }

        if (!(phone.equals(iphone))) {
            result = new Result(204, true, "手机号不一致");
        } else if (sendDate != null && DateUtils.minutie(nowDate, sendDate) > 10) {
            result = new Result(204, true, "验证码超时，请重新获取");
            //临时
        } else if (authCode == null || !(authCode.equals(code))) {
            result = new Result(204, true, "验证码错误");
        }else {
            UseUser useUser = new UseUser();
            useUser.setId(userId);
            useUser.setLoginName(phone);
            useUserMapper.updateByPrimaryKeySelective(useUser);
            result = new Result(200,true,"绑定成功");
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result bindWeChat(String openId, String photo, String nickname, String userId) throws MalformedURLException {

        Result result;
        UseUser us = useUserMapper.queryUserByOpenId(openId);
        if (us != null){
            return new Result(204,true,"当前微信已存在");
        }
        UseUser newUser = new UseUser();
        newUser.setNickName(nickname);
        newUser.setWechatId(openId);
        URL url = new URL(photo);
        File file = new File(url.getFile());
        String fe = file.getName();
        String suffix = fe.substring(fe.lastIndexOf(".") + 1);// 获取后缀
        //临时
        String fileName = StringUtils.getUuid() + "." + "jpg";// 获取文件名

        //上传头像到服务器
        ImgByUrl.getImageByUrl(photo, headImgPath, fileName);
        //获取头像访问地址
        String imgVisit = headImgVisit+fileName;
        newUser.setHeadImg(imgVisit);
        newUser.setId(userId);
        useUserMapper.updateByPrimaryKeySelective(newUser);

        return new Result(200,true,"绑定成功");
    }

    @Override
    public UserInfoPack getUserInfo(String userid) {

        UserInfoPack userInfoPack = new UserInfoPack();
        //根据id获取个人信息
        UseUser useUser = useUserMapper.selectByPrimaryKey(userid);
        if (useUser != null){
            userInfoPack.setBalance(useUser.getBalance());
            userInfoPack.setHeadImg(useUser.getHeadImg());
            userInfoPack.setParentName(useUser.getParentName());
            userInfoPack.setPromotionIncome(useUser.getPromotionIncome());
            userInfoPack.setRoleId(useUser.getRoleId2());
            userInfoPack.setPhone(useUser.getLoginName());
            userInfoPack.setId(useUser.getId());
            userInfoPack.setNickName(useUser.getNickName());
            if (useUser.getRoleId2().equals("-1")){
                userInfoPack.setRoleName("未开通");
            }else if(useUser.getRoleId2().equals("0")){
                userInfoPack.setRoleName("区域代理");
            }else if(useUser.getRoleId2().equals("1")){
                userInfoPack.setRoleName("市级代理");
            }else if(useUser.getRoleId2().equals("2")){
                userInfoPack.setRoleName("省级代理");
            }

        }
        return userInfoPack;
    }

    @Override
    public Result getTeam(String id) {
        //根据id获取个人信息
        UseUser useUser = useUserMapper.selectByPrimaryKey(id);

        Map map = new HashMap();
        //总人数
        int sumCount = useUserMapper.queryCountByParent(id);
        //今日新增
        int todayCount = useUserMapper.queryCountByTodayParent(id);
        //未开通
        List<UseUser> weiUseUsers = useUserMapper.queryListByParent(id,0);
        //已开通
        List<UseUser> yiUseUser = useUserMapper.queryListByParent(id,1);

        map.put("sumCount",sumCount);
        map.put("todayCount",todayCount);
        map.put("weiUseUsers",weiUseUsers);
        map.put("yiUseUser",yiUseUser);
        return new Result(200,true,"获取成功",map);
    }

    @Override
    public Result getMessage(String id) {
        UseUser useUser = useUserMapper.selectByPrimaryKey(id);
        List<Message> messages = messageMapper.queryMessageByUserId(id);
        return new Result(200,true,"获取成功",messages);
    }

    @Override
    public Result updateNickName(String id,String nickName) {

        UseUser useUser = useUserMapper.selectByPrimaryKey(id);
        useUser.setNickName(nickName);
        useUserMapper.updateByPrimaryKeySelective(useUser);
        return new Result(200,true,"修改成功");
    }

    @Override
    public Result updateHeadImg(String userId, MultipartFile img, HttpServletRequest request) {



        String url;
        UseUser users = useUserMapper.selectByPrimaryKey(userId);
        //1.后半段目录：  2020/03/15
        String directory = simpleDateFormat.format(new Date());
        File dir = new File(fileSavePath + directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //后缀
        String suffix = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
        String newFileName = StringUtils.getUuid() + suffix;
        //4.创建这个新文件
        File newFile = new File(fileSavePath + directory + newFileName);
        //5.复制操作
        try {
            img.transferTo(newFile);
            //协议 :// ip地址 ：端口号 / 文件目录(/images/2020/03/15/xxx.jpg)
            url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/images/" + directory + newFileName;
            //url = headImgVisit+newFileName;
            users.setHeadImg(url);
            useUserMapper.updateByPrimaryKeySelective(users);
            return new Result(200, true, "修改成功");
        } catch (IOException e) {
            return new Result(204, true, "上传失败");
        }

    }

    @Override
    public Result profit(String userid) {
        //获取总收益
        double sumPro = uoionSeparateLogMapper.querySepByUserid(userid);
        double todayPro = uoionSeparateLogMapper.querySepByUseridToday(userid);
        double yestayPro = uoionSeparateLogMapper.querySepByUseridYestay(userid);

        List<WriteOff> writeOffs = writeOffMapper.queryWriteByuserid(userid);

        Map map = new HashMap();
        map.put("sumPro",sumPro);
        map.put("todayPro",todayPro);
        map.put("yestayPro",yestayPro);

        map.put("writeOffs",writeOffs);
        return new Result(200,true,"获取成功",map);
    }

    @Override
    public Result toBuTie(String userid) {
        List<UseUserSubsidy> useUserSubsidies = useUserSubsidyMapper.querySubsidyByUserid(userid);
        return new Result(200,true,"获取成功",useUserSubsidies);
    }

    @Override
    @Transactional
    public Result lingquBu(String butieId) {
        UseUserSubsidy useUserSubsidy = useUserSubsidyMapper.selectByPrimaryKey(butieId);
        if (null == useUserSubsidy){
            return new Result(204,true,"id错误");
        }
        if (useUserSubsidy.getReceiveFlg() == 1){
            return new Result(204,true,"当前补贴已领取");
        }

        //领钱
        UseUser useUser = useUserMapper.selectByPrimaryKey(useUserSubsidy.getUserId());
        if (useUser.getSystemNumber() < useUserSubsidy.getConditions()){
            return new Result(204,true,"您当前销售系统数是"+useUser.getSystemNumber()+"不满足领取条件");
        }
        int sum = useUser.getBalance()+useUserSubsidy.getMoney();
        useUser.setBalance(sum);
        useUserMapper.updateByPrimaryKeySelective(useUser);
        //改状态
        useUserSubsidy.setReceiveFlg(1);
        useUserSubsidy.setCreateTime(new Date());
        useUserSubsidyMapper.updateByPrimaryKeySelective(useUserSubsidy);

        return new Result(200,true,"领取成功");
    }

    @Override
    public Result tongBu(String nickName, String headImg,String userId) {
        UseUser useUser = useUserMapper.selectByPrimaryKey(userId);
        useUser.setNickName(nickName);
        useUser.setHeadImg(headImg);
        useUserMapper.updateByPrimaryKeySelective(useUser);
        return new Result(200,true,"同步成功");
    }


}
