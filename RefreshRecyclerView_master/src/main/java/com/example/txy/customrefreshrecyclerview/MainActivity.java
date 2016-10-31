package com.example.txy.customrefreshrecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;

import com.example.headerandfooterwrapper_library.HeaderAndFooterWrapper;
import com.example.refreshrecyclerview_library.RefreshRecyclerView;

public class MainActivity extends Activity {


    private static final int REFRESH = 0;
    private static final int LOADMORE = 1;
    private HeaderAndFooterWrapper headerAndFooterWrapper;
    private RefreshRecyclerViewAdapter recyclerAdapter;
    private RefreshRecyclerView custom_recyclerview;
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what) {
                case REFRESH:
                    custom_recyclerview.onFinishRefresh(true);
                    break;
                case LOADMORE:
                    custom_recyclerview.onFinishRefresh(false);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        custom_recyclerview = (RefreshRecyclerView) findViewById(R.id.custom_recyclerview);
        initRefreshRecyclerView();
    }

    private void initRefreshRecyclerView() {
//        给Recycler设置分割线
        custom_recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerAdapter = new RefreshRecyclerViewAdapter(this);
        headerAndFooterWrapper = new HeaderAndFooterWrapper(recyclerAdapter);
//        不要忘记设置布局管理器
        custom_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        custom_recyclerview.setAdapter(headerAndFooterWrapper);
        custom_recyclerview.addHeaderView(custom_recyclerview.getHeaderView(), headerAndFooterWrapper);
        custom_recyclerview.addFooterView(custom_recyclerview.getFooterView(), headerAndFooterWrapper);
        custom_recyclerview.setOnRefreshListener(new OnRecyclerRefreshListener());
    }

    private class OnRecyclerRefreshListener implements RefreshRecyclerView.OnRefreshListener {
        @Override
        public void onPullDownRefresh() {
//           执行下拉刷新操作，一般是联网更新数据
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(2000);
                    handler.sendEmptyMessage(REFRESH);
                }
            }).start();
        }

        @Override
        public void onLoadingMore() {
//            执行上拉加载操作，一般是联网请求更多分页数据
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(2000);
                    handler.sendEmptyMessage(LOADMORE);
                }
            }).start();
        }
    }
}
