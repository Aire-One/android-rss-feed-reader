package com.example.project.logics.dataTypes;


public class Enclosure {

    private String mUrl;
    private long mLength;
    private String mMimeType;

    public Enclosure() {
        mUrl = "url";
        mLength = 0;
        mMimeType = "null";
    }

    public Enclosure(String url, long length, String mimeType) {
        mUrl = url;
        mLength = length;
        mMimeType = mimeType;
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

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String Url) {
        mUrl = Url;
    }

    public long getLength() {
        return mLength;
    }

    public void setLength(long Length) {
        mLength = Length;
    }

    public String getMimeType() {
        return mMimeType;
    }

    public void setMimeType(String MimeType) {
        mMimeType = MimeType;
    }
}
