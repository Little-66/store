package com.cy.store.mapper;

import com.cy.store.pojo.Order;
import com.cy.store.pojo.Orderitem;

public interface OrderMapper {
    /**
     * 插入订单表
     * @param order
     * @return
     */
    Integer insertOrder(Order order);

    /**
     * 插入结算页面订单表
     * @param orderitem
     * @return
     */
    Integer insertItem(Orderitem orderitem);
}
