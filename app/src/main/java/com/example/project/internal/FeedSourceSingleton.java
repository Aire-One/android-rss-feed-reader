package com.example.project.internal;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FeedSourceSingleton {
    public static final String PREFS_NAME = "FEEDS_LIST";
    public static final String PREFS_FEEDS = "FEEDS";

    private static final Set<String> PREFS_FEEDS_DEFAULT = new HashSet<>();


    private static FeedSourceSingleton mInstance;

    private static Context mContext;


    private SharedPreferences mSharedPreferences;

    private Set<String> mFeeds;

    private FeedSourceSingleton(Context context) {
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        mFeeds = mSharedPreferences.getStringSet(PREFS_FEEDS, PREFS_FEEDS_DEFAULT);
    }

    public static synchronized FeedSourceSingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new FeedSourceSingleton(context);
        }

        return mInstance;
    }

    /// TODO: thread safe with synchronized key word and Context arg
    protected void writeFeed() {
        mSharedPreferences.edit().putStringSet(PREFS_FEEDS, mFeeds).commit();
    }

    public Set<String> getFeedsSet() {
        return Collections.unmodifiableSet(mFeeds);
    }

    public List<String> getFeedsList() { return Collections.unmodifiableList(new ArrayList<String>(mFeeds)); }

    public void addFeed(String feed) {
        mFeeds.add(feed);
        writeFeed();
    }

    public void removeFeed(String feed) {
        mFeeds.remove(feed);
        writeFeed();
    }

    public void clearFeeds() {
        mFeeds.clear();
        writeFeed();
    }

    public boolean isEmpty() {
        return mFeeds.isEmpty();
    }
}
