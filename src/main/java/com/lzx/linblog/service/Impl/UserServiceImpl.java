package com.lzx.linblog.service.Impl;

import com.lzx.linblog.dao.UserRepository;
import com.lzx.linblog.po.User;
import com.lzx.linblog.service.UserService;
import com.lzx.linblog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 87248 on 2020-04-18 22:17
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user=userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }


}
