package com.wowoniu.fendian.service;

import com.wowoniu.fendian.model.UserLogin;

public interface UserLoginService {


    /**
     * 查询最近一次登录信息
     * @param id
     * @return
     */
    UserLogin queryUserLoginById(String id);

    /**
     * 修改连接时间
     * @param id
     * @return
     */
    int updateConTimeUserLoginById(UserLogin userLogin);
}
