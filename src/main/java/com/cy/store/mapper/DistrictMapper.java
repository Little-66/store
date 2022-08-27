package com.cy.store.mapper;

import com.cy.store.pojo.District;

import java.util.List;

public interface DistrictMapper {


    /**
     *
     * @param name
     * @return
     */
    List<String> findCodeByName(String name);
    String findCodeByName2(String name);

    String findParentByCode(String code);
}
