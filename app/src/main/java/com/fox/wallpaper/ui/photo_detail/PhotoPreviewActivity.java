package com.fox.wallpaper.ui.photo_detail;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.share.Share;
import com.facebook.share.ShareApi;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.fox.wallpaper.R;
import com.fox.wallpaper.bases.BaseActivity;
import com.fox.wallpaper.models.Photo;
import com.fox.wallpaper.models.PhotoSearchItem;
import com.fox.wallpaper.ultis.CommonUtils;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.fox.wallpaper.ultis.Constants.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE;

public class PhotoPreviewActivity extends BaseActivity implements PhotoPreviewContract.View { //TODO: DON'T FORGET TO ADD THIS ACTIVITY TO THE MANIFEST FILE!!!

    @BindView(R.id.imageView_widget)
    ImageView imageView_widget;

    @BindView(R.id.ibShare)
    ImageButton ibShare;

    @BindView(R.id.multiple_actions)
    FloatingActionsMenu multiple_actions;

    private ShareDialog shareDialog;

    private Photo photo1;
    private PhotoSearchItem photo2;
    private static String[] link = new String[10];
    private long downloadId;
    private Unbinder unbinder;
    private String status;

    private PhotoPreviewContract.Presenter mPresenter = new PhotoPreviewPresenter(this);    // Presenter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_preview_photo);  //TODO: create the layout and add it here
        initView();

        registerReceiver(onDownloadComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        Intent intent = getIntent();
        status = intent.getStringExtra("status");
        if(intent != null) {
            if(status.equals("fav")) {
                photo1 = (Photo) intent.getSerializableExtra("photo");
            } else if(status.equals("search")) {
                photo2 = (PhotoSearchItem) intent.getSerializableExtra("photo");
            }

        }

        if(photo1 != null) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.ic_no_image);

            Glide.with(PhotoPreviewActivity.this)
                    .load((photo1.getUrlO() != null) ? photo1.getUrlO().trim() : photo1.getUrlL().trim())
                    .error(R.drawable.ic_no_image)
                    .apply(requestOptions)
                    .transition(new DrawableTransitionOptions().crossFade())
                    .skipMemoryCache(false)
                    .into(imageView_widget);

            imageView_widget.setOnTouchListener(new ImageMatrixTouchHandler(this));
            //Add button download <m,z,c,l,o>
            getPhotoUrl();
            initDownloadButton(link);

            ibShare.setOnClickListener(view -> {
                //TODO
                //Share image and content to facebook
                CommonUtils.showInfoToast(PhotoPreviewActivity.this, "Share function is under development !");
            });
        } else if (photo2 != null) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.ic_no_image);

            Glide.with(PhotoPreviewActivity.this)
                    .load((photo2.getUrlO() != null) ? photo2.getUrlO().trim() : photo2.getUrlL().trim())
                    .error(R.drawable.ic_no_image)
                    .apply(requestOptions)
                    .transition(new DrawableTransitionOptions().crossFade())
                    .skipMemoryCache(false)
                    .into(imageView_widget);

            imageView_widget.setOnTouchListener(new ImageMatrixTouchHandler(this));
            //Add button download <m,z,c,l,o>
            getPhotoUrl();
            initDownloadButton(link);

            ibShare.setOnClickListener(view -> {
                //TODO
                //Share image and content to facebook
                CommonUtils.showInfoToast(PhotoPreviewActivity.this, "Share function is under development !");
            });
        }


    }

    private void initView() {
        unbinder = ButterKnife.bind(this);
        shareDialog = new ShareDialog(this);
    }

    private BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Fetching the download id received with the broadcast
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            //Checking if the received broadcast is for our enqueued download by matching download id
            if (downloadId == id) {
                CommonUtils.showSuccessToast(PhotoPreviewActivity.this, "Download Completed");
            }
        }
    };

    private void getPhotoUrl() {
        if(photo1 != null) {
            link[0] = photo1.getUrlO();
            link[1] = photo1.getUrlL();
            link[2] = photo1.getUrlC();
            link[3] = photo1.getUrlZ();
            link[4] = photo1.getUrlM();
        } else if(photo2 != null) {
            link[0] = photo2.getUrlO();
            link[1] = photo2.getUrlL();
            link[2] = photo2.getUrlC();
            link[3] = photo2.getUrlZ();
            link[4] = photo2.getUrlM();
        }

    }

    private void initDownloadButton(final String[] url) {
        for(int i = 0;i < url.length;i++){
            if(url[i] != null){
                FloatingActionButton floatingActionButton = new FloatingActionButton(PhotoPreviewActivity.this);
                switch (i){
                    case 0:
                        if(photo1 != null) {
                            floatingActionButton.setTitle(photo1.getWidthO() + " x " + photo1.getHeightO());
                        } else if(photo2 != null) {
                            floatingActionButton.setTitle(photo2.getWidthO() + " x " + photo2.getHeightO());
                        }
                        break;
                    case 1:
                        if(photo1 != null) {
                            floatingActionButton.setTitle(photo1.getWidthL() + " x " + photo1.getHeightL());
                        } else if(photo2 != null) {
                            floatingActionButton.setTitle(photo2.getWidthL() + " x " + photo2.getHeightL());
                        }
                        break;
                    case 2:
                        if(photo1 != null) {
                            floatingActionButton.setTitle(photo1.getWidthC() + " x " + photo1.getHeightC());
                        } else if(photo2 != null) {
                            floatingActionButton.setTitle(photo2.getWidthC() + " x " + photo2.getHeightC());
                        }
                        break;
                    case 3:
                        if(photo1 != null) {
                            floatingActionButton.setTitle(photo1.getWidthZ() + " x " + photo1.getHeightZ());
                        } else if(photo2 != null) {
                            floatingActionButton.setTitle(photo2.getWidthZ() + " x " + photo2.getHeightZ());
                        }
                        break;
                    case 4:
                        if(photo1 != null) {
                            floatingActionButton.setTitle(photo1.getWidthM() + " x " + photo1.getHeightM());
                        } else if(photo2 != null) {
                            floatingActionButton.setTitle(photo2.getWidthM() + " x " + photo2.getHeightM());
                        }
                        break;
                }
                floatingActionButton.setIcon(R.drawable.ic_download);
                floatingActionButton.setColorNormalResId(R.color.blue_semi_transparent);
                floatingActionButton.setColorPressedResId(R.color.blue_semi_transparent_pressed);
                floatingActionButton.setStrokeVisible(false);
                final int j = i;
                floatingActionButton.setOnClickListener(v -> {
                    try {
                        checkPermission(url[j].trim());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                multiple_actions.addButton(floatingActionButton);
            }
        }
    }

    private void checkPermission(String mUrl) throws IOException {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(PhotoPreviewActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(PhotoPreviewActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                } else {
                    // No explanation needed; request the permission
                    ActivityCompat.requestPermissions(PhotoPreviewActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                }
            } else {
                // Permission has already been granted
                downloadPhotoFromApi(mUrl);
            }
        }else{
            downloadPhotoFromApi(mUrl);
        }
    }

    //Handle permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    //downloadPhotoFromApi(mUrl);
                    CommonUtils.showSuccessToast(PhotoPreviewActivity.this, "Permission granted ... , you can start to download photo now !");
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    CommonUtils.showWarningToast(PhotoPreviewActivity.this, "Permission denied ... !");
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    private void downloadPhotoFromApi(String mUrl) {
        File rootDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Flickr");
        if(!rootDirectory.exists()){
            rootDirectory.mkdir();
        }
        //String fileName = "image" + " " + System.currentTimeMillis();
        String fileName = URLUtil.guessFileName(mUrl, null, MimeTypeMap.getFileExtensionFromUrl(mUrl));
        //File file = new File(rootDirectory, fileName);
        //file.createNewFile();
        //Create download request
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(mUrl.trim()));
        //Allow type of network to download files
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        //request.setRequiresCharging(false);
        //request.setAllowedOverMetered(true);
        request.setTitle("File Download"); //Set title in download notification
        request.setDescription("File is being downloaded..."); ////Set description in download notification
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, fileName); //Get current timestamp as file name
        request.setVisibleInDownloadsUi(true);
        //Get download service and enqueue file
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        downloadId = manager.enqueue(request);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        photo1 = null;
        photo2 = null;
        unregisterReceiver(onDownloadComplete);
    }
}
