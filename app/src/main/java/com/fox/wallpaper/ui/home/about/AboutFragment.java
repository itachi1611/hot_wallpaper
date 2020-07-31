package com.fox.wallpaper.ui.home.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fox.wallpaper.R;
import com.fox.wallpaper.bases.BaseFragment;

public class AboutFragment extends BaseFragment implements AboutContract.View {

    private AboutContract.Presenter mPresenter = new AboutPresenter(this);   // Presenter

    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();
        Bundle bundle = new Bundle();
        //bundle.putString("key", value);
        fragment.setArguments(bundle);
        return fragment;
    }

    public AboutFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO: Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
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
