package com.example.project.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.internal.FeedSourceSingleton;
import com.example.project.logics.core.RSSFeedWorker;
import com.example.project.ui.adapters.FeedListAdapter;
import com.example.project.ui.contracts.IAddFeedSourceDialogListener;
import com.example.project.ui.dialog.AddFeedSourceDialog;

public class ListFeedActivity extends AppCompatActivity {

    public final static String DIALOG_ADD_FEED_SOURCE_TAG = "LIST_FEED_ACTICITY_DIALOG_ADD_FEED";

    private final FeedSourceSingleton feeds = FeedSourceSingleton.getInstance(this);

    private Toolbar mToolbar;

    private ListView mListView;
    private FeedListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_list);

        mToolbar = findViewById(R.id.activity_list_feed_toolbar);
        setSupportActionBar(mToolbar);

        mListView = findViewById(R.id.activity_feed_list_listview);
        mAdapter = new FeedListAdapter(this);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_feed_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list_feed_activity_menu_action_add:
                DialogFragment dialog = AddFeedSourceDialog.newInstance(new IAddFeedSourceDialogListener() {
                    @Override
                    public void onNegativeClick() {}

                    @Override
                    public void onPositiveClick(String newFeed) {
                        feeds.addFeed(newFeed);
                    }

                    @Override
                    public void onCancelClick() {}
                });
                dialog.show(getSupportFragmentManager(), DIALOG_ADD_FEED_SOURCE_TAG);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
