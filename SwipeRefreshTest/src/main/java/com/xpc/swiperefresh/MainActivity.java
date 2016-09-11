package com.xpc.swiperefresh;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeLayout;
    private ListView mylist;
    private List<String> datas = new ArrayList<>();
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniData();
        initView();
    }


    private void iniData() {
        for (int i = 0; i < 20; i++) {
            datas.add("这是添加的数据" + i);
        }
    }
    private void addData(){
        if(datas.size() > 0){
            datas.clear();
        }
        for (int i = 0; i < 20; i++) {
             datas.add("这是最新的数据" + i);
        }
    }
    private void initView() {
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mylist = (ListView) findViewById(R.id.mylist);
     //   swipeLayout.setColorSchemeColors(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        swipeLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
     //   swipeLayout.setSize(SwipeRefreshLayout.LARGE);
        swipeLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.colorWhite));
        //swipeRefreshLayout.setPadding(20, 20, 20, 20);
        //swipeRefreshLayout.setProgressViewOffset(true, 100, 200);
        //swipeRefreshLayout.setDistanceToTriggerSync(50);
        //swipeLayout.setProgressViewEndTarget(true, 100);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.sendEmptyMessageDelayed(0,5000);
            }
        });
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
        mylist.setAdapter(adapter);
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    addData();
                    adapter.notifyDataSetChanged();
                    swipeLayout.setRefreshing(false);
                    break;
            }
            return true;
        }
    });
}
