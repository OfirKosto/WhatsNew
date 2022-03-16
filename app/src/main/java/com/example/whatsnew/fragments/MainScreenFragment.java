package com.example.whatsnew.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavHostController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsnew.ArticleAdapter;
import com.example.whatsnew.Category;
import com.example.whatsnew.CategoryAdapter;
import com.example.whatsnew.R;
import com.example.whatsnew.viewmodels.MainScreenViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainScreenFragment extends Fragment {

    private MainScreenViewModel mViewModel;

    private MaterialButton mMyFavoritesBtn;
    private ArrayList<Category> mCategoriesList;
    private RecyclerView mCategoriesListRecyclerView;
    private CategoryAdapter mCategoryAdapter;

    public static MainScreenFragment newInstance() {
        return new MainScreenFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.main_screen_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(MainScreenViewModel.class);

        initUi(viewRoot);
        initObservers();



        return viewRoot;
    }


    private void initUi(View iRootView){
        mMyFavoritesBtn = iRootView.findViewById(R.id.main_screen_fragment_my_favorites_button);
        mMyFavoritesBtn.setOnClickListener(v -> NavHostFragment.findNavController(this).navigate(R.id.action_mainScreenFragment_to_favoritesArticlesFragment));

        mCategoryAdapter = new CategoryAdapter(mCategoriesList);
        mCategoriesListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mCategoriesListRecyclerView.setHasFixedSize(true);
        mCategoriesListRecyclerView.setAdapter(mCategoryAdapter);

    }

    private void initObservers() {

    }
}