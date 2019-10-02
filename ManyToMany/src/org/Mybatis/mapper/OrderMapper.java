package org.Mybatis.mapper;

import org.Mybatis.domain.Order;

public interface OrderMapper {
    Order selectOrderById(int id);
}
