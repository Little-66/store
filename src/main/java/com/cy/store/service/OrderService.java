package com.cy.store.service;

import com.cy.store.pojo.Address;
import com.cy.store.pojo.Order;
import com.cy.store.pojo.Orderitem;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Param;

public interface OrderService {
    /**
     *
     * @param aid
     * @param uid
     * @param username
     * @param cids
     * @return
     */
    Order insertOrder(
            @Param(value = "aid")
            Integer aid,
            @Param(value = "uid")
            Integer uid,
            @Param(value = "username")
            String username,
            @Param(value = "cids")
            Integer[] cids);

    /**
     *
     * @param orderitem
     * @return
     */



}
