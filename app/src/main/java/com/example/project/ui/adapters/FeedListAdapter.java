package com.example.project.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.internal.FeedSourceSingleton;
import com.example.project.ui.views.ListFeedViewHolder;

public class FeedListAdapter extends BaseAdapter {

    private final FeedSourceSingleton feeds;

    private Context mContext;

    public FeedListAdapter(Context context) {
        mContext = context;
        feeds = FeedSourceSingleton.getInstance(mContext);
    }

    @Override
    public int getCount() {
        return feeds.getFeeds().size();
    }

    @Override
    public Object getItem(int i) {
        return feeds.getFeeds().toArray()[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return new ListFeedViewHolder(mContext, feeds.getFeeds().toArray()[i].toString()).getView();
    }

}
