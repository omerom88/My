package com.example.omerom88.injection;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by omerom88 on 13-Sep-18
 */
@Singleton
@Component(modules={DataModule.class})
public interface DataComponent {
}
