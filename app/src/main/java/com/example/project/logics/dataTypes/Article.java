package com.example.project.logics.dataTypes;


import java.util.ArrayList;
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
        this.mTitle = "Title";
        this.mLink = "link";
        this.mDescription = "desciption";
        this.mAuthor = "author";
        this.mCategories = new ArrayList<>();
        this.mEnclosure = new Enclosure();
        this.mGuid = "Guid";
        this.mPubDate = new Date();
        this.mSource = "source";
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

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String Title) {
        this.mTitle = Title;
    }

    public String getmLink() {
        return mLink;
    }

    public void setLink(String Link) {
        this.mLink = Link;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public List<String> getCategories() {
        return mCategories;
    }

    public void setCategories(List<String> mCategories) {
        this.mCategories = mCategories;
    }

    public Enclosure getEnclosure() {
        return mEnclosure;
    }

    public void setEnclosure(Enclosure mEnclosure) {
        this.mEnclosure = mEnclosure;
    }

    public String getGuid() {
        return mGuid;
    }

    public void setGuid(String mGuid) {
        this.mGuid = mGuid;
    }

    public Date getPubDate() {
        return mPubDate;
    }

    public void setPubDate(Date mPubDate) {
        this.mPubDate = mPubDate;
    }

    public String getSource() {
        return mSource;
    }

    public void setSource(String mSource) {
        this.mSource = mSource;
    }
}
