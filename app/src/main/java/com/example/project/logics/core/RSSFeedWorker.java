package com.example.project.logics.core;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.project.internal.ArticleListSingleton;
import com.example.project.internal.FeedSourceSingleton;
import com.example.project.logics.dataTypes.Article;

import java.util.List;

public class RSSFeedWorker extends AsyncTask<Context, Void, Void> {

    private IRSSFeedWorkerListener mListener;

    public RSSFeedWorker(IRSSFeedWorkerListener listener) {
        mListener = listener;
    }

    @Override
    protected Void doInBackground(Context... contexts) {
        // Assuming the first arg is the current context to work on
        Context context = contexts[0];

        FeedSourceSingleton feeds = FeedSourceSingleton.getInstance(context);

        for (String url : feeds.getFeeds()) {
            Request req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    RSSFeedParser parser = new RSSFeedParser(new IRSSFeedParserListener() {
                        @Override
                        public void onParseFinished(List<Article> articles) {
                            ArticleListSingleton.getInstance().addArticles(articles);
                        }
                    });
                    parser.execute(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("request", error.getLocalizedMessage());
                }
            });

            RSSFeedPullerSingleton.getInstance(context).addToRequestQueue(req);
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... progress) {
        /// TODO: notify progress to caller
    }

    @Override
    protected void onPostExecute(Void result) {
        mListener.onWorkFinished();
    }
}
