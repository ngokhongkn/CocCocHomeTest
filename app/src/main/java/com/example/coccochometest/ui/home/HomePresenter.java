package com.example.coccochometest.ui.home;

import com.example.coccochometest.App;
import com.example.coccochometest.dataservices.CocCocService;
import com.example.coccochometest.model.RssFeed;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends MvpBasePresenter<HomeView> {
    @Inject
    CompositeDisposable compositeSubscription;
    @Inject
    CocCocService cocCocService;

    HomePresenter() {
        App.getAppComponent().inject(this);
    }

    void fetchDataFromServer(boolean pullToRefresh) {
        if (!pullToRefresh) {
            showLoading();
        }

        final Disposable disposable = cocCocService.fetchDataFromServer()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::fetchDataSuccess, this::fetchDataError);
        compositeSubscription.add(disposable);
    }

    private void fetchDataSuccess(RssFeed data) {
        if (getView() != null) {
            showContent();
            getView().setData(data);
        }

    }

    private void fetchDataError(Throwable throwable) {
        System.out.println("error");
        showError(throwable);

    }

    private void showLoading() {
        if (getView() != null) {
            getView().showLoading(false);
        }
    }

    private void showContent() {
        if (getView() != null) {
            getView().showContent();
        }
    }

    private void showError(Throwable throwable) {
        if (getView() != null) {
            getView().showError(throwable, false);
        }
    }
}
