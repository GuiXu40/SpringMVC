package org.Mybatis.test;

import org.Mybatis.domain.Order;
import org.Mybatis.factory.FKSqlSessionFactory;
import org.Mybatis.mapper.OrderMapper;
import org.apache.ibatis.session.SqlSession;

public class ManyToManyTest {
    public static void main(String[] args) {
        SqlSession session= FKSqlSessionFactory.getSqlSession();
        OrderMapper om=session.getMapper(OrderMapper.class);
        Order order =om.selectById(1);
        System.out.println(order);
        System.out.println(order.getUser());
        order.getArticles().forEach(article -> System.out.println(article));
    }
}
