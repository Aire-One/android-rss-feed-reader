package com.example.project.logics.core;

import com.example.project.logics.dataTypes.Article;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XMLParserArticle {

    private IParserListener mListener;

    public XMLParserArticle(IParserListener listener) {
        mListener = listener;
    }

    public void parseXML(String xml) throws XmlPullParserException, IOException {
        // parsing specifications : https://cyber.harvard.edu/rss/rss.html#hrelementsOfLtitemgt

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(false);
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(new StringReader(xml));

        List<Article> articles = new ArrayList<>();
        Article currentArticle = new Article();

        boolean insideItem = false;
        int eventType = parser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                // toUpperCase() to match regardless the case
                switch (parser.getName().toUpperCase()) {
                    case "ITEM":
                        insideItem = true;
                        break;
                    case "TITLE":
                        if (insideItem) {
                            currentArticle.setTitle(safeNextText(parser));
                        }
                        break;
                    case "LINK":
                        if (insideItem) {
                            currentArticle.setTitle(safeNextText(parser));
                        }
                        break;
                    case "DESCRIPTION":
                        if (insideItem) {
                            currentArticle.setDescription(safeNextText(parser));
                        }
                        break;
                    case "AUTHOR":
                        if (insideItem) {
                            currentArticle.setAuthor(safeNextText(parser));
                        }
                        break;
                    case "CATEGORY":
                        if (insideItem) {
                            currentArticle.getCategories().add(safeNextText(parser));
                        }
                        break;
                    case "ENCLOSURE":
                        ///TODO
                        break;
                    case "GUID":
                        if (insideItem) {
                            currentArticle.setGuid(safeNextText(parser));
                        }
                        break;
                    case "PUBDATE":
                        if (insideItem) {
                            currentArticle.setPubDate(new Date(safeNextText(parser)));
                        }
                        break;
                    case "SOURCE":
                        if (insideItem) {
                            currentArticle.setSource(safeNextText(parser));
                        }
                        break;
                }
            }
            else if (eventType == XmlPullParser.END_TAG && parser.getName().equalsIgnoreCase("ITEM")) {
                insideItem = false;
                articles.add(currentArticle);
                currentArticle = new Article();
            }

            eventType = parser.next();
        }

        mListener.onParsingFinished(articles);
    }

    // https://android-developers.googleblog.com/2011/12/watch-out-for-xmlpullparsernexttext.html
    private static String safeNextText(XmlPullParser parser) throws XmlPullParserException, IOException {
        String result = parser.nextText();
        while (parser.getEventType() != XmlPullParser.END_TAG) {
            parser.nextTag();
        }
        return result;
    }

    public interface IParserListener {
        void onParsingFinished(List<Article> articles);
    }
}
