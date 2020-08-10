package com.fox.wallpaper.ui.splash;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.fox.wallpaper.R;
import com.fox.wallpaper.bases.BaseActivity;
import com.fox.wallpaper.ui.intro.IntroActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.fox.wallpaper.ultis.Constants.SPLASH_TIME_OUT;

public class SplashActivity extends BaseActivity implements SplashContract.View { //TODO: DON'T FORGET TO ADD THIS ACTIVITY TO THE MANIFEST FILE!!!

    private Animation animation;
    private ImageView ivLogo;

    private SplashContract.Presenter mPresenter = new SplashPresenter(this);    // Presenter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isOnline();
        hideStatusBar();
        setContentView(R.layout.activity_splash);  //TODO: create the layout and add it here

        ivLogo = findViewById(R.id.ivLogo);

        setAnimation();

        onNavigateTime();
    }

    private void setAnimation() {
        animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.rotate);
        ivLogo.startAnimation(animation);
    }

    private void onNavigateTime() {
        new Handler().postDelayed(() -> {
            navigateActivity(IntroActivity.class);
            finish();
        },SPLASH_TIME_OUT);
    }

}
