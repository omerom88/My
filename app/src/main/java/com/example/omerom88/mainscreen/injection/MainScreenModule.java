package com.example.omerom88.mainscreen.injection;

import android.os.Handler;
import android.os.Looper;

import com.example.omerom88.mainscreen.MainScreenContract;
import com.example.omerom88.mainscreen.MainScreenPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by omerom88 on 13-Sep-18
 */

@Module
public class MainScreenModule {

    private final MainScreenContract.View view;

    public MainScreenModule(MainScreenContract.View view) {
        this.view = view;
    }

    @Provides
    public MainScreenContract.UserActionsListener provideTempMainPresenter(MainScreenPresenter presenter){
        return presenter;
    }

    @Provides
    public MainScreenContract.View providesTempMainView(){
        return view;
    }

    @Provides
    public Handler providesHandler(){
        return new Handler(Looper.getMainLooper());
    }

}
