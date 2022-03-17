package com.example.whatsnew.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsnew.CategoriesProvider;
import com.example.whatsnew.Category;

import java.util.ArrayList;

public class MainScreenViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Category>> mCategoriesList;

    public MainScreenViewModel(){
        mCategoriesList = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Category>> getCategoriesList() {
        return mCategoriesList;
    }

    public void extractCategoriesList(){
        mCategoriesList.postValue(CategoriesProvider.getCategories());
    }
}