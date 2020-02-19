package com.nesun.sec.apps.view.activity.gesture;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nesun.sec.apps.R;
import com.nesun.sec.apps.constant.Constant;
import com.nesun.sec.apps.utils.AppUtil;
import com.nesun.sec.apps.view.activity.BaseActivity;
import com.nesun.sec.apps.view.customview.GestureContentView;
import com.nesun.sec.apps.view.customview.GestureDrawline;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 * 应用锁触发 解锁界面
 *
 *
 */
public class GestureLockCheckActivity extends BaseActivity {

    // --------------------ui
    @BindView(R.id.button_gesturelock_check_back)Button button_back;
    @BindView(R.id.textView_gesturelock_check_tip01) TextView textView_gesturelock_tip01;
    @BindView(R.id.gesturelock_container_check)FrameLayout gesturelock_container;

    // --------------------data
    private GestureContentView mGestureContentView;
    private String packageName;
    private HomeKeyDownReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEvent();
    }

    @Override
    protected void initView() {
        initUI();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gesturelock_check;
    }

    @Override
    protected int getToolbarId() {
        return R.id.tool_bar_gesturelock_check;
    }

    @Override
    protected void initData() {
        super.initData();
        Bundle extras = getIntent().getExtras();
        if(null == extras) {
            return;
        }
        packageName = extras.getString(Constant.EXTRA_LOCKED_APP_PACKAGE_NAME);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);// 注销广播
    }


    private void initEvent(){
        // 注册广播，监听用户点击返回主页
        IntentFilter filter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        receiver = new HomeKeyDownReceiver();
        registerReceiver(receiver, filter);
    }

    private void initUI() {
        // 初始化一个显示各个点的viewGroup
        mGestureContentView = new GestureContentView(this, false, "", new GestureDrawline.GestureCallBack() {
            @Override
            public void onGestureCodeInput(String inputCode) {

                if (!isInputPassValidate(inputCode)) {
                    textView_gesturelock_tip01.setText(R.string.textview_title_gesturelock_check_tip02);
                    mGestureContentView.clearDrawlineState(0L);
                    return;
                }
                if (checkPassword(inputCode)) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(Constant.ACTION_UNLOCK_APP);
                                intent.putExtra(Constant.EXTRA_LOCKED_APP_PACKAGE_NAME, packageName);
                                sendBroadcast(intent);
                                mGestureContentView.clearDrawlineState(0L);
                                finish();
                            }
                        }, 300);
                    } else {
                        textView_gesturelock_tip01.setText(R.string.textview_title_gesturelock_check_tip06);
                        // 左右移动动画
                        Animation shakeAnimation = AnimationUtils.loadAnimation(GestureLockCheckActivity.this, R.anim.shake);
                        textView_gesturelock_tip01.startAnimation(shakeAnimation);
                        // 保持绘制的线，0.4秒后清除
                        mGestureContentView.clearDrawlineState(500);
                    }
            }

            @Override
            public void checkedSuccess() {
            }

            @Override
            public void checkedFail() {
            }
        });
        // 设置手势解锁显示到哪个布局里面
        mGestureContentView.setParentView(gesturelock_container);
    }

    /**
     * 检查输入密码是否正确
     * @param inputCode
     * @return
     */
    private boolean checkPassword(String inputCode) {
        if (TextUtils.isEmpty(inputCode)) return false;
        String saveCode = AppUtil.getGesturePassword(getApplicationContext());
        if (inputCode.equals(saveCode)){
            return true;
        }else {
            return false;
        }
    }

    private boolean isInputPassValidate(String inputPassword) {
        if (TextUtils.isEmpty(inputPassword) || inputPassword.length() < 4) {
            return false;
        }
        return true;
    }


    @OnClick(R.id.button_gesturelock_check_back)
    public void onClickUserBack(){
        gotoLauncher();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            // if the key is back, goto the Launcher Activity
            case KeyEvent.KEYCODE_BACK: {
                gotoLauncher();
                break;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 回到桌面，finish自身
     */
    private void gotoLauncher() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addCategory("android.intent.category.MONKEY");
        startActivity(intent);
        finish();
    }

    /**
     * 监听返回桌面按键
     */
    private class HomeKeyDownReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                gotoLauncher();
            }
        }
    }




}
