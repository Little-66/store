package com.cy.store.util;

import java.io.Serializable;
import java.util.Objects;

public class JsonResult<E> implements Serializable  {
//    状态码
    private Integer stat;
//    描述信息
private  String message;
//    数据 E表示任何类型
private E data;

    public JsonResult(Integer stat, E data) {
        this.stat = stat;
        this.data = data;
    }

    public JsonResult(Integer stat) {
        this.stat = stat;
    }
    public JsonResult(Throwable e){
        this.message = e.getMessage();
    }

    public JsonResult() {
    }

    public JsonResult(Integer stat, String message, E data) {
        this.stat = stat;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "stat=" + stat +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonResult<?> that = (JsonResult<?>) o;
        return Objects.equals(stat, that.stat) &&
                Objects.equals(message, that.message) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stat, message, data);
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
