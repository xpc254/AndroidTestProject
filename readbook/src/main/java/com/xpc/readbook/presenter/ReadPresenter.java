package com.xpc.readbook.presenter;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.xpc.readbook.base.IPresenter;
import com.xpc.readbook.callback.OnClickPerform;
import com.xpc.readbook.callback.ReadIntfaceView;
import com.xpc.readbook.model.Book;
import com.xpc.readbook.thread.ReadThread;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xiepc on 2016/9/2 0002 23:41
 */
public class ReadPresenter implements IPresenter ,OnClickPerform{
    ReadIntfaceView intfaceView;
    private List<Book> bookList = new ArrayList<>();
    public ReadPresenter(ReadIntfaceView intfaceView){
        this.intfaceView = intfaceView;
    }
    private Book<String> addBook(){
        return addBook("边城");
    }
  private Book<String> addBook(String bookName){
        Book book = new Book();
        book.setName(bookName);
        book.setBookMoreMSG("翠翠和爷爷的故事");
        book.setPageCount(100);
        book.setNowPage(0);
        return book;
    }

    /***
     * 开始读书
     */
    public void startReadBook(){
        new Thread(new ReadThread(handler,addBook())).start();
    }
    private Handler handler = new android.os.Handler(new android.os.Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    intfaceView.start();
                    break;
                case 1:
                    intfaceView.doing(msg.obj);
                    break;
                case 2:
                    intfaceView.finish(msg.obj);
                    break;
            }
            return false;
        }
    });

    @Override
    public void onCreate() {
        //初始化操作
    }

    @Override
    public void onAddBook(String bookName) {
        //添加书本
        bookList.add(addBook(bookName));
        Log.i("xiepc","添加书本成功,书架总共："+bookList.size()+"本书");
    }
}
