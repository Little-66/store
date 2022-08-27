package com.cy.store.service;

import com.cy.store.pojo.Cart;
import com.cy.store.pojo.CartVO;
import com.cy.store.pojo.Product;
import org.apache.ibatis.annotations.Param;

import javax.annotation.security.PermitAll;
import java.util.Date;
import java.util.List;

public interface CartService {
    /**
     *
     * @param pid
     * @param uid
     * @param num
     * @param username
     * @return
     */
    void CartInsertProduct( @Param(value = "pid")
                                    Integer pid,
                            @Param(value = "uid")
                                    Integer uid,
                            @Param(value = "num")
                                    Integer num,
                            @Param(value = "username")
                                    String username);

    /**
     *
     * @param uid
     * @return
     */
    List<CartVO> findVOByUid(Integer uid);

    Integer addNumByCid(
            @Param(value = "cid")
            Integer cid,
            @Param(value = "username")
            String username,
            @Param(value = "uid")
            Integer uid);

    Integer reduceNumByCid(
            @Param(value = "cid")
                    Integer cid,
            @Param(value = "username")
                    String username,
            @Param(value = "uid")
                    Integer uid);

    /**
     * 根据cid删除
     * @param cid
     * @return
     */
    Integer deleteByCid(
                         @Param(value = "cid")
                         Integer cid,
                        @Param(value = "uid")
                        Integer uid
                         );

    /**
     *
     * @param cids
     * @return
     */
    List<CartVO> findVOByCids(Integer[] cids,Integer uid);


}
