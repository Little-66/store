package com.cy.store.service;

import com.cy.store.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Date;

public interface UserService {

    /**
     * 注册插入
     * @param user
     */
    void insert(User user);

    /**
     *
     * @param username
     * @param password
     * @return
     */
    User login(String username,String password);

    /**
     *
     * @param uid
     * @param username
     * @param oldPassword
     * @param newPassword
     * @return
     */
    Integer updatePasswordByUid(
                                Integer uid,
                                String username,
                                String oldPassword,
                                String newPassword);

    /**
     * 修改个人资料
     * @param uid
     * @return
     */
    User getById(Integer uid);

    /**
     *
     * @param uid
     * @param username
     * @param user
     * @return
     */
    Integer updateInfoByUid(Integer uid,String username,User user);

    /**
     * 修改头像
     * @param uid
     * @param avatar
     * @param username
     * @return
     */
    Integer updateAvatarByUid(Integer uid,String avatar,String username);

}
