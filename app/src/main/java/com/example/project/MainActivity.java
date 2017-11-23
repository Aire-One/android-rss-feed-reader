package com.example.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.project.logics.Article;

public class MainActivity extends AppCompatActivity implements ArticleFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onListFragmentInteraction(Article item) {
        Toast.makeText(this, "touche item: " + item.getmGuid(), Toast.LENGTH_SHORT).show();
    }
}
