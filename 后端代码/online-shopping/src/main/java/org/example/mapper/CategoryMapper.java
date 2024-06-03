package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("select * from category where category_name=#{categoryName}")
    Category findCategory(String categoryName);

    @Insert("insert into category(category_name,create_user,create_time,update_time) values (#{categoryName},#{createUser},now(),now())")
    void add(Category c);

    @Delete("delete from category where id=#{id}")
    void delete(Integer id);

    @Update("update category set category_name=#{categoryName}, update_time=now() where id=#{id}")
    void update(String categoryName, Integer id);

    @Select("select * from category")
    List<Category> get();

    @Select("select * from category where id=#{id}")
    Category getName(Integer id);

    @Select("select * from category")
    List<Category> getList();
}
