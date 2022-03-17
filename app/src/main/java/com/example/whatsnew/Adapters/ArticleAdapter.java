package com.example.whatsnew.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.whatsnew.Article;
import com.example.whatsnew.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private ArrayList<Article> mArticleList;

    public ArticleAdapter(ArrayList<Article> iArticleList){ mArticleList = iArticleList; }

    public void setArticles(ArrayList<Article> iArticlesList) {
        this.mArticleList = iArticlesList;
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

        holder.title.setText(article.getTitle());

        Glide.with(holder.itemView.getContext())
                .load(article.getUrlToImage())
                .into(holder.articlePhotoImageView);

        if(article.getContent() != null)
            holder.content.setText(article.getContent());
        else
            holder.content.setText(article.getDescription());
    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private MaterialTextView title;
        private ShapeableImageView articlePhotoImageView;
        private MaterialTextView content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.article_card_title_tv);
            articlePhotoImageView = itemView.findViewById(R.id.article_card_article_photo);
            content = itemView.findViewById(R.id.article_card_article_content);

        }
    }
}
