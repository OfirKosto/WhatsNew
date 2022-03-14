package com.example.whatsnew.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavHostController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.fragment.NavHostFragment;

import com.example.whatsnew.R;
import com.example.whatsnew.viewmodels.MainScreenViewModel;
import com.google.android.material.button.MaterialButton;

public class MainScreenFragment extends Fragment {

    private MainScreenViewModel mViewModel;

    private MaterialButton mMyFavoritesBtn;

    public static MainScreenFragment newInstance() {
        return new MainScreenFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.main_screen_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(MainScreenViewModel.class);

        initUi(viewRoot);




        return viewRoot;
    }


    private void initUi(View iRootView){
        mMyFavoritesBtn = iRootView.findViewById(R.id.main_screen_fragment_my_favorites_button);
        mMyFavoritesBtn.setOnClickListener(v -> NavHostFragment.findNavController(this).navigate(R.id.action_mainScreenFragment_to_favoritesArticlesFragment));

    }
}