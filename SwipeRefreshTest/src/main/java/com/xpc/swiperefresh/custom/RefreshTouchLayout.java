package com.xpc.swiperefresh.custom;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListView;

import com.xpc.swiperefresh.R;

/**
 * Created by xiepc on 2016/9/11 0011 23:38
 */
public class RefreshTouchLayout extends SwipeRefreshLayout {
    // listview实例
    private ListView mListView;
    // 上拉接口监听器, 到了最底部的上拉加载操作
    private OnLoadListener mOnLoadListener;
    // ListView的加载中footer
    private View mListViewFooter;
    // 是否在加载中 ( 上拉加载更多 )
    private boolean isLoading = false;

    // 按下时的y坐标
    private int mYDown;
    // 抬起时的y坐标
    private int mLastY;
    // 滑动到最下面时的上拉操作
    private int mTouchSlop;

    public RefreshTouchLayout(Context context) {
        this(context, null);
    }

    public RefreshTouchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mListViewFooter = LayoutInflater.from(context).inflate(
                R.layout.listview_footer, null, false);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        // 初始化ListView对象
        if (mListView == null) {
            getListView();
        }
    }

    // 获取ListView对象
    private void getListView() {
        int childs = getChildCount();
        if (childs > 0) {
            View childView = getChildAt(0);
            if (childView instanceof ListView) {
                mListView = (ListView) childView;
            }
        }
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 按下
                mYDown = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                // 移动
                mLastY = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_UP:
                // 抬起
                if ((mYDown - mLastY) >= mTouchSlop && isLoading == false) {
                    // 设置状态
                    setLoading(true);
                    //
                    mOnLoadListener.onLoad();
                }
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    // 设置加载状态
    public void setLoading(boolean loading) {
        isLoading = loading;
        if (isLoading) {
            mListView.addFooterView(mListViewFooter);
        } else {
            mListView.removeFooterView(mListViewFooter);

        }
    }

    // 设置监听器
    public void setOnLoadListener(OnLoadListener loadListener) {
        mOnLoadListener = loadListener;
    }

    // 加载更多的接口
    public interface OnLoadListener {
        public void onLoad();
    }
}
