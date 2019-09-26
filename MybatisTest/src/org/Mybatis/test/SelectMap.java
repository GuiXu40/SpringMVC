package org.Mybatis.test;

import org.Mybatis.factory.FKSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class SelectMap {
    public static void main(String[] args) {
        //获取session
        SqlSession session= FKSqlSessionFactory.getSqlSession();
        //查询tb_user表所有数据,返回List集合,集合中每个元素都是一个Map
        List<Map<String,Object>> list=session.selectList("org.Mybatis.mapper.UserMapper.selectAllUser");
        //遍历list集合,打印每一个Map对象
        for (Map<String,Object> row:list){
            System.out.println(row);
        }
        //提交事务
        session.commit();
        //关闭session
        session.close();
    }
}
