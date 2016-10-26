package com.pioneers.recyclerviewitemanimation.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.pioneers.recyclerviewitemanimation.BaseApp;
import com.pioneers.recyclerviewitemanimation.R;
import com.pioneers.recyclerviewitemanimation.bean.SaveData;
import com.pioneers.recyclerviewitemanimation.listener.OnItemClickListener;

/**
 * Created by sushanqiang on 2016/10/12.
 */
public class ManAdapter extends BaseRecyclerViewAdapter<SaveData> {
    public ManAdapter(Context context) {
        super(context);
    }
    @Override
    protected void bindVH(ViewHolder vh, final int position) {
        final SaveData data = list.get(position);
        TextView tvTitle=(TextView) vh.findViewById(R.id.tv_save_title);
        final TextView tvContent=(TextView) vh.findViewById(R.id.tv_save_content);
        tvTitle.setText(data.getTitle());
        tvContent.setText(data.getText());
        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseApp.getInstance().showToast("tvTitle"+data.getTitle());
            }
        });
        tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseApp.getInstance().showToast("tvContent"+data.getContent());
                tvContent.setTextColor(context.getResources().getColor(R.color.black));
                notifyDataSetChanged();
            }
        });
//        this.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//                BaseApp.getInstance().showToast(data.getStime()+position+"dadadaX");
//            }
//        });
    }
    @Override
    protected ViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_save_layout, parent, false));
    }

}
