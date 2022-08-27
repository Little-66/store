package com.cy.store.Controller;

import com.cy.store.pojo.CartVO;
import com.cy.store.service.CartService;
import com.cy.store.service.Exception.DeleteException;
import com.cy.store.util.JsonResult;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/Cart")
public class CartController extends BaseController {
    @Resource
    CartService cartService;
    @RequestMapping("/CartInsertProduct")
    public JsonResult<Void> CartInsertProduct(Integer pid,Integer num,HttpSession session){

        cartService.CartInsertProduct(pid,getUidFromSession(session),num,getNameFromSession(session));
            return new JsonResult<>(OK);
    }

    @RequestMapping("/exhibit")
    public JsonResult<List<CartVO>> exhibitByUid(HttpSession session){
        List<CartVO> cartVOS = cartService.findVOByUid(getUidFromSession(session));
    return new JsonResult<>(OK,cartVOS);
    }

    @RequestMapping("/addNum")
    public JsonResult<Integer> addNum(Integer cid,HttpSession session){
        Integer num = cartService.addNumByCid(cid, getNameFromSession(session), getUidFromSession(session));
        return new JsonResult<Integer>(OK,num);
    }

    @RequestMapping("/reduceNum")
    public JsonResult<Integer> reduceNum(Integer cid,HttpSession session){
        Integer num = cartService.reduceNumByCid(cid, getNameFromSession(session), getUidFromSession(session));
        return new JsonResult<Integer>(OK,num);
    }
    @RequestMapping("/deleteByCid")
    public JsonResult<Integer> deleteByCid(Integer cid,HttpSession session){
        cartService.deleteByCid(cid, getUidFromSession(session));
        return new JsonResult<>(OK);

    }

    @RequestMapping("/findByCids")
    public JsonResult<List<CartVO>> findByCids(Integer[] cids,HttpSession session){
        List<CartVO> voByCids = cartService.findVOByCids(cids,getUidFromSession(session));
        return new JsonResult<List<CartVO>>(OK,voByCids);
    }

    @RequestMapping("/deleteByCids")
    public JsonResult<Void>deleteByCids(Integer[] cids, HttpSession session){
        Integer integer = 0;
        for(Integer c:cids){
             integer = cartService.deleteByCid(c, getUidFromSession(session));
        }
        if (integer!=1){
            throw new DeleteException("删除时错误请重试");
        }
        return new JsonResult<>(OK);
    }



}
