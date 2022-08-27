package com.cy.store.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Order extends basePojo{
    private Integer oid;
    private Integer uid;
    private String recvName;
    private String recvPhone;
    private String recvProvince;
    private String recvCity;
    private String recvArea;
    private String recvAddress;
    private Long totalPrice;
    private Integer status;
    private Date orderTime;
    private Date payTime;

    public Order(Integer uid, String recvName, String recvPhone, String recvProvince, String recvCity, String recvArea, String recvAddress, Long totalPrice, Integer status, Date orderTime, Date payTime) {
        this.uid = uid;
        this.recvName = recvName;
        this.recvPhone = recvPhone;
        this.recvProvince = recvProvince;
        this.recvCity = recvCity;
        this.recvArea = recvArea;
        this.recvAddress = recvAddress;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderTime = orderTime;
        this.payTime = payTime;
    }

    public Order(String createdUser, Date createdTime, String modifiedUser, Date modifiedTime, Integer uid, String recvName, String recvPhone, String recvProvince, String recvCity, String recvArea, String recvAddress, Long totalPrice, Integer status, Date orderTime, Date payTime) {
        super(createdUser, createdTime, modifiedUser, modifiedTime);
        this.uid = uid;
        this.recvName = recvName;
        this.recvPhone = recvPhone;
        this.recvProvince = recvProvince;
        this.recvCity = recvCity;
        this.recvArea = recvArea;
        this.recvAddress = recvAddress;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderTime = orderTime;
        this.payTime = payTime;
    }
}
