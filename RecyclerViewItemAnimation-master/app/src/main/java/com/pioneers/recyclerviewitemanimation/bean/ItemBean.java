package com.pioneers.recyclerviewitemanimation.bean;

import java.io.Serializable;

/**
 * Created by Young Pioneers on 16/6/30.
 */
public class ItemBean extends  ResponBean implements Serializable {
    private boolean isChecked;
    public boolean isChecked() {
        return isChecked;
    }
    private String msg;
    private String data;
    private int i;
    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public ItemBean(int i) {
        this.i = i;
    }
    public ItemBean(String msg ,String data) {
        this.msg = msg;
        this.data = data;
    }
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
