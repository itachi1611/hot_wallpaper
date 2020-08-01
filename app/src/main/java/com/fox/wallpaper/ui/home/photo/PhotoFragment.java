package com.fox.wallpaper.ui.home.photo;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fox.wallpaper.R;
import com.fox.wallpaper.adapters.FlickrFavImgStaggeredRecyclerViewAdapter;
import com.fox.wallpaper.bases.BaseFragment;
import com.fox.wallpaper.models.Photo;
import com.fox.wallpaper.ultis.AppLogger;
import com.fox.wallpaper.ultis.RecyclerViewUtils.EndlessRecyclerViewScrollListener;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.fox.wallpaper.ultis.Constants.NUM_COLUMNS;

public class PhotoFragment extends BaseFragment implements PhotoContract.View {

    @BindView(R.id.cgFavType)
    ChipGroup cgFavType;

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    @BindView(R.id.rvImageGrid)
    RecyclerView rvImageGrid;

    private Unbinder unbinder;
    private StaggeredGridLayoutManager layoutManager;
    private FlickrFavImgStaggeredRecyclerViewAdapter adapter;

    //Declare and initial value for first page
    private int page = 1;

    //Declare list photos
    private List<Photo> photos;

    private PhotoContract.Presenter mPresenter = new PhotoPresenter(this);   // Presenter

    public static PhotoFragment newInstance() {
        PhotoFragment fragment = new PhotoFragment();
        Bundle bundle = new Bundle();
        //bundle.putString("key", value);
        fragment.setArguments(bundle);
        return fragment;
    }

    public PhotoFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO: Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        //Init RecyclerView
        initRecyclerView();

        //Handle swipe action to refresh data
        onSwipeRefreshData();

        //Handle Load more event
        onScrollToLoadMore();
    }

    private void initView(View v) {
        unbinder = ButterKnife.bind(this, v);
        photos = new ArrayList<>();
    }

    private void initRecyclerView() {
        layoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, 1);
        rvImageGrid.setLayoutManager(layoutManager);
        rvImageGrid.setItemAnimator(new DefaultItemAnimator());
        rvImageGrid.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvImageGrid.setHasFixedSize(true);
    }

    private void onSwipeRefreshData() {
        swipeContainer.setOnRefreshListener(() -> {
            page = 1;
            onRefreshFlickrImageData(page);
            handleSwipeRefreshAction();
            //onScrollToLoadMore();
        });
    }

    private void onFetchFlickrImageData(int p) {
        mPresenter.onFetch(String.valueOf(p));
    }

    private void onRefreshFlickrImageData(int p) {
        mPresenter.onRefresh(String.valueOf(p));
    }

    private void onLoadMoreFlickrImageData(int p) {
        mPresenter.onLoadMore(String.valueOf(p));
    }

    private void onScrollToLoadMore() {
        rvImageGrid.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                page++;
                onShowLoading();
                onLoadMoreFlickrImageData(page++);
            }
        });
    }

    private void onLoadDataToRecyclerView() {
        if(adapter != null ) {
            adapter = null;
        }
        if(adapter == null) {
            adapter = new FlickrFavImgStaggeredRecyclerViewAdapter(photos);
            //rvImageGrid.getRecycledViewPool().clear();
            rvImageGrid.setAdapter(adapter);
        }
    }

    private void handleSwipeRefreshAction() {
        new Handler().postDelayed(() -> {
            swipeContainer.setRefreshing(false);
        }, 150);
    }

    @Override
    public void onResume() {
        super.onResume();
        //Fetch image data from flickr service
        onFetchFlickrImageData(page);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onFetchSuccess(List<Photo> mPhotos) {
        onHideLoading();
        photos.clear();
        if(mPhotos != null) {
            photos.addAll(mPhotos);
        }
        onLoadDataToRecyclerView();
    }

    @Override
    public void onRefreshSuccess(List<Photo> mPhotos) {
        onHideLoading();
        photos.clear();
        if(mPhotos != null) {
            photos.addAll(mPhotos);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMoreSuccess(List<Photo> mPhotos) {
        onHideLoading();
        if(mPhotos != null) {
            photos.addAll(mPhotos);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(Throwable e) {
        onHideLoading();
        AppLogger.e(e);
    }

}
