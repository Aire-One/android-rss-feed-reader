package com.example.project.ui.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project.R;
import com.example.project.dummy.DummyContent;
import com.example.project.logics.Article;
import com.example.project.ui.adapters.ArticleListFragmentViewAdapter;
import com.example.project.ui.contracts.IArticleListFragmentListener;
import com.example.project.ui.views.RecyclerViewWithSeparator;

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

        List<Article> articles = new ArrayList<Article>();
        for (int i=0;i<100;++i) {
            Article a = new Article();
            a.setTitle("#" + i);
            articles.add(a);
        }

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(new ArticleListFragmentViewAdapter(articles, mListener));

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
