package org.Mybatis.mapper;

import org.Mybatis.domain.Student;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface StudentMapper {
    @Select("select * from tb_student where clazz_id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "sex",property = "sex"),
            @Result(column = "age",property = "age"),
    })
    Student selectStudentByClazzId();
}
