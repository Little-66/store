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
public class Orderitem extends basePojo{
    private Integer id;
    private Integer oid;
    private Integer pid;
    private String title;
    private String image;
    private Long price;
    private Integer num;

    public Orderitem(Integer oid, Integer pid, String title, String image, Long price, Integer num) {
        this.oid = oid;
        this.pid = pid;
        this.title = title;
        this.image = image;
        this.price = price;
        this.num = num;
    }

    public Orderitem(String createdUser, Date createdTime, String modifiedUser, Date modifiedTime, Integer oid, Integer pid, String title, String image, Long price, Integer num) {
        super(createdUser, createdTime, modifiedUser, modifiedTime);
        this.oid = oid;
        this.pid = pid;
        this.title = title;
        this.image = image;
        this.price = price;
        this.num = num;
    }
}
