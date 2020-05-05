package com.lzx.linblog.service;

import com.lzx.linblog.po.User;

/**
 * Created by 87248 on 2020-04-18 22:16
 */
public interface UserService {
    User checkUser(String username,String password);
}
