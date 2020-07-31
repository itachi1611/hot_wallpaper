package com.fox.wallpaper.ui.home.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fox.wallpaper.R;
import com.fox.wallpaper.bases.BaseFragment;

public class ProfileFragment extends BaseFragment implements ProfileContract.View {

    private View v;

    private ProfileContract.Presenter mPresenter = new ProfilePresenter(this);   // Presenter

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        //bundle.putString("key", value);
        fragment.setArguments(bundle);
        return fragment;
    }

    public ProfileFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO: Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {

    }

}
