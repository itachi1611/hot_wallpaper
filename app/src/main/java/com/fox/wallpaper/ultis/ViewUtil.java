package com.fox.wallpaper.ultis;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.AbsListView;

public class ViewUtil {

    public static int getScrollY(AbsListView view, int mHeaderHeight) {
        if (view == null) return 0;

        View c = view.getChildAt(0);
        if (c == null) {
            return 0;
        }

        int firstVisiblePosition = view.getFirstVisiblePosition();
        int top = c.getTop();

        int headerHeight = 0;
        if (firstVisiblePosition >= view.getChildCount()) {
            headerHeight = mHeaderHeight;
        }

        return -top + firstVisiblePosition * c.getHeight() + headerHeight;
    }

    /**
     *
     * @param view
     * @return
     */
    public static Rect getOnScreenRect(View view) {
        return getOnScreenRect(view, true);
    }

    /**
     *
     * @param view
     * @param removeStatusBar
     * @return
     */
    public static Rect getOnScreenRect(View view, boolean removeStatusBar) {
        Rect rect = new Rect();
        final int[] location = new int[2];
        view.getLocationOnScreen(location);

        int statusBarHeight = 0;
        if (removeStatusBar) {
            Rect windowRect = new Rect();
            view.getWindowVisibleDisplayFrame(windowRect);
            statusBarHeight = windowRect.top;
        }

        rect.set(location[0], location[1] - statusBarHeight, location[0] + view.getWidth(), location[1] - statusBarHeight + view.getHeight());
        return rect;
    }

    /**
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeightByActivity(Activity context) {
        Rect frame = new Rect();
        context.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }

}
