package com.xpc.learnrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.xpc.learnrecyclerview.adapter.MyRecyclerAdater;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyRecyclerAdater.OnClickPerform {

    private RecyclerView recyclerView;
    private List<String> datas = new ArrayList<>();
    private MyRecyclerAdater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData(){
        for (int i = 0; i < 30; i++) {
            datas.add("谢鹏程---条目："+i);
        }
    }
   private void initView(){
       recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
     //  recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)); //设置为gridView一样的布局
       recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)); //设置为gridView一样的布局
      // recyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL));
       recyclerView.setItemAnimator(new DefaultItemAnimator());
       adapter = new MyRecyclerAdater(this,datas);
       recyclerView.setAdapter(adapter);
       adapter.setOnClickPerform(this);
   }

    public void onAddItem(View view){
        adapter.addItem(2);
    }
    public void onRemoveItem(View view){
        adapter.removeItem(2);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(MainActivity.this, "onItemClick"+position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Toast.makeText(MainActivity.this, "onItemLongClick"+position, Toast.LENGTH_SHORT).show();
    }
}
