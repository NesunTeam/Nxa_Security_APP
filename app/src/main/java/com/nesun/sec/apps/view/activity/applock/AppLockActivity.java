package com.nesun.sec.apps.view.activity.applock;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nesun.sec.apps.R;
import com.nesun.sec.apps.bean.AppInfoBean;
import com.nesun.sec.apps.bean.LockAppInfoBean;
import com.nesun.sec.apps.dao.AppLockDao;
import com.nesun.sec.apps.presenter.ReadAppsInfoPresenter;
import com.nesun.sec.apps.service.AppLockService;
import com.nesun.sec.apps.utils.ActivityManagerUtils;
import com.nesun.sec.apps.view.activity.BaseActivity;
import com.nesun.sec.apps.view.biz.IReadAppsInfoView;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class AppLockActivity extends BaseActivity implements IReadAppsInfoView {

    // --------------------ui
    @BindView(R.id.button_applock_back) Button button_applock_back;
    @BindView(R.id.button_applock_setting) Button button_applock_setting;
    @BindView(R.id.button_applock_addapp) Button button_applock_addapp;
    @BindView(R.id.listView_applock_01) ListView listView_applock_01;
    @BindView(R.id.imageView_applock_tips01)ImageView imageView_applock_tips01;
    @BindView(R.id.textView_applock_tips01)TextView textView_applock_tips01;
    @BindView(R.id.rotateloading_applock_loding) RotateLoading rotateloading_applock_loding;

    // --------------------data
    public List<LockAppInfoBean> cats = new ArrayList<LockAppInfoBean>();
    private List<AppInfoBean> systemApps = new ArrayList<AppInfoBean>();
    private List<AppInfoBean> userApps = new ArrayList<AppInfoBean>();
    public AppLockAdapter appLockAdapter;
    private AppLockDao dao = new AppLockDao(this);
    private ReadAppsInfoPresenter readAppsInfoPresenter = new ReadAppsInfoPresenter(this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        appLockAdapter = new AppLockAdapter(AppLockActivity.this, R.layout.list_applock, cats);
        listView_applock_01.setAdapter(appLockAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_applock;
    }

    @Override
    protected int getToolbarId() {
        return R.id.tool_bar_applock;
    }

    @Override
    public void showLoding() {
        rotateloading_applock_loding.start();// 开启进度条
        imageView_applock_tips01.setVisibility(View.INVISIBLE); // 关闭空提示
        textView_applock_tips01.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hindLoding() {
        rotateloading_applock_loding.stop(); //停止进度条
    }

    @Override
    public void showReadAppsInfoSuccess(List<AppInfoBean> apps) {
        initAppsInfo(apps);
        appLockAdapter.notifyDataSetChanged();// 刷新通知
        if (cats.size()<=0) { // 判断不存在应用，提示
            imageView_applock_tips01.setVisibility(View.VISIBLE);
            textView_applock_tips01.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showReadAppsInfoFaild(String message) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        readAppsInfoPresenter.readApps(this); // MVP加载数据
        ActivityManagerUtils.checkService(this, AppLockService.class);// 检查服务是否启动，没有则启动
    }

    /**
     * 初始化App信息
     *
     * @param apps
     */
    private void initAppsInfo(List<AppInfoBean> apps) {
        systemApps.clear();
        userApps.clear();
        for (AppInfoBean app : apps) {// add to list
            if (app.isSystemApp()) systemApps.add(app);
            else userApps.add(app);
        }
        cats.clear();
        for (AppInfoBean tmpBean:userApps){
            if (dao.isExists(tmpBean.getPackageName()))
                cats.add(new LockAppInfoBean(tmpBean.getName(), tmpBean.getIcon(),tmpBean.getPackageName()));
        }
        for (AppInfoBean tmpBean:systemApps){
            if (dao.isExists(tmpBean.getPackageName()))
                cats.add(new LockAppInfoBean(tmpBean.getName(), tmpBean.getIcon(),tmpBean.getPackageName(),tmpBean.isSystemApp()));
        }
    }


    @OnClick(R.id.button_applock_back)
    public void onClickUserBack(){
        this.finish();
    }
    @OnClick(R.id.button_applock_setting)
    public void onClickApplockSet(){
        Intent intent = new Intent(this, AppLockSettingActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.button_applock_addapp)
    public void onClickApplockAdd(){
        Intent intent = new Intent(this, AddAppLockActivity.class);
        startActivity(intent);
    }
    @OnItemClick(R.id.listView_applock_01)
    public void listItemClick(int position) {
    }

    public void removeButtonClick(View v){
        int position = listView_applock_01.getPositionForView(v);
        LockAppInfoBean lockAppInfoBean = cats.get(position);//获取ID点击对应的App信息
        boolean isSuccess = dao.delete(lockAppInfoBean.getPackageName());// 删除SQL数据
        cats.remove(position); //数组删除App
        appLockAdapter.notifyDataSetChanged();// refresh ListView// 刷新UI

        if (cats.size()<=0){ // 这里界面展示，如果清空了列表，要展示提示
            imageView_applock_tips01.setVisibility(View.VISIBLE);
            textView_applock_tips01.setVisibility(View.VISIBLE);
        }
    }


}
