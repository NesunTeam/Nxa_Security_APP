package com.nesun.sec.apps.view.activity.gesture;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nesun.sec.apps.R;
import com.nesun.sec.apps.utils.AppUtil;
import com.nesun.sec.apps.view.activity.BaseActivity;
import com.nesun.sec.apps.view.activity.zone.PrivateZoneActivity;
import com.nesun.sec.apps.view.customview.GestureContentView;
import com.nesun.sec.apps.view.customview.GestureDrawline;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 * 私有空间 解锁界面
 *
 *
 */
public class GestureLockPrivateCheckActivity extends BaseActivity {

    // --------------------ui
    @BindView(R.id.button_gesturelock_check_back)Button button_back;
    @BindView(R.id.textView_gesturelock_check_tip01) TextView textView_gesturelock_tip01;
    @BindView(R.id.gesturelock_container_check)FrameLayout gesturelock_container;

    // --------------------data
    private GestureContentView mGestureContentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                                // 这里启动隐私空间
                                Intent intent = new Intent(GestureLockPrivateCheckActivity.this, PrivateZoneActivity.class);
                                startActivity(intent);
                                mGestureContentView.clearDrawlineState(0L);
                                finish();
                            }
                        }, 300);
                    } else {
                        textView_gesturelock_tip01.setText(R.string.textview_title_gesturelock_check_tip06);
                        // 左右移动动画
                        Animation shakeAnimation = AnimationUtils.loadAnimation(GestureLockPrivateCheckActivity.this, R.anim.shake);
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
        finish();
    }


}
