package com.fox.wallpaper.ui.home.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fox.wallpaper.R;
import com.fox.wallpaper.bases.BaseFragment;
import com.fox.wallpaper.ultis.CommonUtils;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

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
        View aboutPage = new AboutPage(getContext())
                .isRTL(false)
                .setDescription(getString(R.string.about_description))
                .setImage(R.mipmap.ic_launcher_round)
                .addItem(new Element().setTitle("Version 1.0 alpha 1"))
                .addGroup("Connect with us")
                .addEmail("shinrojp@gmail.com")
                .addWebsite("https://shinrojp.github.io/shinrojp-android/")
                .addFacebook("https://www.facebook.com/shinrojp")
                .addTwitter("https://twitter.com/shinrojp1")
                .addYoutube("https://www.youtube.com/channel/UC1ld_J1c9SPVHv8jvFiUCeA?view_as=subscriber")
                .addPlayStore("")
                .addInstagram("https://www.instagram.com/shinrojp/")
                .addGitHub("https://github.com/shinrojp")
                .addItem(CommonUtils.getCopyRightsElement(getContext()))
                .create();
        return aboutPage;
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

    private void initView() {}

}
