package org.Mybatis.mapper;

import org.Mybatis.domain.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    //动态查询(查询单个)
    @SelectProvider(type = EmployeeDynaSqlProvider.class,method = "selectWithParam")
    Employee selectEmployeeById(Map<String,Object> param);
    //动态查询(查询多个)
    @SelectProvider(type = EmployeeDynaSqlProvider.class,method = "selectWithParam")
    List<Employee> selectWithParam(Map<String,Object> param);
    //动态插入
    @InsertProvider(type = EmployeeDynaSqlProvider.class,method = "insertEmployee")
    @Options(useGeneratedKeys = true,keyProperty ="id")
    int insertEmployee(Employee employee);
    //动态更新
    @UpdateProvider(type = EmployeeDynaSqlProvider.class,method = "updateEmployee")
    void updateEmployee(Employee employee);
    //动态删除
    @DeleteProvider(type = EmployeeDynaSqlProvider.class,method = "deleteEmployee")
    void deleteEmployee(Map<String,Object> param);
}
