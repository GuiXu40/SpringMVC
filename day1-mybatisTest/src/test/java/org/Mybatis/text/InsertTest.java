package org.Mybatis.text;

import org.Mybatis.entity.User;
import org.Mybatis.factory.FKSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

public class InsertTest {
    public static void main(String[] args){
        //获取Session实例
        SqlSession session= FKSqlSessionFactory.getSqlSession();
        //创建User对象
        User user=new User("jack","男",78);
        //插入数据
      session.insert("org.Mybatis.mapper.UserMapper.saveUser",user);
        //提交事务
        session.commit();
        //关闭session
        session.close();
    }
}
