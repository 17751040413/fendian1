package com.wowoniu.fendian.service;

import com.wowoniu.fendian.model.Activity;

public interface ActivityService {

    /**
     * 根据id获取活动信息
     * @param id
     * @return
     */
    Activity getActivity(int id);
}
