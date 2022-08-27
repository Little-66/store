package com.cy.store.Controller;

import com.cy.store.Controller.Exception.*;
import com.cy.store.pojo.User;
import com.cy.store.service.Exception.InsertException;
import com.cy.store.service.Exception.UsernameRepeatException;
import com.cy.store.service.UserService;
import com.cy.store.util.JsonResult;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/User")
public class UserController extends BaseController {
    @Resource
    private UserService userService;
    @RequestMapping("/register")
    public JsonResult<Void> insert(User user){
       userService.insert(user);
       return new JsonResult<>(OK);
    }

    @RequestMapping("/login")
     public JsonResult<User> login(String username, String password, HttpSession session){
        User login = userService.login(username, password);
        session.setAttribute("uid",login.getUid());
        session.setAttribute("username",login.getUsername());
        return new JsonResult<>(OK,login);
    }
    @RequestMapping("/change_password")
        public JsonResult<Void> change_password(String oldPassword,String newPassword,HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getNameFromSession(session);
        userService.updatePasswordByUid(uid,username,oldPassword,newPassword);
        return new JsonResult<Void>(OK);
    }
    @RequestMapping("/getByUid")
    public JsonResult getByUid(HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getNameFromSession(session);
        User userById = userService.getById(uid);
        User user = new User();
        user.setUsername(username);
        user.setPhone(userById.getPhone());
        user.setEmail(userById.getEmail());
        user.setGender(userById.getGender());
        return new JsonResult(OK,user);

    }
    @RequestMapping("/change_info")
    public JsonResult<User> change_info(User user,HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getNameFromSession(session);
        User user_importMapper = new User();
        user_importMapper.setPhone(user.getPhone());
        user_importMapper.setEmail(user.getEmail());
        user_importMapper.setGender(user.getGender());
        userService.updateInfoByUid(uid,username,user_importMapper);
        return new JsonResult<>(OK);
    }
//    限制上传文件的大小
    public static final int AVATAR_MAX_SIZE = 10*1024*1024;
//    限制类型
    public static final List<String> AVATAR_TYPE = new ArrayList<String>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }
    //    MultipartFile用于接受所有类型的文件 boot整合的mvc的接口
//    @RequestParam mvc注解 浏览器传过来的数据name和方法参数名称不一致时不能自动注入  映射作用  类似于@PathVariable
    @RequestMapping("/change_avatar")
    public JsonResult<String> change_avatar(HttpSession session,
                                          @RequestParam(value = "file")
                                          MultipartFile file){
        if (file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }else if(file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("文件大小超出范围");
        }else if(!AVATAR_TYPE.contains(file.getContentType())){
            throw new FileTypeException("不支持文件类型");
        }
//        上传的文件路径
        String realPath = session.getServletContext().getRealPath("up");
//        File对象指向这个路径，File是否存在
        File dir = new File(realPath);
        if (!dir.exists()){
            dir.mkdirs();
        }
//        返回的是文件名
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String uuid = UUID.randomUUID().toString().toUpperCase();
        String fileUuidName = suffix+uuid;
        File dest = new File(dir,fileUuidName);
        try {
            file.transferTo(dest);
        } catch (FileStateException e) {
            throw new FileUploadIOException("文件状态异常");
        }catch (IOException c){
            throw new FileUploadIOException("文件读写异常");
        }
        Integer uid = getUidFromSession(session);
        String username = getNameFromSession(session);
        String avatar = "/up/"+fileUuidName;
        userService.updateAvatarByUid(uid,avatar,username);
        return new JsonResult<>(OK,avatar);
    }


}
