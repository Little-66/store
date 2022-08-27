package com.cy.store.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
//表公共字段

public class basePojo  implements Serializable {
    private String createdUser ;
    private Date createdTime ;
    private String modifiedUser;
    private Date modifiedTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        basePojo basePojo = (basePojo) o;
        return Objects.equals(createdUser, basePojo.createdUser) &&
                Objects.equals(createdTime, basePojo.createdTime) &&
                Objects.equals(modifiedUser, basePojo.modifiedUser) &&
                Objects.equals(modifiedTime, basePojo.modifiedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdUser, createdTime, modifiedUser, modifiedTime);
    }

    @Override
    public String toString() {
        return "basePojo{" +
                "createdUser='" + createdUser + '\'' +
                ", createdTime=" + createdTime +
                ", modifiedUser='" + modifiedUser + '\'' +
                ", modifiedTime=" + modifiedTime +
                '}';
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public basePojo() {
    }

    public basePojo(String createdUser, Date createdTime, String modifiedUser, Date modifiedTime) {
        this.createdUser = createdUser;
        this.createdTime = createdTime;
        this.modifiedUser = modifiedUser;
        this.modifiedTime = modifiedTime;
    }
}
