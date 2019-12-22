package com.example.coccochometest.dicomponents;

import com.example.coccochometest.App;
import com.example.coccochometest.modules.NetModule;
import com.example.coccochometest.ui.home.HomePresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {
    void inject(App app);

    void inject(HomePresenter homePresenter);
}