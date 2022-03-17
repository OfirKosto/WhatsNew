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
import com.example.whatsnew.Adapters.ArticleAdapter;
import com.example.whatsnew.R;
import com.example.whatsnew.viewmodels.ArticlesDataViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class ArticlesDataFragment extends Fragment {

    private ArticlesDataViewModel mViewModel;

    private MaterialButton mBackBtn;
    private MaterialTextView mCategoryNameTv;
    private String mCategoryName;

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

        mViewModel.getArticlesByCategory(mCategoryName.toLowerCase());

        return viewRoot;
    }

    private void initUi(View iRootView) {

        mBackBtn = iRootView.findViewById(R.id.articles_data_fragment_back_btn);
        mBackBtn.setOnClickListener(v -> NavHostFragment.findNavController(this).popBackStack());


        mArticlesListRecyclerView = iRootView.findViewById(R.id.articles_data_fragment_articles_data_rv);
        mArticleAdapter = new ArticleAdapter(mArticlesList);
        mArticlesListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mArticlesListRecyclerView.setHasFixedSize(true);
        mArticlesListRecyclerView.setAdapter(mArticleAdapter);

        mCategoryNameTv = iRootView.findViewById(R.id.articles_data_fragment_category_name_tv);

        Bundle bundle = getArguments();
        if (bundle != null)
        {
            mCategoryName = bundle.getString("category");
            mCategoryNameTv.setText(mCategoryName);
        }
        else
            Snackbar.make(getView(), R.string.error_transferring_data, Snackbar.LENGTH_LONG).show();

    }

    private void initObservers() {
        mViewModel.getArticlesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Article>>() {
            @Override
            public void onChanged(ArrayList<Article> articles) {
                mArticlesList.clear();
                mArticlesList = articles;
                mArticleAdapter.setArticles(mArticlesList);
                mArticleAdapter.notifyDataSetChanged();
            }
        });

        mViewModel.getErrorLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Snackbar.make(getView(), s, Snackbar.LENGTH_LONG).show();
            }
        });
    }

}