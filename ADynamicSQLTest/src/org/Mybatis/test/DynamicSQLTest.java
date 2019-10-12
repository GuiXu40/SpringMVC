package org.Mybatis.test;

import org.Mybatis.domain.Employee;
import org.Mybatis.factory.FKSqlSessionFactory;
import org.Mybatis.mapper.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicSQLTest {
    public static void main(String[] args) {
        SqlSession session= FKSqlSessionFactory.getSqlSession();
        DynamicSQLTest dynamicSQLTest=new DynamicSQLTest();
        //获得EmployeeMapper对象
        EmployeeMapper em=session.getMapper(EmployeeMapper.class);
//        dynamicSQLTest.testSelectWithParam(em);
//        dynamicSQLTest.updateEmployee(em);
        dynamicSQLTest.deleteEmployee(em);
        session.commit();
        session.close();
    }
    //根据动态参数查询员工数据
    public void testSelectWithParam(EmployeeMapper em){
        //使用Map装载参数
        Map<String,Object> param=new HashMap<>();
        param.put("loginname","guixu");
        param.put("password","123456");
        //调用selectWithParam方法
        List<Employee> list=em.selectWithParam(param);
        //查看返回结果
        System.out.println(list);
    }
    //insert操作
    public void insertEmployee(EmployeeMapper em){
        //创建员工
        Employee e=new Employee();
        e.setLoginname("ysl");
        e.setPassword("123456");
        e.setName("可爱");
        e.setSex("女");
        e.setAge(20);
        e.setPhone("11111111111");
        em.insertEmployee(e);
    }
    public void updateEmployee(EmployeeMapper em){
        //先进行查询操作,选择要修改的员工
        Map<String,Object> param=new HashMap<>();
        param.put("id",5);
        Employee e= (Employee) em.selectEmployeeById(param);
        e.setLoginname("baobao");
        e.setName("测试");
        em.updateEmployee(e);
    }
    public void deleteEmployee(EmployeeMapper em){
        //先进行查询操作,选择要修改的员工
        Map<String,Object> param=new HashMap<>();
        param.put("loginname","guixu");
        param.put("password","123456");
        em.deleteEmployee(param);
    }
}
