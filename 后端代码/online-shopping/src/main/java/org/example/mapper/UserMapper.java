package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.pojo.User;

@Mapper
public interface UserMapper {
    @Select("select *, address from user where username=#{username}")
    public User findName(String username);

    @Insert("insert into user(usertype,username,password,create_time,update_time) values (#{usertype},#{username},#{pd},now(),now())")
    void register(Integer usertype, String username, String pd);

    @Update("update user set nickname=#{nickname},email=#{email},update_time=now() where id=#{id}")
    void update(User user);

    @Update("update user set user_pic=#{avatar},update_time=now() where id=#{id}")
    void updateAvatar(String avatar, Integer id);

    @Update("update user set password=#{pd},update_time=now() where id=#{id}")
    void updatePd(String pd, Integer id);

    @Update("update user set address=#{address}, update_time=now() where id=#{id}")
    void updateAddress(String address, Integer id);

    @Select("select address from user where id=#{id}")
    String getAddress(Integer id);
}
