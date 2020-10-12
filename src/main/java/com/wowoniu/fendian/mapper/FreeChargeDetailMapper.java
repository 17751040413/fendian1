package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.FreeChargeDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FreeChargeDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(FreeChargeDetail record);

    int insertSelective(FreeChargeDetail record);

    FreeChargeDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FreeChargeDetail record);

    int updateByPrimaryKey(FreeChargeDetail record);

    /**
     * 根据条件获取排队人数
     * @param freeId
     * @param type
     * @return
     */
    int queryFreeChargeCountByFreeId(@Param("freeId") String freeId, @Param("type") int type,
                                     @Param("freeType")int freeType);

    /**
     * 根据活动获取参与活动人数
     * @param freeId
     * @return
     */
    List<FreeChargeDetail> queryAllByFreeId(@Param("freeId") String freeId,@Param("type") int type);

    /**
     * 根据条件查询免单人员
     * @param keyWord
     * @param freeId
     * @return
     */
    List<FreeChargeDetail> queryAllByParms(@Param("keyWord") String keyWord,@Param("freeId") String freeId);

    /**
     * 已免
     * @param freeId
     * @return
     */
    double queryYiMian(String freeId);

    /**
     * 查询已免单到几号
     * @param freeId
     * @return
     */
    int queryNumber(String freeId);
}