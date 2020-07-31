package com.fox.wallpaper.ui.intro;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.fox.wallpaper.R;
import com.fox.wallpaper.adapters.IntroSliderAdapter;
import com.fox.wallpaper.bases.BaseActivity;
import com.fox.wallpaper.bases.MainApplication;
import com.fox.wallpaper.helpers.SharedPreferencesHelper;
import com.fox.wallpaper.ui.home.HomeActivity;

public class IntroActivity extends BaseActivity implements IntroContract.View { //TODO: DON'T FORGET TO ADD THIS ACTIVITY TO THE MANIFEST FILE!!!

    private ViewPager viewPager;
    private IntroSliderAdapter adapter;
    private LinearLayout linearLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnNext, btnSkip;
    private SharedPreferencesHelper pref;

    private IntroContract.Presenter mPresenter = new IntroPresenter(this);    // Presenter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get app's preference
        pref = MainApplication.self().getPref();

        if(!pref.isFirstTimeLaunch()) {
            navigateHome();
        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_intro);

        initView();
    }

    private void initView() {
        viewPager = findViewById(R.id.view_pager);
        linearLayout = findViewById(R.id.layoutDots);
        btnNext = findViewById(R.id.btnNext);
        btnSkip = findViewById(R.id.btnSkip);

        //Init dot

        //Init layout
        layouts = new int[]{
            R.layout.layout_slider_one,
            R.layout.layout_slider_two,
            R.layout.layout_slider_three,
            R.layout.layout_slider_four
        };

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        //Init adapter
        adapter = new IntroSliderAdapter(IntroActivity.this, layouts);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
                // changing the next button text 'NEXT' / 'GOT IT'
                if (position == layouts.length - 1) {
                    // last page. make button text to GOT IT
                    btnNext.setText(getString(R.string.start));
                    btnSkip.setVisibility(View.GONE);
                } else {
                    // still pages are left
                    btnNext.setText(getString(R.string.next));
                    btnSkip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnNext.setOnClickListener(view -> {
            // checking for last page if last page home screen will be launched
            int current = getItem(+1);
            if (current < layouts.length) {
                // move to next screen
                viewPager.setCurrentItem(current);
            } else {
                navigateHome();
            }
        });

        btnSkip.setOnClickListener(view -> {
            navigateHome();
        });

    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        linearLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
           linearLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void navigateHome() {
        pref.setFirstTimeLaunch(false);
        navigateActivity(HomeActivity.class);
        finish();
    }

}
