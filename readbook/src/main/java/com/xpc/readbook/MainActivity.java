package com.xpc.readbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xpc.readbook.model.Book;
import com.xpc.readbook.callback.ReadIntfaceView;
import com.xpc.readbook.presenter.ReadPresenter;

public class MainActivity extends AppCompatActivity implements ReadIntfaceView {
    private TextView progressText;
    private ReadPresenter presenter;
    private EditText bookNameEdit;
    ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressText = (TextView) findViewById(R.id.progressText);
        bookNameEdit = (EditText) findViewById(R.id.bookNameEdit);
        presenter = new ReadPresenter(this);
    }

    public void onReadBook(View view) {
        presenter.startReadBook();
    }
   public void onAddBook(View view) {
       String bookName = bookNameEdit.getText().toString().trim();
       if(!TextUtils.isEmpty(bookName)){
           presenter.onAddBook(bookName);
       }else{
           Toast.makeText(MainActivity.this, "书名不能为空", Toast.LENGTH_SHORT).show();
       }

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
