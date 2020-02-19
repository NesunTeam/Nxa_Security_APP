package com.nesun.sec.apps.view.activity.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.nesun.sec.apps.R;
import com.nesun.sec.apps.constant.Constant;
import com.nesun.sec.apps.view.activity.AppActivityManager;
import com.nesun.sec.apps.view.activity.BaseActivity;
import com.nesun.sec.apps.view.activity.gesture.GestureLockActivity;
import com.nesun.sec.apps.view.activity.setting.AboutUsActivity;
import com.nesun.sec.apps.view.activity.setting.UserProtocolActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class UserSettingActivity extends BaseActivity {


    // --------------------ui
    @BindView(R.id.button_user_setting_back)Button button_back;
    @BindView(R.id.switch_setting_01)Switch switch_setting_sub01;
    @BindView(R.id.switch_setting_02)Switch switch_setting_sub02;
    @BindView(R.id.switch_setting_03)Switch switch_setting_sub03;

    @BindView(R.id.textView_setting_sub_01)TextView textView_setting_sub01;
    @BindView(R.id.textView_setting_sub_02)TextView textView_setting_sub02;
    @BindView(R.id.textView_setting_sub_03)TextView textView_setting_sub03;
    @BindView(R.id.textView_setting_sub_04)TextView textView_setting_sub04;
    @BindView(R.id.textView_setting_sub_05)TextView textView_setting_sub05;
    @BindView(R.id.textView_setting_sub_06)TextView textView_setting_sub06;
    @BindView(R.id.textView_setting_sub_07)TextView textView_setting_sub07;
    @BindView(R.id.textView_setting_sub_08)TextView textView_setting_sub08;
    @BindView(R.id.textView_setting_sub_09)TextView textView_setting_sub09;
    @BindView(R.id.textView_setting_sub_10)TextView textView_setting_sub10;
    @BindView(R.id.textView_setting_sub_11)TextView textView_setting_sub11;
    @BindView(R.id.textView_setting_sub_12)TextView textView_setting_sub12;
    @BindView(R.id.textView_setting_sub_13)TextView textView_setting_sub13;
    @BindView(R.id.textView_setting_sub_14)TextView textView_setting_sub14;
    @BindView(R.id.textView_setting_sub_15)TextView textView_setting_sub15;
    @BindView(R.id.textView_setting_sub_16)TextView textView_setting_sub16;
    @BindView(R.id.textView_setting_sub_17)TextView textView_setting_sub17;
    @BindView(R.id.textView_setting_sub_18)TextView textView_setting_sub18;

    // --------------------data



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        switch_setting_sub01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("Fm9333","switch_setting_sub03===="+b);
            }
        });
        switch_setting_sub02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("Fm9333","switch_setting_sub03===="+b);
            }
        });
        switch_setting_sub03.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("Fm9333","switch_setting_sub03===="+b);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected int getToolbarId() {
        return R.id.tool_bar_user_setting;
    }



    @OnClick(R.id.switch_setting_01)
    public void onClickUserSettingSwitch01(){ }

    @OnClick(R.id.switch_setting_02)
    public void onClickUserSettingSwitch02(){ }

    @OnClick(R.id.switch_setting_03)
    public void onClickUserSettingSwitch03(){ }

    @OnClick(R.id.button_user_setting_back)
    public void onClickUserSettingBack(){
        finish();
    }
    @OnClick(R.id.textView_setting_sub_01)
    public void onClickSettingOption01(){
        Log.e("Fm9333","onClickSettingOption01=======权限管理");
    }
    @OnClick(R.id.textView_setting_sub_02)
    public void onClickSettingOption02(){
        Log.e("Fm9333","onClickSettingOption01=======通知栏图标");
    }
    @OnClick(R.id.textView_setting_sub_03)
    public void onClickSettingOption03(){
        Log.e("Fm9333","onClickSettingOption01=======快捷中心开关");
    }
    @OnClick(R.id.textView_setting_sub_04)
    public void onClickSettingOption04(){
        Log.e("Fm9333","onClickSettingOption01=======验证码识别开关");
    }
    @OnClick(R.id.textView_setting_sub_05)
    public void onClickSettingOption05(){
        Log.e("Fm9333","onClickSettingOption01=======小火箭与悬浮窗");
    }
    @OnClick(R.id.textView_setting_sub_06)
    public void onClickSettingOption06(){
        Log.e("Fm9333","onClickSettingOption01=======安全检测");
    }
    @OnClick(R.id.textView_setting_sub_07)
    public void onClickSettingOption07(){
        Log.e("Fm9333","onClickSettingOption01=======清理加速保护名单");
        Intent intent = new Intent(this, GestureLockActivity.class);
        intent.putExtra(Constant.START_ACTIVITY_TYPE_NAME, Constant.START_ACTIVITY_TYPE_3);
        startActivity(intent);
    }
    @OnClick(R.id.textView_setting_sub_08)
    public void onClickSettingOption08(){
        Log.e("Fm9333","onClickSettingOption01=======软件管理");
    }
    @OnClick(R.id.textView_setting_sub_09)
    public void onClickSettingOption09(){
        Log.e("Fm9333","onClickSettingOption01=======骚扰拦截");
    }
    @OnClick(R.id.textView_setting_sub_10)
    public void onClickSettingOption10(){
        Log.e("Fm9333","onClickSettingOption01=======安全短信");
    }
    @OnClick(R.id.textView_setting_sub_11)
    public void onClickSettingOption11(){
        Log.e("Fm9333","onClickSettingOption01=======来电秀");
    }
    @OnClick(R.id.textView_setting_sub_12)
    public void onClickSettingOption12(){
        Log.e("Fm9333","onClickSettingOption01=======隐私保护");
    }
    @OnClick(R.id.textView_setting_sub_13)
    public void onClickSettingOption13(){
        Log.e("Fm9333","onClickSettingOption01=======手机防盗");
    }
    @OnClick(R.id.textView_setting_sub_14)
    public void onClickSettingOption14(){
        Log.e("Fm9333","onClickSettingOption01=======流量监控");
    }
    @OnClick(R.id.textView_setting_sub_15)
    public void onClickSettingOption15(){
        Log.e("Fm9333","onClickSettingOption01=======更新设置");
    }
    @OnClick(R.id.textView_setting_sub_16)
    public void onClickSettingOption16(){
        Log.e("Fm9333","onClickSettingOption01=======使用教程");
        Intent intent = new Intent(this, UserProtocolActivity.class);
        intent.putExtra(Constant.USER_WEB_TYPE,Constant.USER_WEB_TYPE_HELP);
        this.startActivity(intent);
    }
    @OnClick(R.id.textView_setting_sub_17)
    public void onClickSettingOption17(){
        Log.e("Fm9333","onClickSettingOption01=======关于");
        Intent intent = new Intent(UserSettingActivity.this, AboutUsActivity.class);
        UserSettingActivity.this.startActivity(intent);
    }
    @OnClick(R.id.textView_setting_sub_18)
    public void onClickSettingOption18(){
        Log.e("Fm9333","onClickSettingOption01=======退出");


//        MyAlertDialog myAlertDialog = new MyAlertDialog(this).builder()
//                .setTitle("确认吗？")
//                .setMsg("退出应用")
//                .setPositiveButton("确认", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                    }
//                }).setNegativeButton("取消", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                    }
//                });
//        myAlertDialog.show();




//        ConfirmDialog confirmDialog = new ConfirmDialog(this);
//        confirmDialog.setLogoImg(R.mipmap.dialog_notice).setMsg("是否退出应用？");
//        confirmDialog.setClickListener(new ConfirmDialog.OnBtnClickListener() {
//            @Override
//            public void ok() {
//                AppActivityManager.getInstance().AppExit(getApplicationContext());
//            }
//            @Override
//            public void cancel() {
//
//            }
//        });
//        confirmDialog.show();


        // ----------------------- 新Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("提示");
        builder.setMessage("是否退出应用？");
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                AppActivityManager.getInstance().AppExit(getApplicationContext());
            }
        });
        builder.create().show();

    }






}
