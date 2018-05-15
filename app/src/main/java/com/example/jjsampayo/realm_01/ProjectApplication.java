package com.example.jjsampayo.realm_01;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ProjectApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("realm-db")
                .encryptionKey("123456789_123456789_123456789_123456789_123456789_123456789_1234".getBytes())
                .schemaVersion(1)
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
