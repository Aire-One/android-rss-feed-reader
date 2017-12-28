package com.example.project.logics.core;

import android.os.AsyncTask;

import com.example.project.logics.dataTypes.Article;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public class RSSFeedParser extends AsyncTask<String, Void, Void> {

    private XMLParserArticle mParser;
    private IRSSFeedParserListener mListener;

    private List<Article> mArticles;

    public RSSFeedParser(IRSSFeedParserListener listener) {
        mListener = listener;
        mParser = new XMLParserArticle(new XMLParserArticle.IParserListener() {
            @Override
            public void onParsingFinished(List<Article> articles) {
                //mListener.onParseFinished(articles);
                mArticles = articles;
            }
        });
    }

    @Override
    protected Void doInBackground(String... xmls) {
        for (String xml : xmls) {
            try {
                mParser.parseXML(xml);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... progress) {
        /// TODO: notify progress to caller
    }

    @Override
    protected void onPostExecute(Void result) {
        mListener.onParseFinished(mArticles);
    }

}
