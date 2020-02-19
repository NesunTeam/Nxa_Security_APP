package com.nesun.sec.apps.biz;

import com.nesun.sec.apps.bean.AppInfoBean;

import java.util.List;

public interface OnReadAppsInfoListener {


    void readAppsSuccess(List<AppInfoBean> apps);

    void readAppsFailed(String errorMessage);


}
