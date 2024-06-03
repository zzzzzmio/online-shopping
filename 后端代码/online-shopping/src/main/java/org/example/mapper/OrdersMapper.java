package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface OrdersMapper {
    @Insert("insert into orders(goodsId,goodsname,totalprice,shop_user,goodsnum,state,create_user,create_time,update_time) values (#{goodsId},#{goodsName},#{totalPrice},#{shopUser},#{goodsNum},#{state},#{createUser},now(),now())")
    void add(Orders orders);

    @Update("update orders set totalprice=#{totalPrice},goodsnum=#{goodsNum},update_time=now() where id=#{id}")
    void update(Orders orders);

    @Delete("delete from orders where id=#{id}")
    void delete(Integer id);

    @Update("update orders set state=#{state} where id=#{id}")
    void state(Integer id, Integer state);

    @Select("select * from orders where create_user=#{userId}")
    List<Orders> customer(Integer userId);

    @Select("select * from orders where shop_user=#{userId}")
    List<Orders> seller(Integer userId);
}
