package org.Mybatis.test;

import org.Mybatis.domain.Article;
import org.Mybatis.domain.Order;
import org.Mybatis.domain.User;
import org.Mybatis.mapper.OrderMapper;
import org.Mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ManyToManyTest {
    public static void main(String[] args) throws IOException {
        //读取配置文件
        InputStream inputStream= Resources.getResourceAsStream("mybatis-config.xml");
        //初始化mybatis,创建SqlSessionFactory类的实例
        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
        //创建session实例
        SqlSession session=sqlSessionFactory.openSession();
        ManyToManyTest t=new ManyToManyTest();
        t.testSelectUserById(session);
        t.testSelectOrderById(session);
        session.commit();
        session.close();
    }
    //测试一对多关系,查询User(一)-->Order(多)
    public void testSelectUserById(SqlSession session){
        //获得UserMapper代理对象
        UserMapper um=session.getMapper(UserMapper.class);
        //调用selectUserById方法
        User user=um.selectUserById(1);
        //查看查询到的user对象信息
        System.out.println(user.getId()+" "+user.getUsername());
        //查看user对象关联的订单信息
        List<Order> orders=user.getOrders();
        for (Order order: orders){
            System.out.println(order);
        }
    }
    //测试多对多关系,查询Order(多)-->Article(多)
    public void testSelectOrderById(SqlSession session){
        //获取OrderMapper对象
        OrderMapper om=session.getMapper(OrderMapper.class);
        //调用方法
        Order order =om.selectOrderById(2);
        //查看查询到的order对象信息
        System.out.println(order.getId()+" "+order.getCode()+" "+order.getTotal());
        //查看order对象关联的用户信息
        User user=order.getUser();
        System.out.println(user);
        //查看Order 对象关联的商品信息
        List<Article> articles=order.getArticles();
        for (Article article:articles){
            System.out.println(article);
        }
    }
}
