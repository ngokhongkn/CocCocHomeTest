package com.example.coccochometest.api;

import com.example.coccochometest.model.RssFeed;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CocCocApi {
    @GET("rss/tin-moi-nhat.rss")
    Observable<RssFeed> fetchDataFromServer();
}

