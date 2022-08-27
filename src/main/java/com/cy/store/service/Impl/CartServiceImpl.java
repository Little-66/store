package com.cy.store.service.Impl;

import com.cy.store.mapper.CartMapper;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.pojo.Cart;
import com.cy.store.pojo.CartVO;
import com.cy.store.service.CartService;
import com.cy.store.service.Exception.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Resource
   private CartMapper cartMapper;
    @Resource
    private ProductMapper productMapper;
    @Override
    public void CartInsertProduct(Integer pid, Integer uid, Integer num, String username) {
        Cart cart = cartMapper.selectByPidAndUid(uid, pid);
        if(cart==null){
            Long priceByPid = productMapper.findPriceByPid(pid);

            Cart insertCart = new Cart(uid,pid,priceByPid,num);
            insertCart.setCreatedTime(new Date());
            insertCart.setCreatedUser(username);
            insertCart.setModifiedTime(new Date());
            insertCart.setModifiedUser(username);
            Integer insert = cartMapper.insert(insertCart);
            if (insert!=1){
                throw new InsertException("插入时异常");
            }

        }else if(cart!=null){
            Integer num1 = cart.getNum();
            num+=num1;
            Integer integer = cartMapper.updateByCid(cart.getCid(), num, username, new Date());
            if (integer!=1){
                throw new UpdateException("更新时异常");
            }
        }
    }

    @Override
    public List<CartVO> findVOByUid(Integer uid) {
        List<CartVO> cartvoByUid = cartMapper.findVOByUid(uid);
        return cartvoByUid;
    }

    @Override
    public Integer addNumByCid(Integer cid,String username,Integer uid) {
        Cart cartByCid = cartMapper.findByCid(cid);
        if(cartByCid==null){
            throw new ProductNotFoundException("商品找不到");
        }if (cartByCid.getUid()!=uid){
            throw new AccessDeniedException("数据非法访问");
        }
        Integer num = cartByCid.getNum();
        num++;
        Integer integer = cartMapper.updateByCid(cid, num, username, new Date());
        if(integer!=1){
            throw new UpdateException("更新失败");
        }
        return num;
    }

    @Override
    public Integer reduceNumByCid(Integer cid, String username, Integer uid) {
        Cart cartByCid = cartMapper.findByCid(cid);
        if(cartByCid==null){
            throw new ProductNotFoundException("商品找不到");
        }if (cartByCid.getUid()!=uid){
            throw new AccessDeniedException("数据非法访问");
        }
        Integer num = cartByCid.getNum();
        num--;
        Integer integer = cartMapper.updateByCid(cid, num, username, new Date());
        if(integer!=1){
            throw new UpdateException("更新失败");
        }
        return num;
    }

    @Override
    public Integer deleteByCid(Integer cid,Integer uid) {
        Cart result = cartMapper.findByCid(cid);
        if(result.getUid()!=uid){
            throw new AccessDeniedException("非法访问");
        }
        Integer integer = cartMapper.deleteByCid(cid);
        if (integer !=1){
            throw new DeleteException("删除时失败");
        }
        return integer;
    }

    @Override
    public List<CartVO> findVOByCids(Integer[] cids, Integer uid) {
        List<CartVO> cartVOList = cartMapper.findVOByCids(cids);
        for (CartVO product:cartVOList){
            if (product.getUid()!=uid){
                cartVOList.remove(product);
                throw new AccessDeniedException("非法访问");
            }
        }
        return cartVOList;
    }
}
