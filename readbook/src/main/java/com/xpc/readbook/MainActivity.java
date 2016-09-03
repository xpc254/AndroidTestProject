package com.xpc.readbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xpc.readbook.model.Book;
import com.xpc.readbook.callback.ReadIntfaceView;
import com.xpc.readbook.presenter.ReadPresenter;

public class MainActivity extends AppCompatActivity implements ReadIntfaceView {
    private TextView progressText;
    private ReadPresenter presenter;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressText = (TextView) findViewById(R.id.progressText);
        presenter = new ReadPresenter(this);
    }

    public void onReadBook(View view) {
        presenter.startReadBook();
    }

    @Override
    public void start() {
        progressText.setText("开始读书。。。");
    }

    @Override
    public void doing(Object o) {
        progressText.setText("正在读书..." + ((Book)o).getNowPage());
    }

    @Override
    public void finish(Object o) {
        progressText.setText("完成读书,总共页数："+ ((Book)o).getPageCount());
    }
}
