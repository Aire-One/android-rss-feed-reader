package com.example.project.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.internal.FeedSourceSingleton;
import com.example.project.logics.core.RSSFeedWorker;
import com.example.project.logics.dataTypes.Article;
import com.example.project.ui.contracts.IArticleListFragmentListener;

public class ListArticlesActivity extends AppCompatActivity implements IArticleListFragmentListener.OnListFragmentInteractionListener {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_articles);

        mToolbar = findViewById(R.id.activity_list_article_toolbar);
        setSupportActionBar(mToolbar);

        addFeeds();
    }

    @Override
    public void onClick(Article item) {
        Toast.makeText(this, "click " + item.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_articles_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list_article_activity_menu_action_settings:
                return true;
            case R.id.list_article_activity_menu_action_refresh:
                new RSSFeedWorker().execute(this);
                return true;
            case R.id.list_article_activity_menu_action_list:
                Intent intent = new Intent(this, ListFeedActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void addFeeds() {
        FeedSourceSingleton feeds = FeedSourceSingleton.getInstance(this);
        feeds.clearFeeds();
        feeds.addFeed("https://www.xda-developers.com/feed/");
        feeds.addFeed("https://www.archlinux.org/feeds/news/");

        Log.d("Feeds", "size: " + feeds.getFeeds().size()
                + " ; contents: " + feeds.getFeeds().toString());

    }
}
