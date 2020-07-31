package com.fox.wallpaper.bases;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;

public class BaseActivityFullScreen extends AppCompatActivity {

    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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
     * Navigate to new activity
     * @param classname
     */
    protected void navigateActivity(Class classname) {
        Intent intent = new Intent(this, classname);
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

}
