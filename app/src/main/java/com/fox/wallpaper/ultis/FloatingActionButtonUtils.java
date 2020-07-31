package com.fox.wallpaper.ultis;

import android.content.Context;

import com.fox.wallpaper.models.Photo;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FloatingActionButtonUtils {

    private static String[] url = new String[5];

    public static void initFloatingActionButton(Photo photo, FloatingActionsMenu floatingActionsMenu, Context context) {

        //Set link download for button
        url[0] = photo.getUrlO();
        url[1] = photo.getUrlL();
        url[2] = photo.getUrlC();
        url[3] = photo.getUrlZ();
        url[4] = photo.getUrlM();

        //Init size for each type of download
        String resO = getResolutionSize(photo.getWidthO(), photo.getHeightO());
        String resL = getResolutionSize(photo.getWidthL(), photo.getHeightL());
        String resC = getResolutionSize(photo.getWidthC(), photo.getHeightC());
        String resZ = getResolutionSize(photo.getWidthZ(), photo.getHeightZ());
        String resM = getResolutionSize(photo.getWidthM(), photo.getHeightM());

        for(int i = 0; i < url.length; i++) {
            if(!url[i].isEmpty() && url[i] != null) {
                FloatingActionButton actionButton = new FloatingActionButton(context);
                switch (i) {
                    case 0:
                        //actionButton.setTitle(resO);
                        break;
                }
            }
        }

    }

    private static String getResolutionSize(String w, String h) {
        return w + " x " + h;
    }

}
