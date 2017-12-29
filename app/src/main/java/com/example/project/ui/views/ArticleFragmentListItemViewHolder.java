package com.example.project.ui.views;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.logics.dataTypes.Article;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArticleFragmentListItemViewHolder extends RecyclerView.ViewHolder {

    public Article mItem;

    // Member's Views (public cauz final FTW)
    public final View mView;
    public final TextView mTextViewTitle;
    public final TextView mTextViewFeedOrigin;
    public final TextView mTextViewAuthor;
    public final TextView mTextViewDate;
    public final ExpandableLayout mExpendableLayoutDescription;
    public final TextView mTextViewDescription;
    public final ImageButton mButtonSave;
    public final ImageButton mButtonShare;
    public final ImageButton mButtonExpand;

    public ArticleFragmentListItemViewHolder(View view) {
        super(view);

        mView = view;
        mTextViewTitle = view.findViewById(R.id.title);
        mTextViewFeedOrigin = view.findViewById(R.id.textView_feedorigin);
        mTextViewAuthor = view.findViewById(R.id.textView_author);
        mTextViewDate = view.findViewById(R.id.textview_date);
        mExpendableLayoutDescription = view.findViewById(R.id.expandable_layout_description);
        mTextViewDescription = view.findViewById(R.id.textView_description);
        mButtonSave = view.findViewById(R.id.button_fav);
        mButtonShare = view.findViewById(R.id.button_share);
        mButtonExpand = view.findViewById(R.id.button_expand);

        mExpendableLayoutDescription.setOnExpansionUpdateListener(new ExpendableListener());

        mButtonExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("expend listener", "run");

                mExpendableLayoutDescription.toggle();
            }
        });
    }

    public void setup(Article article) {
        mItem = article;

        mTextViewTitle.setText(mItem.getTitle());
        mTextViewFeedOrigin.setText(getHost(mItem.getmLink()));
        mTextViewAuthor.setText(mItem.getAuthor());
        mTextViewDate.setText(getDate(mItem.getPubDate()));
        mExpendableLayoutDescription.collapse(false);
        mTextViewDescription.setText(mItem.getDescription());
    }

    protected String getHost(String url) {
        try {
            URL u = new URL(url);
            return u.getHost();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return "";
    }

    protected String getDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    protected class ExpendableListener implements ExpandableLayout.OnExpansionUpdateListener {
        @Override
        public void onExpansionUpdate(float expansionFraction, int state) {
            mButtonExpand.setRotation(expansionFraction * 180);
        }
    }

}
