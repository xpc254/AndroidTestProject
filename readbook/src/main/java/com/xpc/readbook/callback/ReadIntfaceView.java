package com.xpc.readbook.callback;

import com.xpc.readbook.base.IView;

/**
 * Created by xiepc on 2016/9/2 0002 23:49
 */
public interface ReadIntfaceView<T> extends IView {
    /**更新进度*/
    public void start();
    public void doing(T t);
    public void finish(T t);
}
