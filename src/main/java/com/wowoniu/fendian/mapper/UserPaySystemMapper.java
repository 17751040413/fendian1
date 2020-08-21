package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.UserPaySystem;

public interface UserPaySystemMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserPaySystem record);

    int insertSelective(UserPaySystem record);

    UserPaySystem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserPaySystem record);

    int updateByPrimaryKey(UserPaySystem record);

    /**
     * 查询总额根据支付人id
     * @param payId
     * @return
     */
    double queryMoneyByPaid(String payId);
}