package com.nesun.sec.apps.view.activity.zone;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nesun.sec.apps.R;
import com.nesun.sec.apps.bean.PrivateFileBean;
import com.nesun.sec.apps.constant.Constant;

import java.util.List;

public class PrivateZoneFileAdapter extends ArrayAdapter<PrivateFileBean> {

    private int resourceId;

    public PrivateZoneFileAdapter(@NonNull Context context, int resource, @NonNull List<PrivateFileBean> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PrivateFileBean privateFileBean = getItem(position);//获取当前项的实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

        switch (privateFileBean.getFileType()){
            case Constant.PRIVATE_ZONE_FILE_TYPE_BACK_OLD: // 判断是返回上层
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_folder);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText("...");
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText("上层文件夹");
                ((ImageView) view.findViewById(R.id.imageView_list_private_about)).setVisibility(View.INVISIBLE);
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_DIR: //文件夹
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_folder);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_DIR_IMG: //图片 文件夹
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_folder_xiangce);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_DIR_HID:// 隐藏 文件夹
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_folder_hidden);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_DIR_LIXIAN://离线 文件夹
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_folder_lixian);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_DIR_RECEIVE:// 接收 文件夹
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_folder_receive);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_DIR_BEIFEN:// 备份 文件夹
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_folder_beifen);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_DIR_LIBAO:// 礼包 文件夹
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_folder_libao);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileTimeTrim());
                break;

            case Constant.PRIVATE_ZONE_FILE_TYPE_NOFIND:// 未知
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_nofind);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;

            case Constant.PRIVATE_ZONE_FILE_TYPE_3GP:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_3gp);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;

            case Constant.PRIVATE_ZONE_FILE_TYPE_7Z:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_7z);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_AI:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_ai);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_APE:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_ape);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_APK:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_apk);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_ASS:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_ass);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_AVI:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_avi);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_BAT:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_bat);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_BT:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_bt);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_CAB:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_cab);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_CHM:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_chm);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_CODE:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_code);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_CSS:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_css);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_DMG:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_dmg);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_DOC:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_doc);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_EXE:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_exe);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_FLA:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_fla);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_FLV:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_flv);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_GIF:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_gif);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_HTML:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_html);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_IMG:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_img);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_IPA:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_ipa);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_ISO:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_iso);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_JPEG:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_jpeg);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_LOG:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_log);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_MKV:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_mkv);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_MOV:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_mov);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_MP3:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_mp3);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_MP4:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_mp4);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_MSI:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_msi);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_MUSIC:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_music);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_PDF:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_pdf);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_PNG:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_png);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_PPT:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_ppt);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_PSD:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_psd);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_RAR:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_rar);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_RAW:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_raw);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_RM:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_rm);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_RMVB:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_rmvb);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_SRT:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_srt);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_SSA:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_ssa);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_SWF:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_swf);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_TXT:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_txt);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_VIDEO:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_video);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_WMA:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_wma);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_WMV:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_wmv);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_XLS:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_xls);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_ZIP:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_icon_zip);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_KEYNOTE:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_keynote);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_NUMBERS:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_numbers);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;
            case Constant.PRIVATE_ZONE_FILE_TYPE_PAGES:
                ((ImageView) view.findViewById(R.id.imageView_list_private_file)).setImageResource(R.mipmap.ic_parttern_pages);
                ((TextView) view.findViewById(R.id.textView_list_private_file)).setText(privateFileBean.getFileName());
                ((TextView) view.findViewById(R.id.textView_list_private_file2)).setText(privateFileBean.getFileSize() +"  "+privateFileBean.getFileTimeTrim());
                break;

        }
        return view;
    }


}
