package com.pioneers.recyclerviewitemanimation.bean;

import java.io.Serializable;

/**
 * Created by sushanqiang on 2016/5/30.
 */
public class BuleSaveBean implements Serializable {

    /**
     * cmd : get_news_list
     * data : {"type":1,"array":[]}
     * rcd : 0
     */

    private String cmd;
    /**
     * type : 1
     * array : []
     */

    private DataBean data;
    private int rcd;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getRcd() {
        return rcd;
    }

    public void setRcd(int rcd) {
        this.rcd = rcd;
    }

}
