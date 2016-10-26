package com.pioneers.recyclerviewitemanimation;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.pioneers.recyclerviewitemanimation.adapter.SlideAdapter;
import com.pioneers.recyclerviewitemanimation.bean.ItemBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private SlideAdapter mSlideAdapter;
    private List<ItemBean> mItemBeans = new ArrayList<>();
    private TextView mRightTV,textView,title_tv;
    private ItemTouchHelper mItemTouchHelper;
    InputMethodManager im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        initView();
        initData();
        initListener();
    }
    private void initBean() {
        for (int x = 0; x < 30; x++) {
            mItemBeans.add(new ItemBean(x));
        }
    }
    private void initData() {
        initBean();
        mSlideAdapter = new SlideAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mSlideAdapter,false,true);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mSlideAdapter);
        mSlideAdapter.setItemBeans(mItemBeans);
    }
    private void initListener() {
        mRightTV.setOnClickListener(this);
    }
    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rcv_root);
        textView = (TextView) findViewById(R.id.tv_noData);
        mRightTV = (TextView) findViewById(R.id.right_tv);
        title_tv= (TextView) findViewById(R.id.title_tv);
        title_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent =new Intent(MainActivity.this,NewsSystemActivity.class);
//                startActivity(intent);

                Intent s=new Intent(MainActivity.this,BuleSaveFragment2.class);
                startActivity(s);
            }
        });
    }
    private void initPopUpView() {
        View popupView = getLayoutInflater().inflate(
                R.layout.layout_chioce, null);
        final CheckBox checkbox = (CheckBox) popupView
                .findViewById(R.id.checkbox);
        Button delete = (Button) popupView
                .findViewById(R.id.delete);
        mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.bottomActAnim);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mPopupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
        mPopupWindow.setOutsideTouchable(true);
        tv_count= (TextView) popupView
                .findViewById(R.id.selected);
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox.isChecked()){
                    mSlideAdapter.chioceAll(true);
                    tv_count.setText("已选"+mSlideAdapter.getItemCount()+"项");
                }else {
                    mSlideAdapter.chioceAll(false);
                    tv_count.setText("已选 0 项");
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbox.setChecked(false);
                    if (mItemBeans != null) {
                        Iterator<ItemBean> iterator = mItemBeans.iterator();
                        synchronized (Iterator.class) {
                            while(iterator.hasNext()){
                                ItemBean integer = iterator.next();
                                if(integer.isChecked()){
                                    iterator.remove();   //注意这个地方
                                }
                            }
                        }
                    }
                mSlideAdapter.setCount(0);
                mSlideAdapter.notifyDataSetChanged();
                if (mItemBeans!=null&&mItemBeans.size()==0){
                    if (mPopupWindow != null && mPopupWindow.isShowing()) {
                        mPopupWindow.dismiss();
                    }
                    textView.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "没有数据了", Toast.LENGTH_LONG).show();
                }
                tv_count.setText("已选 0 项");
            }
        });
        mSlideAdapter.setListener(new GetOnCliock());
    }
    PopupWindow mPopupWindow;
    TextView tv_count;
    //当PopupWindow在自身范围以外点击也会收回
    public boolean onTouchEvent(MotionEvent event) {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                hideSoftInput(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                /**
                 *点击EditText的事件，忽略它。
                 */
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    private void hideSoftInput(IBinder token) {
        if (token != null) {
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_tv:
                editItems();
                break;
        }
    }

    private void editItems() {
        if ("编辑".equals(mRightTV.getText().toString())) {
            mRightTV.setText("取消");
            mSlideAdapter.openItemAnimation();
            if (mPopupWindow != null && mPopupWindow.isShowing()) {
                return;
            }
            initPopUpView();
        } else if ("取消".equals(mRightTV.getText().toString())) {
            mRightTV.setText("编辑");
            if (mPopupWindow != null && mPopupWindow.isShowing()) {
                mPopupWindow.dismiss();
                mPopupWindow = null;
            }
            mSlideAdapter.closeItemAnimation();
            mRightTV.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mSlideAdapter.canleOnClick();
                }
            },152);
        }
        tv_count.setText("已选"+mSlideAdapter.getCount()+"项");
    }
    class GetOnCliock implements SlideAdapter.getOnClick {
        @Override
        public void onClick(int count) {
            tv_count.setText("已选"+count+"项");
            Log.e("已选",count+"项");
        }
    }
}
