package com.example.whatsnew.view.fragments;

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

import com.example.whatsnew.view.Adapters.ArticleAdapter;
import com.example.whatsnew.model.Article;
import com.example.whatsnew.R;
import com.example.whatsnew.model.enums.eFragments;
import com.example.whatsnew.view.fragments.dialogs.YesNoDialogFragment;
import com.example.whatsnew.view.viewmodels.FavoritesArticlesViewModel;

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
        mFavoritesArticlesList = new ArrayList<>();

        initUi(viewRoot);
        initObservers();

        mViewModel.getFavoritesArticlesList();
        return viewRoot;
    }



    private void initUi(View iRootView) {

        mBackBtn = iRootView.findViewById(R.id.favorites_articles_fragment_back_btn);
        mBackBtn.setOnClickListener(v -> NavHostFragment.findNavController(this).popBackStack());

        mFavoritesArticlesRecyclerView = iRootView.findViewById(R.id.favorites_articles_fragment_articles_data_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true);
        linearLayoutManager.setStackFromEnd(true);
        mFavoritesArticlesRecyclerView.setLayoutManager(linearLayoutManager);
        mFavoritesArticlesRecyclerView.setHasFixedSize(true);
        mArticleAdapter = new ArticleAdapter(mFavoritesArticlesList, eFragments.FAVORITES_ARTICLES_FRAGMENT);
        setArticleAdapterListener();
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

    private void setArticleAdapterListener() {
        mArticleAdapter.setListener(new ArticleAdapter.ActionButtonListener() {
            @Override
            public void onButtonClicked(Article iArticle) {
                YesNoDialogFragment yesNoDialogFragment = new YesNoDialogFragment(getResources().getString(R.string.remove_from_favorites_question), new YesNoDialogFragment.IYesNoDialogFragmentListener() {
                    @Override
                    public void userResponse(boolean iIsUserAccepted) {
                        if (iIsUserAccepted) {
                            mViewModel.removeFromFavoritesArticlesList(iArticle);
                        }
                    }
                });
                yesNoDialogFragment.show(getActivity().getSupportFragmentManager(), YesNoDialogFragment.getDialogTag());
            }
        });
    }

}