package com.wowoniu.fendian.mapper;

import com.wowoniu.fendian.model.UseRole;
import org.apache.ibatis.annotations.Select;

public interface UserRoleMapper {

    @Select("select * from use_role where id = #{id}")
    UseRole queryUseRoleById(String id);
}
