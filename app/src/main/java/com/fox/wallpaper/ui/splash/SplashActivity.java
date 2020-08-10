package com.fox.wallpaper.ui.splash;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.fox.wallpaper.R;
import com.fox.wallpaper.anim.MyBounceInterpolator;
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

        setAnimation(ivLogo);

        onNavigateTime();
    }

    private void setAnimation(View v) {
        animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bounce);
        // Use custom animation interpolator to achieve the bounce effect
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2,20.0);
        animation.setInterpolator(interpolator);
        ivLogo.startAnimation(animation);
        // Run button animation again after it finished
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setAnimation(v);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void onNavigateTime() {
        new Handler().postDelayed(() -> {
            navigateActivity(IntroActivity.class);
            finish();
        },SPLASH_TIME_OUT);
    }

}
