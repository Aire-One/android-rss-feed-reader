package com.example.project;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.ArticleFragment.OnListFragmentInteractionListener;
import com.example.project.logics.Article;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

public class MyArticleRecyclerViewAdapter extends RecyclerView.Adapter<MyArticleRecyclerViewAdapter.ViewHolder> {

    private Context mContext;

    private final List<Article> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyArticleRecyclerViewAdapter(List<Article> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_article, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitle.setText(mValues.get(position).getmTitle());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public Article mItem;
        public final View mView;
        public final TextView mTitle;
        public final TextView mFeedOrigin;
        public final TextView mAuthor;
        public final TextView mDate;
        public final ExpandableLayout mExpendableLayoutDescription;
        public final ImageButton mButtonSave;
        public final ImageButton mButtonShare;
        public final ImageButton mButtonExpand;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = view.findViewById(R.id.title);
            mFeedOrigin = view.findViewById(R.id.textView_feedorigin);
            mAuthor = view.findViewById(R.id.textView_author);
            mDate = view.findViewById(R.id.textview_date);
            mExpendableLayoutDescription = view.findViewById(R.id.expandable_layout_description);
            mButtonSave = view.findViewById(R.id.button_fav);
            mButtonShare = view.findViewById(R.id.button_share);
            mButtonExpand = view.findViewById(R.id.button_expand);

            mExpendableLayoutDescription.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
                @Override
                public void onExpansionUpdate(float expansionFraction, int state) {
                    mButtonExpand.setRotation(expansionFraction * 180);
                }
            });

            mButtonExpand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("expend listener", "run");

                    mExpendableLayoutDescription.toggle();
                }
            });
        }
    }
}
