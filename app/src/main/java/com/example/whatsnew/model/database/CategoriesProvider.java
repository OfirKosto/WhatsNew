package com.example.whatsnew.model.database;

import com.example.whatsnew.model.Category;
import com.example.whatsnew.model.enums.eCategories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Will Provide categories list from eCategories enum.
 * All you need to do in order to add a new category is
 * to check the name in api and add it to the enum.
 */

public class CategoriesProvider {

    public static ArrayList<Category> getCategories(){
        ArrayList<Category> categories = new ArrayList<>();

        List<String> enumNames = Stream.of(eCategories.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        for (String s: enumNames)
        {
            categories.add(new Category(s, eCategories.valueOf(s).mCategory));
        }

        return categories;
    }
}
