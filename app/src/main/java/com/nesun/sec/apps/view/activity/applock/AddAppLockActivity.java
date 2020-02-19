package com.nesun.sec.apps.view.activity.applock;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nesun.sec.apps.R;
import com.nesun.sec.apps.bean.AppInfoBean;
import com.nesun.sec.apps.bean.LockAppInfoBean;
import com.nesun.sec.apps.dao.AppLockDao;
import com.nesun.sec.apps.presenter.ReadAppsInfoPresenter;
import com.nesun.sec.apps.view.activity.BaseActivity;
import com.nesun.sec.apps.view.biz.IReadAppsInfoView;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class AddAppLockActivity  extends BaseActivity implements IReadAppsInfoView {

    // --------------------ui
    @BindView(R.id.button_addapplock_back) Button button_applock_back;
    @BindView(R.id.button_addapplock_addapp) Button button_addapplock_addapp;
    @BindView(R.id.rotateloading_addapplock_loding)RotateLoading rotateloading_addapplock_loding;
    @BindView(R.id.textView_addapplock_title_01)TextView textView_addapplock_title_01;
    @BindView(R.id.listView_addapplock_01) ListView listView_addapplock_01;

    // --------------------data
    public List<LockAppInfoBean> cats = new ArrayList<LockAppInfoBean>();
    private List<AppInfoBean> systemApps = new ArrayList<AppInfoBean>();
    private List<AppInfoBean> userApps = new ArrayList<AppInfoBean>();
    public AddAppLockAdapter addAppLockAdapter;
    AppLockDao dao = new AppLockDao(this);
    private ReadAppsInfoPresenter readAppsInfoPresenter = new ReadAppsInfoPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEvent();//初始化监听
    }

    @Override
    protected void initView() {
        addAppLockAdapter = new AddAppLockAdapter(AddAppLockActivity.this, R.layout.list_addapplock, cats);// 加载List数据
        listView_addapplock_01.setAdapter(addAppLockAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        readAppsInfoPresenter.readApps(this); // MVP加载数据
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_addapplock;
    }

    @Override
    protected int getToolbarId() {
        return R.id.tool_bar_addapplock;
    }

    @Override
    public void showLoding() {
        rotateloading_addapplock_loding.start(); //进度条开启
        textView_addapplock_title_01.setVisibility(View.INVISIBLE); // 隐藏标题
    }

    @Override
    public void hindLoding() {
        rotateloading_addapplock_loding.stop();
        textView_addapplock_title_01.setVisibility(View.VISIBLE); // 显示标题
        setButtonCloseClick(); // 读取完毕，按钮不可点击
    }

    @Override
    public void showReadAppsInfoSuccess(List<AppInfoBean> apps) {
        initAppsInfo(apps); //初始化数据
        addAppLockAdapter.notifyDataSetChanged();// 刷新列表
    }

    @Override
    public void showReadAppsInfoFaild(String message) {
    }


    /**
     * 初始化AppInfos数据
     */
    private void initAppsInfo(List<AppInfoBean> apps){
        systemApps.clear();
        userApps.clear();
        for (AppInfoBean app : apps) {
            if (app.isSystemApp()) systemApps.add(app);
            else userApps.add(app);
        }
        for (AppInfoBean tmpBean:userApps){
            if (!dao.isExists(tmpBean.getPackageName()) && !tmpBean.getPackageName().equals(getPackageName()))
                cats.add(new LockAppInfoBean(tmpBean.getName(), tmpBean.getIcon(),tmpBean.getPackageName()));
        }
        for (AppInfoBean tmpBean:systemApps){
            if (!dao.isExists(tmpBean.getPackageName()) && !tmpBean.getPackageName().equals(getPackageName()))
                cats.add(new LockAppInfoBean(tmpBean.getName(), tmpBean.getIcon(),tmpBean.getPackageName(),tmpBean.isSystemApp()));
        }
    }


    /**
     * 实时更新，获取用户App总数
     * @return
     */
    public int getUserAppSize(){
        int size = 0;
        for (LockAppInfoBean tmp:cats){
            if (!tmp.isSystemApp())size++;
        }
        return size;
    }

    /**
     * 监听Listview 滚动效果
     */
    public void initEvent() {
        listView_addapplock_01.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                // prevent the data haven't loaded
                if (cats.size()<=0) return;
                if (firstVisibleItem >= getUserAppSize()) {
                    textView_addapplock_title_01.setText(R.string.textview_title_applock_sub02);
                } else {
                    textView_addapplock_title_01.setText(R.string.textview_title_applock_sub01);
                }
            }
        });
    }

    @OnClick(R.id.button_addapplock_back)
    public void onClickUserBack(){
        this.finish();
    }

    @OnClick(R.id.button_addapplock_addapp)
    public void onClickApplockAdd(){
        //遍历 list，将选中的数据记录，并删除当前List中选中数据
        Iterator<LockAppInfoBean> iterator = cats.iterator();
        while (iterator.hasNext()) {
            LockAppInfoBean tmp = iterator.next();
            if (tmp.isSelect()) {
                dao.insert(tmp.getPackageName()); //这里将数据记录到SQL
                iterator.remove(); // 移除这条数据
            }
        }
        addAppLockAdapter.notifyDataSetChanged();// 通知刷新List
        setButtonCloseClick();// 改变回按钮状态
    }

    @OnItemClick(R.id.listView_addapplock_01)
    public void listItemClick(int position) {
    }

    public void selectButtonClick(View v){
        ImageView imageView = (ImageView)v.findViewById(R.id.imageView_list_addapplock_select);// 通过View获取绑定ID视图
        int position = listView_addapplock_01.getPositionForView(v); // 通过视图获取listID
        LockAppInfoBean lockAppInfoBean = cats.get(position);//获取ID点击对应的App信息
        if (!lockAppInfoBean.isSelect()) { // 判断没有选中，则选中，改图片
            lockAppInfoBean.setSelect(true);
            imageView.setImageResource(R.mipmap.add_applock_select_yes);
        }else {
            lockAppInfoBean.setSelect(false);
            imageView.setImageResource(R.mipmap.add_applock_select_no);
        }
        cats.set(position, lockAppInfoBean); //记录选中状态

        // 做判断，只要有任意一个勾选，设置按钮属性 可点击
        boolean isSelect = false;
        for (LockAppInfoBean tmp:cats){
            if (tmp.isSelect())isSelect =true;
        }
        // 判断有任意勾选，则可点击，否则还原不可点击状态
        if (isSelect) {setButtonOpenClick();}
        else {setButtonCloseClick();}
    }


    /**
     * 设置 添加 按钮允许被点击
     */
    public void setButtonOpenClick(){
        button_addapplock_addapp.setClickable(true);//勾选，则允许点击添加
        button_addapplock_addapp.setBackgroundColor(Color.argb(255, 0, 215, 141));
        button_addapplock_addapp.setTextColor(Color.argb(255, 255, 255, 255));
    }

    /**
     * 设置 添加 按钮禁止被点击
     */
    public void setButtonCloseClick(){
        button_addapplock_addapp.setClickable(false);//未选择App状态，禁止点击添加
        button_addapplock_addapp.setBackgroundColor(Color.argb(241,241,241,241));
        button_addapplock_addapp.setTextColor(Color.argb(211,187,187,187));
    }


}
