package com.xpc.readbook.presenter;

import android.os.Handler;
import android.os.Message;

import com.xpc.readbook.base.IPresenter;
import com.xpc.readbook.callback.ReadIntfaceView;
import com.xpc.readbook.model.Book;
import com.xpc.readbook.thread.ReadThread;


/**
 * Created by xiepc on 2016/9/2 0002 23:41
 */
public class ReadPresenter implements IPresenter {
    ReadIntfaceView intfaceView;
    public ReadPresenter(ReadIntfaceView intfaceView){
        this.intfaceView = intfaceView;
    }
    private Book<String> addBook(){
        Book book = new Book();
        book.setName("边城");
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
}
