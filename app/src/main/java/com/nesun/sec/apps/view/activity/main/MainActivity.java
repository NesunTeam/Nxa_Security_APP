package com.nesun.sec.apps.view.activity.main;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nesun.sec.apps.R;
import com.nesun.sec.apps.constant.Constant;
import com.nesun.sec.apps.utils.AppUtil;
import com.nesun.sec.apps.view.activity.BaseActivity;
import com.nesun.sec.apps.view.activity.applock.AppLockActivity;
import com.nesun.sec.apps.view.activity.gesture.GestureLockActivity;
import com.nesun.sec.apps.view.activity.gesture.GestureLockPrivateCheckActivity;
import com.nesun.sec.apps.view.activity.security.SecurityScanningActivity;
import com.nesun.sec.apps.view.activity.user.UserInfoActivity;
import com.nesun.sec.apps.view.customview.MyScrollView;
import com.nesun.sec.apps.view.customview.QQSecureView;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * @author: luckyShane
 * @date: 2019/3/4
 */
public class MainActivity extends BaseActivity  {

    // --------------------ui
    @BindView(R.id.scrollView01_mian)MyScrollView scrollView01;// 滚动视图
    @BindView(R.id.qqsec_01) QQSecureView qqSecureView;// 防护动画视图
    @BindView(R.id.textView_activity_main_title)TextView textView_activity_main_title;// 标题文字

    @BindView(R.id.button_login) Button loginButton;// Tou Button
    @BindView(R.id.linearyout_biaoti) LinearLayout linearLayout_Biaoti;// 标题一个

    @BindView(R.id.linearyout_gezi_sub01)LinearLayout linearLayout_gezi01;
    @BindView(R.id.linearyout_gezi_sub02)LinearLayout linearLayout_gezi02;
    @BindView(R.id.linearyout_gezi_sub03)LinearLayout linearLayout_gezi03;
    @BindView(R.id.linearyout_gezi_sub04)LinearLayout linearLayout_gezi04;

    @BindView(R.id.linearyout_sangong_sub01)LinearLayout linearLayout_sangong01;
    @BindView(R.id.linearyout_sangong_sub02)LinearLayout linearLayout_sangong02;
    @BindView(R.id.linearyout_sangong_sub03)LinearLayout linearLayout_sangong03;
    @BindView(R.id.linearyout_sangong_sub04)LinearLayout linearLayout_sangong04;
    @BindView(R.id.linearyout_sangong_sub05)LinearLayout linearLayout_sangong05;
    @BindView(R.id.linearyout_sangong_sub06)LinearLayout linearLayout_sangong06;

    @BindView(R.id.linearyout_ads01)LinearLayout linearLayout_ads01;
    @BindView(R.id.linearyout_ads02)LinearLayout linearLayout_ads02;
    @BindView(R.id.linearyout_ads03)LinearLayout linearLayout_ads03;
    @BindView(R.id.linearyout_texiao)LinearLayout linearyout_texiao;
    @BindView(R.id.tool_bar) Toolbar tool_bar;

    // --------------------data




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        Typeface fontFace = Typeface.createFromAsset(getAssets(), "fmzyht.ttf");
        textView_activity_main_title.setTypeface(fontFace);
//        textView_activity_main_sec_showhu.setTypeface(fontFace);
//        Typeface fontFace2 = Typeface.createFromAsset(getAssets(), "fmnumber.otf");
//        textView_activity_main_sec_number.setTypeface(fontFace2);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getToolbarId() {
        return R.id.tool_bar;
    }




    /**
     * 展示一次安全数值动画(1~99)
     */
//    public synchronized void showSecNumberAnim(int startNum,int endNum){
//        if (startNum == endNum || startNum<=0 || startNum>=100 || endNum<=0 || endNum>=100)return;
//        if (startNum<endNum) isNumberUp = true;
//        else isNumberUp = false;
//        secNumber = startNum;
//        maxNumber = endNum;
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (isNumberUp){
//                    while (secNumber<maxNumber){
//                        try {
//                            Thread.sleep(30); //每次数值变化间隔
//                        } catch (InterruptedException e) {
//                        }
//                        secNumber++;
//                        Message msg = Message.obtain();
//                        msg.arg1 = 1;
//                        if (secNumber>=maxNumber) msg.arg1=99;
//                        mHandler.sendMessage(msg);
//                    }
//                }else {
//                    while (secNumber>maxNumber){
//                        try {
//                            Thread.sleep(30); //每次数值变化间隔
//                        } catch (InterruptedException e) {
//                        }
//                        secNumber--;
//                        Message msg = Message.obtain();
//                        msg.arg1 = 1;
//                        if (secNumber<=maxNumber) msg.arg1=99;
//                        mHandler.sendMessage(msg);
//                    }
//                }
//            }
//        }).start();
//    }


//    int secNumber = 0;
//    int maxNumber = 0;
//    boolean isNumberUp;
//    Handler mHandler = new Handler(){
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            textView_activity_main_sec_number.setText(""+secNumber);
//            changeColor(secNumber);
//            if (msg.arg1==99){
//                textView_activity_main_sec_tips.setVisibility(View.INVISIBLE);
//                button_activity_main_sec_start.setVisibility(View.VISIBLE);
//            }
//        }
//    };


    /**
     * 改变背景颜色 （测试）
     */
//    public void changeColor(int secNumber){
//
//        // 变化的颜色（渐变，所以有两组）
//        int blue = 0xFF469EFE, yellow = 0xFFfdc825, red = 0xFFfa6d35;
//        int blue2 = 0xFF3F2BF4, yellow2 = 0xFFff8602, red2 = 0xFFef1130;
//
//        int evaluate = red; // 初始默认颜色
//        int evaluate2 = red2; // 初始默认颜色2
//
//        ArgbEvaluator evaluator = new ArgbEvaluator(); // ARGB求值器
//
//        if (secNumber>0 && secNumber<=45){
//            tool_bar.setBackgroundResource(R.drawable.bg_sec_type03);
//            linearyout_texiao.setBackgroundResource(R.drawable.bg_sec_type03);
//        }
//        else if (secNumber>45 && secNumber<=55){ //计算渐变
//            float blis = (float)((secNumber-45)*0.1);
//            evaluate = (Integer) evaluator.evaluate(blis, red, yellow);
//            evaluate2 = (Integer) evaluator.evaluate(blis, red2, yellow2);
//            GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{evaluate,evaluate2});
//            tool_bar.setBackground(bg);
//            linearyout_texiao.setBackground(bg);
//        }
//        else if (secNumber>55 && secNumber<=75){
//            tool_bar.setBackgroundResource(R.drawable.bg_sec_type02);
//            linearyout_texiao.setBackgroundResource(R.drawable.bg_sec_type02);
//        }
//        else if (secNumber>75 && secNumber<=85){//计算渐变
//            float blis = (float)((secNumber-75)*0.1);
//            evaluate = (Integer) evaluator.evaluate(blis, yellow, blue);
//            evaluate2 = (Integer) evaluator.evaluate(blis, yellow2, blue2);
//            GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{evaluate,evaluate2});
//            tool_bar.setBackground(bg);
//            linearyout_texiao.setBackground(bg);
//        }else {
//            tool_bar.setBackgroundResource(R.drawable.bg_sec_type01);
//            linearyout_texiao.setBackgroundResource(R.drawable.bg_sec_type01);
//        }
//
//    }

    @OnClick(R.id.button_login)
    public void onClickUserPhoto(){
        Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.qqsec_01)
    public void onClickQQSecView(){
        Log.e("FM9333","qqSecureView-动画");
    }
    @OnClick(R.id.linearyout_biaoti)
    public void onClickTipTitle(){
        Log.e("FM9333","Biaoti-小卫提醒");
    }
    @OnClick(R.id.linearyout_gezi_sub01)
    public void onClickGeziSub01(){
        Intent intent = new Intent(MainActivity.this, SecurityScanningActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.linearyout_gezi_sub02)
    public void onClickGeziSub02(){
        Log.e("FM9333","GeZi-权限监控");
    }
    @OnClick(R.id.linearyout_gezi_sub03)
    public void onClickGeziSub03(){
        if (AppUtil.isSetPassword(getApplicationContext())){ // 设置过密码，直接进入
            Intent intent = new Intent(MainActivity.this, AppLockActivity.class);
            startActivity(intent);
        }else { //没设置过密码，进入设置密码界面
            Intent intent = new Intent(MainActivity.this, GestureLockActivity.class);
            intent.putExtra(Constant.START_ACTIVITY_TYPE_NAME, Constant.START_ACTIVITY_TYPE_1);//跳应用锁
            startActivity(intent);
        }
    }

    @OnClick(R.id.linearyout_gezi_sub04)
    public void onClickGeziSub04(){
        if (AppUtil.isSetPassword(getApplicationContext())){ // 设置过密码，直接进入
            Intent intent = new Intent(MainActivity.this, GestureLockPrivateCheckActivity.class);
            startActivity(intent);
        }else { //没设置过密码，进入设置密码界面
            Intent intent = new Intent(MainActivity.this, GestureLockActivity.class);
            intent.putExtra(Constant.START_ACTIVITY_TYPE_NAME, Constant.START_ACTIVITY_TYPE_2); //跳隐私空间
            startActivity(intent);
        }
    }

    @OnClick(R.id.linearyout_sangong_sub01)
    public void onClickSanGongSub01(){
        Log.e("FM9333","SanGong-111111111111111111");
    }
    @OnClick(R.id.linearyout_sangong_sub02)
    public void onClickSanGongSub02(){
        Log.e("FM9333","SanGong-222222222222222222");
    }
    @OnClick(R.id.linearyout_sangong_sub03)
    public void onClickSanGongSub03(){
        Log.e("FM9333","SanGong-333333333333333333");
    }
    @OnClick(R.id.linearyout_sangong_sub04)
    public void onClickSanGongSub04(){
        Log.e("FM9333","SanGong-444444444444444444");
    }
    @OnClick(R.id.linearyout_sangong_sub05)
    public void onClickSanGongSub05(){
        Log.e("FM9333","SanGong-555555555555555555");
    }
    @OnClick(R.id.linearyout_sangong_sub06)
    public void onClickSanGongSub06(){
        Intent intent = new Intent(MainActivity.this, SGMoreActivity.class);
        startActivity(intent);
    }
    @OnClick({R.id.linearyout_ads01})
    public void onClickAds01(){
        Log.e("FM9333","ADS-广告1");
    }
    @OnClick(R.id.linearyout_ads02)
    public void onClickAds02(){
        Log.e("FM9333","ADS-广告2");
    }
    @OnClick(R.id.linearyout_ads03)
    public void onClickAds03(){
        Log.e("FM9333","ADS-广告3");
    }


    /**
     * 滚动视图监听
     * @param scrollX
     * @param scrollY
     * @param oldX
     * @param oldY
     */
//    @Override
//    public void onScroll(int scrollX, int scrollY, int oldX, int oldY) {
//        boolean isCan  = false;
//        if (oldY<scrollY) isCan = true;
//        if (scrollY<linearLayout_Biaoti.getTop() && isCan){
//            scrollView01.post(new Runnable(){
//                @Override
//                public void run(){
//                    scrollView01.fling(linearLayout_Biaoti.getTop());
//                }
//            });
//        }
//    }


}
