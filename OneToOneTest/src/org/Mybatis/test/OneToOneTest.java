package org.Mybatis.test;

import org.Mybatis.domain.Person;
import org.Mybatis.mapper.PersonMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class OneToOneTest {
    public static void main(String[] args) throws IOException {
        //读取mybatis-config.xml配置文件
        InputStream inputStream= Resources.getResourceAsStream("mybatis-config.xml");
        //初始化mybatis,创建SqlSessionFactory类的实例
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        //创建session实例
        SqlSession session=sqlSessionFactory.openSession();
        //获得mapper接口的代理对象
        PersonMapper pm=session.getMapper(PersonMapper.class);
        //直接调用接口的方法,查询id为1的Person数据
        Person p=pm.selectPersonById(1);
        //打印person对象
        System.out.println(p);
        //打印Person关联的Card对象
        System.out.println(p.getCard());
        //提交事务
        session.commit();
        //关闭session
        session.close();
    }
}
