package com.cy.store.Controller;

import com.cy.store.Controller.Exception.*;
import com.cy.store.service.Exception.*;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;
import java.rmi.server.ServerCloneException;

public class BaseController {
    public final static int OK=200;
//    自动拦截异常   根据异常类
@ExceptionHandler({ServiceException.class,FileUploadException.class})
public JsonResult<Void> exceptionHandler(Throwable e){
    JsonResult<Void> jsonResult = new JsonResult<>(e);
    if(e instanceof UsernameRepeatException){
        jsonResult.setStat(4001);
        jsonResult.setMessage("用户名已存在");
    }else if(e instanceof InsertException){
        jsonResult.setStat(5001);
        jsonResult.setMessage("未知错误");
    }else if(e instanceof UsernameNotFoundException){
        jsonResult.setStat(4002);
        jsonResult.setMessage("用户名找不到");
    }else if(e instanceof PasswordErrorException){
        jsonResult.setStat(4003);
        jsonResult.setMessage("密码错误");
    }else if(e instanceof UpdateException){
        jsonResult.setStat(5002);
        jsonResult.setMessage("更新错误未知异常");
    }else if (e instanceof FileEmptyException) {
        jsonResult.setStat(6000);
        jsonResult.setMessage("文件为空");
    } else if (e instanceof FileSizeException) {
        jsonResult.setStat(6001);
        jsonResult.setMessage("文件过大");
    } else if (e instanceof FileTypeException) {
        jsonResult.setStat(6002);
        jsonResult.setMessage("文件类型错误");
    } else if (e instanceof FileStateException) {
        jsonResult.setStat(6003);
        jsonResult.setMessage("文件状态异常");
    } else if (e instanceof FileUploadIOException) {
        jsonResult.setStat(6004);
        jsonResult.setMessage("文件传输异常");
    }else if(e instanceof AddressCountLimitException){
        jsonResult.setStat(7000);
        jsonResult.setMessage("您的收货地址过多");
    }else if(e instanceof AccessDeniedException){
        jsonResult.setStat(8000);
        jsonResult.setMessage("用户非法访问");
    }else if(e instanceof AddressNotExistException){
        jsonResult.setStat(8001);
        jsonResult.setMessage("收货地址数据不存在");
    }else if(e instanceof RemoveException){
        jsonResult.setStat(9000);
        jsonResult.setMessage("删除时发生位置错误");
    }else if(e instanceof ProductNotFoundException){
        jsonResult.setStat(1000);
        jsonResult.setMessage("商品不存在");
    }else if(e instanceof DeleteException){
        jsonResult.setStat(2000);
        jsonResult.setMessage("删除时失败");
    }
    return jsonResult;
}
protected final Integer getUidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
}
protected final String getNameFromSession(HttpSession session){
    return session.getAttribute("username").toString();
}
}
