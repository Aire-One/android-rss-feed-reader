package com.example.project.logics.core;

import android.os.AsyncTask;
import android.util.Log;

import com.example.project.dummy.DummyContent;
import com.example.project.logics.dataTypes.Article;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public class RSSFeedParser extends AsyncTask<String, Void, Void> {

    private XMLParserArticle mParser;

    public RSSFeedParser() {
        mParser = new XMLParserArticle(new XMLParserArticle.IParserListener() {
            @Override
            public void onParsingFinished(List<Article> articles) {
                /// TODO : update articles main list
                Log.d("parser", "finish, add " + articles.size() + " elements");
                DummyContent.ITEMS.addAll(articles);
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
        /// TODO: notify work is finished to caller
    }

}
