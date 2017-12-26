package com.example.project.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.project.R;

public class RecyclerViewWithSeparator extends android.support.v7.widget.RecyclerView {

    private Context mContext;

    private final int SEPARATOR_RESOURCE_ID = R.drawable.line_divider;

    public RecyclerViewWithSeparator(Context context) {
        super(context);
        mContext = context;

        setLayoutManager(new LinearLayoutManager(context));
        addItemDecoration(new CustomItemDecoration());
    }

    protected class CustomItemDecoration extends ItemDecoration {
        private final Drawable mDivider = mContext.getResources().getDrawable(SEPARATOR_RESOURCE_ID);

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);

            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }
}
