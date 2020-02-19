package com.nesun.sec.apps.presenter;

import android.content.Context;
import android.os.Handler;

import com.nesun.sec.apps.bean.AppInfoBean;
import com.nesun.sec.apps.biz.IReadAppsInfoBiz;
import com.nesun.sec.apps.biz.OnReadAppsInfoListener;
import com.nesun.sec.apps.biz.ReadAppsInfoBiz;
import com.nesun.sec.apps.view.biz.IReadAppsInfoView;

import java.util.List;

public class ReadAppsInfoPresenter {

    private IReadAppsInfoBiz iReadAppsInfoBiz;
    private IReadAppsInfoView iReadAppsInfoView;
    private Handler mHandler = new Handler();

    public ReadAppsInfoPresenter(IReadAppsInfoView iReadAppsInfoView){
        this.iReadAppsInfoView = iReadAppsInfoView;
        iReadAppsInfoBiz = new ReadAppsInfoBiz();
    }

    public void readApps(final Context context){
        iReadAppsInfoView.showLoding();
        iReadAppsInfoBiz.readAppsInfo(context, new OnReadAppsInfoListener() {
            @Override
            public void readAppsSuccess(final List<AppInfoBean> apps) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iReadAppsInfoView.hindLoding();
                        iReadAppsInfoView.showReadAppsInfoSuccess(apps);
                    }
                });
            }
            @Override
            public void readAppsFailed(final String errorMessage) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iReadAppsInfoView.hindLoding();
                        iReadAppsInfoView.showReadAppsInfoFaild(errorMessage);
                    }
                });
            }
        });
    }



}
