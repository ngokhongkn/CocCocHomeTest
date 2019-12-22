package com.example.coccochometest;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Switch;

import com.example.coccochometest.ui.base.BaseActivity;
import com.example.coccochometest.ui.home.HomeFragment;

public class MainActivity extends BaseActivity {

    private static final String NIGHT_MODE = "night_mode";

    private SharedPreferences mSharedPref;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        // init shared preferences
        mSharedPref = getPreferences(Context.MODE_PRIVATE);

        if (isNightModeEnabled()) {
            setAppTheme(R.style.AppTheme_Base_Night);
        } else {
            setAppTheme(R.style.AppTheme_Base_Light);
        }

        super.onCreate(savedInstanceState);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    protected Fragment createMainFragment() {
        return HomeFragment.newInstance();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        Switch mSwitchNightMode = menu.findItem(R.id.action_night_mode)
                .getActionView().findViewById(R.id.item_switch);

        // Get state from preferences
        if (isNightModeEnabled()) {
            mSwitchNightMode.setChecked(true);
        } else {
            mSwitchNightMode.setChecked(false);
        }

        mSwitchNightMode.setOnCheckedChangeListener((compoundButton, b) -> {
            if (isNightModeEnabled()) {
                setIsNightModeEnabled(false);
                setAppTheme(R.style.AppTheme_Base_Light);
            } else {
                setIsNightModeEnabled(true);
                setAppTheme(R.style.AppTheme_Base_Night);
            }

            // Recreate activity
            recreate();
        });

        return true;
    }

    private void setAppTheme(@StyleRes int style) {
        setTheme(style);
    }

    private boolean isNightModeEnabled() {
        return mSharedPref.getBoolean(NIGHT_MODE, false);
    }

    private void setIsNightModeEnabled(boolean state) {
        SharedPreferences.Editor mEditor = mSharedPref.edit();
        mEditor.putBoolean(NIGHT_MODE, state);
        mEditor.apply();
    }
}
