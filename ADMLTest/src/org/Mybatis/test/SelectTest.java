package org.Mybatis.test;

import org.Mybatis.domain.User;
import org.Mybatis.factory.FKSqlSessionFactory;
import org.Mybatis.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;

public class SelectTest {
    public static void main(String[] args) {
        SqlSession session= FKSqlSessionFactory.getSqlSession();
        UserMapper um=session.getMapper(UserMapper.class);
        //根据id查询User对象
        User user=um.selectUserById(1);
        System.out.println(user);
        session.commit();
        session.close();
    }
}
