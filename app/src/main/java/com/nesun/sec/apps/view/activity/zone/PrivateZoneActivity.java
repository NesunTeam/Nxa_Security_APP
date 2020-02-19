package com.nesun.sec.apps.view.activity.zone;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.hb.dialog.myDialog.MyAlertInputDialog;
import com.lcw.library.imagepicker.ImagePicker;
import com.lcw.library.imagepicker.activity.GlideLoader;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.nesun.sec.apps.R;
import com.nesun.sec.apps.bean.PrivateFileBean;
import com.nesun.sec.apps.constant.Constant;
import com.nesun.sec.apps.engine.PrivateZoneEngine;
import com.nesun.sec.apps.view.activity.BaseActivity;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;

public class PrivateZoneActivity  extends BaseActivity implements  OnBMClickListener{


    // --------------------ui
    @BindView(R.id.tool_bar_private_zone) Toolbar tool_bar;
    @BindView(R.id.button_private_zone_back)Button button_private_zone_back;
    @BindView(R.id.button_private_zone_setting)Button button_private_zone_setting;
    @BindView(R.id.button_private_zone_addapp)Button button_private_zone_addapp;

    @BindView(R.id.listView_private_zone_01)ListView listView_private_zone_01;
    @BindView(R.id.bmb_fmtest) BoomMenuButton bmb_fmtest; //悬浮按钮

    // --------------------data
    private List<PrivateFileBean> files = new ArrayList<PrivateFileBean>();
    public PrivateZoneFileAdapter privateZoneFileAdapter;
    public PrivateZoneEngine privateZoneEngine;
    public String tmpPath ;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        privateZoneFileAdapter = new PrivateZoneFileAdapter(PrivateZoneActivity.this, R.layout.list_private_file, files);
        listView_private_zone_01.setAdapter(privateZoneFileAdapter);

        // 圆形按钮初始化
        for (int i = 0; i < bmb_fmtest.getPiecePlaceEnum().pieceNumber(); i++) {
            bmb_fmtest.addBuilder(getSquareTextInsideCircleButtonBuilder(i));
        }
    }



    // 圆形按钮 属性
    public static final int BMB_TYPE_VIDEO=0;
    public static final int BMB_TYPE_IMAGE=1;
    public static final int BMB_TYPE_FILE=2;
    public static final int BMB_TYPE_DIR=3;

    /**
     * 圆形弹出式按钮 创建
     * @return
     */
    TextInsideCircleButton.Builder getSquareTextInsideCircleButtonBuilder(int index) {
        switch (index){
            case BMB_TYPE_VIDEO:
                return new TextInsideCircleButton.Builder().normalImageRes(R.mipmap.private_zone_add_icon_02).normalText("添加视频").normalTextColor(Color.parseColor("#90000000")).normalColor(Color.parseColor("#f7f8f8")).pieceColor(Color.WHITE).listener(this);
            case BMB_TYPE_IMAGE:
                return new TextInsideCircleButton.Builder().normalImageRes(R.mipmap.private_zone_add_icon_01).normalText("添加照片").normalTextColor(Color.parseColor("#90000000")).normalColor(Color.parseColor("#f7f8f8")).pieceColor(Color.WHITE).listener(this);
            case BMB_TYPE_FILE:
                return new TextInsideCircleButton.Builder().normalImageRes(R.mipmap.private_zone_add_icon_03).normalText("添加文件").normalTextColor(Color.parseColor("#90000000")).normalColor(Color.parseColor("#f7f8f8")).pieceColor(Color.WHITE).listener(this);
            case BMB_TYPE_DIR:
                return new TextInsideCircleButton.Builder().normalImageRes(R.mipmap.private_zone_add_icon_04).normalText("新建文件夹").normalTextColor(Color.parseColor("#90000000")).normalColor(Color.parseColor("#f7f8f8")).pieceColor(Color.WHITE).listener(this);
            default:
                return new TextInsideCircleButton.Builder().normalImageRes(R.mipmap.private_zone_add_icon_01).normalText("...").normalTextColor(Color.parseColor("#90000000")).normalColor(Color.parseColor("#f7f8f8")).pieceColor(Color.WHITE).listener(this);
            }
        }


    /**
     * 圆形弹出式按钮监听
     * @param index
     */
    @Override
    public void onBoomButtonClick(int index) {
        Log.e("FM9333","Index============"+index);
        switch (index){
            case BMB_TYPE_VIDEO:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showPhoneVideos();//展示添加视频
                    }
                },500);
                break;
            case BMB_TYPE_IMAGE:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showPhonePhotos();//展示添加图片
                    }
                },500);
                break;
            case BMB_TYPE_FILE:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showPhoneFiles();//展示添加文件
                    }
                },500);
                break;
            case BMB_TYPE_DIR:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showCreateDirectory();//展示 新增文件夹
                    }
                },500);
                break;
            default:
                break;
        }
    }





    @Override
    protected void initData() {
        super.initData();

        // 初始化 文件加密引擎，获取文件路径信息
        privateZoneEngine = new PrivateZoneEngine(this);
        tmpPath = PrivateZoneEngine.filePath4;
        List<PrivateFileBean> tmpList = privateZoneEngine.getFilesInfo(PrivateZoneEngine.filePath4);
        for (PrivateFileBean bean : tmpList){
            files.add(new PrivateFileBean(bean.getFileName(),bean.getFileTime(),bean.getFileSize(),bean.getFilePath(),bean.getFileType()));
        }
        privateZoneFileAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_private_zone;
    }

    @Override
    protected int getToolbarId() {
        return R.id.tool_bar_private_zone;
    }




    @OnClick(R.id.button_private_zone_back)
    public void onClickBack(){
        this.finish();
    }


    @OnClick(R.id.button_private_zone_setting)
    public void onClickSetting(){

    }

    /**
     * 点击添加按钮
     */
    @OnClick(R.id.button_private_zone_addapp)
    public void onClickAddPrivate(){
    }

    /**
     * 点击列表监听
     * @param position
     */
    @OnItemClick(R.id.listView_private_zone_01)
    public void listItemClick(int position) {
        Log.e("FM9333","position="+position);
        PrivateFileBean privateFileBean = files.get(position);//获取ID点击对应的App信息
        if (TextUtils.isEmpty(privateFileBean.getFilePath()))return;

        // 先判断是否返回上层界面（post handler）
        if (privateFileBean.getFileType()==Constant.PRIVATE_ZONE_FILE_TYPE_BACK_OLD){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onClickNoFileBack();
                }
            }, 200);
        }

        // 然后再读取文件，判断是文件或文件夹
        File file = new File(privateFileBean.getFilePath());
        if (file.isFile()){ // 判断是文件类型
            switch (privateFileBean.getFileType()){
                case Constant.PRIVATE_ZONE_FILE_TYPE_IMG: //图片
                case Constant.PRIVATE_ZONE_FILE_TYPE_PNG:
                case Constant.PRIVATE_ZONE_FILE_TYPE_GIF:
                case Constant.PRIVATE_ZONE_FILE_TYPE_JPEG:
                    // show IMG
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(file), "image/*");
                    startActivity(intent);
                    break;
                case Constant.PRIVATE_ZONE_FILE_TYPE_MKV: // 视频
                case Constant.PRIVATE_ZONE_FILE_TYPE_MOV:
                case Constant.PRIVATE_ZONE_FILE_TYPE_MP4:
                case Constant.PRIVATE_ZONE_FILE_TYPE_RM:
                case Constant.PRIVATE_ZONE_FILE_TYPE_RMVB:
                case Constant.PRIVATE_ZONE_FILE_TYPE_VIDEO:
                case Constant.PRIVATE_ZONE_FILE_TYPE_WMA:
                case Constant.PRIVATE_ZONE_FILE_TYPE_WMV:
                    Intent intent2 = new Intent(Intent.ACTION_VIEW);
                    Uri uri = Uri.fromFile(file);
                    intent2.setDataAndType(uri, "video/*");
                    startActivity(intent2);
                    break;
            }
        }else if (file.isDirectory()){ //判断类型是文件夹(读取文件耗时，采用线程post)
            tmpPath = privateFileBean.getFilePath();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    files.clear();
                    List<PrivateFileBean> tmpList = privateZoneEngine.getFilesInfo(tmpPath);
                    for (PrivateFileBean bean : tmpList)files.add(new PrivateFileBean(bean.getFileName(),bean.getFileTime(),bean.getFileSize(),bean.getFilePath(),bean.getFileType()));
                    privateZoneFileAdapter.notifyDataSetChanged();
                }
            }, 200);
        }
    }
;


    public long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (!TextUtils.isEmpty(tmpPath) && !tmpPath.equals(PrivateZoneEngine.filePath4)) {
                onClickNoFileBack();
                return true;
            }else {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    Toast.makeText(getApplicationContext(), "再按一次退出隐私空间",Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    finish();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * 点击返回上层
     */
    public void onClickNoFileBack(){
        int index = tmpPath.lastIndexOf("/");
        tmpPath = tmpPath.substring(0,index);
//        Log.e("SSSS","tmpPath="+tmpPath);
        files.clear();
        List<PrivateFileBean> tmpList = privateZoneEngine.getFilesInfo(tmpPath);
        for (PrivateFileBean bean : tmpList) files.add(new PrivateFileBean(bean.getFileName(),bean.getFileTime(),bean.getFileSize(),bean.getFilePath(),bean.getFileType()));
        privateZoneFileAdapter.notifyDataSetChanged();
    }


    /**
     * 长按List监听
     * @param position
     * @return
     */
    @OnItemLongClick(R.id.listView_private_zone_01)
    public boolean listItemLongClick(int position){
        Log.e("FM9333","Long position="+position);
        PrivateFileBean privateFileBean = files.get(position);//获取ID点击对应的App信息
        if (privateFileBean.getFileType()!=Constant.PRIVATE_ZONE_FILE_TYPE_BACK_OLD)showListDialog(); //展示List弹窗（非返回上层才生效）
        return true;
    }

    /**
     * 右侧详情
     * @param v
     */
    public void infoButtonClick(View v){
        int position = listView_private_zone_01.getPositionForView(v);
        PrivateFileBean privateFileBean = files.get(position);//获取ID点击对应的App信息
        showListDialog();
    }


    /***
     * 展示弹窗式选项List
     */
    public void showListDialog(){
        new XPopup.Builder(this)
                .asCenterList("选择", new String[]{"取消加密", "删除文件","属性"},
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                Log.e("FM9333","Long 点击弹窗="+position);
                            }
                        })
                .show();
    }


    /**
     * 展示新建文件夹选项
     */
    public void showCreateDirectory(){
        final MyAlertInputDialog myAlertInputDialog = new MyAlertInputDialog(this).builder()
                .setTitle("新建文件夹")
                .setEditText("");
        myAlertInputDialog.setPositiveButton("创建", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("FM9333","Iuput = "+myAlertInputDialog.getResult());
                myAlertInputDialog.dismiss();
                privateZoneEngine.addPrivateFileDir(tmpPath,myAlertInputDialog.getResult());// 创建文件夹
                refreshDirsInfo();//刷新当前列表
            }
        }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAlertInputDialog.dismiss();
            }
        });
        myAlertInputDialog.show();
    }


    /**
     * 刷新当前文件列表
     */
    public void refreshDirsInfo(){
        if (TextUtils.isEmpty(tmpPath))return;
        files.clear();
        List<PrivateFileBean> tmpList = privateZoneEngine.getFilesInfo(tmpPath);
        for (PrivateFileBean bean : tmpList){
            files.add(new PrivateFileBean(bean.getFileName(),bean.getFileTime(),bean.getFileSize(),bean.getFilePath(),bean.getFileType()));
        }
        privateZoneFileAdapter.notifyDataSetChanged();
    }


    public static int REQUEST_SELECT_IMAGES_CODE = 33301;
    public static int REQUEST_SELECT_VIDEOS_CODE = 33302;
    /**
     * 展示图片选择九宫格
     */
    public void showPhonePhotos(){
        ImagePicker.getInstance()
            .setTitle("添加照片")//设置标题
            .showCamera(true)//设置是否显示拍照按钮
            .showImage(true)//设置是否展示图片
            .showVideo(false)//设置是否展示视频
            .setSingleType(true)//设置图片视频不能同时选择
            .setMaxCount(30)//设置最大选择图片数目(默认为1，单选)
//            .setImagePaths(mImageList)//保存上一次选择图片的状态，如果不需要可以忽略
            .setImageLoader(new GlideLoader())//设置自定义图片加载器
            .start(PrivateZoneActivity.this, REQUEST_SELECT_IMAGES_CODE);//REQEST_SELECT_IMAGES_CODE为Intent调
    }

    /**
     * 展示手机内视频九宫格
     */
    public void showPhoneVideos(){
        ImagePicker.getInstance()
                .setTitle("添加视频")//设置标题
                .showCamera(false)//设置是否显示拍照按钮
                .showImage(false)//设置是否展示图片
                .showVideo(true)//设置是否展示视频
                .setSingleType(true)//设置图片视频不能同时选择
                .setMaxCount(10)//设置最大选择图片数目(默认为1，单选)
                .setImageLoader(new GlideLoader())//设置自定义图片加载器
                .start(PrivateZoneActivity.this, REQUEST_SELECT_VIDEOS_CODE);//REQEST_SELECT_IMAGES_CODE为Intent调
    }


    /**
     * 展示文件选择器
     */
    public void showPhoneFiles(){

    }


    /**
     * 监听用户选择加密的 相册文件
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SELECT_IMAGES_CODE && resultCode == RESULT_OK) {
            List<String> imagePaths = data.getStringArrayListExtra(ImagePicker.EXTRA_SELECT_IMAGES);
            if (imagePaths != null) {
                for (String path : imagePaths) {
//                    Log.e("FM9333", "FM Image pathpathpathpathpath = " + path);
                    privateZoneEngine.addPrivateFile(path,PrivateZoneEngine.filePathXiangCe);
                }
            }
        }else if (requestCode == REQUEST_SELECT_VIDEOS_CODE && resultCode == RESULT_OK) {
            List<String> imagePaths = data.getStringArrayListExtra(ImagePicker.EXTRA_SELECT_IMAGES);
            if (imagePaths != null) {
                for (String path : imagePaths) {
                    privateZoneEngine.addPrivateFile(path,PrivateZoneEngine.filePathShiPin);
                }
            }
        }
        refreshDirsInfo();//刷新当前列表
    }
}
