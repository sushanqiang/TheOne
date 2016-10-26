package com.pioneers.recyclerviewitemanimation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.pioneers.recyclerviewitemanimation.ItemTouchHelperAdapter;
import com.pioneers.recyclerviewitemanimation.R;
import com.pioneers.recyclerviewitemanimation.bean.ItemBean;
import com.pioneers.recyclerviewitemanimation.view.SlideRelativeLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Young Pioneers on 16/6/30.
 */
public class SlideAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  implements ItemTouchHelperAdapter {

    public static final int NORMAL = 1000;
    public static final int SLIDE = 2000;
    private int mState = NORMAL;
    int count = 0;
    private List<ItemBean> mItemBeans;
    private List<SlideViewHolder> mSlideViewHolders = new ArrayList<>();
    protected AdapterView.OnItemClickListener mOnItemClickListener;
    public void openItemAnimation() {
        mState = SLIDE;
        for (SlideViewHolder holder : mSlideViewHolders) {
            holder.openItemAnimation();
        }
    }
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
//        Collections.swap(mItemBeans, fromPosition, toPosition);
//        notifyItemMoved(fromPosition, toPosition);
////        init();
//        notifyDataSetChanged();
        /***
         * item移动位置
         *默认返回true
         */
        return true;
    }
    @Override
    public void onItemDismiss(int position) {
        if (mItemBeans!=null&&mItemBeans.size()>0) {
            if (mItemBeans.get(position).isChecked()&&count>0){
                count--;
                listener.onClick(count);
            }
            mItemBeans.remove(position);
            notifyItemRemoved(position);
        }
        notifyDataSetChanged();
    }

    public void chioceAll(boolean chioce) {
        mState = SLIDE;
        for (ItemBean itemBean : mItemBeans) {
            itemBean.setChecked(chioce);
        }
        if (chioce) {
            count = mItemBeans.size();
        } else {
            count = 0;
        }
        notifyDataSetChanged();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void canleOnClick() {
        mState = NORMAL;
        for (ItemBean itemBean : mItemBeans) {
            itemBean.setChecked(false);
        }
        count = 0;
        notifyDataSetChanged();
    }

    public void closeItemAnimation() {
        mState = NORMAL;
        for (SlideViewHolder holder : mSlideViewHolders) {
            holder.closeItemAnimation();
        }
    }

    public void setItemBeans(List<ItemBean> beans) {
        mItemBeans = beans;
        notifyDataSetChanged();
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SlideViewHolder slideViewHolder = new SlideViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false), parent.getContext());
        mSlideViewHolders.add(slideViewHolder);
        return slideViewHolder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SlideViewHolder) holder).bind(mItemBeans.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemBeans == null ? 0 : mItemBeans.size();
    }

    private class SlideViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        private SlideRelativeLayout mSlideRelativeLayout;
        private CheckBox mCheckBox;
        private ItemBean mItemBean;
        private Context context;
        private TextView textView;
        public SlideViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            mSlideRelativeLayout = (SlideRelativeLayout) itemView.findViewById(R.id.item_root);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.item_checkbox);
            textView = (TextView) itemView.findViewById(R.id.item_title_num);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        public void bind(ItemBean itemBean) {
            mItemBean = itemBean;
            mCheckBox.setChecked(itemBean.isChecked());
            textView.setText(itemBean.getI()+"===");
            switch (mState) {
                case NORMAL:
                    mSlideRelativeLayout.close();
                    break;
                case SLIDE:
                    mSlideRelativeLayout.open();
                    break;
            }
        }
        public void openItemAnimation() {
            mSlideRelativeLayout.openAnimation();
        }
        public void closeItemAnimation() {
            mSlideRelativeLayout.closeAnimation();
        }
        public void chioceALL(boolean b) {
            notifyDataSetChanged();
        }
        public void setCheckBox() {
            mCheckBox.setChecked(!mCheckBox.isChecked());
            mItemBean.setChecked(mCheckBox.isChecked());
            if (mItemBean.isChecked()) {
                count++;
            } else {
                if (count > 0)
                    count--;
            }
            listener.onClick(count);
        }
        @Override
        public void onClick(View v) {
            //关闭状态下，不能点击选择
            if (mState == NORMAL) {
                Toast.makeText(context, "onItemClick", Toast.LENGTH_LONG).show();
            } else {
                setCheckBox();
            }
        }
        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(context, "onItemLongClick", Toast.LENGTH_LONG).show();

            return false;
        }
    }

    getOnClick listener;

    public interface getOnClick {
        void onClick(int count);
    }

    public getOnClick getListener() {
        return listener;
    }

    public void setListener(getOnClick listener) {
        this.listener = listener;
    }
}
