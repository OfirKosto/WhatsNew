package com.example.whatsnew.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whatsnew.R;
import com.example.whatsnew.viewmodels.FavoritesArticlesViewModel;
import com.example.whatsnew.viewmodels.MainScreenViewModel;
import com.google.android.material.button.MaterialButton;

public class FavoritesArticlesFragment extends Fragment {

    public static FavoritesArticlesFragment newInstance() {
        return new FavoritesArticlesFragment();
    }

    private FavoritesArticlesViewModel mViewModel;

    private MaterialButton mBackBtn;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.favorites_articles_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(FavoritesArticlesViewModel.class);

        initUi(viewRoot);

        return viewRoot;
    }

    private void initUi(View iRootView) {

        mBackBtn = iRootView.findViewById(R.id.favorites_articles_fragment_back_btn);
        mBackBtn.setOnClickListener(v -> NavHostFragment.findNavController(this).popBackStack());
    }

}