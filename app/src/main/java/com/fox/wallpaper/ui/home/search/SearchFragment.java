package com.fox.wallpaper.ui.home.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fox.wallpaper.R;
import com.fox.wallpaper.bases.BaseFragment;
import com.paulrybitskyi.persistentsearchview.PersistentSearchView;
import com.paulrybitskyi.persistentsearchview.adapters.model.SuggestionItem;
import com.paulrybitskyi.persistentsearchview.listeners.OnSearchConfirmedListener;
import com.paulrybitskyi.persistentsearchview.listeners.OnSearchQueryChangeListener;
import com.paulrybitskyi.persistentsearchview.listeners.OnSuggestionChangeListener;
import com.paulrybitskyi.persistentsearchview.utils.VoiceRecognitionDelegate;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SearchFragment extends BaseFragment implements SearchContract.View {

    @BindView(R.id.persistentSearchView)
    PersistentSearchView persistentSearchView;

    private Unbinder unbinder;

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
        });

        persistentSearchView.setOnSearchQueryChangeListener((searchView, oldQuery, newQuery) -> {
        // Handle a search query change. This is the place where you'd
        // want load new suggestions based on the newQuery parameter.
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

    private void initView(View v) {
        unbinder = ButterKnife.bind(this, v);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
