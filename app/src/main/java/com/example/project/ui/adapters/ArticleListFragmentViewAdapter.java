package com.example.project.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project.R;
import com.example.project.logics.dataTypes.Article;
import com.example.project.ui.contracts.IArticleListFragmentListener;
import com.example.project.ui.views.ArticleFragmentListItemViewHolder;

import java.util.List;

public class ArticleListFragmentViewAdapter extends RecyclerView.Adapter<ArticleFragmentListItemViewHolder> {

    private Context mContext;

    private final List<Article> mValues;
    private final IArticleListFragmentListener.OnListFragmentInteractionListener mListener;

    public ArticleListFragmentViewAdapter(List<Article> items, IArticleListFragmentListener.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;

        setHasStableIds(true);
    }

    @Override
    public ArticleFragmentListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_article, parent, false);

        return new ArticleFragmentListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleFragmentListItemViewHolder holder, final int position) {
        // associate view with its article
        holder.setup(mValues.get(position));

        // listeners
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(mValues.get(position));
                }
            }
        });
        holder.mButtonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onShareButtonClicked(mValues.get(position));
                }
            }
        });
        holder.mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onFavButtonCliecked(mValues.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
