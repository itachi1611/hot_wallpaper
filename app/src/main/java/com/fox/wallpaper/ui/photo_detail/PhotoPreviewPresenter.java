package com.fox.wallpaper.ui.photo_detail;

public class PhotoPreviewPresenter implements PhotoPreviewContract.Presenter {

    private PhotoPreviewContract.View mView;

    public PhotoPreviewPresenter(PhotoPreviewContract.View mView) {
        this.mView = mView;
    }

}
