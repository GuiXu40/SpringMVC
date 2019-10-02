package org.Mybatis.mapper;

import org.Mybatis.domain.Employee;

import java.util.HashMap;
import java.util.List;

public interface EmployeeMapper {
    List<Employee> selectEmployeeByIdLike(HashMap<String,Object> params);
    Employee selectEmployeeWithId(Integer id);
    void updateEmployeeIfNecessary(Employee employee);
    List<Employee> selectEmployeeIn(List<Integer> ids);
    List<Employee> selectEmployeeLikeName(Employee employee);
}
