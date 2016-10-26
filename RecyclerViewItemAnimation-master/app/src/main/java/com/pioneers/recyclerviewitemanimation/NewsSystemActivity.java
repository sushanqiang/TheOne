package com.pioneers.recyclerviewitemanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.pioneers.recyclerviewitemanimation.adapter.RescyclerItemAdapter;
import com.pioneers.recyclerviewitemanimation.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

public class NewsSystemActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    RescyclerItemAdapter<ItemBean> adapter;

    private List<ItemBean> mItemBeans = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();
    }
    RescyclerItemAdapter.ViewHolder vh;
    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rcv_roots);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RescyclerItemAdapter<ItemBean>(this) {
            @Override
            protected ViewHolder getViewHolder(ViewGroup parent, int viewType) {
                 vh = ViewHolder.get(context, parent, R.layout.item_mianyan_layout);
                return vh;
            }

            @Override
            protected void bindVH(ViewHolder holder, int position) {
                ItemBean data= (ItemBean) getItem(position);
                if (data!=null){

                }
            }
        };
        mRecyclerView.setAdapter(adapter);
        initData();
    }
    private void initData() {
        for (int x = 0; x < 30; x++) {
            mItemBeans.add(new ItemBean(x+"msg","===data="+x));
        }
        if (mItemBeans.size()>0){
            adapter.reSetList(mItemBeans);
        }
    }
}
