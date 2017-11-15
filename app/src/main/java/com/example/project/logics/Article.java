package com.example.project.logics;


import java.util.Date;
import java.util.List;


/**
 * following specification from https://www.rssboard.org/rss-specification
 */
public class Article {

    private String mTitle;
    private String mLink;
    private String mDescription;
    private String mAuthor;
    private List<String> mCategories;
    private Enclosure mEnclosure;
    private String mGuid;
    private Date mPubDate;
    private String mSource;

    public Article() {
    }

    public Article(String mTitle, String mLink, String mDescription, String mAuthor,
                   List<String> mCategories, Enclosure mEnclosure, String mGuid, Date mPubDate,
                   String mSource) {
        this.mTitle = mTitle;
        this.mLink = mLink;
        this.mDescription = mDescription;
        this.mAuthor = mAuthor;
        this.mCategories = mCategories;
        this.mEnclosure = mEnclosure;
        this.mGuid = mGuid;
        this.mPubDate = mPubDate;
        this.mSource = mSource;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmLink() {
        return mLink;
    }

    public void setmLink(String mLink) {
        this.mLink = mLink;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public List<String> getmCategories() {
        return mCategories;
    }

    public void setmCategories(List<String> mCategories) {
        this.mCategories = mCategories;
    }

    public Enclosure getmEnclosure() {
        return mEnclosure;
    }

    public void setmEnclosure(Enclosure mEnclosure) {
        this.mEnclosure = mEnclosure;
    }

    public String getmGuid() {
        return mGuid;
    }

    public void setmGuid(String mGuid) {
        this.mGuid = mGuid;
    }

    public Date getmPubDate() {
        return mPubDate;
    }

    public void setmPubDate(Date mPubDate) {
        this.mPubDate = mPubDate;
    }

    public String getmSource() {
        return mSource;
    }

    public void setmSource(String mSource) {
        this.mSource = mSource;
    }
}
