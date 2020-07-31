package com.fox.wallpaper.ui.customs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class FoxySwipeRefreshLayout extends SwipeRefreshLayout {

    private float startY;
    private float startX;

    private boolean mIsFoxyDragged;
    private final int mTouchSlop;

    public FoxySwipeRefreshLayout(@NonNull Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public FoxySwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                startY = ev.getY();
                startX = ev.getX();
                mIsFoxyDragged = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if(mIsFoxyDragged) {
                    return false;
                }
                float endX = ev.getX();
                float endY = ev.getY();
                float distanceX = Math.abs(endX - startX);
                float distanceY = Math.abs(endY - startY);
                if(distanceX > mTouchSlop && distanceX > distanceY) {
                    mIsFoxyDragged = true;
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mIsFoxyDragged = false;
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

}
