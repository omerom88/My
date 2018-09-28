package com.example.omerom88.injection;

import com.example.omerom88.mainscreen.injection.MainScreenComponent;
import com.example.omerom88.mainscreen.injection.MainScreenModule;
import com.example.omerom88.repository.Preferences;
import com.example.omerom88.screens.BasicFragment;
import com.example.omerom88.screens.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by omerom88 on 13-Sep-18
 */
@Singleton
@Component(modules = {AppModule.class, DataModule.class, PortabellaModule.class})
public interface AppComponent {

    void inject(MainActivity target);

    MainScreenComponent plus(MainScreenModule mainScreenModule);

    void inject(BasicFragment target);

    void inject(Preferences preferences);

}
