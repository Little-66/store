package com.cy.store.mapper;

import com.cy.store.pojo.Cart;
import com.cy.store.pojo.CartVO;
import org.apache.ibatis.annotations.Param;

import javax.annotation.security.PermitAll;
import java.util.Date;
import java.util.List;

public interface CartMapper {
    /**
     * 给购物车插入商品
     * @param cart
     * @return
     */
    Integer insert(Cart cart);


    /**
     *
     * @param uid
     * @param pid
     * @return
     */
    Cart selectByPidAndUid(
            @Param(value = "uid")
            Integer uid,
            @Param(value = "pid")
            Integer pid);


    /**
     *
     * @param cid
     * @param num
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateByCid(
                        @Param(value = "cid")
                        Integer cid,
                        @Param(value = "num")
                        Integer num,
                        @Param(value = "modifiedUser")
                        String modifiedUser,
                        @Param(value = "modifiedTime")
                        Date modifiedTime);

    /**
     * 查询某用户的购物车数据
     * @param uid 用户id
     * @return 该用户的购物车数据的列表
     */
    List<CartVO> findVOByUid(Integer uid);

    /**
     * 根据购物车数据id查询购物车数据详情
     * @param cid 购物车数据id
     * @return 匹配的购物车数据详情，如果没有匹配的数据则返回null
     */
    Cart findByCid(Integer cid);

    /**
     * 根据若干个购物车数据id查询详情的列表
     * @param cids 若干个购物车数据id
     * @return 匹配的购物车数据详情的列表
     */
    List<CartVO> findVOByCids(Integer[] cids);

    /**
     *
     * @param cid
     * @return
     */
    Integer deleteByCid(Integer cid);

}
