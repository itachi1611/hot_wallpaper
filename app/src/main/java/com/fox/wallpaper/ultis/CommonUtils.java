package com.fox.wallpaper.ultis;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;

import com.fox.wallpaper.R;
import com.google.android.material.textfield.TextInputEditText;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.Element;

public class CommonUtils {

    /**
     * Hide keyboard
     * @param view
     */
    public static void hideKeyBoard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Hide keyboard on blur
     * @param editText
     */
    public static void hideKeyBoardOnBlur(TextInputEditText editText) {
        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus) {
                hideKeyBoard(v);
            }
        });
    }

    /**
     * Get system date
     * @return
     */
    public static String getSystemDate() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        month++;
        String m = (month < 10) ? "0" + month : String.valueOf(month);
        String d = (day < 10) ? "0" + day : String.valueOf(day);
        return year + "-" + m + "-" + d;
    }

    /**
     * Get image url for glide
     * @param s
     * @return
     */
    public static String getImageUrlForGlide(String s) {
        if(s.contains("http") || s.contains("https")){
            return s;
        }
        return "http://" + s.substring(2);
    }

    /**
     *
     * @param s
     * @return
     */
    public static String getProgramUrlForWebView(String s) {
        if(s.contains("http") || s.contains("https")){
            return s;
        }
        return "http://" + s.substring(2);
    }

    /**
     *
     * @param context
     * @param message
     */
    public static void toastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    //FancyToast

    /**
     *
     * @param context
     * @param message
     */
    public static void showToast(Context context, String message) {
        FancyToast.makeText(context, message, FancyToast.LENGTH_SHORT, FancyToast.DEFAULT,true).show();
    }

    /**
     *
     * @param context
     * @param message
     */
    public static void showSuccessToast(Context context, String message) {
        FancyToast.makeText(context, message ,FancyToast.LENGTH_SHORT, FancyToast.SUCCESS,true).show();
    }

    /**
     *
     * @param context
     * @param message
     */
    public static void showInfoToast(Context context, String message) {
        FancyToast.makeText(context, message, FancyToast.LENGTH_SHORT, FancyToast.INFO,true).show();
    }

    /**
     *
     * @param context
     * @param message
     */
    public static void showWarningToast(Context context, String message) {
        FancyToast.makeText(context, message, FancyToast.LENGTH_SHORT, FancyToast.WARNING,true).show();
    }

    /**
     *
     * @param context
     * @param message
     */
    public static void showErrorToast(Context context, String message) {
        FancyToast.makeText(context, message, FancyToast.LENGTH_SHORT, FancyToast.ERROR,true).show();
    }

    /**
     *
     * @param context
     * @param message
     */
    public static void showConfusingToast(Context context, String message) {
        FancyToast.makeText(context, message, FancyToast.LENGTH_SHORT, FancyToast.CONFUSING,true).show();
    }

    /**
     *
     * @param context
     * @param message
     * @param duration
     * @param type
     * @param icon
     */
    public static void showCustomToastWithParam(Context context, String message, int duration, int type, boolean icon) {
        FancyToast.makeText(context, message, duration, type, icon).show();
    }

    /**
     *
     * @param context
     * @param message
     * @param duration
     * @param type
     * @param image
     * @param icon
     */
    public static void showCustomImageToastWithParam(Context context, String message, int duration, int type, int image, boolean icon) {
        FancyToast.makeText(context, message, duration, type, image, icon).show();
    }

    /**
     *
     * @param context
     * @param message
     * @param duration
     * @param type
     * @param image
     */
    public static void showCustomToastWithoutIcon(Context context, String message, int duration, int type, int image) {
        FancyToast.makeText(context, message, duration, type, image, false);
    }

    /**
     *
     * @param mContext
     * @return
     */
    public static Element getCopyRightsElement(Context mContext) {
        Element copyRightsElement = new Element();
        final String copyrights = String.format(mContext.getString(R.string.copy_right), Calendar.getInstance().get(Calendar.YEAR));
        copyRightsElement.setTitle(copyrights);
        copyRightsElement.setIconDrawable(R.drawable.ic_copy_right);
        copyRightsElement.setIconTint(mehdi.sakout.aboutpage.R.color.about_item_icon_color);
        copyRightsElement.setIconNightTint(android.R.color.white);
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(v -> Toast.makeText(mContext, copyrights, Toast.LENGTH_SHORT).show());
        return copyRightsElement;
    }

    /**
     *
     * @param mTitle
     * @param mIconTint
     * @param mIconDrawable
     * @param mValue
     * @param mGravity
     * @param listener
     * @return
     */
    public static Element createAboutElement(String mTitle, Integer mIconTint, Integer mIconDrawable, String mValue, Integer mGravity, View.OnClickListener listener) {
        Element element = new Element();
        element.setTitle(mTitle);
        element.setIconTint(mIconTint);
        element.setIconDrawable(mIconDrawable);
        element.setValue(mValue);
        element.setGravity(mGravity);
        element.setOnClickListener(listener);
        return element;
    }

    /**
     *
     * @param currentSetting
     */
    public static void simulateDayNight(int currentSetting, Context mContext) {
        final int DAY = 0;
        final int NIGHT = 1;
        final int FOLLOW_SYSTEM = 3;

        int currentNightMode = mContext.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (currentSetting == DAY && currentNightMode != Configuration.UI_MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if (currentSetting == NIGHT && currentNightMode != Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else if (currentSetting == FOLLOW_SYSTEM) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }
    }

}
