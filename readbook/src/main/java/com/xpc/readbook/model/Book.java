package com.xpc.readbook.model;

/**
 * Created by xiepc on 2016/9/2 0002 23:27
 */
public class Book<T> {
    private String name;
    private int pageCount;
    private int nowPage;
    private T bookMoreMSG;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public T getBookMoreMSG() {
        return bookMoreMSG;
    }

    public void setBookMoreMSG(T bookMoreMSG) {
        this.bookMoreMSG = bookMoreMSG;
    }
}
