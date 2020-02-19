package com.nesun.sec.apps.view.activity.setting;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.nesun.sec.apps.R;
import com.nesun.sec.apps.constant.Constant;
import com.nesun.sec.apps.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutUsActivity extends BaseActivity {

    // --------------------ui
    @BindView(R.id.button_user_setting_aboutus_back)Button button_bakc;
    @BindView(R.id.textView_aboutus_title)TextView textView_aboutus_title;
    @BindView(R.id.textView_setting_aboutus_01)TextView textView_setting_aboutus_01;
    @BindView(R.id.textView_setting_aboutus_02)TextView textView_setting_aboutus_02;
    @BindView(R.id.textView_setting_aboutus_03)TextView textView_setting_aboutus_03;
    @BindView(R.id.textView_setting_aboutus_04)TextView textView_setting_aboutus_04;

    // --------------------data



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        Typeface fontFace = Typeface.createFromAsset(getAssets(), "fmzyht.ttf");
        textView_aboutus_title.setTypeface(fontFace);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_aboutus;
    }

    @Override
    protected int getToolbarId() {
        return R.id.tool_bar_user_setting_aboutus;
    }




    @OnClick({R.id.button_user_setting_aboutus_back})
    public void onClickBack(){
        finish();
    }

    @OnClick(R.id.textView_setting_aboutus_01)
    public void onClickSettingAboutusOption01(){
        Log.e("Fm9333","onClickSettingAboutusOption01=======粉丝论坛");
        Intent intent = new Intent(AboutUsActivity.this, UserProtocolActivity.class);
        intent.putExtra(Constant.USER_WEB_TYPE,Constant.USER_WEB_TYPE_FANS);
        AboutUsActivity.this.startActivity(intent);
    }
    @OnClick(R.id.textView_setting_aboutus_02)
    public void onClickSettingAboutusOption02(){
        Log.e("Fm9333","onClickSettingAboutusOption02=======推荐好友");
    }
    @OnClick(R.id.textView_setting_aboutus_03)
    public void onClickSettingAboutusOption03(){
        Log.e("Fm9333","onClickSettingAboutusOption03=======使用协议");
        Intent intent = new Intent(AboutUsActivity.this, UserProtocolActivity.class);
        intent.putExtra(Constant.USER_WEB_TYPE,Constant.USER_WEB_TYPE_PROTOCOL);
        AboutUsActivity.this.startActivity(intent);
    }
    @OnClick(R.id.textView_setting_aboutus_04)
    public void onClickSettingAboutusOption04(){
        Log.e("Fm9333","onClickSettingAboutusOption04=======官方网站");
        Intent intent = new Intent(AboutUsActivity.this, UserProtocolActivity.class);
        intent.putExtra(Constant.USER_WEB_TYPE,Constant.USER_WEB_TYPE_GUAN);
        AboutUsActivity.this.startActivity(intent);
    }



}
