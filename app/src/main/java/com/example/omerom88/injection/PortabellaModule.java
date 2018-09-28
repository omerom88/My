package com.example.omerom88.injection;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by omerom88 on 13-Sep-18
 */
@Module
public class PortabellaModule {

    @Provides
    @Singleton
    public Scheduler provideScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }

}
