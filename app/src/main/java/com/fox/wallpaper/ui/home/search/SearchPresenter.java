package com.fox.wallpaper.ui.home.search;

import android.util.Log;

import com.fox.wallpaper.api.ApiUtil;
import com.fox.wallpaper.models.PhotoSearch;
import com.fox.wallpaper.models.PhotoSearchItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.fox.wallpaper.ultis.Constants.FLICKR_API_KEY;
import static com.fox.wallpaper.ultis.Constants.FLICKR_FORMAT;
import static com.fox.wallpaper.ultis.Constants.FLICKR_NO_JSON_CALLBACK;
import static com.fox.wallpaper.ultis.Constants.FLICKR_SEARCH_METHOD;
import static com.fox.wallpaper.ultis.Constants.FLICKR_SEARCH_OPTION;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View mView;

    public SearchPresenter(SearchContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void onSearch(String p) {
        ApiUtil.getSearchImageList(false, null)
                .onFetchSearchPhoto(
                        FLICKR_SEARCH_METHOD,
                        FLICKR_API_KEY,
                        null,
                        p,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        String.valueOf(5),
                        null,
                        String.valueOf(1),
                        String.valueOf(2),
                        String.valueOf(7),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        String.valueOf(0),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        FLICKR_SEARCH_OPTION,
                        null,
                        null,
                        FLICKR_FORMAT,
                        FLICKR_NO_JSON_CALLBACK
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<PhotoSearch>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PhotoSearch PhotoSearch) {
                        Log.d("NamNT", String.valueOf(PhotoSearch.getPhotos().getPhoto().size()));
                        mView.onSearchSuccess(onConvertData(PhotoSearch));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private List<PhotoSearchItem> onConvertData(PhotoSearch photoSearch) {
        List<PhotoSearchItem> photos = new ArrayList<>();
        if(photoSearch != null) {
            photos.addAll(photoSearch.getPhotos().getPhoto());
            return photos;
        }
        return null;
    }

}
