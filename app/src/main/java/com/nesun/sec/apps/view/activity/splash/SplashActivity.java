package com.nesun.sec.apps.view.activity.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.nesun.sec.apps.utils.AppUtil;
import com.nesun.sec.apps.view.activity.BaseActivity;

import java.util.Random;

/*
 * @author: luckyShane
 * @date: 2019/3/4
 */
public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkStartActivity();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return -1;
    }

    @Override
    protected int getToolbarId() {
        return -1;
    }

    /**
     * 判断用户不是第一次启动
     */
    private  void checkStartActivity(){
        try {
            String isFirstRun = AppUtil.isFirstRun(getApplicationContext());
            if (!TextUtils.isEmpty(isFirstRun)){
                Intent intent = new Intent(SplashActivity.this, SplashImageActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }else {
                Intent intent = new Intent(SplashActivity.this, SplashVideoActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private  void checkStartActivity2(){
        try {
            int index  = new Random().nextInt(9);
            if (index<5){
                Intent intent = new Intent(SplashActivity.this, SplashVideoActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }else {
                Intent intent = new Intent(SplashActivity.this, SplashImageActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
