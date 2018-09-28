package com.example.omerom88.mainscreen;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.omerom88.R;
import com.example.omerom88.application.PortabellaApplication;
import com.example.omerom88.mainscreen.injection.MainScreenModule;
import com.example.omerom88.screens.BasicFragment;
import com.example.omerom88.settings.FragmentExitEvent;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

/**
 * Created by omerom88 on 13-Sep-18
 */
public class MainScreenFragment extends BasicFragment implements MainScreenContract.View {

    private Disposable permissionDisposable;

    @Inject
    public MainScreenContract.UserActionsListener presenter;


    public MainScreenFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PortabellaApplication.getAppComponent()
                         .plus(new MainScreenModule(this))
                         .inject(this);
        eventBus.register(this);

        checkPermissions();

    }

    @Subscribe
    public void onFragmentExit(FragmentExitEvent event) {

    }

    private void checkPermissions() {
        if (!checkPermission()) {
            FragmentActivity activity = getActivity();
            permissionDisposable = new RxPermissions(activity)
                    .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE)
                    .subscribe(granted -> {
                        if (granted) {
                            Toast.makeText(getContext(), "we can start", Toast
                                    .LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Permission not granted", Toast
                                    .LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        eventBus.unregister(this);
        if (permissionDisposable != null) {
            permissionDisposable.dispose();
        }
    }

    private boolean checkPermission() {
        Context context = getContext();
        if (context != null) {
            int permissionCheckWrite = ContextCompat.checkSelfPermission(context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int permissionCheckRead = ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE);
            return permissionCheckWrite == android.content.pm.PackageManager.PERMISSION_GRANTED
                    && permissionCheckRead == android.content.pm.PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_main_screen_portrait, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }
}
