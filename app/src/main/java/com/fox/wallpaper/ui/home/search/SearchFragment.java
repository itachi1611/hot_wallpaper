package com.fox.wallpaper.ui.home.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fox.wallpaper.R;
import com.fox.wallpaper.bases.BaseFragment;

public class SearchFragment extends BaseFragment implements SearchContract.View {

    private View v;

    private SearchContract.Presenter mPresenter = new SearchPresenter(this);   // Presenter

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle bundle = new Bundle();
        //bundle.putString("key", value);
        fragment.setArguments(bundle);
        return fragment;
    }

    public SearchFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO: Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
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
