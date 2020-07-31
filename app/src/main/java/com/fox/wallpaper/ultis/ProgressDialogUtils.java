package com.fox.wallpaper.ultis;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.fox.wallpaper.R;

public class ProgressDialogUtils {

    public ProgressDialogUtils() {}

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(context.getResources().getString(R.string.progress_dialog_title));
        progressDialog.setMessage(context.getResources().getString(R.string.progress_dialog_message));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        if(progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        }
        progressDialog.setContentView(R.layout.layout_progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        //progressDialog.setMax(100);
        //progressDialog.setProgress(0);
        progressDialog.show();
        return progressDialog;
    }

}
