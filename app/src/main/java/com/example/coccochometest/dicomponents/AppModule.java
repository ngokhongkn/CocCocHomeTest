package com.example.coccochometest.dicomponents;

import android.app.Application;
import android.content.Context;

import com.example.coccochometest.App;
import com.example.coccochometest.datamanager.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class AppModule {
    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    Context appContext() {
        return app;
    }

    @Provides
    @Singleton
    CompositeDisposable provideCompositeSubscription() {
        return new CompositeDisposable();
    }

    @Provides
    @Singleton
    DataManager provideDataManager() {
        return new DataManager();
    }
}
