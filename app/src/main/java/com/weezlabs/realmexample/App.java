package com.weezlabs.realmexample;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {
    private static Context sAppContext;


    public static Context getAppContext() {
        return sAppContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sAppContext = getApplicationContext();

        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);
    }
}
