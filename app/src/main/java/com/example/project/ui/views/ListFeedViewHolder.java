package com.example.project.ui.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.ui.contracts.IListFeedViewHolderListener;


public class ListFeedViewHolder {

    private Context mContext;

    private String mName;

    private View mView;
    private TextView mTextViewFeedName;
    private ImageButton mButtonDelete;

    private IListFeedViewHolderListener mListener;

    public ListFeedViewHolder(Context context, String name, IListFeedViewHolderListener listener) {
        mContext = context;
        mName = name;
        mListener = listener;

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

        mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onDeleteButtonClicked(mName);
            }
        });

        mTextViewFeedName.setText(mName);
    }

}
