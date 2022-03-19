package com.example.whatsnew.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsnew.model.Category;
import com.example.whatsnew.view.Adapters.CategoryAdapter;
import com.example.whatsnew.R;
import com.example.whatsnew.view.viewmodels.MainScreenViewModel;
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

        mCategoriesList = new ArrayList<>();

        initUi(viewRoot);
        initObservers();

        mViewModel.extractCategoriesList();

        return viewRoot;
    }


    private void initUi(View iRootView){
        mMyFavoritesBtn = iRootView.findViewById(R.id.main_screen_fragment_my_favorites_button);
        mMyFavoritesBtn.setOnClickListener(v -> NavHostFragment.findNavController(this).navigate(R.id.action_mainScreenFragment_to_favoritesArticlesFragment));

        mCategoriesListRecyclerView = iRootView.findViewById(R.id.main_screen_fragment_categories_rv);
        mCategoryAdapter = new CategoryAdapter(mCategoriesList);
        mCategoriesListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mCategoriesListRecyclerView.setHasFixedSize(true);
        mCategoriesListRecyclerView.setAdapter(mCategoryAdapter);
        mCategoryAdapter.setListener(new CategoryAdapter.CategoryListener() {
            @Override
            public void onCategoryClicked(Category iCategory) {
                Bundle bundle = new Bundle();
                bundle.putString("category", iCategory.getName());
                NavHostFragment.findNavController(MainScreenFragment.this).navigate(R.id.action_mainScreenFragment_to_articlesDataFragment, bundle);
            }
        });

    }

    private void initObservers() {
        mViewModel.getCategoriesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Category>>() {
            @Override
            public void onChanged(ArrayList<Category> categories) {
                mCategoriesList.clear();
                mCategoriesList = categories;
                mCategoryAdapter.setCategories(mCategoriesList);
                mCategoryAdapter.notifyDataSetChanged();
            }
        });
    }
}