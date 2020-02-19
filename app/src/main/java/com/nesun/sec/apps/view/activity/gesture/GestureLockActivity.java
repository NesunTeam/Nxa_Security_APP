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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nesun.sec.apps.R;
import com.nesun.sec.apps.constant.Constant;
import com.nesun.sec.apps.utils.AppUtil;
import com.nesun.sec.apps.view.activity.BaseActivity;
import com.nesun.sec.apps.view.activity.applock.AppLockActivity;
import com.nesun.sec.apps.view.activity.zone.PrivateZoneActivity;
import com.nesun.sec.apps.view.customview.GestureContentView;
import com.nesun.sec.apps.view.customview.GestureDrawline;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 * 九宫格解锁 初始化设置密码，修改密码界面
 *
 *
 */
public class GestureLockActivity extends BaseActivity {

    // --------------------ui
    @BindView(R.id.button_gesturelock_back)Button button_back;
    @BindView(R.id.textView_gesturelock_tip01) TextView textView_gesturelock_tip01;
    @BindView(R.id.gesturelock_container)FrameLayout gesturelock_container;
    @BindView(R.id.imageView_gesture_note_tips01)ImageView imageView_gesture_note_tips01;

    // --------------------data
    private GestureContentView mGestureContentView;
    private boolean mIsFirstInput = true;
    private String mFirstPassword = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setUpViews();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gesturelock;
    }

    @Override
    protected int getToolbarId() {
        return R.id.tool_bar_gesturelock;
    }


    private void setUpViews() {
        // 初始化一个显示各个点的viewGroup
        mGestureContentView = new GestureContentView(this, false, "", new GestureDrawline.GestureCallBack() {
            @Override
            public void onGestureCodeInput(String inputCode) {
                if (!isInputPassValidate(inputCode)) {
                    textView_gesturelock_tip01.setText(R.string.textview_title_gesturelock_check_tip02);
                    mGestureContentView.clearDrawlineState(0L);
                    return;
                }

                if (mIsFirstInput) {
                    mFirstPassword = inputCode;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mGestureContentView.clearDrawlineState(0L);
                            imageView_gesture_note_tips01.setImageResource(R.mipmap.gesture_node_tips02); // 重新设置提示图片
                            textView_gesturelock_tip01.setText(R.string.textview_title_gesturelock_check_tip03);
                        }
                    }, 300);
                } else {
                    if (inputCode.equals(mFirstPassword)) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mGestureContentView.clearDrawlineState(0L);
                            }
                        }, 500);
                        updateCodeList(inputCode); // 设置成功，保存密码
                        checkGotoActivity(); // 设置密码成功，检查是否跳转界面
                    } else {
                        textView_gesturelock_tip01.setText(R.string.textview_title_gesturelock_check_tip04);
                        // 左右移动动画
                        Animation shakeAnimation = AnimationUtils.loadAnimation(GestureLockActivity.this, R.anim.shake);
                        textView_gesturelock_tip01.startAnimation(shakeAnimation);
                        // 保持绘制的线，0.4秒后清除
                        mGestureContentView.clearDrawlineState(500);
                    }
                }
                mIsFirstInput = false;
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
     * 检查是否需要进行跳转页面
     */
    private void checkGotoActivity(){
        try {
            String type = getIntent().getStringExtra(Constant.START_ACTIVITY_TYPE_NAME).toString();
            if (type.equals(Constant.START_ACTIVITY_TYPE_1)){ //应用锁
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(GestureLockActivity.this, AppLockActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 500);
            }else if (type.equals(Constant.START_ACTIVITY_TYPE_2)){ // 隐私空间
                // 这里启动隐私空间
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(GestureLockActivity.this, PrivateZoneActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 500);
            }else if (type.equals(Constant.START_ACTIVITY_TYPE_3)){ // 修改密码
                Toast.makeText(GestureLockActivity.this, R.string.textview_title_gesturelock_check_tip05, Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 500);
            }else if (type.equals(Constant.START_ACTIVITY_TYPE_4)){ // 待定

            }else{

            }
        }catch (Exception e){}
    }


    private void updateCodeList(String inputCode) {
        if (TextUtils.isEmpty(inputCode)) return;
        AppUtil.setGesturePassword(getApplicationContext(),inputCode);// 更新选择的图案
    }

    private boolean isInputPassValidate(String inputPassword) {
        if (TextUtils.isEmpty(inputPassword) || inputPassword.length() < 4) {
            return false;
        }
        return true;
    }

    @OnClick(R.id.button_gesturelock_back)
    public void onClickUserBack(){
        this.finish();
    }

}
