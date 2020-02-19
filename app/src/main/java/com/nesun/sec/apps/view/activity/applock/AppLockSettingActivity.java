package com.nesun.sec.apps.view.activity.applock;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.nesun.sec.apps.R;
import com.nesun.sec.apps.constant.Constant;
import com.nesun.sec.apps.utils.AppUtil;
import com.nesun.sec.apps.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AppLockSettingActivity  extends BaseActivity {

    // --------------------ui
    @BindView(R.id.button_applock_setting_back)Button button_back;
    @BindView(R.id.switch_applock_setting_01)Switch switch_applock_setting_01;
    @BindView(R.id.textView_applock_setting_sub_01)TextView textView_applock_setting_sub_01;
    @BindView(R.id.textView_applock_setting_sub_02)TextView textView_applock_setting_sub_02;
    @BindView(R.id.textView_applock_setting_sub_03)TextView textView_applock_setting_sub_03;


    // --------------------data

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        if (AppUtil.isApplockStatus(getApplicationContext()))switch_applock_setting_01.setChecked(true);
        else switch_applock_setting_01.setChecked(false);

        switch_applock_setting_01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                AppUtil.setAppLockStatus(getApplicationContext(),b);
                if (b) {
                    Intent intent = new Intent(Constant.ACTION_OPEN_APPLOCK);
                    sendBroadcast(intent);
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_applocksetting;
    }

    @Override
    protected int getToolbarId() {
        return R.id.tool_bar_applock_setting;
    }



    @OnClick(R.id.button_applock_setting_back)
    public void onClickUserBack(){
        this.finish();
    }
    @OnClick(R.id.switch_applock_setting_01)
    public void onClickApplockSettingSwitch(){

    }
    @OnClick(R.id.textView_applock_setting_sub_01)
    public void onClickSettingOption01(){
    }
    @OnClick(R.id.textView_applock_setting_sub_02)
    public void onClickSettingOption02(){
        Intent intent = new Intent(this, AppLockSettingSubActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.textView_applock_setting_sub_03)
    public void onClickSettingOption03(){
    }


}
