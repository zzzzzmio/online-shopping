package org.example.service;

import org.example.pojo.Orders;
import org.example.pojo.PageBean;

public interface OrdersService {
    void add(Orders orders);

    void update(Orders orders);

    void delete(Integer id);

    void state(Integer id, Integer state);

    PageBean<Orders> customer(Integer pageNum, Integer pageSize);

    PageBean<Orders> seller(Integer pageNum, Integer pageSize);
}
