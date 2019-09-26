package org.Mybatis.test;

import org.Mybatis.domain.User;
import org.Mybatis.factory.FKSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

public class SelectTest {
    public static void main(String[] args) {
        //获取Session实例
        SqlSession session= FKSqlSessionFactory.getSqlSession();
        //根据id查询User对象
        User user=session.selectOne("org.Mybatis.mapper.UserMapper.selectUser",1);
        System.out.println(user);
        //提交事务
        session.commit();
        session.close();
    }

}
