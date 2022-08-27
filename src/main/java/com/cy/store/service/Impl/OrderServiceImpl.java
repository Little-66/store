package com.cy.store.service.Impl;

import com.cy.store.mapper.AddressMapper;
import com.cy.store.mapper.OrderMapper;
import com.cy.store.pojo.Address;
import com.cy.store.pojo.CartVO;
import com.cy.store.pojo.Order;
import com.cy.store.pojo.Orderitem;
import com.cy.store.service.AddressService;
import com.cy.store.service.CartService;
import com.cy.store.service.Exception.InsertException;
import com.cy.store.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    AddressService addressService;
    @Resource
    OrderMapper orderMapper;
    @Resource
    CartService cartService;
    @Override
    public Order insertOrder(Integer aid, Integer uid, String username, Integer[] cids) {
        Date nowDate = new Date();
        List<CartVO> cartVOList = cartService.findVOByCids(cids,uid);
        Long allPrice  = 0l;
        for (CartVO p:cartVOList){
            allPrice+=p.getRealPrice()*p.getNum();
        }
        Address address = addressService.findByAid(aid, uid);
        Order order = new Order();
        order.setUid(uid);
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        // 补全数据：totalPrice
        order.setTotalPrice(allPrice);
        // 补全数据：status
        order.setStatus(0);
        // 补全数据：下单时间
        order.setOrderTime(nowDate);
        // 补全数据：日志
        order.setCreatedUser(username);
        order.setCreatedTime(nowDate);
        order.setModifiedUser(username);
        order.setModifiedTime(nowDate);
        Integer integer = orderMapper.insertOrder(order);
        for (CartVO p:cartVOList){
            Orderitem orderitem = new Orderitem();
            orderitem.setOid(order.getOid());
            orderitem.setPid(p.getPid());
            orderitem.setTitle(p.getTitle());
            orderitem.setImage(p.getImage());
            orderitem.setNum(p.getNum());
            orderitem.setCreatedTime(order.getCreatedTime());
            orderitem.setCreatedUser(order.getCreatedUser());
            orderitem.setModifiedTime(order.getModifiedTime());
            orderitem.setModifiedUser(order.getModifiedUser());
            Integer integer1 = orderMapper.insertItem(orderitem);
            if (integer1!=1){
                throw new InsertException("创建订单异常");
            }
        }

        if (integer!=1){
            throw new InsertException("创建订单异常");
        }
        return order;
    }


}
