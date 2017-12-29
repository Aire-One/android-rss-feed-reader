package com.example.project.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.internal.ArticleListSingleton;
import com.example.project.internal.FeedSourceSingleton;
import com.example.project.logics.core.IRSSFeedWorkerListener;
import com.example.project.logics.core.RSSFeedWorker;
import com.example.project.logics.dataTypes.Article;
import com.example.project.ui.contracts.IAddFeedSourceDialogListener;
import com.example.project.ui.contracts.IArticleListFragmentListener;
import com.example.project.ui.dialog.AddFeedSourceDialog;

public class ListArticlesActivity extends AppCompatActivity implements IArticleListFragmentListener.OnListFragmentInteractionListener {

    public static final String DIALOG_ADD_FEED_SOURCE_TAG = "LIST_ARTICLE_ACTIVITY_DIALOG_ADD_FEED";

    private final Context CONTEXT = this;
    private FeedSourceSingleton FEEDS;

    private Toolbar mToolbar;
    private FloatingActionButton mFab;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_articles);

        FEEDS = FeedSourceSingleton.getInstance(this);

        mToolbar = findViewById(R.id.activity_list_article_toolbar);
        setSupportActionBar(mToolbar);

        mFab = findViewById(R.id.activity_list_article_floatingActionButton);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialog = AddFeedSourceDialog.newInstance(new IAddFeedSourceDialogListener() {
                    @Override
                    public void onNegativeClick() {}

                    @Override
                    public void onPositiveClick(String newFeed) {
                        FEEDS.addFeed(newFeed);
                        refreshArticleList();
                    }

                    @Override
                    public void onCancelClick() {}
                });
                dialog.show(getSupportFragmentManager(), DIALOG_ADD_FEED_SOURCE_TAG);
            }
        });

        mSwipeRefreshLayout = findViewById(R.id.activity_list_article_swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshArticleList();
            }
        });

        // add some tests feeds
        addFeeds();

        refreshArticleList();
    }

    @Override
    public void onClick(Article item) {
        Toast.makeText(this, "click " + item.getTitle(), Toast.LENGTH_SHORT).show();
        ///TODO : load article webview
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_articles_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            /*case R.id.list_article_activity_menu_action_settings:
                return true;*/
            case R.id.list_article_activity_menu_action_refresh:
                refreshArticleList();
                return true;
            case R.id.list_article_activity_menu_action_list:
                Intent intent = new Intent(this, ListFeedActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void refreshArticleList() {
        ArticleListSingleton.getInstance().clearArticles();
        mSwipeRefreshLayout.setRefreshing(true);

        RSSFeedWorker worker = new RSSFeedWorker(new IRSSFeedWorkerListener() {
            @Override
            public void onWorkFinished() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        worker.execute(CONTEXT);
    }

    protected void addFeeds() {
        FeedSourceSingleton feeds = FeedSourceSingleton.getInstance(this);
        feeds.clearFeeds();
        feeds.addFeed("https://www.xda-developers.com/feed/");
        feeds.addFeed("https://www.archlinux.org/feeds/news/");
        //feeds.addFeed("http://turnoff.us/feed.xml");
    }
}
