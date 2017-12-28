package com.example.project.logics.core;

import com.example.project.logics.dataTypes.Article;

import java.util.List;

public interface IRSSFeedParserListener {
    void onParseFinished(List<Article> articles);
    ///TODO: add other callbacks for events to notify work updates
}
