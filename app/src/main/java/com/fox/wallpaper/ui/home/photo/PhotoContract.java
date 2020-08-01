package com.fox.wallpaper.ui.home.photo;

import com.fox.wallpaper.models.Photo;

import java.util.List;

public interface PhotoContract {

    interface View {
        void onFetchSuccess(List<Photo> mPhotos);
        void onRefreshSuccess(List<Photo> mPhotos);
        void onLoadMoreSuccess(List<Photo> mPhotos);
        void onError(Throwable e);
    }

    interface Presenter {
        void onFetch(String p);
        void onRefresh(String p);
        void onLoadMore(String p);
    }

}
