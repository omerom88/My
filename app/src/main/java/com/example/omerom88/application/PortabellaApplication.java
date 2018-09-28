package com.example.omerom88.application;


import android.app.Application;

import com.example.omerom88.R;
import com.example.omerom88.injection.AppComponent;
import com.example.omerom88.injection.AppModule;
import com.example.omerom88.injection.DaggerAppComponent;
import com.example.omerom88.injection.PortabellaModule;

import io.reactivex.annotations.NonNull;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;



/**
 * Created by omerom88 on 13-Sep-18
 */
public class PortabellaApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initDagger();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Reef.otf")
                .setFontAttrId(R.attr.fontPath)
                .build());

    }

    @NonNull
    private AppComponent initDagger() {
        return DaggerAppComponent.builder()
                                 .appModule(new AppModule(this))
                                 .portabellaModule(new PortabellaModule())
                                 .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
