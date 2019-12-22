package com.example.coccochometest.dataservices;

import com.example.coccochometest.model.RssFeed;

import io.reactivex.Observable;

public interface CocCocService {
    Observable<RssFeed> fetchDataFromServer();
}
