package com.it.dao;

import com.it.domain.User;

import java.util.List;


public interface IUserDao {
    /*
     * 根据id 查询用户信息*/
    List<User> findByMobile(String mobile);

    /*
     * 保存操作*/
    void saveUser(User user);

    /*
     * 更新操作*/
    void updateUser(User user);

    /*
     * 删除操作*/
    int  deleteUser(User user);

}
