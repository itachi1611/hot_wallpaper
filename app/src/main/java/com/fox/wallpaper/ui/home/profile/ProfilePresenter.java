package com.fox.wallpaper.ui.home.profile;

public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View mView;

    public ProfilePresenter(ProfileContract.View mView) {
        this.mView = mView;
    }

}
