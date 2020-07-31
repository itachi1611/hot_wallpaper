package com.fox.wallpaper.ui.home.photo;

import com.fox.wallpaper.models.Photo;

import java.util.List;

public interface PhotoContract {

    interface View {
        void onFetchSuccess(List<Photo> mPhotos);
        void onFetchError(Throwable e);
    }

    interface Presenter {
        void onFetchData(String p);
    }

}
