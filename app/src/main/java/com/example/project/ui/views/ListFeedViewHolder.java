package com.example.project.ui.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.R;


public class ListFeedViewHolder {

    private Context mContext;

    private String mName;

    private View mView;
    private TextView mTextViewFeedName;
    private ImageButton mButtonDelete;

    public ListFeedViewHolder(Context context, String name) {
        mContext = context;
        mName = name;

        buildView();
    }

    public View getView() {
        if (mView == null) {
            buildView();
        }

        return mView;
    }

    protected void buildView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.feedlist_holder_layout, null);

        mTextViewFeedName = mView.findViewById(R.id.list_feed_viewholder_name_textView);
        mButtonDelete = mView.findViewById(R.id.list_feed_viewholder_delete_button);

        mTextViewFeedName.setText(mName);
    }

}
