package com.example.project.internal;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
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

    public Set<String> getFeeds() {
        return mFeeds;
    }

    public void addFeed(String feed) {
        mFeeds.add(feed);
        writeFeed();
    }
}
