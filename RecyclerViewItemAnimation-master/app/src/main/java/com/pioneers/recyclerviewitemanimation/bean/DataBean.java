package com.pioneers.recyclerviewitemanimation.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sushanqiang on 2016/5/30.
 */
public class DataBean  implements Serializable {
    private int type;
    private List<SaveData> array;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<SaveData> getArray() {
        return array;
    }

    public void setArray(List<SaveData> array) {
        this.array = array;
    }
}