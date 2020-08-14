package com.wowoniu.fendian.service.impl;

import com.wowoniu.fendian.mapper.ActivityMapper;
import com.wowoniu.fendian.model.Activity;
import com.wowoniu.fendian.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityMapper activityMapper;

    @Override
    public Activity getActivity(int id) {
        return activityMapper.selectByPrimaryKey(id);
    }
}
