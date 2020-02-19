package com.nesun.sec.apps.view.activity.splash;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nesun.sec.apps.R;
import com.nesun.sec.apps.constant.Constant;
import com.nesun.sec.apps.utils.AppUtil;
import com.nesun.sec.apps.view.activity.BaseActivity;
import com.nesun.sec.apps.view.activity.main.MainActivity;
import com.nesun.sec.apps.view.activity.setting.UserProtocolActivity;
import com.nesun.sec.apps.view.customview.CustomVideoView;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * @author: luckyShane
 * @date: 2019/3/4
 */
public class SplashVideoActivity extends BaseActivity {


    // --------------------ui
    @BindView(R.id.customVideoView01)CustomVideoView customVideoView01; // Video
    @BindView(R.id.splash_video_img_01)ImageView imageView_splash01; // 标题
    @BindView(R.id.splash_video_img_02)ImageView imageView_splash02; //标题 7 字
    @BindView(R.id.splash_video_img_03)ImageView imageView_splash03; //标题语句
    @BindView(R.id.splash_button_start_app)Button button_start_app;
    @BindView(R.id.textView_splash_rule01)TextView textView_splash_rule01;
    @BindView(R.id.imageView_splash_rule01)ImageView imageView_splash_rule01;
    @BindView(R.id.imageView_splash_backgrouard_01)ImageView imageView_splash_backgrouard_01;

    // --------------------data
    boolean isChecked;
    boolean isVideoPlayEnded;
    int playVideoPosition = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        int dis[] = AppUtil.getScreenDispaly(getApplicationContext());
        int width = dis[0];
        // 视频初始化+启动
        customVideoView01.setVideoURI(Uri.parse("android.resource://"+ SplashVideoActivity.this.getPackageName()+"/"+R.raw.start));

        // 设置图片视图信息
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imageView_splash01.getLayoutParams();
        params.height = (int) (width/1.55);
        imageView_splash01.setLayoutParams(params);

        showTitleImg();// 在视频中展示标题

        // 设置 7 图片视图信息
        RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) imageView_splash02.getLayoutParams();
        params2.height = (int) (width/1);
        imageView_splash02.setLayoutParams(params2);
        imageView_splash02.setAlpha(0f); //先隐藏该视图

        // 设置标题语句 图片信息
        RelativeLayout.LayoutParams params3 = (RelativeLayout.LayoutParams) imageView_splash03.getLayoutParams();
        params3.height = (int) (width/2.5);
        params3.width = (int) (width/2.5);
        imageView_splash03.setLayoutParams(params3);
        imageView_splash03.setAlpha(0f); //先隐藏该视图

        // 设置Button初始化
        RelativeLayout.LayoutParams params4 = (RelativeLayout.LayoutParams) button_start_app.getLayoutParams();
        params4.width = (int) (width/1.9);
        button_start_app.setLayoutParams(params4);
        button_start_app.setAlpha(0f); //先隐藏该视图

        // 设置TextView 初始化
        textView_splash_rule01.setAlpha(0f); //先隐藏该视图

        // 设置打钩选项图片
        imageView_splash_rule01.setAlpha(0f); //先隐藏该视图
        imageView_splash_rule01.setImageResource(R.mipmap.splash_check_yes);
        isChecked = true;

        // 监听播放完毕，展示其他组件，然后点击确认进入App
        customVideoView01.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                isVideoPlayEnded = true; // 播放完毕
                hideTitleImg(); // 播放完毕，关闭Title
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected int getToolbarId() {
        return -1;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!isVideoPlayEnded) {
            customVideoView01.start(); //Start Video
            customVideoView01.seekTo(playVideoPosition);
        } else{
            imageView_splash_backgrouard_01.setImageResource(R.drawable.bg_top_bar2);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (!isVideoPlayEnded) {
            customVideoView01.pause(); //Pause Video
            playVideoPosition = customVideoView01.getCurrentPosition();
        }
    }

    @OnClick(R.id.imageView_splash_rule01)
    public void onClickCheckRuleImageView(){
        if (isChecked){
            imageView_splash_rule01.setImageResource(R.mipmap.splash_check_no);
            isChecked=false;
        }else {
            imageView_splash_rule01.setImageResource(R.mipmap.splash_check_yes);
            isChecked=true;
        }
    }

    @OnClick(R.id.textView_splash_rule01)
    public void onClickCheckRuleTextView(){
        Intent intent = new Intent(SplashVideoActivity.this, UserProtocolActivity.class);
        intent.putExtra(Constant.USER_WEB_TYPE,Constant.USER_WEB_TYPE_PROTOCOL);
        SplashVideoActivity.this.startActivity(intent);
    }

    @OnClick(R.id.splash_button_start_app)
    public void onClickStartApp(){
        if (!isChecked) {// 校验是否勾选服务协议
            Toast.makeText(SplashVideoActivity.this,R.string.textview_title_gesturelock_check_tip10,Toast.LENGTH_SHORT).show();
            return;
        }
        AppUtil.setIsFirstRun(getApplicationContext(),"1");// 记录用户已经看过视频，记录，以后不再展示视频
        button_start_app.setBackgroundColor(Color.rgb(0, 198, 114));// 改变按钮背景颜色，形成加载错觉
        showMain();// 启动相关Activity
    }


    private void showMain() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashVideoActivity.this, MainActivity.class);
                startActivity(intent);
                SplashVideoActivity.this.finish();
            }
        }, 600);
    }

    private void showTitleImg(){
        //透明度起始为1，结束时为0
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView_splash01, "alpha", 0f, 1f);
        animator.setDuration(2500);//时间2.5s
        animator.start();
    }

    private void hideTitleImg(){
        //透明度起始为1，结束时为0
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView_splash01, "alpha", 1f, 0f);
        animator.setDuration(500);//时间1s
        animator.addListener(new Animator.AnimatorListener() {
            public void onAnimationStart(Animator animation) {
            }
            public void onAnimationEnd(Animator animation) {
                show7TitleImg(); // 开始展示 7 字 Title
                showADTitleImg(); // 开始展示广告语 Title
            }
            public void onAnimationCancel(Animator animation) {
            }
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animator.start();
    }

    /**
     * 显示“7”字标题
     */
    private void show7TitleImg(){
        //透明度起始为1，结束时为0
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView_splash02, "alpha", 0f, 1f);
        animator.setDuration(900);//时间1s
        animator.start();
    }

    /**
     * 显示广告语 标题
     */
    private void showADTitleImg(){
        //透明度起始为1，结束时为0
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView_splash03, "alpha", 0f, 1f);
        animator.setDuration(1300);//时间1s
        animator.addListener(new Animator.AnimatorListener() {
            public void onAnimationStart(Animator animation) {
            }
            public void onAnimationEnd(Animator animation) {
                showStartAppButton(); // 开始展示button
                showTextViewRule01(); // 开始展示同意协议
                showImageViewRule01(); // 开始展示同意协议打钩
            }
            public void onAnimationCancel(Animator animation) {
            }
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animator.start();
    }


    /**
     * 显示按钮样式
     */
    private void showStartAppButton(){
        //透明度起始为1，结束时为0
        ObjectAnimator animator = ObjectAnimator.ofFloat(button_start_app, "alpha", 0f, 1f);
        animator.setDuration(400);//时间1s
        animator.start();
    }

    /**
     * 显示协议文本信息
     */
    private void showTextViewRule01(){
        //透明度起始为1，结束时为0
        ObjectAnimator animator = ObjectAnimator.ofFloat(textView_splash_rule01, "alpha", 0f, 1f);
        animator.setDuration(400);//时间1s
        animator.start();
    }


    /**
     * 显示协议 打钩图片
     */
    private void showImageViewRule01(){
        //透明度起始为1，结束时为0
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView_splash_rule01, "alpha", 0f, 1f);
        animator.setDuration(400);//时间1s
        animator.start();
    }

}
