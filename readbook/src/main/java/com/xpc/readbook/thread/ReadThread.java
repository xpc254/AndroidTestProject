package com.xpc.readbook.thread;

import android.os.Handler;

import com.xpc.readbook.model.Book;

/**
 * Created by xiepc on 2016/9/2 0002 23:53
 */
public class ReadThread implements Runnable{
    private Handler handler;
    private Book book;
    private int pageCount;
    public ReadThread(Handler handler, Book book){
        this.handler =handler;
        this.book = book;
    }
    @Override
    public void run() {
        pageCount = book.getPageCount();
        int current = book.getNowPage();
        handler.sendEmptyMessage(0);//开始读书
        while (current < pageCount){
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            current += 2;
            book.setNowPage(current);
            handler.obtainMessage(1,book).sendToTarget();
        }
        handler.obtainMessage(2,book).sendToTarget();;//完成读书
    }
}
