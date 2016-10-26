package com.pioneers.recyclerviewitemanimation;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.pioneers.recyclerviewitemanimation.adapter.BaseViewHolder;
import com.pioneers.recyclerviewitemanimation.adapter.ManAdapter;
import com.pioneers.recyclerviewitemanimation.adapter.SuperBaseAdapter;
import com.pioneers.recyclerviewitemanimation.bean.BuleSaveBean;
import com.pioneers.recyclerviewitemanimation.bean.SaveData;
import com.pioneers.recyclerviewitemanimation.http.NoHttpHelper;
import com.pioneers.recyclerviewitemanimation.listener.OnItemClickListener;
import com.yolanda.nohttp.rest.Response;

import java.util.Hashtable;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sushanqiang on 2016/5/30.
 */
public class BuleSaveFragment2 extends AppCompatActivity {
    private static final String SAVE_TYPE = "type";
    @BindView(R.id.dadadadad)
    RecyclerView recycler;
    private String type="0";
    @BindView(R.id.swipe_save)
    public SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_num)
    public TextView tvNum;
    private ListView mList;
    private int page = 1;
    SuperBaseAdapter superBaseAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item_save2);
        initView();
    }
    private void initView() {
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        superBaseAdapter=new SuperBaseAdapter<SaveData>(this) {
            @Override
            protected void convert(BaseViewHolder holder, SaveData item, int position) {
                holder.setText(R.id.id_mianyan_phone,item.getType()+"'adadqadadada'");
                holder.setText(R.id.id_mianyan_tv,item.getText())
                        .setOnClickListener(R.id.id_mianyan_tv,new OnItemChildClickListener());
            }
            @Override
            protected int getItemViewLayoutId(int position, SaveData item) {
                return R.layout.item_mianyan_layout;
            }
        };
        superBaseAdapter.setOnItemChildClickListener(new SuperBaseAdapter.OnRecyclerViewItemChildClickListener() {
            @Override
            public void onItemChildClick(SuperBaseAdapter adapter, View view, int position) {
                if (view.getId()==R.id.id_item_mianyan){
                    BaseApp.getInstance().showToast("id_item_mianyan");
                }
            }
        });
        /**
         * add item click event
         */
        superBaseAdapter.setOnItemClickListener(new SuperBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object item, int position) {
                Toast.makeText(BuleSaveFragment2.this,"you click item",Toast.LENGTH_SHORT).show();
            }
        });
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                intData();
            }
        });
        recycler.setAdapter(superBaseAdapter);
        intData();
    }
    private void intData() {
        String path="http://mobile.cloudfence.cn:8080/invoke/invoke.do";
        final String key="cloudfence"+type;
        NoHttpHelper.getHelper().postString(this,path, getParmas(page,type),true,false, new NoHttpHelper.CallBack() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                mRefreshLayout.setRefreshing(false);
                if (response!=null){
                    BuleSaveBean data=null;
                    try {
                        data = JSONObject.parseObject(response.get(), BuleSaveBean.class);
                        if (data!=null&&data.getData().getArray().size()>0){
                            superBaseAdapter.reSetList(data.getData().getArray());
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                if (superBaseAdapter.getItemCount()>0){
                    tvNum.setVisibility(View.GONE);
                }else {
                    tvNum.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFinish(int what) {

            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                mRefreshLayout.setRefreshing(false);
                if (superBaseAdapter.getItemCount()>0){
                    tvNum.setVisibility(View.GONE);
                }else {
                    tvNum.setVisibility(View.VISIBLE);
                }
            }

        });


    }
    public Map<String,String> getParmas(int page, String type) {
        Map<String,String> params = new Hashtable<>();
        params.put("cmd", "get_news_list");
        params.put("cmdtype", "");
        JSONObject data = new JSONObject();
        data.put("dt", "get_news_list");
        data.put("type", type);
        data.put("page", String.valueOf(page));
        params.put("data", data.toString());
        return params;
    }
}