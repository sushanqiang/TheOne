package com.pioneers.recyclerviewitemanimation.adapter;

/**
 * Created by sushanqiang on 2016/10/11.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
public abstract class SwipeMenuAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    @Override
    public final VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = onCreateContentView(parent, viewType);
        return onCompatCreateViewHolder(contentView, viewType);
    }
    public abstract View onCreateContentView(ViewGroup parent, int viewType);
    public abstract VH onCompatCreateViewHolder(View realContentView, int viewType);
    @Override
    public final void onBindViewHolder(VH holder, int position, List<Object> payloads) {
        onBindViewHolder(holder, position);
    }
    public void onCompatBindViewHolder(VH holder, int position, List<Object> payloads) {
        onCompatBindViewHolder(holder, position, payloads);
    }
}
