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
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.whatsnew.Adapters.ArticleAdapter;
import com.example.whatsnew.Article;
import com.example.whatsnew.R;
import com.example.whatsnew.viewmodels.FavoritesArticlesViewModel;
import com.example.whatsnew.viewmodels.MainScreenViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class FavoritesArticlesFragment extends Fragment {

    public static FavoritesArticlesFragment newInstance() {
        return new FavoritesArticlesFragment();
    }

    private FavoritesArticlesViewModel mViewModel;

    private ArrayList<Article> mFavoritesArticlesList;
    private RecyclerView mFavoritesArticlesRecyclerView;
    private ArticleAdapter mArticleAdapter;
    private Button mBackBtn;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.favorites_articles_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(FavoritesArticlesViewModel.class);

        initUi(viewRoot);
        initObservers();

        mViewModel.getFavoritesArticlesList();
        return viewRoot;
    }



    private void initUi(View iRootView) {

        mBackBtn = iRootView.findViewById(R.id.favorites_articles_fragment_back_btn);
        mBackBtn.setOnClickListener(v -> NavHostFragment.findNavController(this).popBackStack());

        mFavoritesArticlesRecyclerView = iRootView.findViewById(R.id.favorites_articles_fragment_articles_data_rv);
        mArticleAdapter = new ArticleAdapter(mFavoritesArticlesList);
        mFavoritesArticlesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mFavoritesArticlesRecyclerView.setHasFixedSize(true);
        mFavoritesArticlesRecyclerView.setAdapter(mArticleAdapter);

    }

    private void initObservers() {
        mViewModel.getFavoritesArticlesListLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Article>>() {
            @Override
            public void onChanged(ArrayList<Article> articles) {
                mFavoritesArticlesList.clear();
                mFavoritesArticlesList = articles;
                mArticleAdapter.setArticles(mFavoritesArticlesList);
                mArticleAdapter.notifyDataSetChanged();
            }
        });
    }


}