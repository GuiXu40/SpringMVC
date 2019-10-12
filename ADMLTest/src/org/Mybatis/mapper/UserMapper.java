package org.Mybatis.mapper;

import org.Mybatis.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Insert("Insert into tb_user1(name,sex,age) values(#{name},#{sex},#{age})")
    @Options(useGeneratedKeys = true ,keyProperty = "id")
    int saveUser(User user);

    @Delete("delete from tb_user1 where id=#{id}")
    int removeUser(@Param("id") int id);

    @Update("update tb_user1 set name=#{name},sex=#{sex},age=#{age} where id=#{id}")
    void modifyUser(User user);

    @Select("select * from tb_user1 where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "sex",property = "sex"),
            @Result(column = "age",property = "age"),
    })
    User selectUserById(Integer id);

    @Select("select * from tb_user1")
    List<User> selectAllUser();
}
