package org.Mybatis.test;
import org.Mybatis.domain.Person;
import org.Mybatis.factory.FKSqlSessionFactory;
import org.Mybatis.mapper.PersonMapper;
import org.apache.ibatis.session.SqlSession;
public class OneToOneTest {
    public static void main(String[] args) {
        SqlSession session= FKSqlSessionFactory.getSqlSession();
        PersonMapper pm=session.getMapper(PersonMapper.class);
        Person p= (Person) pm.selectPersonById(1);
        System.out.println(p);
        System.out.println(p.getCard());
        session.commit();
        session.close();
    }
}
