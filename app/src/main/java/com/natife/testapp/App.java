package com.natife.testapp;

import android.app.Application;

import com.natife.testapp.di.app.AppComponent;
import com.natife.testapp.di.app.DaggerAppComponent;

public class App extends Application {

    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.factory()
                .create(this);
    }
}
