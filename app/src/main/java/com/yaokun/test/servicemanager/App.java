package com.yaokun.test.servicemanager;

import android.app.Application;

import com.yaokun.test.servicemanager.utils.GlobalStorage;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        GlobalStorage.initGlobalStorage(this);
    }

}
