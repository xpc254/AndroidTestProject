package com.xpc.swiperefresh;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xpc.swiperefresh.custom.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class RefreshActivity extends BaseActivity {
     private RefreshLayout myRefreshLayout;
//    private RefreshTouchLayout myRefreshLayout;
    private ListView myListView;
    private List<String> datas = new ArrayList<>();
    private ArrayAdapter adapter;
    private TextView titleText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        setStatusBar(this,getResources().getColor(R.color.colorPrimary));
        iniData();
        initView();
    }
    private void initView() {
        titleText = (TextView) findViewById(R.id.titleText);
        titleText.setText("上拉加载更多");
        myRefreshLayout = (RefreshLayout) findViewById(R.id.myRefreshLayout);
        myListView = (ListView) findViewById(R.id.myListView);
        //   swipeLayout.setColorSchemeColors(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        myRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        //   swipeLayout.setSize(SwipeRefreshLayout.LARGE);
        myRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.colorWhite));
        //swipeRefreshLayout.setPadding(20, 20, 20, 20);
        //swipeRefreshLayout.setProgressViewOffset(true, 100, 200);
        //swipeRefreshLayout.setDistanceToTriggerSync(50);
        //swipeLayout.setProgressViewEndTarget(true, 100);
        myRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.sendEmptyMessageDelayed(0,5000);
            }
        });
        myRefreshLayout.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                handler.sendEmptyMessageDelayed(1,3000);
            }
        });
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast.makeText(getApplicationContext(),"这是第" + String.valueOf(position + 1) + "项",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void iniData() {
        for (int i = 0; i < 20; i++) {
            datas.add("这是原始的数据" + i);
        }
    }
    private void refreshData(){
        if(datas.size() > 0){
            datas.clear();
        }
        for (int i = 0; i < 20; i++) {
            datas.add("这是最新的数据" + i);
        }
    }
    private void addData(){
        for (int i = 0; i < 20; i++) {
            datas.add("这是添加的数据" + i);
        }
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 0: //下拉刷新
                    refreshData();
                    adapter.notifyDataSetChanged();
                    myRefreshLayout.setRefreshing(false);
                    break;
                case 1: //上拉加载更多
                    Log.i("xiepc","---加载更多---");
                    addData();
                    adapter.notifyDataSetChanged();
                    myRefreshLayout.setLoading(false);
                    break;
            }
            return true;
        }
    });
}
