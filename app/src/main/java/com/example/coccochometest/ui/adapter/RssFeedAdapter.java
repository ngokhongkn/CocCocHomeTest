package com.example.coccochometest.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coccochometest.R;
import com.example.coccochometest.model.FeedItem;
import com.example.coccochometest.ui.customviews.CountUpView;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class RssFeedAdapter extends RecyclerView.Adapter<RssFeedAdapter.ViewHolder> {
    private List<FeedItem> feedItemList;
    private Context context;

    private final OnFeedItemClickListener onFeedItemClickListener;

    public interface OnFeedItemClickListener {

        void onItemClicked(FeedItem item);
    }

    public RssFeedAdapter(Context context, OnFeedItemClickListener onFeedItemClickListener) {
        this.context = context;
        this.feedItemList = new ArrayList<>();
        this.onFeedItemClickListener = onFeedItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View item = inflater.inflate(R.layout.layout_card_rss_feed, viewGroup, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        FeedItem item = feedItemList.get(i);
        viewHolder.itemTitle.setText(item.getMtitle());
        Document doc = Jsoup.parse(item.getMdescription());
        Element link = doc.select("a").last();

        String description = doc.body().text();

        if (link != null && link.childNodes().size() > 0) {
            String imageUrl = link.childNode(0).attr("src");
            Picasso.with(context)
                    .load(imageUrl)
                    .fit()
                    .centerCrop()
                    .into(viewHolder.itemImage);
        }

        viewHolder.itemDescription.setText(description);
        viewHolder.itemDate.setTimeOrigin(item.getMpubDate());
        viewHolder.itemDate.run();

        viewHolder.itemParent.setOnClickListener(view -> onFeedItemClickListener.onItemClicked(item));
    }

    public void setItems(List<FeedItem> items) {
        feedItemList.clear();
        feedItemList.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return feedItemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView itemTitle;
        private final TextView itemDescription;
        private final CountUpView itemDate;
        private final ImageView itemImage;
        private final CardView itemParent;

        ViewHolder(View view) {
            super(view);
            itemParent = view.findViewById(R.id.item_parent);
            itemTitle = view.findViewById(R.id.item_title);
            itemDescription = view.findViewById(R.id.item_description);
            itemDate = view.findViewById(R.id.item_date);
            itemImage = view.findViewById(R.id.item_image);
        }
    }
}
