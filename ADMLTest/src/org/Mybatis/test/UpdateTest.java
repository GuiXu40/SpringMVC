package org.Mybatis.test;

import org.Mybatis.domain.User;
import org.Mybatis.factory.FKSqlSessionFactory;
import org.Mybatis.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;

public class UpdateTest {
    public static void main(String[] args) {
        SqlSession session= FKSqlSessionFactory.getSqlSession();
        UserMapper um=session.getMapper(UserMapper.class);
        User user=um.selectUserById(5);
        user.setName("update");
        user.setSex("女");
        user.setAge(45);
        um.modifyUser(user);
        session.commit();
        session.close();
    }
}
