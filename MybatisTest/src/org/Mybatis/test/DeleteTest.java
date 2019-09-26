package org.Mybatis.test;

import org.Mybatis.factory.FKSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

public class DeleteTest {
    public static void main(String[] args) {
        //获取session实例
        SqlSession session= FKSqlSessionFactory.getSqlSession();
        session.delete("org.Mybatis.mapper.UserMapper.removeUser",1);
        //提交事务
        session.commit();
        //关闭
        session.close();
    }
}
