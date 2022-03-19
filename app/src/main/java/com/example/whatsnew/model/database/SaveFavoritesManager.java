package com.example.whatsnew.model.database;

import com.example.whatsnew.model.ApplicationContext;
import com.example.whatsnew.model.Article;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class SaveFavoritesManager {

    private static final String FILE_NAME = "FavoritesArticles";

    private static void saveToFile(ArrayList<Article> i_DataToSave)
    {
        try {
            FileOutputStream fos = ApplicationContext.getContext()
                    .openFileOutput(FILE_NAME, MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(i_DataToSave);
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Article> loadFromFile()
    {
        ArrayList<Article>  userFavorites = new ArrayList<> ();
        try {
            FileInputStream fis = ApplicationContext.getContext().openFileInput(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            userFavorites = (ArrayList<Article>)ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            userFavorites = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return userFavorites;
    }

    public static void addArticleToFavoritesFile(Article iArticle){

        ArrayList<Article> favoritesArticlesList = loadFromFile();

        if(!isContainArticleInFavorites(iArticle))
        {
            favoritesArticlesList.add(iArticle);
            saveToFile(favoritesArticlesList);
        }
    }

    public static boolean isContainArticleInFavorites(Article iArticle)
    {
        ArrayList<Article> favoritesArticlesList = loadFromFile();

        for (Article article: favoritesArticlesList)
        {
            if(article.getUrl().equals(iArticle.getUrl()))
            {
                return true;
            }
        }
        return false;
    }

    public static void removeArticleFromFavoritesFile(Article iArticle){

        ArrayList<Article> favoritesArticlesList = loadFromFile();

        for(int i = 0; i < favoritesArticlesList.size(); i++)
        {
            if (iArticle.getUrl().equals(favoritesArticlesList.get(i).getUrl()))
            {
                favoritesArticlesList.remove(i);
                saveToFile(favoritesArticlesList);
                break;
            }
        }
    }
}
