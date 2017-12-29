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
import android.widget.Adapter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.project.R;
import com.example.project.dummy.DummyContent;
import com.example.project.internal.ArticleListSingleton;
import com.example.project.internal.IArticleListSingletonListener;
import com.example.project.logics.core.IRSSFeedParserListener;
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

    private RecyclerView.Adapter mAdapter;

    private IArticleListSingletonListener mArticleListHook;

    public ArticleListFragment() {
        super();

        mArticleListHook = new IArticleListSingletonListener() {
            @Override
            public void onUpdateList(List<Article> articles) {
                mAdapter.notifyDataSetChanged();
            }
        };
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_article, container, false);

        mContext = view.getContext();

        /// TODO: fix the non-sense crash with custom recyclerview
        //RecyclerViewWithSeparator recyclerView = (RecyclerViewWithSeparator) view;
        //recyclerView.setAdapter(new ArticleListFragmentViewAdapter(DummyContent.ITEMS, mListener));

        final RecyclerView recyclerView = (RecyclerView) view;

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if (mAdapter == null) {
            mAdapter = new ArticleListFragmentViewAdapter(ArticleListSingleton.getInstance().getArticles(), mListener);
        }
        recyclerView.setAdapter(mAdapter);

        // add a listener to ArticleListSingleton to auto update the list on changes
        ArticleListSingleton.getInstance().addHook(mArticleListHook);

        return view;
    }

    @Override
    public void onDestroyView() {
        ArticleListSingleton.getInstance().removeHook(mArticleListHook);
        mAdapter = null; // destroy the adapter to avoid artifacts from previous instances

        super.onDestroyView();
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
