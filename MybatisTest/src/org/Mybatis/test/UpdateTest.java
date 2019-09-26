package org.Mybatis.test;

import org.Mybatis.domain.User;
import org.Mybatis.factory.FKSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

public class UpdateTest {
    public static void main(String[] args) {
        //获取session实例
        SqlSession session= FKSqlSessionFactory.getSqlSession();
        //根据ID查血User对象
        User user=session.selectOne("org.Mybatis.mapper.UserMapper.selectUser",1);
        //修改User的属性值
        user.setName("tom");
        user.setAge(66);
        session.update("org.Mybatis.mapper.UserMapper.modifyUser",user);
        //提交事务
        session.commit();
        //关闭session
        session.close();
    }
}
