package org.Mybatis.test;

import org.Mybatis.domain.Clazz;
import org.Mybatis.factory.FKSqlSessionFactory;
import org.Mybatis.mapper.ClazzMapper;
import org.apache.ibatis.session.SqlSession;

public class OneToManyTest {
    public static void main(String[] args) {
        SqlSession session= FKSqlSessionFactory.getSqlSession();
        ClazzMapper cm=session.getMapper(ClazzMapper.class);
        Clazz clazz=cm.selectById(1);
        System.out.println(clazz);
        clazz.getStudents().forEach(student -> System.out.println(student));
        session.commit();
        session.close();
    }
}
