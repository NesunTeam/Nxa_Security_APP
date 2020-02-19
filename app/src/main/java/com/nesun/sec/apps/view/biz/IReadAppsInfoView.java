package com.nesun.sec.apps.view.biz;

import com.nesun.sec.apps.bean.AppInfoBean;

import java.util.List;

public interface IReadAppsInfoView {
    void showLoding();
    void hindLoding();
    void showReadAppsInfoSuccess(List<AppInfoBean> apps);
    void showReadAppsInfoFaild(String message);
}
