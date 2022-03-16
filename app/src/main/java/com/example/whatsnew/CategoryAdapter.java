package com.example.whatsnew;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private ArrayList<Category> mCategoriesList;


    public CategoryAdapter(ArrayList<Category> iCategoriesList){ mCategoriesList =  iCategoriesList;}

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CategoryAdapter.ViewHolder holder, int position) {
        Category category = mCategoriesList.get(position);

        holder.button.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return mCategoriesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private MaterialButton button;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            button = itemView.findViewById(R.id.category_card_button);

        }
    }
}
