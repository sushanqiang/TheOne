package com.pioneers.recyclerviewitemanimation.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.pioneers.recyclerviewitemanimation.bean.ResponBean;
import com.pioneers.recyclerviewitemanimation.bean.SaveData;

import java.util.List;

/**
 * Created by sushanqiang on 2016/10/11.
 */
public class DataAdapter extends SuperBaseAdapter<SaveData>{

    public DataAdapter(Context context) {
        super(context);
    }
    @Override
    protected void convert(BaseViewHolder holder, SaveData item, int position) {
    }
    @Override
    protected int getItemViewLayoutId(int position, SaveData item) {
        return 0;
    }
}
