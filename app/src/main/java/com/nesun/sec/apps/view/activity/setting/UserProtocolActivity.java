package com.nesun.sec.apps.view.activity.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.nesun.sec.apps.R;
import com.nesun.sec.apps.constant.Constant;
import com.nesun.sec.apps.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class UserProtocolActivity  extends BaseActivity {


    // --------------------ui
    @BindView(R.id.button_user_setting_userprotocol_back) Button button_back;
    @BindView(R.id.webView_userprotocol_01) WebView webView_userprotocol_01;
    @BindView(R.id.textView_user_protocol_title) TextView textView_user_protocol_title;


    // --------------------data



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        String type = getIntent().getStringExtra(Constant.USER_WEB_TYPE).toString();
        String url  = "";
        if (type.equals(Constant.USER_WEB_TYPE_FANS)){ url = Constant.USER_WEB_TYPE_FANS_URL; textView_user_protocol_title.setText(R.string.textview_title_userprotocol_01);}
        if (type.equals(Constant.USER_WEB_TYPE_PROTOCOL)) { url = Constant.USER_WEB_TYPE_PROTOCOL_URL; textView_user_protocol_title.setText(R.string.textview_title_userprotocol_02);}
        if (type.equals(Constant.USER_WEB_TYPE_GUAN)){ url = Constant.USER_WEB_TYPE_GUAN_URL; textView_user_protocol_title.setText(R.string.textview_title_userprotocol_03);}
        if (type.equals(Constant.USER_WEB_TYPE_HELP)){ url = Constant.USER_WEB_TYPE_HELP_URL; textView_user_protocol_title.setText(R.string.textview_title_userprotocol_04);}
        setWebView(url);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_userprotocol;
    }

    @Override
    protected int getToolbarId() {
        return R.id.tool_bar_user_setting_userprotocol;
    }

    @OnClick({R.id.button_user_setting_userprotocol_back})
    public void onClickBack(){
        finish();
    }

    public void setWebView(String url) {
        WebSettings webSettings = webView_userprotocol_01.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        webView_userprotocol_01.loadUrl(url);
        webView_userprotocol_01.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }});
    }
}
