package com.fox.wallpaper.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.fox.wallpaper.R;
import com.fox.wallpaper.holders.FlickrSearchImgViewHolder;
import com.fox.wallpaper.models.PhotoSearchItem;

import java.util.List;

public class FlickrSearchImgStaggeredRecyclerViewAdapter extends RecyclerView.Adapter<FlickrSearchImgViewHolder> {

    private List<PhotoSearchItem> mImageLists;

    //Image ratio
    private ConstraintSet constraintSet = new ConstraintSet();

    public ConstraintSet getConstraintSet() {
        return constraintSet;
    }

    public FlickrSearchImgStaggeredRecyclerViewAdapter(List<PhotoSearchItem> mImageLists) {
        this.mImageLists = mImageLists;
    }

    @NonNull
    @Override
    public FlickrSearchImgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FlickrSearchImgViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flickr_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FlickrSearchImgViewHolder holder, int position) {
        PhotoSearchItem photo = mImageLists.get(position);
        holder.onBindData(photo);
        setDiffRatio(photo, holder.constrainContainer, holder.imageViewWidget);
    }

    @Override
    public int getItemCount() {
        return (mImageLists != null) ? mImageLists.size() : 0;
    }

    public List<PhotoSearchItem> getFlickrFavoritesImageList() {
        return mImageLists;
    }

    private void setDiffRatio(PhotoSearchItem photo, ConstraintLayout constraintLayout, ImageView imageView) {
        int width, height;
        if(photo.getUrlO() != null) {
            width = photo.getWidthO();
            height = photo.getHeightO();
        } else {
            width = photo.getWidthL();
            height = photo.getHeightL();
        }
        String ratio = String.format("%d:%d", width, height);
        constraintSet.clone(constraintLayout);
        constraintSet.setDimensionRatio(imageView.getId(),ratio);
        constraintSet.applyTo(constraintLayout);
    }

    public void setFlickrFavoritesImageList(List<PhotoSearchItem> photos) {
        this.mImageLists.clear();
        this.mImageLists = photos;
        notifyDataSetChanged();
    }

}
