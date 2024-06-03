package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.OrdersMapper;
import org.example.pojo.Orders;
import org.example.pojo.PageBean;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrdersService implements org.example.service.OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public void add(Orders orders) {
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId= (Integer) map.get("id");
        orders.setCreateUser(userId);
        ordersMapper.add(orders);
    }

    @Override
    public void update(Orders orders) {
        ordersMapper.update(orders);
    }

    @Override
    public void delete(Integer id) {
        ordersMapper.delete(id);
    }

    @Override
    public void state(Integer id, Integer state) {
        ordersMapper.state(id,state);
    }

    @Override
    public PageBean<Orders> customer(Integer pageNum, Integer pageSize) {
        PageBean<Orders> pb=new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer userId= (Integer) map.get("id");
        List<Orders> list=ordersMapper.customer(userId);
        Page<Orders> p= (Page<Orders>) list;
        pb.setItems(p.getResult());
        pb.setTotal(p.getTotal());
        return pb;
    }

    @Override
    public PageBean<Orders> seller(Integer pageNum, Integer pageSize) {
        PageBean<Orders> pb=new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer userId= (Integer) map.get("id");
        List<Orders> list=ordersMapper.seller(userId);
        Page<Orders> p= (Page<Orders>) list;
        pb.setItems(p.getResult());
        pb.setTotal(p.getTotal());
        return pb;
    }
}
