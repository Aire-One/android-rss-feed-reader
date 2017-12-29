package com.example.project.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.internal.FeedSourceSingleton;
import com.example.project.ui.contracts.IFeedListAdapterListener;
import com.example.project.ui.contracts.IListFeedViewHolderListener;
import com.example.project.ui.views.ListFeedViewHolder;

import java.util.List;

public class FeedListAdapter extends BaseAdapter implements IListFeedViewHolderListener {

    private List<String> mList;

    private final Context mContext;

    private IFeedListAdapterListener mListener;

    public FeedListAdapter(Context context, List<String> list, IFeedListAdapterListener listener) {
        mContext = context;
        mList = list;
        mListener = listener;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return new ListFeedViewHolder(mContext, mList.get(i).toString(), this).getView();
    }

    public void updateList(List<String> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public void onDeleteButtonClicked(String item) {
        mListener.onDeleteButtonClicked(item);
    }
}
