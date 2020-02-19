package com.nesun.sec.apps.view.activity.security;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nesun.sec.apps.R;
import com.nesun.sec.apps.bean.SecScanBean;
import com.nesun.sec.apps.view.activity.BaseActivity;
import com.nesun.sec.apps.view.customview.ScanView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

public class SecurityScanningActivity extends BaseActivity {

    // --------------------ui
    @BindView(R.id.button_sec_scanning_back)Button button_sec_scanning_back;
    @BindView(R.id.button_activity_sec_scanning_start)Button button_activity_sec_scanning_start;
    @BindView(R.id.button_sec_scan_cancel)Button button_sec_scan_cancel;
    @BindView(R.id.button_sec_scan_setting)Button button_sec_scan_setting;
    @BindView(R.id.scanView_sec_01)ScanView scanView_sec_01;
    @BindView(R.id.textView_sec_scanning_number)TextView textView_sec_scanning_number;
    @BindView(R.id.textView_sec_scanning_tips)TextView textView_sec_scanning_tips;
    @BindView(R.id.listView_sec_scan_01) ListView listView_sec_scan_01;
    @BindView(R.id.relativeLayout_rl_circle) RelativeLayout relativeLayout_rl_circle;
    @BindView(R.id.imageView_rl_circle) ImageView imageView_rl_circle;


    @BindView(R.id.linearLayout_scanning_01)LinearLayout linearLayout_scanning_01;
    @BindView(R.id.tool_bar_sec_scanning)Toolbar tool_bar_sec_scanning;

    // --------------------data
    public SecScanAdapter secScanAdapter;
    public List<SecScanBean> cats = new ArrayList<>();
    public boolean isStart = false;

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
        return R.layout.activity_sec_scanning;
    }

    @Override
    protected int getToolbarId() {
        return R.id.tool_bar_sec_scanning;
    }


    /**
     * UI相关初始化
     */
    public void initUI(){

        // 数据初始化
        initData();

        // List适配器
        secScanAdapter = new SecScanAdapter(SecurityScanningActivity.this, R.layout.list_sec_scan_item, cats);
        listView_sec_scan_01.setAdapter(secScanAdapter);
        secScanAdapter.notifyDataSetChanged();

        // 动画旋转
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.sec_scan_rotate_forever);
        relativeLayout_rl_circle.startAnimation(rotateAnimation);
        imageView_rl_circle.setVisibility(View.VISIBLE);

        textView_sec_scanning_tips.setVisibility(View.INVISIBLE);
        textView_sec_scanning_number.setVisibility(View.INVISIBLE);

        // 按钮不可点击
        setButtonCloseClick();
    }

    public void initData(){
        cats.clear();
        cats.add(new SecScanBean("网络环境","等待中"));
        cats.add(new SecScanBean("系统漏洞","等待中"));
        cats.add(new SecScanBean("病毒木马","等待中"));
        cats.add(new SecScanBean("支付环境","等待中"));
        cats.add(new SecScanBean("账号安全","等待中"));
        cats.add(new SecScanBean("隐私保护","等待中"));
    }


    @OnClick({R.id.button_sec_scanning_back})
    public void onClickBack(){
        if (isStart)isStart = false;
        finish();
    }

    @OnClick({R.id.button_sec_scan_cancel})
    public void onClickCancel(){
        if (isStart)isStart = false;
        finish();
    }


    @OnClick({R.id.button_sec_scan_setting})
    public void onClickSetting(){

    }

    @OnClick({R.id.button_activity_sec_scanning_start})
    public void onClickStartScan(){
        if (!isStart) startSecScan();
    }

    /**
     * 调用此方法 开始扫描 UI操作
     */
    public void startSecScan(){
        if (isStart) return;
        isStart = true;
        scanView_sec_01.start();
        button_activity_sec_scanning_start.setVisibility(View.INVISIBLE);
        textView_sec_scanning_number.setVisibility(View.VISIBLE);
        textView_sec_scanning_tips.setVisibility(View.VISIBLE);
        imageView_rl_circle.setVisibility(View.INVISIBLE);

        //
        setButtonOpenClick();

        // 数据初始化
        initData();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (secScanValue<100 && isStart){
                    try {
                        int times = new Random().nextInt(100)+50;
                        Thread.sleep(times);
                    } catch (InterruptedException e) {
                    }
                    secScanValue++;
                    Message msg = Message.obtain();
                    msg.arg1 = secScanValue;
                    mHandler.sendMessage(msg);
                }
            }
        }).start();
    }

    /**
     * 调用此方法 停止扫描 UI操作
     */
    public  void stopSecScan(){
        if (!isStart)return;
        isStart = false;
        secScanValue=0;
        scanView_sec_01.stop();
        button_activity_sec_scanning_start.setVisibility(View.VISIBLE);
        textView_sec_scanning_number.setVisibility(View.INVISIBLE);
        textView_sec_scanning_tips.setVisibility(View.INVISIBLE);
        imageView_rl_circle.setVisibility(View.VISIBLE);
        setButtonCloseClick();

        // 启动Activity
        Intent intent = new Intent(SecurityScanningActivity.this, SecurityScanningResultActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_bottom,R.anim.anim_slide_out_bottom);
        finish();
    }



    /**
     * UI通信Handler
     */
    int secScanValue = 0;
    Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("FM9333","msg==============="+msg.arg1);
            textView_sec_scanning_number.setText(msg.arg1+"%");
            if (msg.arg1==1){
                selectListItem(0);
                textView_sec_scanning_tips.setText("正在扫描:"+cats.get(0).getScanType());
            }else if (msg.arg1==10){
                cats.get(0).setScanStatus("安全");
                cats.get(0).setScanSafe(true);
                textView_sec_scanning_tips.setText("正在扫描:"+cats.get(1).getScanType());
                selectListItem(1);
            } else if (msg.arg1==30){
                cats.get(1).setScanStatus("安全");
                cats.get(1).setScanSafe(true);
                textView_sec_scanning_tips.setText("正在扫描:"+cats.get(2).getScanType());
                selectListItem(2);
            }else if (msg.arg1==50){
                cats.get(2).setScanStatus("安全");
                cats.get(2).setScanSafe(true);
                textView_sec_scanning_tips.setText("正在扫描:"+cats.get(3).getScanType());
                selectListItem(3);
            }else if (msg.arg1==70){
                cats.get(3).setScanStatus("安全");
                cats.get(3).setScanSafe(true);
                textView_sec_scanning_tips.setText("正在扫描:"+cats.get(4).getScanType());
                selectListItem(4);
            }else if (msg.arg1==90){
                cats.get(4).setScanStatus("安全");
                cats.get(4).setScanSafe(true);
                textView_sec_scanning_tips.setText("正在扫描:"+cats.get(5).getScanType());
                selectListItem(5);
            }else if (msg.arg1>=100){
                cats.get(5).setScanStatus("安全");
                cats.get(5).setScanSafe(true);
                textView_sec_scanning_tips.setText("扫描完毕");
                selectListItem(-1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        stopSecScan();
                    }
                },300);
            }
            // 改变背景颜色
            int number = 100 - msg.arg1;
            changeColor(number);
        }
    };

    /**
     * 当前正在扫描某一应用
     * @param index
     */
    public void selectListItem(final int index){
        secScanAdapter.setSelectItem(index); //自定义的变量，以便让adapter知道要选中哪一项
        secScanAdapter.notifyDataSetInvalidated();//提醒数据已经变动
//        listView_sec_scan_01.setSelection(index);
        listView_sec_scan_01.post(new Runnable() {
            @Override
            public void run() {
                if (index>=0)listView_sec_scan_01.smoothScrollToPosition(index);
//                else listView_sec_scan_01.smoothScrollToPosition(cats.size()-1);
            }
        });
    }



    /**
     * 设置 添加 按钮允许被点击
     */
    public void setButtonOpenClick(){
        button_sec_scan_cancel.setClickable(true);//勾选，则允许点击添加
        button_sec_scan_cancel.setBackgroundColor(Color.argb(255, 0, 215, 141));
        button_sec_scan_cancel.setTextColor(Color.argb(255, 255, 255, 255));
    }

    /**
     * 设置 添加 按钮禁止被点击
     */
    public void setButtonCloseClick(){
        button_sec_scan_cancel.setClickable(false);//未选择App状态，禁止点击添加
        button_sec_scan_cancel.setBackgroundColor(Color.argb(241,241,241,241));
        button_sec_scan_cancel.setTextColor(Color.argb(211,187,187,187));
    }






    /**
     * 改变背景颜色 （测试）
     */
    public void changeColor(int secNumber){

        // 变化的颜色（渐变，所以有两组）
        int blue = 0xFF469EFE, yellow = 0xFFfdc825, red = 0xFFfa6d35;
        int blue2 = 0xFF3F2BF4, yellow2 = 0xFFff8602, red2 = 0xFFef1130;

        int evaluate = red; // 初始默认颜色
        int evaluate2 = red2; // 初始默认颜色2

        ArgbEvaluator evaluator = new ArgbEvaluator(); // ARGB求值器

        if (secNumber>=0 && secNumber<=45){
            tool_bar_sec_scanning.setBackgroundResource(R.drawable.bg_sec_type03);
            linearLayout_scanning_01.setBackgroundResource(R.drawable.bg_sec_type03);
        }
        else if (secNumber>45 && secNumber<=55){ //计算渐变
            float blis = (float)((secNumber-45)*0.1);
            evaluate = (Integer) evaluator.evaluate(blis, red, yellow);
            evaluate2 = (Integer) evaluator.evaluate(blis, red2, yellow2);
            GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{evaluate,evaluate2});
            tool_bar_sec_scanning.setBackground(bg);
            linearLayout_scanning_01.setBackground(bg);
        }
        else if (secNumber>55 && secNumber<=75){
            tool_bar_sec_scanning.setBackgroundResource(R.drawable.bg_sec_type02);
            linearLayout_scanning_01.setBackgroundResource(R.drawable.bg_sec_type02);
        }
        else if (secNumber>75 && secNumber<=85){//计算渐变
            float blis = (float)((secNumber-75)*0.1);
            evaluate = (Integer) evaluator.evaluate(blis, yellow, blue);
            evaluate2 = (Integer) evaluator.evaluate(blis, yellow2, blue2);
            GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{evaluate,evaluate2});
            tool_bar_sec_scanning.setBackground(bg);
            linearLayout_scanning_01.setBackground(bg);
        }else {
            tool_bar_sec_scanning.setBackgroundResource(R.drawable.bg_sec_type01);
            linearLayout_scanning_01.setBackgroundResource(R.drawable.bg_sec_type01);
        }

    }


}
