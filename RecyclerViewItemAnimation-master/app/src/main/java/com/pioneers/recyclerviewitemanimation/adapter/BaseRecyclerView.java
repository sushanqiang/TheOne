package com.pioneers.recyclerviewitemanimation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 */
public abstract class BaseRecyclerView<T> extends RecyclerView.Adapter implements View.OnClickListener {

    protected Context context;
    protected List<T> list;
    protected OnItemClickListener onItemClickListener;
    public BaseRecyclerView(Context context) {
        this.context = context;
        if (list==null){
            list=new ArrayList<>();
        }
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public void reSetList(List<T> dataList) {
        if (null != dataList) {
            list.clear();
            list = dataList;
        }
        notifyDataSetChanged();
    }

    public void addDatasAtFoot(List<T> items) {
        if (items != null) {
            list.addAll(items);
        }
        notifyDataSetChanged();
    }

    public void addData(T item, int poison) {
        if (null != item) {
            list.add(poison, item);
        }
        notifyDataSetChanged();
    }

    public void addData(T item) {
        if (null != item) {
            list.add(item);
        }
        notifyDataSetChanged();
    }

    public void delete(int poison) {
        if (poison >= 0 && poison <= getItemCount() - 1) {
            list.remove(poison);
        }
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if (null != onItemClickListener) {
            onItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindVH(holder, position);
    }

    protected abstract void bindVH(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }
    }
}
