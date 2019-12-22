package com.example.coccochometest.ui.customviews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.example.coccochometest.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CountUpView extends android.support.v7.widget.AppCompatTextView implements Runnable {
    private static final String DATE_FORMAT_SERVER = "EEE, dd MMM yyyy HH:mm:ss Z";
    private boolean stop = false;
    private Context context;

    private long timeOrigin;

    public CountUpView(Context context) {
        super(context);
        this.context = context;
    }

    public CountUpView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public CountUpView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    public void run() {
        if (!stop) {

            long ts = System.currentTimeMillis() - timeOrigin;

            long second = ts / 1000;
            long minute = ts / (60 * 1000);
            long hour = ts / (60 * 60 * 1000);
            long day = ts / (24 * 60 * 60 * 1000);

            if (second <= 59) {
                setText(context.getResources().getString(R.string.count_up_just_happen));
            } else if (minute <= 59) {
                setText(getResources().getString(R.string.ts_minute, minute));
            } else if (hour <= 23) {
                setText(getResources().getString(R.string.ts_hour, hour));
            } else {
                setText(getResources().getString(R.string.ts_day, day));
            }
            postDelayed(this, 10000);
        }
    }

    public void setTimeOrigin(String dateString) {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(DATE_FORMAT_SERVER, Locale.ENGLISH);
        inputDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date date = inputDateFormat.parse(dateString);
            timeOrigin = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void Stop() {
        stop = true;
    }
}
