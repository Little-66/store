package com.cy.store.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class District extends basePojo{
    private Integer id;
    private String parent;
    private String code;
    private String name;

    public District(String parent, String code, String name) {
        this.parent = parent;
        this.code = code;
        this.name = name;
    }

    public District(String createdUser, Date createdTime, String modifiedUser, Date modifiedTime, String parent, String code, String name) {
        super(createdUser, createdTime, modifiedUser, modifiedTime);
        this.parent = parent;
        this.code = code;
        this.name = name;
    }
}
