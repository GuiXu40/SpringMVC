package org.Mybatis.factory;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

//创建一个工厂类,用于创建SqlSessionFactory对象,在获取SqlSessionduixiang (因为每次测试都需要读取配置文件)
public class FKSqlSessionFactory {
    private static SqlSessionFactory sqlSessionFactory=null;
    //初始化创建SqlSessionFactory对象
    static {
        try {
            //1.读取配置文件
            InputStream inputStream= Resources.getResourceAsStream("mybatis-config.xml");
            //2.创建SqlSessionFactory工厂
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //3.获取SqlSession对象的静态方法
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
    //4.获取SqlSessionFactory的静态方法
    public static SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }
}