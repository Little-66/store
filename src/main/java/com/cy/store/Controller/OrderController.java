package com.cy.store.Controller;

import com.cy.store.pojo.Address;
import com.cy.store.pojo.Order;
import com.cy.store.service.AddressService;
import com.cy.store.service.OrderService;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/Order")
public class OrderController extends BaseController{
    @Resource
    private AddressService addressService;
   @Resource
    private OrderService orderService;

   @RequestMapping("/findAddressByUid")
   public JsonResult<List<Address>> findAddressByUid(HttpSession session){
       List<Address> addresses = addressService.findByUid(getUidFromSession(session));
       return new JsonResult<List<Address>>(OK,addresses);
   }
   @RequestMapping("/insertOrder")
    public JsonResult<Order> insertOrder(Integer aid,Integer[] cids,HttpSession session){
       Order order = orderService.insertOrder(aid, getUidFromSession(session), getNameFromSession(session), cids);
       return new JsonResult<Order>(OK,order);
   }
}
