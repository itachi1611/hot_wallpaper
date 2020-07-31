package com.fox.wallpaper.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.fox.wallpaper.R;
import com.fox.wallpaper.holders.FlickrFavImgViewHolder;
import com.fox.wallpaper.models.Photo;

import java.util.List;

public class FlickrFavImgStaggeredRecyclerViewAdapter extends RecyclerView.Adapter<FlickrFavImgViewHolder> {

    private List<Photo> mImageLists;

    //Image ratio
    private ConstraintSet constraintSet = new ConstraintSet();

    public ConstraintSet getConstraintSet() {
        return constraintSet;
    }

    public FlickrFavImgStaggeredRecyclerViewAdapter(List<Photo> mImageLists) {
        this.mImageLists = mImageLists;
    }

    @NonNull
    @Override
    public FlickrFavImgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FlickrFavImgViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flickr_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FlickrFavImgViewHolder holder, int position) {
        Photo photo = mImageLists.get(position);
        holder.onBindData(photo);
        setDiffRatio(photo, holder.constrainContainer, holder.imageViewWidget);
    }

    @Override
    public int getItemCount() {
        return (mImageLists != null) ? mImageLists.size() : 0;
    }

    public List<Photo> getFlickrFavoritesImageList() {
        return mImageLists;
    }

    private void setDiffRatio(Photo photo, ConstraintLayout constraintLayout, ImageView imageView) {
        int width, height;
        if(photo.getUrlO() != null) {
            width = Integer.parseInt(photo.getWidthO());
            height = Integer.parseInt(photo.getHeightO());
        } else {
            width = Integer.parseInt(photo.getWidthL());
            height = Integer.parseInt(photo.getHeightL());
        }
        String ratio = String.format("%d:%d", width, height);
        constraintSet.clone(constraintLayout);
        constraintSet.setDimensionRatio(imageView.getId(),ratio);
        constraintSet.applyTo(constraintLayout);
    }

    public void setFlickrFavoritesImageList(List<Photo> photos) {
        this.mImageLists.clear();
        this.mImageLists = photos;
        notifyDataSetChanged();
    }

}
