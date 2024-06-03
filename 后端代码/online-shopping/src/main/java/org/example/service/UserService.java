package org.example.service;

import org.example.pojo.User;

public interface UserService {
    User findName(String username);

    void register(Integer usertype, String username, String pd);

    void update(User user);

    void updateAvatar(String avatar);

    void updatePd(String newPwd);

    void updateAddress(String address);

    String getAddress();
}
