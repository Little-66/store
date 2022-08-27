package com.cy.store.service;

import com.cy.store.pojo.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressService {
    /**
     * 插入地址
     * @param address
     * @return
     */
    Integer insert(Integer uid,String username,Address address);

    /**
     * 通过uid查找用户的地址
     * @param uid
     * @return
     */
    List<Address> findByUid(Integer uid);

    /**
     *
     * @param aid
     * @param uid
     * @param username
     * @return
     */
    void updateDefault(Integer aid,Integer uid,String username);

    /**
     * 根据aid删除地址
     * @param aid
     * @return
     */
    Integer removeAddressByAid(Integer aid);

    /**
     * 根据aid查询地址信息
     * @param aid
     * @param uid
     * @return
     */
    Address findByAid(
            @Param(value = "aid")
                    Integer aid,
            @Param(value = "uid")
                    Integer uid);

}
