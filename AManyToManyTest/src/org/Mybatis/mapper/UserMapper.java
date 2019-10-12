package org.Mybatis.mapper;

import org.Mybatis.domain.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from tb_user where id=#{id}")
    User selectById(int id);
}
