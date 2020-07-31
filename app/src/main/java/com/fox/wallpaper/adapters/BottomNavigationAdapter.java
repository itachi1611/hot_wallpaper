package com.fox.wallpaper.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.fox.wallpaper.ui.home.about.AboutFragment;
import com.fox.wallpaper.ui.home.photo.PhotoFragment;
import com.fox.wallpaper.ui.home.profile.ProfileFragment;
import com.fox.wallpaper.ui.home.search.SearchFragment;


public class BottomNavigationAdapter extends FragmentStatePagerAdapter {

    public BottomNavigationAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public BottomNavigationAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0 :
                fragment = PhotoFragment.newInstance();
                break;
            case 1 :
                fragment = SearchFragment.newInstance();
                break;
            case 2 :
                fragment = ProfileFragment.newInstance();
                break;
            case 3 :
                fragment = AboutFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

}
