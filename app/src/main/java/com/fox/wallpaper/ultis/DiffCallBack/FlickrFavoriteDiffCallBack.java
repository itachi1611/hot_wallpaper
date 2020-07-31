package com.fox.wallpaper.ultis.DiffCallBack;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.fox.wallpaper.models.Photo;

import java.util.List;

public class FlickrFavoriteDiffCallBack extends DiffUtil.Callback {

    List<Photo> newList;
    List<Photo> oldList;

    public FlickrFavoriteDiffCallBack(List<Photo> newList, List<Photo> oldList) {
        this.newList = newList;
        this.oldList = oldList;
    }

    @Override
    public int getOldListSize() {
        return (oldList != null) ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return (newList != null) ? newList.size() :0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return newList.get(newItemPosition).getId() == oldList.get(oldItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        int result = newList.get(newItemPosition).compareTo(oldList.get(oldItemPosition));
        return result == 0;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        Photo newPhoto = newList.get(newItemPosition);
        Photo oldPhoto = oldList.get(oldItemPosition);
        Bundle diff = new Bundle();
        if(!newPhoto.getTitle().equals(oldPhoto.getTitle())) {
            diff.putString("id", newPhoto.getId());
        }
        if(diff.size() == 0) {
            return null;
        }
        return diff;
    }

}
