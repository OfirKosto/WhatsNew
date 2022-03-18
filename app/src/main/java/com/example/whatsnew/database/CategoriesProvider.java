package com.example.whatsnew.database;

import com.example.whatsnew.Category;
import com.example.whatsnew.enums.eCategories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CategoriesProvider {

//    private ArrayList<Category> mCategoriesList;
//    private static CategoriesProvider mCategoriesProviderInstance = null;
//
//    private CategoriesProvider(){
//        mCategoriesList = new ArrayList<>();
//    }
//
//    public static CategoriesProvider getInstance()
//    {
//        if(mCategoriesProviderInstance == null)
//        {
//            mCategoriesProviderInstance = new CategoriesProvider();
//        }
//
//        return mCategoriesProviderInstance;
//    }

    public static ArrayList<Category> getCategories(){
        ArrayList<Category> categories = new ArrayList<>();

        List<String> enumNames = Stream.of(eCategories.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        for (String s: enumNames)
        {
            categories.add(new Category(eCategories.valueOf(s).mCategory));
        }

        return categories;
    }

}
