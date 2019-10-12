package org.Mybatis.domain;

import java.util.List;

public class Order {
    private Integer id;
    private String code;
    private Double total;
    //订单和用户是多对一,一个订单-->一个用户
    private User user;
    //订单和商品是多对多关系
    private List<Article> articles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "id: "+id+"-->code: "+code+"-->total: "+total;
    }
}
