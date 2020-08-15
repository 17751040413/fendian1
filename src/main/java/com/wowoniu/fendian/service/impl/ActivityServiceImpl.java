package com.wowoniu.fendian.service.impl;

import com.wowoniu.fendian.mapper.ActivityMapper;
import com.wowoniu.fendian.model.Activity;
import com.wowoniu.fendian.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityMapper activityMapper;

    @Override
    public Activity getActivity(String id) {
        return activityMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Activity> getAll() {
        return activityMapper.queryAll();
    }
}
