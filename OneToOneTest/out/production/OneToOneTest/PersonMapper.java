package org.Mybatis.mapper;

import org.Mybatis.domain.Person;

public interface PersonMapper {
//    根据id查询person
//    方法名和参数必须和xml文件中的<select..>元素的id属性和parameterType属性一致
    Person selectPersonById(Integer id);
}
