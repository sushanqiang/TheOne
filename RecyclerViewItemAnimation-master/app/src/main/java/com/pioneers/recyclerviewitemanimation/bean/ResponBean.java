package com.pioneers.recyclerviewitemanimation.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sushanqiang on 2016/2/24.
 */
public class ResponBean<T> implements Serializable {
    private List<T> dataList;
    private String status;
    private String msg;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public String getStatus() {
        return status;
    }
    public boolean isSuccess() {
        return status.equalsIgnoreCase("OK");
    }
    public boolean isAlert() {
        return status.equalsIgnoreCase("alert");
    }
    public boolean isFailure() {
        return status.equalsIgnoreCase("ERROR");
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
