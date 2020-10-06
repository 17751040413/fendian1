package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.UseUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UseUserMapper {

    /**
     * 根据openid查询用户
     * @param openId
     * @return
     */
    UseUser queryUserByOpenId(String openId);


    int deleteByPrimaryKey(String id);

    int insert(UseUser record);

    int insertSelective(UseUser record);

    UseUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UseUser record);

    int updateByPrimaryKey(UseUser record);

    /**
     * 根据用户名查询
     * @param loginName
     * @return
     */
    UseUser queryUserByLoginName(String loginName);

    /**
     * 获取总人数根据父类
     * @param parentId
     * @return
     */
    int queryCountByParent(String parentId);

    /**
     * 获取总人数根据父类今天
     * @param parentId
     * @return
     */
    int queryCountByTodayParent(String parentId);

    /**
     * 获取用户信息根据父级
     * @param parentId
     * @return
     */
    List<UseUser> queryListByParent(@Param("parentId") String parentId, @Param("flg") int flg);

}
