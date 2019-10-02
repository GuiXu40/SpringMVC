package org.Mybatis.test;

        import org.Mybatis.domain.Employee;
        import org.Mybatis.mapper.EmployeeMapper;
        import org.apache.ibatis.io.Resources;
        import org.apache.ibatis.session.SqlSession;
        import org.apache.ibatis.session.SqlSessionFactory;
        import org.apache.ibatis.session.SqlSessionFactoryBuilder;
        import java.io.IOException;
        import java.io.InputStream;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

public class DynamicSQLTest {
    public static void main(String[] args) throws IOException {
        //读取配置文件
        InputStream inputStream= Resources.getResourceAsStream("mybatis-config.xml");
        //初始化mybatis,创建SqlSessionFactory类的实例
        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
        //创建session实例
        SqlSession session=sqlSessionFactory.openSession();
        DynamicSQLTest t=new DynamicSQLTest();
        t.testSelectEmployeeLikeName(session);
        session.commit();
        session.close();
    }
    public void testSelectEmployeeByIdLike(SqlSession session){
        EmployeeMapper em=session.getMapper(EmployeeMapper.class);
        HashMap<String,Object> params=new HashMap<>();
        params.put("id",1);
        List<Employee> employees=em.selectEmployeeByIdLike(params);
        employees.forEach(employee -> System.out.println(employee));
    }
    public void testSelectEmployeeChoose(SqlSession session){
        EmployeeMapper em=session.getMapper(EmployeeMapper.class);
        HashMap<String,Object> params=new HashMap<>();
        params.put("id",1);
        params.put("loginname","guixu");
        params.put("password","123456");
        List<Employee> employees=em.selectEmployeeByIdLike(params);
        employees.forEach(employee -> System.out.println(employee));
    }
    public void testEmployeeIfNecessary(SqlSession session){
        EmployeeMapper em=session.getMapper(EmployeeMapper.class);
        Employee employee=em.selectEmployeeWithId(4);
        employee.setLoginname("mary");
        employee.setPassword("123");
        employee.setName("玛丽");
        em.updateEmployeeIfNecessary(employee);
    }
    public void testSelectEmployeeIn(SqlSession session){
        EmployeeMapper em=session.getMapper(EmployeeMapper.class);
        List<Integer> ids=new ArrayList<>();
        ids.add(1);
        ids.add(2);
        List<Employee> list=em.selectEmployeeIn(ids);
        list.forEach(employee -> System.out.println(employee));
    }
    public void testSelectEmployeeLikeName(SqlSession session){
        EmployeeMapper em=session.getMapper(EmployeeMapper.class);
        Employee employee=new Employee();
        employee.setName("g");
        List<Employee> list=em.selectEmployeeLikeName(employee);
        System.out.println(list);
    }
}
