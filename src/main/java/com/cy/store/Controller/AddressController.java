package com.cy.store.Controller;

import com.cy.store.pojo.Address;
import com.cy.store.service.AddressService;
import com.cy.store.util.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/Address")
public class AddressController extends BaseController{
    @Resource
   private AddressService addressService;

    @RequestMapping("/insertAddress")
    public JsonResult<Void> insertAddress(HttpSession session, Address address){
        Integer uid = getUidFromSession(session);
        String username = getNameFromSession(session);
        addressService.insert(uid,username,address);
        return new JsonResult<>(OK);
    }
    @RequestMapping("/findAddressByUid")
    public JsonResult<List<Address>> findAddressByUid(HttpSession session){
        Integer uid = getUidFromSession(session);
        List<Address> addressByUid = addressService.findByUid(uid);
        return new JsonResult<>(OK,addressByUid);
    }
    @RequestMapping("/updateDefault/{aid}")
    public JsonResult updateDefault(
            @PathVariable("aid")
            Integer aid,HttpSession session){
//        Integer integerAid = Integer.valueOf(aid);
        Integer uid = getUidFromSession(session);
        String username = getNameFromSession(session);
        addressService.updateDefault(aid,uid,username);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/RemoveAddress/{aid}")
    public JsonResult<Void> RemoveAddress(
            @PathVariable("aid")
            Integer aid){
        Integer integer = addressService.removeAddressByAid(aid);
        return new JsonResult<>(OK);
    }


}
