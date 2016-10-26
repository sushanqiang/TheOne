/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pioneers.recyclerviewitemanimation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pioneers.recyclerviewitemanimation.bean.ResponBean;
import com.pioneers.recyclerviewitemanimation.listener.OnItemClickListener;
import com.pioneers.recyclerviewitemanimation.listener.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView的adapter类
 */
public abstract class RescyclerItemAdapter<T extends ResponBean> extends RecyclerView.Adapter<RescyclerItemAdapter.ViewHolder> {
    private List<T> list;
    protected Context context;
    private LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    public RescyclerItemAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        list = new ArrayList<>();
    }
    public RescyclerItemAdapter(List<T> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public int getItemCount() {
        return list != null && list.size() > 0 ? list.size() : 0;
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

    public void delete(T item) {
        if (item != null) {
            list.remove(item);
        }
        notifyDataSetChanged();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public RescyclerItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getViewHolder(parent, viewType);
    }
    @Override
    public void onBindViewHolder(final RescyclerItemAdapter.ViewHolder holder, int position) {
        holder.setOnItemClickListener(mOnItemClickListener);
        holder.setOnItemLongClickListener(onItemLongClickListener);
        bindVH(holder, position);
    }
    protected abstract ViewHolder getViewHolder(ViewGroup parent, int viewType);
    protected abstract void bindVH(RescyclerItemAdapter.ViewHolder holder, int position);
    /**
     * 当做是hashMap
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private SparseArray<View> views = new SparseArray<View>();
//        private View convertView;
        OnItemClickListener OnItemClickListener;
        OnItemLongClickListener onItemLongClickListener;
        private ViewHolder(View convertView) {
            super(convertView);
//            this.convertView = convertView;
            //当带点击item时，颜色变化
//            if (clickAble) {
//                convertView.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        switch (event.getAction()) {
//                            case MotionEvent.ACTION_DOWN:
//                                v.setBackgroundResource(R.drawable.mine_selector);
//                                break;
//                            case MotionEvent.ACTION_UP:
//                                v.setBackgroundResource(R.color.white);
//                                break;
//                        }
//                        return false;
//                    }
//                });
//            }
        }

        public static ViewHolder get(Context context, ViewGroup parent, int layoutId) {
            return new ViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
        }


        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.OnItemClickListener = onItemClickListener;
        }

        public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
            this.onItemLongClickListener = onItemLongClickListener;
        }
//        public View getConvertView() {
//            return convertView;
//        }
//
//        public void setConvertView(View convertView) {
//            this.convertView = convertView;
//        }
//
//        public View findViewById(int viewId) {
//            View view = views.get(viewId);
//            if (view == null) {
//                view = convertView.findViewById(viewId);
//                views.put(viewId, view);
//            }
//            return view;
//        }

//        public void setImg(int viewId, String value, int x, int y) {
//            SimpleDraweeView img = (SimpleDraweeView) findViewById(viewId);
//            FrescoHelper.setdraweeView(img, Uri.parse(value), FrescoHelper.getResizeOptions(x, y));
//        }
//
//        public SimpleDraweeView getImg(int viewId) {
//            SimpleDraweeView img = (SimpleDraweeView) findViewById(viewId);
//            return img;
//        }
//
//        public ImageView getImg2(int viewId) {
//            ImageView img2 = (ImageView) findViewById(viewId);
//            return img2;
//        }
//
//        public void setText(int viewId, String value) {
//            TextView textView = (TextView) findViewById(viewId);
//            textView.setText(value);
//        }
//
//        public TextView getText(int viewId) {
//            TextView textView = (TextView) findViewById(viewId);
//            return textView;
//        }
//
//        public View setOnClickListener(int viewId, View.OnClickListener listener) {
//            View view = findViewById(viewId);
//            if (view != null) {
//                view.setOnClickListener(listener);
//            }
//            return view;
//        }

        @Override
        public void onClick(View v) {
            if (OnItemClickListener != null) {
                OnItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (onItemLongClickListener != null) {
                onItemLongClickListener.onItemLongClick(v, getAdapterPosition());
            }
            return false;
        }
    }

}
