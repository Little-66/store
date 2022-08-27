package com.cy.store.service.Impl;

import com.cy.store.mapper.UserMapper;
import com.cy.store.pojo.User;
import com.cy.store.service.Exception.*;
import com.cy.store.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.security.auth.login.CredentialException;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Resource
  private UserMapper userMapper;
    @Override
    public void insert(User user) {
//        先判断是否重复用户名
        String username = user.getUsername();
        String password = user.getPassword();
        User result = userMapper.findByUsername(username);
        if(result==null){
            Date date = new Date();
//            md5加密密码
            String salt = UUID.randomUUID().toString().toUpperCase();
            user.setSalt(salt);
            user.setPassword(MD5(password,salt));
            user.setIsDelete(0);
//           创建时间
            user.setCreatedTime(date);
//            创建人
            user.setCreatedUser(user.getUsername());
//            最后修改执行人'
            user.setModifiedUser(user.getUsername());
//            最后修修改时间
            user.setModifiedTime(date);
            Integer insertresult = userMapper.insert(user);

            if(insertresult==null){
                throw new InsertException("系统错误未知异常");
            }
        }else if (result!=null){
//            抛出异常
            throw new UsernameRepeatException("用户名已存在");
        }

    }

    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUsername(username);
        if(result==null){
            throw new UsernameNotFoundException("用户名不存在");
        }else if(result!=null||result.getIsDelete()==1){
//            查询结果的密码
            String resultPassword = result.getPassword();
//            查询结果的盐值
            String resultSalt = result.getSalt();
//            输入的密码和查询结果的盐值进行加密处理
            String resultMD5password = MD5(password, resultSalt);

            if(!resultPassword.equals(resultMD5password)){
                throw new PasswordErrorException("密码错误");
            }else if(result.getIsDelete()==1){
                throw new UsernameNotFoundException("用户名不存在");
            }
        }
//        减小数据量的提交提升数据传输效率
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    @Override
    public Integer updatePasswordByUid(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw  new UsernameNotFoundException("用户不存在");
        }
        String oldMd5Password = MD5(oldPassword, result.getSalt());
        if(!oldMd5Password.equals(result.getPassword())){
            throw new PasswordErrorException("密码错误");
        }
        String newMd5Password = MD5(newPassword, result.getSalt());
        Integer integer = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        if(integer==null){
            throw new UpdateException("未知更新错误");
        }
        return integer;
    }

    @Override
    public User getById(Integer uid) {
        User result = userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UsernameNotFoundException("用户不存在");
        }
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    @Override
    public Integer updateInfoByUid(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
            if(result==null||result.getIsDelete()==1){
                throw new UsernameNotFoundException("用户不存在");
            }
            user.setUid(uid);
//            user.setUsername(username);
            user.setModifiedTime(new Date());
            user.setModifiedUser(username);
        Integer integer = userMapper.updateInfoByUid(user);
        if(integer!=1){
            throw new UpdateException("更新失败未知错误");
        }
        return integer;
    }

    @Override
    public Integer updateAvatarByUid(Integer uid, String avatar, String username) {
        User result = userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UsernameNotFoundException("用户名不存在");
        }
        Integer integer = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if(integer!=1){
            throw new UpdateException("更新时异常");
        }
        return null;
    }


    //    密码加密方法
    public String MD5(String password,String salt){
//        三次加密
        for (int i = 0; i <3 ; i++) {
            password =DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return  password;

    }

}
