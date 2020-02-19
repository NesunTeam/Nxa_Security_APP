package com.nesun.sec.apps.engine;


import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.nesun.sec.apps.bean.PrivateFileBean;
import com.nesun.sec.apps.constant.Constant;
import com.nesun.sec.apps.dao.PrivateFileDao;
import com.nesun.sec.apps.utils.MD5Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * 隐私空间引擎
 *
 *
 *
 */
public class PrivateZoneEngine  {

    public Context context;
    public static String filePath4 = "/sdcard/.nesun";

    public static String fileNameXiangCe = "私密相册";
    public static String fileNameShiPin = "私密视频";
    public static String fileNameWenJian = "私密文件";
//    public static String fileNameJieShou = "我的接收";
//    public static String fileNameJieXiaZai = "离线下载";

    public static String filePathXiangCe = filePath4 +File.separator+ MD5Util.md5(fileNameXiangCe);
    public static String filePathShiPin = filePath4 +File.separator+ MD5Util.md5(fileNameShiPin);
    public static String filePathWenJian = filePath4 +File.separator+ MD5Util.md5(fileNameWenJian);

//    public static String filePathJieShou = filePath4+ MD5Util.md5(fileNameJieShou);
//    public static String filePathXiaZai = filePath4+ MD5Util.md5(fileNameJieXiaZai);

    public PrivateFileDao privateFileDao;



    public PrivateZoneEngine(Context context){
        this.context = context;
        privateFileDao = new PrivateFileDao(context);
        initData();
    }


    public void initData(){

        // 检查基础文件夹是否存在，不存在创建
        File file = new File(filePath4);
        if (!file.exists())file.mkdir();

        // 检查隐私文件夹是否创建
        file = new File(filePathXiangCe);
        if (!file.exists()) {
            file.mkdir();
        }
        file = new File(filePathShiPin);
        if (!file.exists()) {
            file.mkdir();
        }
        file = new File(filePathWenJian);
        if (!file.exists()){
            file.mkdir();
        }
//        file = new File(filePathJieShou);
//        if (!file.exists()){
//            file.mkdir();
//        }
//        file = new File(filePathXiaZai);
//        if (!file.exists()){
//            file.mkdir();
//        }

        // 记录文件夹信息到SQL
        String tiems = getTime();
        PrivateFileBean privateFileBean1 = new PrivateFileBean(fileNameXiangCe,tiems,"N",filePathXiangCe, Constant.PRIVATE_ZONE_FILE_TYPE_DIR_IMG);
        PrivateFileBean privateFileBean2 = new PrivateFileBean(fileNameWenJian,tiems,"N",filePathWenJian,Constant.PRIVATE_ZONE_FILE_TYPE_DIR_HID);
        PrivateFileBean privateFileBean3 = new PrivateFileBean(fileNameShiPin,tiems,"N",filePathShiPin,Constant.PRIVATE_ZONE_FILE_TYPE_DIR_RECEIVE);
//        PrivateFileBean privateFileBean3 = new PrivateFileBean(fileNameJieShou,tiems,"N",filePathJieShou,Constant.PRIVATE_ZONE_FILE_TYPE_DIR_RECEIVE);
//        PrivateFileBean privateFileBean4 = new PrivateFileBean(fileNameJieXiaZai,tiems,"N",filePathXiaZai,Constant.PRIVATE_ZONE_FILE_TYPE_DIR_LIXIAN);

        if (!privateFileDao.isExists(privateFileBean1.getFilePath()))privateFileDao.add(privateFileBean1);
        if (!privateFileDao.isExists(privateFileBean2.getFilePath()))privateFileDao.add(privateFileBean2);
        if (!privateFileDao.isExists(privateFileBean3.getFilePath()))privateFileDao.add(privateFileBean3);

//        if (!privateFileDao.isExists(privateFileBean4.getFilePath()))privateFileDao.add(privateFileBean4);
//        List<PrivateFileBean> list = privateFileDao.selectAll();
//        Log.e("list","list ========="+list.size());

    }


    public List<PrivateFileBean> getFilesInfo(String filePath){
        Log.e("FM9333","filePath="+filePath);
        List<PrivateFileBean> lists = new ArrayList<PrivateFileBean>();
        File file = new File(filePath);
        if (file.isDirectory()){
            File[] files = file.listFiles();
            if (files!=null && files.length>0){
                for (File tmp:files){
                    String path = tmp.getPath();
                    Log.e("FM9333","Path666="+path);
                    if (privateFileDao.isExists(path)) {
                        PrivateFileBean bean = privateFileDao.selectByPath(path);
                        if (tmp.isDirectory())lists.add(0,bean);
                        else lists.add(bean);
                    }
                }
            }
            if (!filePath.equals(filePath4)) {
                PrivateFileBean backBean = new PrivateFileBean("BACK", "BACK", "BACK", "BACK", Constant.PRIVATE_ZONE_FILE_TYPE_BACK_OLD);
                lists.add(0, backBean);
            }
        }
        return lists;
    }


    /**
     * 创建新文件夹
     * @param filePath
     * @param fileName
     */
    public void addPrivateFileDir(String filePath,String fileName){
        if (TextUtils.isEmpty(fileName)|| TextUtils.isEmpty(filePath))return;
        if (fileName.length()>15)fileName = fileName.substring(0,14)+"...";

        String path;
        if (filePath.endsWith("/"))path = filePath+MD5Util.md5(fileName);
        else path = filePath+File.separator+MD5Util.md5(fileName);
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }

        // 记录文件夹信息到SQL
        String tiems = getTime();
        PrivateFileBean privateFileBean1 = new PrivateFileBean(fileName,tiems,"N",path, Constant.PRIVATE_ZONE_FILE_TYPE_DIR);
        if (!privateFileDao.isExists(privateFileBean1.getFilePath()))privateFileDao.add(privateFileBean1);
    }


    /**
     * 添加隐私文件到目录
     * @param filePath
     * @param toFilePath
     */
    public void addPrivateFile(String filePath,String toFilePath){

        if (TextUtils.isEmpty(filePath) || TextUtils.isEmpty(toFilePath)){return;}

        File oldFile = new File(filePath);
        if (oldFile.exists()&&oldFile.isFile()){
            String fileName = oldFile.getName();

            String path;
            if (toFilePath.endsWith("/"))path = toFilePath+MD5Util.md5(fileName);
            else path = toFilePath+File.separator+MD5Util.md5(fileName);
            int type = getFileType(fileName);
//            Log.e("FM9333","ToFile="+path);
            String fileSize = getFileSizeString(filePath);
//            Log.e("FM9333","fileSize="+fileSize);
            String time = getTime();
//            Log.e("FM9333","time="+time);

            PrivateFileBean bean =new PrivateFileBean(fileName,time,fileSize,path,type);
            if (!privateFileDao.isExists(path))privateFileDao.add(bean);

            // 拷贝文件
            try {
                File toFile = newFile(toFilePath,MD5Util.md5(fileName));
                InputStream is = new FileInputStream(oldFile);
                FileOutputStream fos = new FileOutputStream(toFile);
                byte[] buffer = new byte[4096];
                int byteCount = 0;
                while ((byteCount = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, byteCount);
                }
                fos.flush();
                is.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public int getFileType(String fileName){
        if (TextUtils.isEmpty(fileName))return -1;
        String tmp = fileName.toLowerCase();
        if (tmp.endsWith(".3gp")) return Constant.PRIVATE_ZONE_FILE_TYPE_3GP;
        else if (tmp.endsWith(".7z")) return Constant.PRIVATE_ZONE_FILE_TYPE_7Z;
        else if (tmp.endsWith(".ai")) return Constant.PRIVATE_ZONE_FILE_TYPE_AI;
        else if (tmp.endsWith(".ape")) return Constant.PRIVATE_ZONE_FILE_TYPE_APE;
        else if (tmp.endsWith(".apk")) return Constant.PRIVATE_ZONE_FILE_TYPE_APK;
        else if (tmp.endsWith(".ass")) return Constant.PRIVATE_ZONE_FILE_TYPE_ASS;
        else if (tmp.endsWith(".avi")) return Constant.PRIVATE_ZONE_FILE_TYPE_AVI;
        else if (tmp.endsWith(".bat")) return Constant.PRIVATE_ZONE_FILE_TYPE_BAT;
        else if (tmp.endsWith(".bt")) return Constant.PRIVATE_ZONE_FILE_TYPE_BT;
        else if (tmp.endsWith(".cab")) return Constant.PRIVATE_ZONE_FILE_TYPE_CAB;
        else if (tmp.endsWith(".chm")) return Constant.PRIVATE_ZONE_FILE_TYPE_CHM;
        else if (tmp.endsWith(".code")) return Constant.PRIVATE_ZONE_FILE_TYPE_CODE;
        else if (tmp.endsWith(".css")) return Constant.PRIVATE_ZONE_FILE_TYPE_CSS;
        else if (tmp.endsWith(".dmg")) return Constant.PRIVATE_ZONE_FILE_TYPE_DMG;
        else if (tmp.endsWith(".doc")) return Constant.PRIVATE_ZONE_FILE_TYPE_DOC;
        else if (tmp.endsWith(".exe")) return Constant.PRIVATE_ZONE_FILE_TYPE_EXE;
        else if (tmp.endsWith(".fla")) return Constant.PRIVATE_ZONE_FILE_TYPE_FLA;
        else if (tmp.endsWith(".flv")) return Constant.PRIVATE_ZONE_FILE_TYPE_FLV;
        else if (tmp.endsWith(".gif")) return Constant.PRIVATE_ZONE_FILE_TYPE_GIF;
        else if (tmp.endsWith(".html")) return Constant.PRIVATE_ZONE_FILE_TYPE_HTML;
        else if (tmp.endsWith(".img")) return Constant.PRIVATE_ZONE_FILE_TYPE_IMG;
        else if (tmp.endsWith(".ipa")) return Constant.PRIVATE_ZONE_FILE_TYPE_IPA;
        else if (tmp.endsWith(".iso")) return Constant.PRIVATE_ZONE_FILE_TYPE_ISO;
        else if (tmp.endsWith(".jpg")) return Constant.PRIVATE_ZONE_FILE_TYPE_JPEG;
        else if (tmp.endsWith(".log")) return Constant.PRIVATE_ZONE_FILE_TYPE_LOG;
        else if (tmp.endsWith(".mkv")) return Constant.PRIVATE_ZONE_FILE_TYPE_MKV;
        else if (tmp.endsWith(".mov")) return Constant.PRIVATE_ZONE_FILE_TYPE_MOV;
        else if (tmp.endsWith(".mp3")) return Constant.PRIVATE_ZONE_FILE_TYPE_MP3;
        else if (tmp.endsWith(".mp4")) return Constant.PRIVATE_ZONE_FILE_TYPE_MP4;
        else if (tmp.endsWith(".msi")) return Constant.PRIVATE_ZONE_FILE_TYPE_MSI;
        else if (tmp.endsWith(".music")) return Constant.PRIVATE_ZONE_FILE_TYPE_MUSIC;
        else if (tmp.endsWith(".pdf")) return Constant.PRIVATE_ZONE_FILE_TYPE_PDF;
        else if (tmp.endsWith(".png")) return Constant.PRIVATE_ZONE_FILE_TYPE_PNG;
        else if (tmp.endsWith(".ppt")) return Constant.PRIVATE_ZONE_FILE_TYPE_PPT;
        else if (tmp.endsWith(".psd")) return Constant.PRIVATE_ZONE_FILE_TYPE_PSD;
        else if (tmp.endsWith(".rar")) return Constant.PRIVATE_ZONE_FILE_TYPE_RAR;
        else if (tmp.endsWith(".raw")) return Constant.PRIVATE_ZONE_FILE_TYPE_RAW;
        else if (tmp.endsWith(".rm")) return Constant.PRIVATE_ZONE_FILE_TYPE_RM;
        else if (tmp.endsWith(".rmvb")) return Constant.PRIVATE_ZONE_FILE_TYPE_RMVB;
        else if (tmp.endsWith(".srt")) return Constant.PRIVATE_ZONE_FILE_TYPE_SRT;
        else if (tmp.endsWith(".ssa")) return Constant.PRIVATE_ZONE_FILE_TYPE_SSA;
        else if (tmp.endsWith(".swf")) return Constant.PRIVATE_ZONE_FILE_TYPE_SWF;
        else if (tmp.endsWith(".txt")) return Constant.PRIVATE_ZONE_FILE_TYPE_TXT;
        else if (tmp.endsWith(".video")) return Constant.PRIVATE_ZONE_FILE_TYPE_VIDEO;
        else if (tmp.endsWith(".wma")) return Constant.PRIVATE_ZONE_FILE_TYPE_WMA;
        else if (tmp.endsWith(".wmv")) return Constant.PRIVATE_ZONE_FILE_TYPE_WMV;
        else if (tmp.endsWith(".xls")) return Constant.PRIVATE_ZONE_FILE_TYPE_XLS;
        else if (tmp.endsWith(".zip")) return Constant.PRIVATE_ZONE_FILE_TYPE_ZIP;
        else if (tmp.endsWith(".keynote")) return Constant.PRIVATE_ZONE_FILE_TYPE_KEYNOTE;
        else if (tmp.endsWith(".numbers")) return Constant.PRIVATE_ZONE_FILE_TYPE_NUMBERS;
        else if (tmp.endsWith(".pages")) return Constant.PRIVATE_ZONE_FILE_TYPE_PAGES;
        else return Constant.PRIVATE_ZONE_FILE_TYPE_NOFIND;
    }


    public File newFile(String filePath, String fileName){
        if(filePath == null || filePath.length() == 0
                || fileName == null || fileName.length() == 0){
            return null;
        }
        try {
            //判断目录是否存在，如果不存在，递归创建目录
            File dir = new File(filePath);
            if(!dir.exists()){
                dir.mkdirs();
            }

            //组织文件路径
            StringBuilder sbFile = new StringBuilder(filePath);
            if(!filePath.endsWith("/")){
                sbFile.append("/");
            }
            sbFile.append(fileName);

            //创建文件并返回文件对象
            File file = new File(sbFile.toString());
            if(!file.exists()){
                file.createNewFile();
            }
            return file;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public void removeFile(String filePath) {
        if(filePath == null || filePath.length() == 0){
            return;
        }
        try {
            File file = new File(filePath);
            if(file.exists()){
                removeFile(file);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void removeFile(File file){
        //如果是文件直接删除
        if(file.isFile()){
            file.delete();
            return;
        }
        //如果是目录，递归判断，如果是空目录，直接删除，如果是文件，遍历删除
        if(file.isDirectory()){
            File[] childFile = file.listFiles();
            if(childFile == null || childFile.length == 0){
                file.delete();
                return;
            }
            for(File f : childFile){
                removeFile(f);
            }
            file.delete();
        }
    }



    public String getFileSizeString(String filePath){
        float siz = getFileSize(filePath);
        return readableFileSize((long) siz);
    }
    public static String readableFileSize(long size) {
        if(size <= 0) return "0";
        final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + units[digitGroups];
    }

    float size = 0;
    public float getFileSize(String filePath) {
        if(filePath == null || filePath.length() == 0){
            return 0;
        }
        try {
            File file = new File(filePath);
            if(file.exists()){
                size = 0;
                return getSize(file);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }

    private float getSize(File file) {
        try {
            //如果是目录则递归计算其内容的总大小
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                for (File f : children) {
                    size += getSize(f);
                }
                return size;
            }
            //如果是文件则直接返回其大小
            else {
                return (float) file.length();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return size;
    }



    public void copyFile(String filePath, String newDirPath) {
        if(filePath == null || filePath.length() == 0){
            return;
        }
        try {
            File file = new File(filePath);
            if(!file.exists()){
                return;
            }
            //判断目录是否存在，如果不存在，则创建
            File newDir = new File(newDirPath);
            if(!newDir.exists()){
                newDir.mkdirs();
            }
            //创建目标文件
            File newFile = newFile(newDirPath, file.getName());
            InputStream is = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(newFile);
            byte[] buffer = new byte[4096];
            int byteCount = 0;
            while ((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();
            is.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void copyDir(String dirPath, String newDirPath) {
        if(dirPath == null || dirPath.length() == 0
                || newDirPath==null || newDirPath.length() == 0){
            return;
        }
        try {
            File file = new File(dirPath);
            if(!file.exists() && !file.isDirectory()){
                return;
            }
            File[] childFile = file.listFiles();
            if(childFile == null || childFile.length == 0){
                return;
            }
            File newFile = new File(newDirPath);
            newFile.mkdirs();
            for (File fileTemp : childFile) {
                if(fileTemp.isDirectory()){
                    copyDir(fileTemp.getPath(), newDirPath + "/" + fileTemp.getName());
                }else {
                    copyFile(fileTemp.getPath(), newDirPath);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void moveFile(String filePath, String newDirPath) {
        if(filePath == null || filePath.length() == 0
                || newDirPath==null || newDirPath.length() == 0){
            return;
        }
        try {
            //拷贝文件
            copyFile(filePath, newDirPath);
            //删除原文件
            removeFile(filePath);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void moveDir(String dirPath, String newDirPath) {
        if(dirPath == null || dirPath.length() == 0
                || newDirPath==null || newDirPath.length() == 0){
            return;
        }
        try {
            //拷贝目录
            copyDir(dirPath, newDirPath);
            //删除目录
            removeFile(dirPath);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件添加时间
     * @return
     */
    public String getTime(){
//        "yyyy-MM-dd_HH:mm"
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd_HH:mm");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

}
