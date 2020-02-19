package com.nesun.sec.apps.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.nesun.sec.apps.constant.Constant;

/**
 * Created by Administrator on 2016/10/31.
 */

public class AppUtil {

    /**
     * 获取屏幕分辨率
     * @param context
     * @return
     */
    public static int[] getScreenDispaly(Context context) {
        // 获取屏幕配置
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        int height = dm.heightPixels;       // 屏幕高度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        int screenHeight = (int) (height / density);// 屏幕高度(dp)
        int result[] = { width, height };
        return result;
    }


    /**
     * 检查是否存在九宫格密码，没有则表示第一次运行App，需要设置密码
     * @return
     */
    public static boolean isSetPassword(Context context){
            String saveCode = getGesturePassword(context);
            if (!TextUtils.isEmpty(saveCode))return true;
            else return false;
    }
    /**
     * 获取九宫格密码
     * @param context
     * @return
     */
    public static String getGesturePassword(Context context){
        return  PrefUtil.getValue(context,Constant.KEY_GESTURE_CODE);
    }

    /**
     * 设置九宫格密码
     * @param context
     * @param value
     */
    public static void  setGesturePassword(Context context,String value){
        PrefUtil.setValue(context,Constant.KEY_GESTURE_CODE,value);
    }



    /**
     * 获取 App是否首次运行
     * @param context
     * @return
     */
    public static String isFirstRun(Context context){
        return PrefUtil.getValue(context, Constant.IS_FIRST_RUN);
    }

    public static void setIsFirstRun(Context context,String value){
        PrefUtil.setValue(context, Constant.IS_FIRST_RUN,value);// 记录用户已经看过视频，记录，以后不再展示视频
    }


    /**
     * 获取应用锁开关状态
     */
    public static boolean isApplockStatus(Context context){
        String status= PrefUtil.getValue(context,Constant.KEY_APPLOCK_STATUS);
        if (TextUtils.isEmpty(status)){ // 读取不到数据，第一次打开应用锁，设置为开启状态
            setAppLockStatus(context,true);
            return true;
        }else if (status.equals("1")){ // 读取到1 为开启状态
            return true;
        }else if (status.equals("0")){ // 读取到0 为关闭状态
            return false;
        }else {
            return false;
        }
    }

    public static void setAppLockStatus(Context context,boolean isOpen){
        if (isOpen)PrefUtil.setValue(context,Constant.KEY_APPLOCK_STATUS,"1");
        else PrefUtil.setValue(context,Constant.KEY_APPLOCK_STATUS,"0");
    }

    /**
     * 加锁时机设定
     */
    public static int getApplockType(Context context){
        String status= PrefUtil.getValue(context,Constant.KEY_APPLOCK_TYPE_NAME);
        if (TextUtils.isEmpty(status)) { // 读取不到数据，第一次打开应用锁，类型设置为1,
            return Constant.KEY_APPLOCK_TYPE_1;
        }else if (status.equals("1")){ // 读取到1
            return Constant.KEY_APPLOCK_TYPE_1;
        }else if (status.equals("2")){ // 读取到2
            return Constant.KEY_APPLOCK_TYPE_2;
        }else {
            return Constant.KEY_APPLOCK_TYPE_2;
        }
    }

    public static void setApplockType(Context context,int type){
        if (type==Constant.KEY_APPLOCK_TYPE_1)PrefUtil.setValue(context,Constant.KEY_APPLOCK_TYPE_NAME,"1");
        else if (type==Constant.KEY_APPLOCK_TYPE_2)PrefUtil.setValue(context,Constant.KEY_APPLOCK_TYPE_NAME,"2");
    }





}
