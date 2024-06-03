package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Gwc;

import java.util.List;

@Mapper
public interface GwcMapper {
    @Insert("insert into gwc(goodsId,goodsname,price,cover_img,totalprice,shop_user,goodsnum,create_user,create_time,update_time) values (#{goodsId},#{goodsName},#{price},#{coverImg},#{totalPrice},#{shopUser},#{goodsNum},#{createUser},now(),now())")
    void add(Gwc gwc);

    @Update("update gwc set totalprice=#{totalPrice},goodsnum=#{goodsNum} where id=#{id}")
    void update(Gwc gwc);

    @Delete("delete from gwc where id=#{id} ;")
    void delete(Integer id);

    @Select("select * from gwc where create_user=#{userId}")
    List<Gwc> get(Integer userId);
}
