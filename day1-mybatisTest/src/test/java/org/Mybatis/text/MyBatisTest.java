package org.Mybatis.text;
import org.Mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MyBatisTest {
    public static void main(String[] args) throws  Exception{
        //1.读取配置文件
        InputStream inputStream= Resources.getResourceAsStream("mybatis-config.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory=builder.build(inputStream);
        //3.使用工厂生产SqlSession对象
        SqlSession session=factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
//        UserMapper userMapper=session.getMapper(UserMapper.class);
//        //5.使用代理对象执行方法
//        List<User> users=userMapper.findAll();
//        for (User user:users){
//            System.out.println(user);
//        }
        //6.释放资源
        User user=new User("admireab","男",30);
        session.insert("org.Mybatis.mapper.UserMapper.saveUser",user);
        session.commit();
        session.close();
        inputStream.close();
    }
}
