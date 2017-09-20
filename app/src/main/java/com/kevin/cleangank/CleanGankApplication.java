package com.kevin.cleangank;

import android.app.Application;

import com.kevin.cleangank.model.app.CleanGank;

import timber.log.Timber;


/**
 * Created by zhouwenkai on 2017/8/10.
 */

public class CleanGankApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CleanGank.init(this)
                .withApiHost("http://gank.io/api/")
                .withIsReleased(false)
                .configure();

        Timber.plant(new Timber.DebugTree());
    }
}
