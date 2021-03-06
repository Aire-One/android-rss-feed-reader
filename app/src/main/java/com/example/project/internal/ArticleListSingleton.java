package com.example.project.internal;

import android.util.Log;

import com.example.project.logics.dataTypes.Article;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleListSingleton {
    private static final ArticleListSingleton mInstance = new ArticleListSingleton();
    public static ArticleListSingleton getInstance() {
        return mInstance ;
    }

    private List<Article> mArticles;

    private List<IArticleListSingletonListener> mListeners;

    private ArticleListSingleton() {
        mArticles = new ArrayList<>();
        mListeners = new ArrayList<>();
    }

    // getArticle() give a read only list
    // (giving a modifiable list would break the freaking hooking system)
    public List<Article> getArticles() {
        return Collections.unmodifiableList(mArticles);
    }

    public void addHook(IArticleListSingletonListener listener) {
        mListeners.add(listener);
    }

    public void removeHook(IArticleListSingletonListener listener) {
        mListeners.remove(listener);
    }

    public void addArticle(Article article) {
        mArticles.add(article);
        notifyUpdate();
    }

    public synchronized void addArticles(List<Article> articles) {
        if (articles == null) {
            Log.d("AddArticle", "Addition articles list is null");
            return;
        }

        Log.d("AddArticle", "current list: " + mArticles.toString());
        Log.d("AddArticle", "addition list: " + articles.toString());

        mArticles.addAll(articles);
        notifyUpdate();
    }

    public void clearArticles() {
        mArticles.clear();
        notifyUpdate();
    }

    protected void notifyUpdate() {
        for (IArticleListSingletonListener listener : mListeners) {
            listener.onUpdateList(mArticles);
        }
    }
}
