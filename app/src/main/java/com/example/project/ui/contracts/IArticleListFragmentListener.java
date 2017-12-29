package com.example.project.ui.contracts;

import com.example.project.logics.dataTypes.Article;

public class IArticleListFragmentListener {

    public interface OnListFragmentInteractionListener {

        void onClick(Article item);
        void onShareButtonClicked(Article item);
        void onFavButtonCliecked(Article item);
    }

}
