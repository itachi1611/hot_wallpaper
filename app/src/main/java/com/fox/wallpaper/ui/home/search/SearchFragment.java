package com.fox.wallpaper.ui.home.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.fox.wallpaper.R;
import com.fox.wallpaper.adapters.FlickrFavImgStaggeredRecyclerViewAdapter;
import com.fox.wallpaper.adapters.FlickrSearchImgStaggeredRecyclerViewAdapter;
import com.fox.wallpaper.bases.BaseFragment;
import com.fox.wallpaper.models.PhotoSearchItem;
import com.fox.wallpaper.ultis.AppLogger;
import com.paulrybitskyi.persistentsearchview.PersistentSearchView;
import com.paulrybitskyi.persistentsearchview.adapters.model.SuggestionItem;
import com.paulrybitskyi.persistentsearchview.listeners.OnSuggestionChangeListener;
import com.paulrybitskyi.persistentsearchview.utils.VoiceRecognitionDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.fox.wallpaper.ultis.Constants.NUM_COLUMNS;

public class SearchFragment extends BaseFragment implements SearchContract.View {

    @BindView(R.id.persistentSearchView)
    PersistentSearchView persistentSearchView;

    @BindView(R.id.rvSearchPhoto)
    RecyclerView rvSearchPhoto;

    private StaggeredGridLayoutManager layoutManager;
    private FlickrSearchImgStaggeredRecyclerViewAdapter adapter;

    private Unbinder unbinder;

    //Declare list photos
    private List<PhotoSearchItem> photos;

    private SearchContract.Presenter mPresenter = new SearchPresenter(this);   // Presenter

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle bundle = new Bundle();
        //bundle.putString("key", value);
        fragment.setArguments(bundle);
        return fragment;
    }

    public SearchFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO: Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
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

        persistentSearchView.setOnLeftBtnClickListener(v1 -> {
        // Handle the left button click
        });

        persistentSearchView.setOnClearInputBtnClickListener(v2 -> {
        // Handle the clear input button click
        });

        // Setting a delegate for the voice recognition input
        persistentSearchView.setVoiceRecognitionDelegate(new VoiceRecognitionDelegate(this));

        persistentSearchView.setOnSearchConfirmedListener((searchView, query) -> {
        // Handle a search confirmation. This is the place where you'd
        // want to perform a search against your data provider.
            onSearchFlickrImageData(query);
        });

        persistentSearchView.setOnSearchQueryChangeListener((searchView, oldQuery, newQuery) -> {
        // Handle a search query change. This is the place where you'd
        // want load new suggestions based on the newQuery parameter.
            //onSearchFlickrImageData(newQuery);
        });

        persistentSearchView.setOnSuggestionChangeListener(new OnSuggestionChangeListener() {
            @Override
            public void onSuggestionPicked(SuggestionItem suggestion) {
            // Handle a suggestion pick event. This is the place where you'd
            // want to perform a search against your data provider.
            }

            @Override
            public void onSuggestionRemoved(SuggestionItem suggestion) {
            // Handle a suggestion remove event. This is the place where
            // you'd want to remove the suggestion from your data provider.
            }
        });

        // Disabling the suggestions since they are unused in
        // the simple implementation
        persistentSearchView.setSuggestionsDisabled(false);

    }

    private void initRecyclerView() {
        layoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, 1);
        rvSearchPhoto.setLayoutManager(layoutManager);
        rvSearchPhoto.setItemAnimator(new DefaultItemAnimator());
        rvSearchPhoto.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvSearchPhoto.setHasFixedSize(true);
    }

    private void onSearchFlickrImageData(String s) {
        onShowLoading();
        mPresenter.onSearch(s);
    }

    private void initView(View v) {
        unbinder = ButterKnife.bind(this, v);
        photos = new ArrayList<>();
    }

    @Override
    public void onResume() {
        super.onResume();
        onSearchFlickrImageData("nature");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void onLoadDataToRecyclerView() {
        if(adapter != null ) {
            adapter = null;
        }
        if(adapter == null) {
            adapter = new FlickrSearchImgStaggeredRecyclerViewAdapter(photos);
            //rvImageGrid.getRecycledViewPool().clear();
            rvSearchPhoto.setAdapter(adapter);
        }
    }

    @Override
    public void onSearchSuccess(List<PhotoSearchItem> mPhotos) {
        onHideLoading();
        photos.clear();
        if(mPhotos != null) {
            photos.addAll(mPhotos);
        }
        if(persistentSearchView.isExpanded()) {
            persistentSearchView.collapse();
            return;
        }
        onLoadDataToRecyclerView();
    }

    @Override
    public void onError(Throwable e) {
        onHideLoading();
        AppLogger.e(e);
    }

}
