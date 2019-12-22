package com.example.coccochometest;

import android.app.Application;

import com.example.coccochometest.dicomponents.AppComponent;
import com.example.coccochometest.dicomponents.AppModule;
import com.example.coccochometest.dicomponents.DaggerAppComponent;
import com.example.coccochometest.modules.NetModule;

public class App extends Application {
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        /*Init Dagger DI*/
        appComponent = DaggerAppComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)).netModule(new NetModule())
                .build();

        getAppComponent().inject(this);

    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
