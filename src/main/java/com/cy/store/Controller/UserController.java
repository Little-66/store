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
//    ???????????????????????????
    public static final int AVATAR_MAX_SIZE = 10*1024*1024;
//    ????????????
    public static final List<String> AVATAR_TYPE = new ArrayList<String>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }
    //    MultipartFile????????????????????????????????? boot?????????mvc?????????
//    @RequestParam mvc?????? ???????????????????????????name???????????????????????????????????????????????????  ????????????  ?????????@PathVariable
    @RequestMapping("/change_avatar")
    public JsonResult<String> change_avatar(HttpSession session,
                                          @RequestParam(value = "file")
                                          MultipartFile file){
        if (file.isEmpty()){
            throw new FileEmptyException("????????????");
        }else if(file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("????????????????????????");
        }else if(!AVATAR_TYPE.contains(file.getContentType())){
            throw new FileTypeException("?????????????????????");
        }
//        ?????????????????????
        String realPath = session.getServletContext().getRealPath("up");
//        File???????????????????????????File????????????
        File dir = new File(realPath);
        if (!dir.exists()){
            dir.mkdirs();
        }
//        ?????????????????????
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String uuid = UUID.randomUUID().toString().toUpperCase();
        String fileUuidName = suffix+uuid;
        File dest = new File(dir,fileUuidName);
        try {
            file.transferTo(dest);
        } catch (FileStateException e) {
            throw new FileUploadIOException("??????????????????");
        }catch (IOException c){
            throw new FileUploadIOException("??????????????????");
        }
        Integer uid = getUidFromSession(session);
        String username = getNameFromSession(session);
        String avatar = "/up/"+fileUuidName;
        userService.updateAvatarByUid(uid,avatar,username);
        return new JsonResult<>(OK,avatar);
    }


}
