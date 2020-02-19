package com.nesun.sec.apps.view.activity.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import com.nesun.sec.apps.R;
import com.nesun.sec.apps.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * @author: luckyShane
 * @date: 2019/3/4
 */
public class SGMoreActivity extends BaseActivity {

    // --------------------ui
    @BindView(R.id.button_main_sgmore_back)Button button_main_sgmore_back;
    @BindView(R.id.linearyout_main_sgmore_sub01) LinearLayout linearyout_main_sgmore_sub01;
    @BindView(R.id.linearyout_main_sgmore_sub02) LinearLayout linearyout_main_sgmore_sub02;
    @BindView(R.id.linearyout_main_sgmore_sub03) LinearLayout linearyout_main_sgmore_sub03;
    @BindView(R.id.linearyout_main_sgmore_sub04) LinearLayout linearyout_main_sgmore_sub04;
    @BindView(R.id.linearyout_main_sgmore_sub05) LinearLayout linearyout_main_sgmore_sub05;
    @BindView(R.id.linearyout_main_sgmore_sub06) LinearLayout linearyout_main_sgmore_sub06;
    @BindView(R.id.linearyout_main_sgmore_sub07) LinearLayout linearyout_main_sgmore_sub07;
    @BindView(R.id.linearyout_main_sgmore_sub08) LinearLayout linearyout_main_sgmore_sub08;
    @BindView(R.id.linearyout_main_sgmore_sub09) LinearLayout linearyout_main_sgmore_sub09;

    @BindView(R.id.linearLayout_main_sgmore_t_01) LinearLayout linearLayout_main_sgmore_t_01;
    @BindView(R.id.linearLayout_main_sgmore_t_02) LinearLayout linearLayout_main_sgmore_t_02;
    @BindView(R.id.linearLayout_main_sgmore_t_03) LinearLayout linearLayout_main_sgmore_t_03;
    @BindView(R.id.linearLayout_main_sgmore_t_04) LinearLayout linearLayout_main_sgmore_t_04;
    @BindView(R.id.linearLayout_main_sgmore_t_05) LinearLayout linearLayout_main_sgmore_t_05;
    @BindView(R.id.linearLayout_main_sgmore_t_06) LinearLayout linearLayout_main_sgmore_t_06;
    @BindView(R.id.linearLayout_main_sgmore_t_07) LinearLayout linearLayout_main_sgmore_t_07;
    @BindView(R.id.linearLayout_main_sgmore_t_08) LinearLayout linearLayout_main_sgmore_t_08;


    // --------------------data



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_sgmore;
    }

    @Override
    protected int getToolbarId() {
        return R.id.tool_bar_main_sgmore;
    }

    @OnClick(R.id.button_main_sgmore_back)
    public void onClickBack(){
        finish();
    }
    @OnClick(R.id.linearyout_main_sgmore_sub01)
    public void onClickSGMoreSub01(){
        Log.e("FM9333","SGMoreSub=1111111111111111");
    }
    @OnClick(R.id.linearyout_main_sgmore_sub02)
    public void onClickSGMoreSub02(){
        Log.e("FM9333","SGMoreSub=22222222222222222");
    }
    @OnClick(R.id.linearyout_main_sgmore_sub03)
    public void onClickSGMoreSub03(){
        Log.e("FM9333","SGMoreSub=33333333333333333");
    }
    @OnClick(R.id.linearyout_main_sgmore_sub04)
    public void onClickSGMoreSub04(){
        Log.e("FM9333","SGMoreSub=44444444444444444");
    }
    @OnClick(R.id.linearyout_main_sgmore_sub05)
    public void onClickSGMoreSub05(){
        Log.e("FM9333","SGMoreSub=5555555555555555");
    }
    @OnClick(R.id.linearyout_main_sgmore_sub06)
    public void onClickSGMoreSub06(){
        Log.e("FM9333","SGMoreSub=666666666666666");
    }
    @OnClick(R.id.linearyout_main_sgmore_sub07)
    public void onClickSGMoreSub07(){
        Log.e("FM9333","SGMoreSub=7777777777777777777");
    }
    @OnClick(R.id.linearyout_main_sgmore_sub08)
    public void onClickSGMoreSub08(){
        Log.e("FM9333","SGMoreSub=8888888888888888888");
    }
    @OnClick(R.id.linearyout_main_sgmore_sub09)
    public void onClickSGMoreSub09(){
        Log.e("FM9333","SGMoreSub=999999999999999999");
    }

    @OnClick({R.id.linearLayout_main_sgmore_t_01,R.id.linearLayout_main_sgmore_t_01_button})
    public void onClickSGMoreTui01(){
        Log.e("FM9333","SGMoreTui=111111111111111111");
    }
    @OnClick({R.id.linearLayout_main_sgmore_t_02,R.id.linearLayout_main_sgmore_t_02_button})
    public void onClickSGMoreTui02(){ Log.e("FM9333","SGMoreTui=22222222222222");}
    @OnClick({R.id.linearLayout_main_sgmore_t_03,R.id.linearLayout_main_sgmore_t_03_button})
    public void onClickSGMoreTui03(){
        Log.e("FM9333","SGMoreTui=333333333333333333");
    }
    @OnClick({R.id.linearLayout_main_sgmore_t_04,R.id.linearLayout_main_sgmore_t_04_button})
    public void onClickSGMoreTui04(){
        Log.e("FM9333","SGMoreTui=444444444444444444");
    }
    @OnClick({R.id.linearLayout_main_sgmore_t_05,R.id.linearLayout_main_sgmore_t_05_button})
    public void onClickSGMoreTui05(){
        Log.e("FM9333","SGMoreTui=55555555555555555");
    }
    @OnClick({R.id.linearLayout_main_sgmore_t_06,R.id.linearLayout_main_sgmore_t_06_button})
    public void onClickSGMoreTui06(){
        Log.e("FM9333","SGMoreTui=6666666666666666666");
    }
    @OnClick({R.id.linearLayout_main_sgmore_t_07,R.id.linearLayout_main_sgmore_t_07_button})
    public void onClickSGMoreTui07(){
        Log.e("FM9333","SGMoreTui=77777777777777777777");
    }
    @OnClick({R.id.linearLayout_main_sgmore_t_08,R.id.linearLayout_main_sgmore_t_08_button})
    public void onClickSGMoreTui08(){
        Log.e("FM9333","SGMoreTui=88888888888888888888");
    }


}
