package com.pioneers.recyclerviewitemanimation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.pioneers.recyclerviewitemanimation.listener.OnItemClickListener;
import com.pioneers.recyclerviewitemanimation.listener.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by syshanqiang
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder> {
    protected List<T> list;
    protected Context context;
    protected LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public OnItemLongClickListener getOnItemLongClickListener() {
        return onItemLongClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public BaseRecyclerViewAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        list = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
    public Object getItem(int position) {
        return list.get(position);
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

    @Override
    public BaseRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.setIsRecyclable(false);
        bindVH(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v)
            {
                mOnItemClickListener.onItemClick(holder.itemView, position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener()  {
            @Override
            public boolean onLongClick(View v) {
                mOnItemClickListener.onItemClick(holder.itemView, position);
                return true;
            }
        });
    }

    protected abstract void bindVH(ViewHolder holder, int position);

    protected abstract ViewHolder getViewHolder(ViewGroup parent, int viewType);

    protected static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        com.pioneers.recyclerviewitemanimation.listener.OnItemClickListener OnItemClickListener;
        OnItemLongClickListener onItemLongClickListener;
        private SparseArray<View> views = new SparseArray<View>();
        private View convertView;

        protected ViewHolder(View view) {
            super(view);
            this.convertView = view;
        }
        public View getConvertView() {
            return convertView;
        }

        public void setConvertView(View convertView) {
            this.convertView = convertView;
        }

        public View findViewById(int viewId) {
            View view = views.get(viewId);
            if (view == null) {
                view = convertView.findViewById(viewId);
                views.put(viewId, view);
            }
            return view;
        }


        public ImageView getImg2(int viewId) {
            ImageView img2 = (ImageView) findViewById(viewId);
            return img2;
        }

        public void setText(int viewId, String value) {
            TextView textView = (TextView) findViewById(viewId);
            textView.setText(value);
        }

        public TextView getText(int viewId) {
            TextView textView = (TextView) findViewById(viewId);
            return textView;
        }

        public View setOnClickListener(int viewId, View.OnClickListener listener) {
            View view = findViewById(viewId);
            if (view != null) {
                view.setOnClickListener(listener);
            }
            return view;
        }

        @Override
        public boolean onLongClick(View v) {
            if (onItemLongClickListener != null) {
                onItemLongClickListener.onItemLongClick(v, getAdapterPosition());
            }
            return false;
        }

        @Override
        public void onClick(View v) {
            if (OnItemClickListener != null) {
                OnItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.OnItemClickListener = onItemClickListener;
        }

        public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
            this.onItemLongClickListener = onItemLongClickListener;
        }
    }
}
