package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.Activity;

import java.util.List;

public interface ActivityMapper {
    int deleteByPrimaryKey(String id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    /**
     * 获取所有活动
     * @return
     */
    List<Activity> queryAll();
}