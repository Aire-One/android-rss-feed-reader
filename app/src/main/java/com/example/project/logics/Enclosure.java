package com.example.project.logics;


public class Enclosure {

    private String mUrl;
    private long mLength;
    private String mMimeType;

    public Enclosure() {
    }

    public Enclosure(String mUrl, long mLength, String mMimeType) {
        this.mUrl = mUrl;
        this.mLength = mLength;
        this.mMimeType = mMimeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Enclosure enclosure = (Enclosure) o;

        if (mLength != enclosure.mLength) return false;
        if (mUrl != null ? !mUrl.equals(enclosure.mUrl) : enclosure.mUrl != null) return false;
        return mMimeType != null ? mMimeType.equals(enclosure.mMimeType) : enclosure.mMimeType == null;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public long getmLength() {
        return mLength;
    }

    public void setmLength(long mLength) {
        this.mLength = mLength;
    }

    public String getmMimeType() {
        return mMimeType;
    }

    public void setmMimeType(String mMimeType) {
        this.mMimeType = mMimeType;
    }
}
