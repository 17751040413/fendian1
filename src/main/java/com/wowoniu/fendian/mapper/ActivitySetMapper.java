package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.FissionSetDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 会员活动设置汇总DAO层
 *
 * @author yuany
 * @date 2020-06-28
 */
public interface ActivitySetMapper {

    /**
     * 用户ID获取裂变详情
     *
     * @param userId
     * @return
     */
    @Select("SELECT * FROM fission_set_detail WHERE id = (SELECT id FROM fission_set WHERE user_id = #{userId}) ORDER BY level")
    List<FissionSetDetail> getFissionSetDetail(@Param("userId") String userId);
}
