package com.nesun.sec.apps.biz;

import android.content.Context;

import com.nesun.sec.apps.bean.AppInfoBean;
import com.nesun.sec.apps.engine.AppManagerEngine;

import java.util.List;

public class ReadAppsInfoBiz implements IReadAppsInfoBiz {


    private Thread initDateThread;// thread operation

    @Override
    public void readAppsInfo(final Context context, final OnReadAppsInfoListener onReadAppsInfoListener) {

        if (context==null){
            onReadAppsInfoListener.readAppsFailed("context is null.");
            return;
        }

        if (null != initDateThread && initDateThread.isAlive()) {return;}
        initDateThread = new Thread() {
            @Override
            public void run() {
                AppManagerEngine.getInstalledAppInfo(
                        context,
                        new AppManagerEngine.AppInfoListener() {
                            @Override
                            public void onGetInfoCompleted(List<AppInfoBean> apps) {
                                onReadAppsInfoListener.readAppsSuccess(apps);
                            }
                        });
            }
        };
        initDateThread.start();

    }



}
