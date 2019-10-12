package org.Mybatis.mapper;

import org.Mybatis.domain.Clazz;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

public interface ClazzMapper {
    @Select("select * from tb_clazz where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "code",property = "code"),
            @Result(column = "name",property = "name"),
            @Result(column = "id",property = "students",many = @Many(select = "org.Mybatis.mapper.StudentMapper.selectStudentByClazzId",fetchType = FetchType.LAZY))
    })
    Clazz selectById(int id);
}
