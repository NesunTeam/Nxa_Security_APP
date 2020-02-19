package com.nesun.sec.apps.view.activity.security;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.nesun.sec.apps.R;
import com.nesun.sec.apps.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SecurityScanningResultActivity extends BaseActivity {

    // --------------------ui
    @BindView(R.id.button_sec_scan_result_back)Button button_sec_scan_result_back;
    @BindView(R.id.button_sec_scan_result_setting)Button button_sec_scan_result_setting;

    // --------------------data


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sec_scan_result;
    }

    @Override
    protected int getToolbarId() {
        return R.id.tool_bar_sec_scan_result;
    }

    @OnClick({R.id.button_sec_scan_result_back})
    public void onClickBack(){
        finish();
    }

    @OnClick({R.id.button_sec_scan_result_setting})
    public void onClickSetting(){

    }


}
