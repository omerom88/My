package com.example.omerom88.screens;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.omerom88.R;
import com.example.omerom88.animations.SplashScreenFragment;
import com.example.omerom88.application.AddFragmentEvent;
import com.example.omerom88.application.PortabellaApplication;
import com.example.omerom88.mainscreen.MainScreenFragment;
import com.example.omerom88.repository.LocalData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * Created by omerom88 on 13-Sep-18
 */
public class MainActivity extends AppCompatActivity {

    MainScreenFragment fragment;

    @Inject
    EventBus eventBus;

    @Inject
    LocalData prefs;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PortabellaApplication.getAppComponent().inject(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        decorView.setOnSystemUiVisibilityChangeListener(visibility -> {
            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                decorView.setSystemUiVisibility(uiOptions);
            }
        });
        setContentView(R.layout.activity_main);
        checkPermissionAndStartApp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        eventBus.register(this);
        prefs.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
        prefs.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Subscribe
    public void addFragment(AddFragmentEvent event) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (event.isReplace()) {
            transaction.replace(R.id.container, event.getFragment(), null);
        } else {
            transaction.setCustomAnimations(R.anim.fade_in_anim, R.anim.fade_out_anim,
                    R.anim.fade_in_anim, R.anim.fade_out_anim);
            transaction.add(R.id.container, event.getFragment(), null);
        }
        addFragmentToBackStack(event, transaction);
        transaction.commitAllowingStateLoss();
    }

    private void addFragmentToBackStack(AddFragmentEvent event, FragmentTransaction transaction) {
        if (event.isAddToBackStack()) {
            transaction.addToBackStack(event.getFragment().getBackStackName());
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.container);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        BasicFragment currentFragment = (BasicFragment) getCurrentFragment();
        try {
            if (!currentFragment.onBackPressed()) {
                super.onBackPressed();
            }
        } catch (Exception e) {
            Toast.makeText(this,
                    R.string.errorToast, Toast.LENGTH_LONG).show();
        }
    }

    private void checkPermissionAndStartApp() {
        new RxPermissions(this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .doOnNext(granted -> {
                    if (granted) {
                        openMainFragment();
//                        openSplashScreenFragment();
                    } else {
                        finishAndRemoveTask();
                    }
                })
                .subscribe();
    }

    private void openSplashScreenFragment() {
        addFragment(new AddFragmentEvent(SplashScreenFragment.newInstance(), false, false));
    }

    private void openMainFragment() {
        fragment = new MainScreenFragment();
        addFragment(new AddFragmentEvent(fragment, false, false));
    }
}
