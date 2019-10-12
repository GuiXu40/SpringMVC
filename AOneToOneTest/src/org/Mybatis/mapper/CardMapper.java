package org.Mybatis.mapper;
import org.Mybatis.domain.Card;
import org.apache.ibatis.annotations.Select;
public interface CardMapper {
    @Select("select * from tb_card where id=#{id}")
    Card selectCardById(Integer id);
}
