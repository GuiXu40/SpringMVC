package org.Mybatis.mapper;

import org.Mybatis.domain.Article;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleMapper {
    @Select("select * from tb_article where id in(select article_id  from tb_item where order_id=#{id})")
    List<Article> selectByOrderId(Integer order_id);
}
