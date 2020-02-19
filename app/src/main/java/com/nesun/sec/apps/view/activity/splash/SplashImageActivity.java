package com.nesun.sec.apps.view.activity.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nesun.sec.apps.R;
import com.nesun.sec.apps.utils.AppUtil;
import com.nesun.sec.apps.view.activity.BaseActivity;
import com.nesun.sec.apps.view.activity.main.MainActivity;
import com.victor.loading.rotate.RotateLoading;

import butterknife.BindView;

/*
 * @author: luckyShane
 * @date: 2019/3/4
 */
public class SplashImageActivity extends BaseActivity {

    // --------------------ui
    @BindView(R.id.splash_img_01)ImageView imageView_splash01;
    @BindView(R.id.rotateloading)RotateLoading rotateLoading01;


    // --------------------data
    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        int dis[] = AppUtil.getScreenDispaly(getApplicationContext());
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imageView_splash01.getLayoutParams();
        params.height = (int) (dis[0]/1.65);
        imageView_splash01.setLayoutParams(params);
        rotateLoading01.start();

        showMain();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash2;
    }

    @Override
    protected int getToolbarId() {
        return -1;
    }


    private void showMain() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashImageActivity.this, MainActivity.class);
                startActivity(intent);
                SplashImageActivity.this.finish();
            }
        }, 1200);
    }


}
