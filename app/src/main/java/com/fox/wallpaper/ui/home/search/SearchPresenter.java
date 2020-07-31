package com.fox.wallpaper.ui.home.search;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View mView;

    public SearchPresenter(SearchContract.View mView) {
        this.mView = mView;
    }

}
