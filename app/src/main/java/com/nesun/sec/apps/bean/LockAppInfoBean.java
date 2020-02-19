package com.nesun.sec.apps.bean;

import android.graphics.drawable.Drawable;

public class LockAppInfoBean {


    // 应用名
    private String appName;

    // 应用图标
    private int appIcon;
    private Drawable draIcon;

    // 应用是否加锁
    private boolean isLock;

    // 应用选择界面，是否选中应用
    private boolean isSelect;

    // 其他属性 包名，MainActivity之类的

    private String packageName;
    private boolean systemApp;// is system app

    // ==========================================

    public LockAppInfoBean(String appname, int appicon){
        appName = appname;
        appIcon = appicon;
    }
    public LockAppInfoBean(String appname, Drawable appicon){
        appName = appname;
        draIcon = appicon;
    }
    public LockAppInfoBean(String appname, Drawable appicon, String packagename){
        appName = appname;
        draIcon = appicon;
        packageName = packagename;
    }
    public LockAppInfoBean(String appname, Drawable appicon, String packagename, boolean issystemapp){
        appName = appname;
        draIcon = appicon;
        systemApp = issystemapp;
    }


    public boolean isLock() {
        return isLock;
    }

    public int getAppIcon() {
        return appIcon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppIcon(int appIcon) {
        this.appIcon = appIcon;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }


    public Drawable getDraIcon() {
        return draIcon;
    }

    public void setDraIcon(Drawable draIcon) {
        this.draIcon = draIcon;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public boolean isSystemApp() {
        return systemApp;
    }

    public void setSystemApp(boolean systemApp) {
        this.systemApp = systemApp;
    }
}
