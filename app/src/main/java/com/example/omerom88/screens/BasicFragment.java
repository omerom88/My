package com.example.omerom88.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.omerom88.application.PortabellaApplication;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import org.greenrobot.eventbus.EventBus;
import javax.inject.Inject;

/**
 * Created by omerom88 on 13-Sep-18
 */
abstract public class BasicFragment extends Fragment {

    @Inject protected EventBus eventBus;

    protected boolean isAttached;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PortabellaApplication.getAppComponent().inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        isAttached = false;
        compositeDisposable.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public void removeDisposable(Disposable disposable) {
        compositeDisposable.remove(disposable);
    }

    public boolean onBackPressed() {
        return false;
    }

    public String getBackStackName() {
        return this.getClass().getName();
    }


}

