package com.wowoniu.fendian.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.wowoniu.fendian.mapper.ActivitySetMapper;
import com.wowoniu.fendian.model.FissionSet;
import com.wowoniu.fendian.model.FissionSetDetail;
import com.wowoniu.fendian.service.ActivitySetService;
import com.wowoniu.fendian.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动设置接口实现
 *
 * @author yuany
 * @date 2020-07-08
 */
@Service("activitySetService")
public class ActivitySetServiceImpl implements ActivitySetService {

    @Autowired
    private ActivitySetMapper activitySetMapper;

    /**
     * 商家ID获取裂变及详情
     *
     * @param userId 商家ID
     * @return
     */
    @Override
    public JSONObject getFissionAndDetail(String userId) {
        JSONObject jsonObject = new JSONObject();
        FissionSet fissionSet = activitySetMapper.getFissionSet(userId);
        if (fissionSet == null) {
            return null;
        }
        jsonObject.put("fission", fissionSet);
        jsonObject.put("detail", activitySetMapper.getFissionSetDetail(fissionSet.getId()));
        return jsonObject;
    }

    /**
     * 新增/更新裂变
     *
     * @param fissionSet 裂变实体
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addOrUpdateFission(FissionSet fissionSet) {
        if (fissionSet == null) {
            return false;
        }
        //新增
        if (fissionSet.getId() == null) {
            fissionSet.setId(StringUtils.getUuid());
            activitySetMapper.addFissionSet(fissionSet);
        } else {
            //更新
            activitySetMapper.updateFissionSet(fissionSet);
        }
        return true;
    }

    /**
     * 新增/更新裂变详情
     *
     * @param fissionSetDetail 裂变详情实体
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addOrUpdateFissionDetail(FissionSetDetail fissionSetDetail) {
        if (fissionSetDetail == null) {
            return false;
        }
        //新增
        if (fissionSetDetail.getId() == null) {
            fissionSetDetail.setId(StringUtils.getUuid());
            fissionSetDetail.setLevel(activitySetMapper.getFissionSetDetail(fissionSetDetail.getFissionId()).size() + 1);
            activitySetMapper.addFissionSetDetail(fissionSetDetail);
        } else {
            //更新
            List<FissionSetDetail> fissionSetDetailList = new ArrayList<>();
            fissionSetDetailList.add(fissionSetDetail);
            activitySetMapper.updateFissionSetDetailList(fissionSetDetailList);
        }
        return true;
    }

    /**
     * 删除裂变详情
     *
     * @param id        裂变详情ID
     * @param fissionId 裂变ID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteFissionDetail(String id, String fissionId) {
        if (StringUtils.isEmpity(id)) {
            return false;
        }
        activitySetMapper.deleteFissionSetDetail(id);
        //获取裂变集合 重新排序
        List<FissionSetDetail> fissionSetDetailList = activitySetMapper.getFissionSetDetail(fissionId);
        if (CollectionUtils.isEmpty(fissionSetDetailList)) {
            return true;
        }
        int level = 0;
        for (FissionSetDetail fissionSetDetail : fissionSetDetailList) {
            level++;
            fissionSetDetail.setLevel(level);
        }
        activitySetMapper.updateFissionSetDetailList(fissionSetDetailList);

        return true;
    }
}
