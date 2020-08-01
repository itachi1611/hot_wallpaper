package com.fox.wallpaper.ui.home;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.fox.wallpaper.R;
import com.fox.wallpaper.adapters.BottomNavigationAdapter;
import com.fox.wallpaper.bases.BaseActivity;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeActivity extends BaseActivity implements HomeContract.View { //TODO: DON'T FORGET TO ADD THIS ACTIVITY TO THE MANIFEST FILE!!!

    @BindView(R.id.bottom_navigation_view_linear)
    BubbleNavigationLinearView nav;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private Unbinder unbinder;

    private BottomNavigationAdapter adapter;

    private HomeContract.Presenter mPresenter = new HomePresenter(this);    // Presenter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isOnline();
        setContentView(R.layout.activity_home);  //TODO: create the layout and add it here

        initView();

        initBottomNavigation();
    }

    private void initBottomNavigation() {
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                nav.setCurrentActiveItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        nav.setNavigationChangeListener((view, position) -> viewPager.setCurrentItem(position, true));
    }

    private void initView() {
        unbinder = ButterKnife.bind(this);
        adapter = new BottomNavigationAdapter(getSupportFragmentManager());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onBackPressed() {
        if(viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            //TODO
            //Make the dialog confirm to exit
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

}
