package com.fox.wallpaper.bases;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fox.wallpaper.ultis.ProgressDialogUtils;

import io.reactivex.disposables.CompositeDisposable;

public class BaseActivity extends AppCompatActivity {

    private CompositeDisposable disposable = new CompositeDisposable();
    private ProgressDialog progressDialog;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //gestureDetector = new GestureDetector(new SwipeDetector(this));
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TouchEvent dispatcher.
        if (gestureDetector != null) {
            if (gestureDetector.onTouchEvent(ev))
                // If the gestureDetector handles the event, a swipe has been
                // executed and no more needs to be done.
                return true;
        }
        return super.dispatchTouchEvent(ev);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return gestureDetector.onTouchEvent(event);
//    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    /**
     * Hide status bar
     */
    public void hideStatusBar() {
        if(Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
            supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
            getActionBar().hide();
        }else{
            View decorView = getWindow().getDecorView();
            // Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
            supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
            getSupportActionBar().hide();
        }
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
    }

    /**
     *
     * @param containerId
     * @param fragment
     */
    protected void loadFragmentToContainer(int containerId, @NonNull Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * replace or add fragment to the container
     *
     * @param fragment pass android.support.v4.app.Fragment
     * @param bundle pass your extra bundle if any
     * @param popBackStack if true it will clear back stack
     * @param findInStack if true it will load old fragment if found
     */
    public void loadFragmentToContainer(int containerId, Fragment fragment, @Nullable Bundle bundle, boolean popBackStack, boolean findInStack) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        String tag = fragment.getClass().getName();
        Fragment parentFragment;
        if (findInStack && fm.findFragmentByTag(tag) != null) {
            parentFragment = fm.findFragmentByTag(tag);
        } else {
            parentFragment = fragment;
        }
        if (popBackStack) {
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            ft.addToBackStack(parentFragment.getClass().getName() + "");
        }
        ft.replace(containerId, parentFragment, tag);
        ft.commit();
        fm.executePendingTransactions();
    }

    /**
     * Remove fragment
     * @param fragment
     */
    protected void removeFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.remove(fragment);
        transaction.commit();
    }

    /**
     * Add fragment
     *
     * @param containerId R.id.
     * @param fragment fragment
     */
    protected void addFragment(int containerId, Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(containerId, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.addToBackStack(backStateName);
        transaction.commit();
    }

    /**
     *
     * @param containerId
     * @param fragment
     * @param tag
     */
    protected void addFragment(int containerId, Fragment fragment, String tag) {
        String backStateName = fragment.getClass().getName();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(containerId, fragment, tag).hide(fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.addToBackStack(backStateName);
        transaction.commit();
    }

    /**
     * Replace fragment
     *
     * @param containerId R.id.
     * @param fragment fragment
     */
    protected void replaceFragment(int containerId, Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.addToBackStack(backStateName);
        transaction.commit();
    }

    /**
     * Pop fragment
     */
    protected Boolean popFragment() {
        int countStack = getSupportFragmentManager().getBackStackEntryCount();
        if (countStack > 1) {
            getSupportFragmentManager().popBackStack();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        if (!popFragment()){
            super.onBackPressed();
        }
    }

    /**
     * Navigate to new activity
     * @param classname
     */
    protected void navigateActivity(Class classname) {
        Intent intent = new Intent(this, classname);
        startActivity(intent);
    }

    /**
     * Navigate to new activity
     * @param classname
     * @param option
     */
    protected void navigateActivity(Class classname, int option) {
        Intent intent = new Intent(this, classname);
        intent.setFlags(option);
        startActivity(intent);
    }

    /**
     * Navigate to new activity
     * @param classname
     * @param k
     * @param v
     * @param option
     */
    protected void navigateActivity(Class classname, String k, int v, int option) {
        Intent intent = new Intent(this, classname);
        intent.putExtra(k, v);
        intent.setFlags(option);
        startActivity(intent);
    }

    /**
     * Navigate to new activity for result
     * @param classname
     * @param request_code
     */
    protected void navigateActivityForResult(Class classname, int request_code) {
        Intent intent = new Intent(this, classname);
        startActivityForResult(intent, request_code);
    }

    /**
     * Show progress dialog
     */
    protected void onShowLoading() {
        onHideLoading();
        progressDialog = ProgressDialogUtils.showLoadingDialog(this);
    }

    /**
     * Hide progress dialog
     */
    protected void onHideLoading() {
        if(progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            //progressDialog.cancel();
        }
    }

}
