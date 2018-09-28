package com.example.omerom88.mainscreen;

import android.os.Handler;

import com.example.omerom88.application.Logger;
import com.example.omerom88.repository.LocalData;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Created by omerom88 on 13-Sep-18
 */
public class MainScreenPresenter implements MainScreenContract.UserActionsListener {

    private final LocalData prefs;

    private final EventBus eventBus;

    private final Logger log;

    private MainScreenContract.View view;

    @Inject
    MainScreenPresenter(MainScreenContract.View view, LocalData prefs, EventBus eventBus, Logger logger) {
        this.view = view;
        this.prefs = prefs;
        this.eventBus = eventBus;
        this.log = logger;
    }
}
