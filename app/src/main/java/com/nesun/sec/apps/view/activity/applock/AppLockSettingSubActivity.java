package com.nesun.sec.apps.view.activity.applock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nesun.sec.apps.R;
import com.nesun.sec.apps.constant.Constant;
import com.nesun.sec.apps.utils.AppUtil;
import com.nesun.sec.apps.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AppLockSettingSubActivity extends BaseActivity {

    // --------------------ui
    @BindView(R.id.button_applock_setting_sub_back)Button button_applock_setting_sub_back;
    @BindView(R.id.textView_applock_setting_sub_sub_01)TextView textView_applock_setting_sub_sub_01;
    @BindView(R.id.textView_applock_setting_sub_sub_02)TextView textView_applock_setting_sub_sub_02;
    @BindView(R.id.imageView_applock_setting_sub_01)ImageView imageView_applock_setting_sub_01;
    @BindView(R.id.imageView_applock_setting_sub_02)ImageView imageView_applock_setting_sub_02;

    // --------------------data

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        // 检查当前加锁类型
        if (AppUtil.getApplockType(getApplicationContext())== Constant.KEY_APPLOCK_TYPE_1){
            imageView_applock_setting_sub_01.setVisibility(View.VISIBLE);
            imageView_applock_setting_sub_02.setVisibility(View.INVISIBLE);
        }else {
            imageView_applock_setting_sub_01.setVisibility(View.INVISIBLE);
            imageView_applock_setting_sub_02.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_applocksetting_sub;
    }

    @Override
    protected int getToolbarId() {
        return R.id.tool_bar_applock_setting_sub;
    }


    @OnClick(R.id.button_applock_setting_sub_back)
    public void onClickUserBack(){
        this.finish();
    }

    @OnClick(R.id.textView_applock_setting_sub_sub_01)
    public void onClickSettingOption01(){
        imageView_applock_setting_sub_01.setVisibility(View.VISIBLE);
        imageView_applock_setting_sub_02.setVisibility(View.INVISIBLE);
        AppUtil.setApplockType(getApplicationContext(),Constant.KEY_APPLOCK_TYPE_1);
    }
    @OnClick(R.id.textView_applock_setting_sub_sub_02)
    public void onClickSettingOption02(){
        imageView_applock_setting_sub_01.setVisibility(View.INVISIBLE);
        imageView_applock_setting_sub_02.setVisibility(View.VISIBLE);
        AppUtil.setApplockType(getApplicationContext(),Constant.KEY_APPLOCK_TYPE_2);
    }


}
