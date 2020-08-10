package com.fox.wallpaper.ui.home.search;

import com.fox.wallpaper.models.PhotoSearchItem;

import java.util.List;

public interface SearchContract {

    interface View {
        void onSearchSuccess(List<PhotoSearchItem> mPhotos);
        void onError(Throwable e);
    }

    interface Presenter {
        void onSearch(String p);
    }

}
