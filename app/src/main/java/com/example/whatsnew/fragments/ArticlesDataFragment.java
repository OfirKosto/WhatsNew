package com.example.whatsnew.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whatsnew.Article;
import com.example.whatsnew.ArticleAdapter;
import com.example.whatsnew.CategoryAdapter;
import com.example.whatsnew.R;
import com.example.whatsnew.viewmodels.ArticlesDataViewModel;
import com.example.whatsnew.viewmodels.MainScreenViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ArticlesDataFragment extends Fragment {

    private ArticlesDataViewModel mViewModel;

    private MaterialButton mBackBtn;
    private ArrayList<Article> mArticlesList;
    private RecyclerView mArticlesListRecyclerView;
    private ArticleAdapter mArticleAdapter;

    public static ArticlesDataFragment newInstance() {
        return new ArticlesDataFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.articles_data_fragment, container, false);

        mViewModel = new ViewModelProvider(this).get(ArticlesDataViewModel.class);
        mArticlesList = new ArrayList<>();

        initUi(viewRoot);
        initObservers();

        getArticles(viewRoot);

        return viewRoot;
    }

    private void initUi(View iRootView) {

        mBackBtn = iRootView.findViewById(R.id.articles_data_fragment_back_btn);
        mBackBtn.setOnClickListener(v -> NavHostFragment.findNavController(this).popBackStack());

        mArticleAdapter = new ArticleAdapter(mArticlesList);
        mArticlesListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mArticlesListRecyclerView.setHasFixedSize(true);
        mArticlesListRecyclerView.setAdapter(mArticleAdapter);
    }

    private void initObservers() {
        mViewModel.getArticlesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Article>>() {
            @Override
            public void onChanged(ArrayList<Article> articles) {
                mArticlesList = articles;
                mArticleAdapter = new ArticleAdapter(mArticlesList);
                mArticlesListRecyclerView.setAdapter(mArticleAdapter);
            }
        });

        mViewModel.getErrorLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Snackbar.make(getView(), s, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void getArticles(View iRootView) {
        //TODO READ FROM INTENT ARTICLE CATEGORY
//        mViewModel.getArticlesByCategory();
    }

}