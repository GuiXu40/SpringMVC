package org.Mybatis.test;

import org.Mybatis.domain.User;
import org.Mybatis.factory.FKSqlSessionFactory;
import org.Mybatis.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;

public class InsertTest {
    public static void main(String[] args) {
        SqlSession session= FKSqlSessionFactory.getSqlSession();
        UserMapper um=session.getMapper(UserMapper.class);
        User user=new User();
        user.setName("test");
        user.setSex("男");
        user.setAge(55);
        um.saveUser(user);
        //查看生成的主键
        System.out.println(user.getId());
        session.commit();
        session.close();
    }
}
