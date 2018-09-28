package com.example.omerom88.injection;

import com.example.omerom88.repository.LocalData;
import com.example.omerom88.repository.Preferences;
import com.google.gson.Gson;
import javax.inject.Singleton;

import dagger.Module;
import retrofit2.Retrofit;
import okhttp3.OkHttpClient;
import dagger.Provides;

/**
 * Created by omerom88 on 13-Sep-18
 */
@Module
public class DataModule {
    @Singleton
    @Provides
    public LocalData provideLocalData(Preferences preferences) {
        return preferences;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .build();
    }
}
