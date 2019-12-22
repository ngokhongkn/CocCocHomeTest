package com.example.coccochometest.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class FeedItem implements Parcelable {
    @Element(name = "pubDate")
    private String mpubDate;
    @Element(name = "title")
    private String mtitle;
    @Element(name = "link")
    private String mlink;
    @Element(name = "description")
    private String mdescription;

    public FeedItem() {
    }

    public FeedItem(String mdescription, String mlink, String mtitle, String mpubDate) {
        this.mdescription = mdescription;
        this.mlink = mlink;
        this.mtitle = mtitle;
        this.mpubDate = mpubDate;
    }

    public String getMpubDate() {
        return mpubDate;
    }

    public void setMpubDate(String mpubDate) {
        this.mpubDate = mpubDate;
    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getMlink() {
        return mlink;
    }

    public void setMlink(String mlink) {
        this.mlink = mlink;
    }

    public String getMdescription() {
        return mdescription;
    }

    public void setMdescription(String mdescription) {
        this.mdescription = mdescription;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mpubDate);
        dest.writeString(this.mtitle);
        dest.writeString(this.mlink);
        dest.writeString(this.mdescription);
    }

    protected FeedItem(Parcel in) {
        this.mpubDate = in.readString();
        this.mtitle = in.readString();
        this.mlink = in.readString();
        this.mdescription = in.readString();
    }

    public static final Creator<FeedItem> CREATOR = new Creator<FeedItem>() {
        @Override
        public FeedItem createFromParcel(Parcel source) {
            return new FeedItem(source);
        }

        @Override
        public FeedItem[] newArray(int size) {
            return new FeedItem[size];
        }
    };
}
