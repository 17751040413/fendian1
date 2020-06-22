package com.wowoniu.fendian.service.serviceImpl;

import com.wowoniu.fendian.mapper.UserLoginMapper;
import com.wowoniu.fendian.model.UserLogin;
import com.wowoniu.fendian.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    UserLoginMapper userLoginMapper;
    @Override
    public UserLogin queryUserLoginById(String id) {

        return userLoginMapper.queryUserLoginById(id);
    }

    @Override
    public int updateConTimeUserLoginById(UserLogin userLogin) {
        return userLoginMapper.updateConTimeUserLoginById(userLogin);
    }
}
