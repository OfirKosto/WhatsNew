package com.example.whatsnew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.whatsnew.model.database.DataBaseManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseManager.getInstance().initializeFavoritesData();
    }
}