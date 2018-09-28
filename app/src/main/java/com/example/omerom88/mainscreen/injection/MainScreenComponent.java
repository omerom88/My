package com.example.omerom88.mainscreen.injection;

import com.example.omerom88.mainscreen.MainScreenFragment;

import dagger.Subcomponent;

/**
 * Created by omerom88 on 13-Sep-18
 */

@Subcomponent(modules = MainScreenModule.class)
public interface MainScreenComponent {

    void inject(MainScreenFragment fragment);

}
