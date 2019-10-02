package org.Mybatis.mapper;

import org.Mybatis.domain.User;

public interface UserMapper {
    User selectUserById(int id);
}
