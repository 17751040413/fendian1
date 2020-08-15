package com.wowoniu.fendian.service;

import com.wowoniu.fendian.model.Activity;

import java.util.List;

public interface ActivityService {

    /**
     * 根据id获取活动信息
     * @param id
     * @return
     */
    Activity getActivity(String id);

    /**
     * 获取所有活动
     * @return
     */
    List<Activity> getAll();
}
