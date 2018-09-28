package com.example.omerom88.injection;

import android.app.Application;
import android.content.Context;
import android.os.Looper;

import com.example.omerom88.application.Logger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by omerom88 on 13-Sep-18
 */
@Module
public class AppModule {
    private Application application;


    public AppModule(Application application) {

        this.application = application;

    }


    /**
     * The method names for the providers,
     * <p>
     * such as provideContext(),
     * <p>
     * are not important and can be named anything you like.
     * <p>
     * Dagger only looks at the return type.
     */

    @Provides
    @Singleton
    public Context provideContext() {

        return application;

    }


    @Provides
    @Singleton
    public Looper provideMainLooper() {

        return Looper.getMainLooper();

    }

    @Provides
    @Singleton
    public Logger provideLogger() {
        return new Logger();
    }
}
