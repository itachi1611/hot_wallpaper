package com.fox.wallpaper.bases;

import androidx.multidex.MultiDexApplication;

import com.fox.wallpaper.api.LenientTypeAdapterFactory;
import com.fox.wallpaper.helpers.SharedPreferencesHelper;
import com.fox.wallpaper.ultis.AppLogger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainApplication extends MultiDexApplication {

    private Gson mGson;

    private static MainApplication mSelf;

    public static MainApplication self() {
        return mSelf;
    }

    public static SharedPreferencesHelper pref;

    public Gson getGson() {
        return mGson;
    }

    public SharedPreferencesHelper getPref() {
        return pref;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSelf = this;
        mGson = new GsonBuilder().registerTypeAdapterFactory(new LenientTypeAdapterFactory()).create();
        pref = SharedPreferencesHelper.getInstance(getApplicationContext());
        AppLogger.init();
    }

}
