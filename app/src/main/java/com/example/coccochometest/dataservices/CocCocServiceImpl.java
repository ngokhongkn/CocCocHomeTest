package com.example.coccochometest.dataservices;

import com.example.coccochometest.api.CocCocApi;
import com.example.coccochometest.model.RssFeed;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CocCocServiceImpl implements CocCocService {
    private final CocCocApi cocCocApi;

    @Inject
    public CocCocServiceImpl(CocCocApi cocCocApi) {
        this.cocCocApi = cocCocApi;
    }

    @Override
    public Observable<RssFeed> fetchDataFromServer() {
        return cocCocApi.fetchDataFromServer();
    }
}
