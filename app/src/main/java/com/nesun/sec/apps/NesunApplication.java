package com.nesun.sec.apps;

import android.app.Application;
import android.content.Context;

/***
 *
 * 能信安Application
 *
 * FM9333
 *
 *
 *
 *
 */
public class NesunApplication extends Application {

    private static NesunApplication nesunApplication;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        nesunApplication = this;
    }

    public static NesunApplication getContext() {
        return nesunApplication;
    }


}
