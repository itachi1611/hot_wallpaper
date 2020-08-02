package com.fox.wallpaper.ui.home.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fox.wallpaper.R;
import com.fox.wallpaper.bases.BaseFragment;
import com.fox.wallpaper.bases.MainApplication;
import com.fox.wallpaper.helpers.SharedPreferencesHelper;
import com.fox.wallpaper.ui.customs.AuthBottomDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProfileFragment extends BaseFragment implements ProfileContract.View, View.OnClickListener {

    @BindView(R.id.lnNoLogin)
    LinearLayout lnNoLogin;

    @BindView(R.id.btnLogin)
    Button btnLogin;

    private Unbinder unbinder;
    private SharedPreferencesHelper pref;

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
        initView(view);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!isLogin()) {
            lnNoLogin.setVisibility(View.VISIBLE);
        } else {
            lnNoLogin.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void initView(View v) {
        unbinder = ButterKnife.bind(this, v);
        //Get app's preference
        pref = MainApplication.self().getPref();
    }

    private boolean isLogin() {
        boolean isLogin;
        if(pref.isLogin()) {
            isLogin = true;
        } else {
            isLogin = false;
        }
        return isLogin;
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnLogin) {
            onShowLoginFragment();
        }
    }

    private void onShowLoginFragment() {
        AuthBottomDialogFragment authFragment = AuthBottomDialogFragment.newInstance();
        authFragment.show(getChildFragmentManager(), AuthBottomDialogFragment.TAG);
    }

}
