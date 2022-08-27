package com.cy.store.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Address extends basePojo {
    private Integer aid;
    private Integer uid;
    private String name;
    private String provinceName;
    private String provinceCode;
    private String cityName;
    private String cityCode;
    private String areaName;
    private String areaCode;
    private String zip;
    private String address;
    private String phone;
    private String tel;
    private String tag;
    private Integer isDefault;

    public Address( Integer uid, String name, String provinceName, String provinceCode, String cityName, String cityCode, String areaName, String areaCode, String zip, String address, String phone, String tel, String tag, Integer isDefault) {

        this.uid = uid;
        this.name = name;
        this.provinceName = provinceName;
        this.provinceCode = provinceCode;
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.areaName = areaName;
        this.areaCode = areaCode;
        this.zip = zip;
        this.address = address;
        this.phone = phone;
        this.tel = tel;
        this.tag = tag;
        this.isDefault = isDefault;
    }

    public Address(String createdUser, Date createdTime, String modifiedUser, Date modifiedTime, Integer aid, Integer uid, String name, String provinceName, String provinceCode, String cityName, String cityCode, String areaName, String areaCode, String zip, String address, String phone, String tel, String tag, Integer isDefault) {
        super(createdUser, createdTime, modifiedUser, modifiedTime);
        this.aid = aid;
        this.uid = uid;
        this.name = name;
        this.provinceName = provinceName;
        this.provinceCode = provinceCode;
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.areaName = areaName;
        this.areaCode = areaCode;
        this.zip = zip;
        this.address = address;
        this.phone = phone;
        this.tel = tel;
        this.tag = tag;
        this.isDefault = isDefault;
    }
}
