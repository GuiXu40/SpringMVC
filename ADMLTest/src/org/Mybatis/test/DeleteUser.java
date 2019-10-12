package org.Mybatis.test;

import org.Mybatis.domain.User;
import org.Mybatis.factory.FKSqlSessionFactory;
import org.Mybatis.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;

public class DeleteUser {
    public static void main(String[] args) {
        SqlSession session= FKSqlSessionFactory.getSqlSession();
        UserMapper um=session.getMapper(UserMapper.class);
        um.removeUser(5);
        session.commit();
        session.close();
    }
}
