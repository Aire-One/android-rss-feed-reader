package com.example.project.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.logics.dataTypes.Article;
import com.example.project.ui.contracts.IArticleListFragmentListener;

public class ListArticlesActivity extends AppCompatActivity implements IArticleListFragmentListener.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_articles);
    }

    @Override
    public void onClick(Article item) {
        Toast.makeText(this, "click " + item.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
