package com.fox.wallpaper.ui.home.photo;

import android.util.Log;

import com.fox.wallpaper.api.ApiUtil;
import com.fox.wallpaper.models.FlickrFavorites;
import com.fox.wallpaper.models.Photo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.fox.wallpaper.ultis.Constants.FLICKR_API_KEY;
import static com.fox.wallpaper.ultis.Constants.FLICKR_FORMAT;
import static com.fox.wallpaper.ultis.Constants.FLICKR_METHOD;
import static com.fox.wallpaper.ultis.Constants.FLICKR_NO_JSON_CALLBACK;
import static com.fox.wallpaper.ultis.Constants.FLICKR_OPTION;
import static com.fox.wallpaper.ultis.Constants.FLICKR_PER_PAGE;
import static com.fox.wallpaper.ultis.Constants.FLICKR_USER_ID;

public class PhotoPresenter implements PhotoContract.Presenter {

    private PhotoContract.View mView;

    public PhotoPresenter(PhotoContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void onFetchData(String p) {
        ApiUtil.getFavoritesImageList(false, null)
                .onFetchFlickrFavoritesImageList(FLICKR_METHOD, FLICKR_API_KEY, FLICKR_USER_ID, null, null, FLICKR_OPTION, FLICKR_PER_PAGE, p, FLICKR_FORMAT, FLICKR_NO_JSON_CALLBACK)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<FlickrFavorites>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FlickrFavorites flickrFavorites) {
                        Log.d("NamNT", String.valueOf(flickrFavorites.getPhotos().getPhoto().size()));
                        mView.onFetchSuccess(onConvertData(flickrFavorites));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onFetchError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private List<Photo> onConvertData(FlickrFavorites favorites) {
        List<Photo> photos = new ArrayList<>();
        if(favorites != null) {
            photos.addAll(favorites.getPhotos().getPhoto());
            return photos;
        }
        return null;
    }

}
