package com.example.project.internal;

import com.example.project.logics.dataTypes.Article;

import java.util.List;

public interface IArticleListSingletonListener {

    void onUpdateList(List<Article> articles);

}
