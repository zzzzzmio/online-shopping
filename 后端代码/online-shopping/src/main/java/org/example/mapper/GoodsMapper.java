package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Goods;

import java.util.List;

@Mapper
public interface GoodsMapper {
    @Select("select * from goods where goodsname=#{goodsName}")
    Goods findGoods(String goodsName);

    @Insert("insert into goods(goodsname,content,cover_img,category_id,price,create_user,create_time,update_time) values (#{goodsName},#{content},#{coverImg},#{categoryId},#{price},#{createUser},now(),now())")
    void add(Goods g);

    @Select("select * from goods where id!=#{id} and goodsname=#{goodsName}")
    Goods findOther(Integer id, String goodsName);

    @Update("update goods set goodsname=#{goodsName},content=#{content},cover_img=#{coverImg},price=#{price},update_time=now() where id=#{id}")
    void update(Goods g);

    @Select("select * from goods where id=#{id}")
    Goods get(Integer id);

    @Delete("delete from goods where id=#{id}")
    void delete(Integer id);

    List<Goods> getList(Integer categoryId);

//    @Select("select * from goods where create_user=#{userId}")
    List<Goods> seller(Integer userId,Integer categoryId);
}
