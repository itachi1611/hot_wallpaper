package com.fox.wallpaper.ultis;

import com.fox.wallpaper.BuildConfig;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Constants {

    public static final int SPLASH_TIME_OUT  = 3000;

    //Database and Preference
    public static final String PREF_NAME = "wallpaper_pref";

    public static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    public static final String IS_LOGIN = "IsLogin";

    public static final int PRIVATE_MODE = 0;

    public static final String DB_NAME = "wallpaper";

    //Retrofit
    public static final String BASE_URL = "https://www.flickr.com/";

    //Flickr
    public static final String FLICKR_API_KEY = BuildConfig.FLICKR_API_KEY;
    public static String FLICKR_USER_ID;
    public static final String FLICKR_NO_JSON_CALLBACK = "1";
    public static final String FLICKR_METHOD = "flickr.favorites.getList";
    public static final String FLICKR_OPTION = "views, media, path_alias, url_sq, url_t, url_s, url_q, url_m, url_n, url_z, url_c, url_l, url_o";
    public static final String FLICKR_PER_PAGE = "30";
    public static final String FLICKR_FORMAT = "json";

    //Api Option
    public static final long READ_TIMEOUT = 60;

    public static final int WRITE_TIMEOUT = 60;

    public static final int CONNECT_TIMEOUT = 60;

    //BaseActivity
    public static final int SWIPE_MIN_DISTANCE = 120;

    public static final int SWIPE_MAX_OFF_PATH = 250;

    public static final int SWIPE_THRESHOLD_VELOCITY = 200;

    public static final int NUM_COLUMNS = 2;

    static {
        try {
            FLICKR_USER_ID = URLDecoder.decode(BuildConfig.FLICKR_USER_ID, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            AppLogger.e(e);
        }
    }

    //Permissions
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 999;

}
