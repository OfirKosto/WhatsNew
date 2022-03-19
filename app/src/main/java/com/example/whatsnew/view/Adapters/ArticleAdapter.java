package com.example.whatsnew.view.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.whatsnew.model.Article;
import com.example.whatsnew.R;
import com.example.whatsnew.model.enums.eFragments;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private ArrayList<Article> mArticleList;

    private eFragments mCurrentFragment;

    public ArticleAdapter(ArrayList<Article> iArticleList, eFragments iCurrentFragment){
        mArticleList = iArticleList;
        mCurrentFragment = iCurrentFragment;
    }

    public void setArticles(ArrayList<Article> iArticlesList) {
        this.mArticleList = iArticlesList;
    }

    public interface ActionButtonListener{
        void onButtonClicked(Article iArticle);
    }

    private ActionButtonListener listener;

    public void setListener(ActionButtonListener listener)
    {
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_card,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ArticleAdapter.ViewHolder holder, int position) {
        Article article = mArticleList.get(position);

        // sets title textview
        holder.title.setText(article.getTitle());

        // sets photo imageview
        Glide.with(holder.itemView.getContext())
                .load(article.getUrlToImage())
                .into(holder.articlePhotoImageView);

        // sets content textview
        if(article.getContent() != null)
            holder.content.setText(article.getContent());
        else
            holder.content.setText(article.getDescription());

        // sets the action button to the holder depends the fragment
        if (mCurrentFragment.equals(eFragments.ARTICLES_DATA_FRAGMENT))
        {
            holder.bindArticlesDataFragment();
        }
        else if(mCurrentFragment.equals(eFragments.FAVORITES_ARTICLES_FRAGMENT))
        {
            holder.bindFavoritesArticlesFragment();
        }
        holder.actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonClicked(article);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private MaterialTextView title;
        private ShapeableImageView articlePhotoImageView;
        private MaterialTextView content;
        private MaterialButton actionButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.article_card_title_tv);
            articlePhotoImageView = itemView.findViewById(R.id.article_card_article_photo);
            content = itemView.findViewById(R.id.article_card_article_content);
            actionButton = itemView.findViewById(R.id.article_card_favorite_btn);
        }

        public void bindArticlesDataFragment() {
            actionButton.setBackground(itemView.getResources().getDrawable(R.drawable.ic_baseline_full_star, itemView.getContext().getTheme()));
        }

        public void bindFavoritesArticlesFragment() {
            actionButton.setBackground(itemView.getResources().getDrawable(R.drawable.ic_baseline_delete_image, itemView.getContext().getTheme()));
        }
    }
}
