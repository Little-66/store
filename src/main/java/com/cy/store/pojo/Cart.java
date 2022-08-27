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
public class Cart extends basePojo{
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;

    public Cart(Integer uid, Integer pid, Long price, Integer num) {
        this.uid = uid;
        this.pid = pid;
        this.price = price;
        this.num = num;
    }

    public Cart(String createdUser, Date createdTime, String modifiedUser, Date modifiedTime, Integer uid, Integer pid, Long price, Integer num) {
        super(createdUser, createdTime, modifiedUser, modifiedTime);
        this.uid = uid;
        this.pid = pid;
        this.price = price;
        this.num = num;
    }
}
