package com.example.project.ui.fragments;

import android.app.DownloadManager;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.project.R;
import com.example.project.dummy.DummyContent;
import com.example.project.logics.core.RSSFeedParser;
import com.example.project.logics.core.RSSFeedPullerSingleton;
import com.example.project.logics.dataTypes.Article;
import com.example.project.ui.adapters.ArticleListFragmentViewAdapter;
import com.example.project.ui.contracts.IArticleListFragmentListener;

import java.util.ArrayList;
import java.util.List;

public class ArticleListFragment extends Fragment {

    private Context mContext;
    private IArticleListFragmentListener.OnListFragmentInteractionListener mListener;


    public ArticleListFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_article, container, false);

        mContext = view.getContext();

        //RecyclerViewWithSeparator recyclerView = (RecyclerViewWithSeparator) view;
        //recyclerView.setAdapter(new ArticleListFragmentViewAdapter(DummyContent.ITEMS, mListener));

        RecyclerView recyclerView = (RecyclerView) view;

        /*List<Article> articles = new ArrayList<Article>();
        for (int i=0;i<100;++i) {
            Article a = new Article();
            a.setTitle("#" + i);
            articles.add(a);
        }*/

        Request req = new StringRequest(Request.Method.GET, "https://www.xda-developers.com/feed/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                RSSFeedParser parser = new RSSFeedParser();
                parser.execute(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("request", error.getLocalizedMessage());
            }
        });
        RSSFeedPullerSingleton.getInstance(mContext).addToRequestQueue(req);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(new ArticleListFragmentViewAdapter(DummyContent.ITEMS, mListener));

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IArticleListFragmentListener.OnListFragmentInteractionListener) {
            mListener = (IArticleListFragmentListener.OnListFragmentInteractionListener) context;
        }
        else {
            throw new RuntimeException(context.toString() + " must implement my custom listener (IArticleListFragmentListener.OnListFragmentInteractionListener)");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
