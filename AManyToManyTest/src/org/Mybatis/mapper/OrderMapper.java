package org.Mybatis.mapper;

import org.Mybatis.domain.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

public interface OrderMapper {
    @Select("select * from tb_order where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "code",property = "code"),
            @Result(column = "total",property = "total"),
            @Result(column = "user_id",property = "user",one = @One(select = "org.Mybatis.mapper.UserMapper.selectById",fetchType = FetchType.EAGER)),
            @Result(column = "id",property = "articles",many = @Many(select = "org.Mybatis.mapper.ArticleMapper.selectByOrderId",fetchType = FetchType.LAZY))
    })
    Order selectById(int id);
}
