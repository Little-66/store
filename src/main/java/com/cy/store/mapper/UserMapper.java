package com.cy.store.mapper;

import com.cy.store.pojo.User;
import com.cy.store.service.Exception.InsertException;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

//@Mapper
public interface UserMapper {
    /**
     * 注册插入用户数据
     * @param user
     * @return
     */
    Integer insert(User user);

    /**
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 通过uid查用户
     * @param uid
     * @return
     */
    User findByUid(Integer uid);

    /**
     * 修改密码
     * @param uid
     * @param newPassword
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updatePasswordByUid(@Param(value = "uid")
                                Integer uid,
                                @Param(value = "newPassword")
                                String newPassword,
                                @Param(value = "modifiedUser")
                                String modifiedUser,
                                @Param(value = "modifiedTime")
                                Date modifiedTime);

    /**
     * 修改个人资料
     * @param user
     * @return
     */
    Integer updateInfoByUid(User user);


    /**
     * 上传头像
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateAvatarByUid(@Param(value = "uid")
                              Integer uid,
                              @Param(value = "avatar")
                              String avatar,
                              @Param(value = "modifiedUser")
                              String modifiedUser,
                              @Param(value = "modifiedTime")
                              Date modifiedTime);


}
