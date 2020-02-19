package com.nesun.sec.apps.view.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nesun.sec.apps.R;
import com.nesun.sec.apps.view.activity.BaseActivity;
import com.nesun.sec.apps.view.activity.setting.FeedbackActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class UserInfoActivity extends BaseActivity {

    // --------------------ui
    @BindView(R.id.user_zhiwen_img)ImageView imageView_zhiwen;
    @BindView(R.id.button_user_back)Button button_user_back;
    @BindView(R.id.button_user_setting)Button button_user_setting;
    @BindView(R.id.button_user_fankui)Button button_user_fankui;
    @BindView(R.id.textView_username)TextView textView_userName;

    @BindView(R.id.linearyout_user_sub01)LinearLayout linearLayout_user_sub01;
    @BindView(R.id.linearyout_user_sub02)LinearLayout linearLayout_user_sub02;
    @BindView(R.id.linearyout_user_sub03)LinearLayout linearLayout_user_sub03;

    @BindView(R.id.linearyout_user_biaoti01)LinearLayout linearLayout_user_biaoti01;
    @BindView(R.id.linearyout_user_biaoti02)LinearLayout linearLayout_user_biaoti02;
    @BindView(R.id.linearyout_user_biaoti03)LinearLayout linearLayout_user_biaoti03;
    @BindView(R.id.linearyout_user_biaoti04)LinearLayout linearLayout_user_biaoti04;


    // --------------------data



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        Animation animation = AnimationUtils.loadAnimation(UserInfoActivity.this,R.anim.alpha_img);
        imageView_zhiwen.startAnimation(animation);// 设置动画
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected int getToolbarId() {
        return R.id.tool_bar_user;
    }


    @OnClick(R.id.button_user_back)
    public void onClickUserBack(){
        UserInfoActivity.this.finish();
    }
    @OnClick(R.id.button_user_setting)
    public void onClickUserSetting(){
        Intent intent = new Intent(UserInfoActivity.this, UserSettingActivity.class);
        UserInfoActivity.this.startActivity(intent);
    }
    @OnClick(R.id.button_user_fankui)
    public void onClickUserFankui(){
        Intent intent = new Intent(UserInfoActivity.this, FeedbackActivity.class);
        UserInfoActivity.this.startActivity(intent);
    }
    @OnClick(R.id.user_zhiwen_img)
    public void onClickUserPhoto(){
        Log.e("FM9333","UserPhoto-111111111111111");
        Intent intent = new Intent(UserInfoActivity.this, UserLoginActivity.class);
        UserInfoActivity.this.startActivity(intent);
    }
    @OnClick(R.id.textView_username)
    public void onClickUserName(){
        Log.e("FM9333","UserName-111111111111111");
        Intent intent = new Intent(UserInfoActivity.this, UserLoginActivity.class);
        UserInfoActivity.this.startActivity(intent);
    }
    @OnClick(R.id.linearyout_user_sub01)
    public void onClickUserModule01(){
        Log.e("FM9333","UserModule-111111111111111");
    }
    @OnClick(R.id.linearyout_user_sub02)
    public void onClickUserModule02(){
        Log.e("FM9333","UserModule-222222222222222");
    }
    @OnClick(R.id.linearyout_user_sub03)
    public void onClickUserModule03(){
        Log.e("FM9333","UserModule-333333333333333");
    }
    @OnClick(R.id.linearyout_user_biaoti01)
    public void onClickUserAds01(){
        Log.e("FM9333","UserAds-111111111111111");
    }
    @OnClick(R.id.linearyout_user_biaoti02)
    public void onClickUserAds02(){
        Log.e("FM9333","UserAds-222222222222222");
    }
    @OnClick(R.id.linearyout_user_biaoti03)
    public void onClickUserAds03(){
        Log.e("FM9333","UserAds-333333333333333");
    }
    @OnClick(R.id.linearyout_user_biaoti04)
    public void onClickUserAds04(){
        Log.e("FM9333","UserAds-444444444444444");
    }


}
