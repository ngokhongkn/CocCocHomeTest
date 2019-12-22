package com.example.coccochometest.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "rss", strict = false)
public class RssFeed implements Parcelable {

    @Element(name = "channel")
    private Channel mChannel;

    public Channel getmChannel() {
        return mChannel;
    }

    public RssFeed() {
    }

    public RssFeed(Channel mChannel) {
        this.mChannel = mChannel;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mChannel, flags);
    }

    protected RssFeed(Parcel in) {
        this.mChannel = in.readParcelable(Channel.class.getClassLoader());
    }

    public static final Creator<RssFeed> CREATOR = new Creator<RssFeed>() {
        @Override
        public RssFeed createFromParcel(Parcel source) {
            return new RssFeed(source);
        }

        @Override
        public RssFeed[] newArray(int size) {
            return new RssFeed[size];
        }
    };
}