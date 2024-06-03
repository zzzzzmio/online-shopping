package org.example.service.impl;

import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.example.utils.Md5Util;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService implements org.example.service.UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findName(String username) {
        return userMapper.findName(username);
    }

    @Override
    public void register(Integer usertype, String username, String pd) {
        userMapper.register(usertype,username,pd);
    }

    @Override
    public void update(User user) {
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer id= (Integer) map.get("id");
        user.setId(id);
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatar) {
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer id= (Integer) map.get("id");
        userMapper.updateAvatar(avatar,id);
    }

    @Override
    public void updatePd(String newPwd) {
        String pd= Md5Util.getMD5String(newPwd);
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer id= (Integer) map.get("id");
        userMapper.updatePd(pd,id);
    }

    @Override
    public void updateAddress(String address) {
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer id= (Integer) map.get("id");
        userMapper.updateAddress(address,id);
    }

    @Override
    public String getAddress() {
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer id= (Integer) map.get("id");
        return userMapper.getAddress(id);
    }
}
