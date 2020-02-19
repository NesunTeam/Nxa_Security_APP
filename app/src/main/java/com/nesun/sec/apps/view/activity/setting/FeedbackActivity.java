package com.nesun.sec.apps.view.activity.setting;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nesun.sec.apps.R;
import com.nesun.sec.apps.presenter.FeedbackPresenter;
import com.nesun.sec.apps.view.activity.BaseActivity;
import com.nesun.sec.apps.view.biz.IFeedbackView;
import com.nesun.sec.apps.view.customview.LoadingDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class FeedbackActivity  extends BaseActivity implements IFeedbackView {

    // --------------------ui
    @BindView(R.id.button_user_setting_feedback_back)Button button_back;
    @BindView(R.id.editText_feedback_01) EditText editText_feedback_01;
    @BindView(R.id.editText_feedback_02) EditText editText_feedback_02;
    @BindView(R.id.button_feedback_01)Button button_feedback_01;


    // --------------------data
    private FeedbackPresenter feedbackPresenter = new FeedbackPresenter(this); //MVP
    private LoadingDialog mLoadingDialog; // 自定义Dialog


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected int getToolbarId() {
        return R.id.tool_bar_user_setting_feedback;
    }

    @OnClick({R.id.button_user_setting_feedback_back})
    public void onClickBack(){
        finish();
    }


    @OnClick({R.id.button_feedback_01})
    public void onClickSubmit(){
        String feedback_message =editText_feedback_01.getText().toString();
        String feedback_userinfo =editText_feedback_02.getText().toString();
        if (TextUtils.isEmpty(feedback_message)){
            Toast.makeText(this,R.string.textview_title_gesturelock_check_tip07,Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(feedback_userinfo)){
            Toast.makeText(this,R.string.textview_title_gesturelock_check_tip08,Toast.LENGTH_SHORT).show();
            return;
        }
        feedbackPresenter.submit();
    }

    @Override
    public String getFeedbackMessage() {
        return editText_feedback_01.getText().toString();
    }

    @Override
    public String getFeedbackPhone() {
        return editText_feedback_02.getText().toString();
    }

    @Override
    public void showLoding() {
        button_feedback_01.setClickable(false);//未选择App状态，禁止点击添加
        button_feedback_01.setBackgroundColor(Color.argb(241,222,222,222));
        button_feedback_01.setTextColor(Color.argb(211,177,177,177));
        if (mLoadingDialog == null) mLoadingDialog = LoadingDialog.showDialog(this, getString(R.string.textview_title_gesturelock_check_tip13));
        mLoadingDialog.show();
    }

    @Override
    public void hindLoding() {
        button_feedback_01.setClickable(true);//勾选，则允许点击添加
        button_feedback_01.setBackgroundColor(Color.argb(255, 0, 215, 141));
        button_feedback_01.setTextColor(Color.argb(255, 255, 255, 255));
        if (mLoadingDialog != null)  mLoadingDialog.dismiss();
    }
    @Override
    public void showFeedbackSuccess() {
        Toast.makeText(this,R.string.textview_title_gesturelock_check_tip09,Toast.LENGTH_SHORT).show();
        editText_feedback_01.setText("");
        editText_feedback_02.setText("");
    }

    @Override
    public void showFeedbackFaild() {
        Toast.makeText(this,R.string.textview_title_gesturelock_check_tip11,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFeedbackError() {
        Toast.makeText(this,R.string.textview_title_gesturelock_check_tip12,Toast.LENGTH_SHORT).show();
    }



}
