package com.cy.store.service.Impl;

import com.cy.store.mapper.AddressMapper;
import com.cy.store.mapper.DistrictMapper;
import com.cy.store.mapper.UserMapper;
import com.cy.store.pojo.Address;
import com.cy.store.pojo.User;
import com.cy.store.service.AddressService;
import com.cy.store.service.Exception.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    private AddressMapper addressMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private DistrictMapper districtMapper;
    @Value(value = "${user.address.max-count}")
    private Integer maxCount;
    @Override
    public Integer insert(Integer uid, String username, Address address) {
        Integer addressCount = addressMapper.countByUid(uid);
        if(addressCount>=maxCount){
            throw new AddressCountLimitException("您的地址数量过多");
        }
        User result = userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UsernameNotFoundException("用户不存在");
        }
        String provinceCode = districtMapper.findCodeByName2(address.getProvinceName());
        String cityCode = districtMapper.findCodeByName2(address.getCityName());
       List<String> areaCodeList = districtMapper.findCodeByName(address.getAreaName());
       String areaCode = "";
        address.setUid(uid);
        Integer  isDefault = addressCount==0? 1:0;
        address.setIsDefault(isDefault);
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        address.setProvinceCode(provinceCode);
        address.setCityCode(cityCode);
        for(String a:areaCodeList){
            String areaParent = districtMapper.findParentByCode(a);
            if (areaParent.equals(cityCode)){
                areaCode = a;
            }
        }
        address.setAreaCode(areaCode);
        Integer insert = addressMapper.insert(address);
        if (insert!=1){
            throw new InsertException("插入时未知异常");
        }
        return insert;
    }

    @Override
    public List<Address> findByUid(Integer uid) {
        List<Address> addressByUid = addressMapper.findByUid(uid);
        for(Address address:addressByUid){
            address.setUid(null);
            address.setAreaCode(null);
            address.setCityCode(null);
            address.setProvinceCode(null);
            address.setZip(null);
            address.setTel(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
        }
        return addressByUid;
    }

    @Override
    public void updateDefault(Integer aid, Integer uid, String username) {
        Address address = addressMapper.findByAid(aid);
        if(address==null){
            throw new AddressNotExistException("收货地址不存在");
        }if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        Integer integer = addressMapper.updateDefault_0(uid);
        if(integer<1){
            throw new UpdateException("更新时异常");
        }
        Integer integer1 = addressMapper.updateDefaultByAid_1(aid, username, new Date());
        if(integer1!=1){
            throw new UpdateException("更新时异常");
        }
    }

    @Override
    public Integer removeAddressByAid(Integer aid) {
        Integer integer = addressMapper.removeAddressByAid(aid);
        if(integer!=1){
            throw new RemoveException("删除时发生未知错误");
        }
        return integer;
    }

    public Address findByAid(Integer aid,Integer uid){
        Address addressByAid = addressMapper.findByAid(aid);
        addressByAid.setProvinceCode(null);
        addressByAid.setCityCode(null);
        addressByAid.setAreaCode(null);
        addressByAid.setCreatedUser(null);
        addressByAid.setCreatedTime(null);
        addressByAid.setModifiedUser(null);
        addressByAid.setCreatedTime(null);
        return addressByAid;
    }
}
