package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.UoionSeparateLog;
import org.apache.ibatis.annotations.Param;

public interface UoionSeparateLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(UoionSeparateLog record);

    int insertSelective(UoionSeparateLog record);

    UoionSeparateLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UoionSeparateLog record);

    int updateByPrimaryKey(UoionSeparateLog record);

    /**
     * 根据分账人id查询分账总金额
     * @param userid
     * @return
     */
    double queryUnionSepPriceByUserId(@Param("userid") String userid, @Param("unionId") String unionId);

    /**
     * 根据联盟id和入账状态查询分账金额
     * @param unionId
     * @param isEntry
     * @return
     */
    double queryUnionPriceByUnionAndIsEntry(@Param("unionId") String unionId,@Param("isEntry") int isEntry);

    /**
     * 根据入账人查询分账金额
     * @param userid
     * @return
     */
    double querySepByUserid(String userid);

    /**
     * 根据入账人查询分账金额
     * @param userid
     * @return
     */
    double querySepByUseridToday(String userid);


    /**
     * 根据入账人查询分账金额
     * @param userid
     * @return
     */
    double querySepByUseridYestay(String userid);
}