package com.lzx.linblog.dao;

import com.lzx.linblog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 87248 on 2020-04-18 22:18
 */
public interface UserRepository extends JpaRepository<User,Long> {
    //根据用户和密码查询
    User findByUsernameAndPassword(String username,String password);


}
