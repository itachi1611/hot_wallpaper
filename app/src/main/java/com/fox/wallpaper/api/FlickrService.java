package com.fox.wallpaper.api;

import com.fox.wallpaper.models.FlickrFavorites;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrService {

    /**
     * Get data from services
    */
    @GET("services/rest/")
    Observable<FlickrFavorites> onFetchFlickrFavoritesImageList(
            @Query("method") String method,
            @Query("api_key") String api_key,
            @Query("user_id") String user_id,
            @Query("min_fave_date") String min_fave_date,
            @Query("max_fave_date") String max_fave_date,
            @Query("extras") String option,
            @Query("per_page") String per_page,
            @Query("page") String page,
            @Query("format") String format,
            @Query("nojsoncallback") String is_call_back
    );

}
