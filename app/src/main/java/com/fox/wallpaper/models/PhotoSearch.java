package com.fox.wallpaper.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoSearch {

    @SerializedName("photos")
    @Expose
    private PhotosSearchItem photos;
    @SerializedName("stat")
    @Expose
    private String stat;

    public PhotosSearchItem getPhotos() {
        return photos;
    }

    public void setPhotos(PhotosSearchItem photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

}
