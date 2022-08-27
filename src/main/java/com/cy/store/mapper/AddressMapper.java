package com.cy.store.mapper;


import com.cy.store.pojo.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AddressMapper {

    /**
     * //插入收货地址
     * @param address
     * @return
     */
    Integer insert(Address address);

    /**
     * 根据用户id查寻地址总数  加以限制
     * @param uid
     * @return
     */
    Integer countByUid(Integer uid);

    /**
     * 根据uid查找用户的收货地址
     * @param uid
     * @return
     */
    List<Address> findByUid(Integer uid);

    /**
     * 根据查询aid判断地址是否存在
     * @param aid
     * @return
     */
    Address findByAid(Integer aid);

    /**
     * 先把所有都变0
     * @return
     */
    Integer updateDefault_0(Integer uid);

    /**
     * 把用户选中的变为默认
     * @param aid
     * @return
     */
    Integer updateDefaultByAid_1(@Param(value = "aid")
                                    Integer aid,
                                    @Param(value = "modifiedUser")
                                    String modifiedUser,
                                    @Param(value = "modifiedTime")
                                    Date modifiedTime
    );

    /**
     * 根据aid删除地址
     * @param aid
     * @return
     */
        Integer removeAddressByAid(Integer aid);
}
