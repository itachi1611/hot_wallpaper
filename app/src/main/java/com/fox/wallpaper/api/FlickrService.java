package com.fox.wallpaper.api;

import com.fox.wallpaper.models.FlickrFavorites;
import com.fox.wallpaper.models.PhotoSearch;

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

    /**
     * Search tag from services
     */
    @GET("services/rest/")
    Observable<PhotoSearch> onFetchSearchPhoto (
        @Query("method") String method,
        @Query("api_key") String api_key,
        @Query("user_id") String user_id,
        @Query("tags") String tags,
        @Query("tag_mode") String tag_mode,
        @Query("text") String text,
        @Query("min_upload_date") String min_upload_date,
        @Query("max_upload_date") String max_upload_date,
        @Query("min_taken_date") String min_taken_date,
        @Query("max_taken_date") String max_taken_date,
        @Query("license") String license,
        @Query("sort") String sort,
        @Query("privacy_filter") String privacy_filter,
        @Query("bbox") String bbox,
        @Query("accuracy") String accuracy,
        @Query("safe_search") String safe_search,
        @Query("content_type") String content_type,
        @Query("machine_tags") String machine_tags,
        @Query("machine_tag_mode") String machine_tag_mode,
        @Query("group_id") String group_id,
        @Query("contacts") String contacts,
        @Query("woe_id") String woe_id,
        @Query("place_id") String place_id,
        @Query("media") String media,
        @Query("has_geo") String has_geo,
        @Query("geo_context") String geo_context,
        @Query("lat") String lat,
        @Query("lon") String lon,
        @Query("radius") String radius,
        @Query("radius_units") String radius_units,
        @Query("is_commons") String is_commons,
        @Query("in_gallery") String in_gallery,
        @Query("is_getty") String is_getty,
        @Query("extras") String extras,
        @Query("per_page") String per_page,
        @Query("page") String page,
        @Query("format") String format,
        @Query("nojsoncallback") String is_call_back
    );

}
