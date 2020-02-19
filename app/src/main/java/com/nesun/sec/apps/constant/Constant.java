package com.nesun.sec.apps.constant;

import android.net.Uri;

/**
 * Created by yu.
 * This class define the constant which is needed more than once.
 */
public final class Constant {
    // action
    public static final String ACTION_SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    public static final String ACTION_UNLOCK_APP = "android.ittianyu.provider.UNLOCKED_APP";
    public static final String ACTION_OPEN_APPLOCK = "android.ittianyu.provider.OPEN_APPLOCK";

    // Uri
    public static final Uri URI_APP_LOCK_DATA_CHANGED = Uri.parse("content://com.ittianyu.observer.APP_LOCK_DATA_CHANGED");
    public static final Uri URI_PRIVATE_FILE_DATA_CHANGED = Uri.parse("content://com.ittianyu.observer.PRIVATE_FILE_DATA_CHANGED");

    // phone safe info
    public static final String KEY_SAFE_PHONE = "safe_phone";
    public static final String KEY_SIM_INFO = "sim_info";
    public static final String KEY_CONTACT_PHONE = "contact_phone";

    // phone safe setting
    public static final String KEY_CB_PHONE_SAFE = "cb_pref_phone_safe";
    public static final String KEY_CB_BIND_SIM = "cb_pref_bind_sim";
    public static final String KEY_CB_GPS_TRACE = "cb_pref_gps_trace";
    public static final String KEY_CB_DEVICE_ADMIN = "cb_pref_device_admin";
    public static final String KEY_CB_REMOTE_LOCK_SCREEN = "cb_pref_remote_lock_screen";
    public static final String KEY_CB_REMOTE_WIPE_DATA = "cb_pref_remote_wipe_data";
    public static final String KEY_CB_ALARM = "cb_pref_alarm";
    public static final String KEY_CB_APP_LOCK = "cb_pref_app_lock";

    // sms command
    public static final String SMS_GPS_TRACE = "#*gps*#";
    public static final String SMS_REMOTE_LOCK_SCREEN = "#*lock screen*#";
    public static final String SMS_REMOTE_WIPE_DATA = "#*wipe data*#";
    public static final String SMS_ALARM = "#*alarm*#";

    // setting center
    public static final String KEY_CB_AUTO_UPDATE = "cb_pref_auto_update";
    public static final String KEY_CB_BLACKLIST_INTERCEPT = "cb_pref_blacklist_intercept";
    public static final String KEY_CB_SHOW_INCOMING_LOCATION = "cb_pref_show_incoming_location";

    // FloatToast key
    public static final String KEY_FLOAT_TOAST_X = "float_toast_x";
    public static final String KEY_FLOAT_TOAST_Y = "float_toast_y";

    // Extra
    public static final String EXTRA_RESTORE_TYPE = "extra_restore_type";
    public static final String EXTRA_RESTORE_FILE = "extra_restore_file_name";
    public static final String EXTRA_LOCKED_APP_PACKAGE_NAME = "extra_locked_app_package_name";
    public static final String EXTRA_VIRUSES = "extra_viruses";

    // password key
    public static final int ENCRYPTION_COUNT = 3;
    public static final String KEY_APP_LOCK_PASSWORD = "app_lock_password";
    public static final String KEY_PHONE_SAFE_PASSWORD = "phone_safe_password";


    // 9宫格解锁功能 ---------- 手势密码点的状态
    public static final int POINT_STATE_NORMAL = 0; // 正常状态
    public static final int POINT_STATE_SELECTED = 1; // 按下状态
    public static final int POINT_STATE_WRONG = 2; // 错误状态


    // 9宫格设置完毕后，需要跳转的页面 参数传递
    public static final String START_ACTIVITY_TYPE_NAME = "START_ACTIVITY_TYPE_NAME";
    public static final String START_ACTIVITY_TYPE_1 = "START_ACTIVITY_TYPE_1"; // 应用锁界面
    public static final String START_ACTIVITY_TYPE_2 = "START_ACTIVITY_TYPE_2"; // 隐私空间界面
    public static final String START_ACTIVITY_TYPE_3 = "START_ACTIVITY_TYPE_3"; // 修改密码界面
    public static final String START_ACTIVITY_TYPE_4 = "START_ACTIVITY_TYPE_4"; // 待定界面...

    // 首页启动，启动Activity 检查是否第一次启动
    public static final String IS_FIRST_RUN = "IS_FIRST_RUN";

    // 隐私密码九宫格key
    public static final String KEY_GESTURE_CODE = "KEY_GESTURE_CODE";
    public static final String KEY_APPLOCK_STATUS = "KEY_APPLOCK_STATUS";

    // app应用锁 加锁类型
    public static final String KEY_APPLOCK_TYPE_NAME = "KEY_APPLOCK_TYPE_NAME";
    public static final int KEY_APPLOCK_TYPE_1 = 1; //直到锁屏再加锁
    public static final int KEY_APPLOCK_TYPE_2 = 2; //退出应用立即加锁


    // 用户协议，粉丝论坛等相关信息
    public static final String USER_WEB_TYPE ="USER_WEB_TYPE";
    public static final String USER_WEB_TYPE_FANS ="TYPE_1";
    public static final String USER_WEB_TYPE_PROTOCOL ="TYPE_2";
    public static final String USER_WEB_TYPE_GUAN ="TYPE_3";
    public static final String USER_WEB_TYPE_HELP ="TYPE_4";

    public static String USER_WEB_TYPE_FANS_URL ="https://m.qq.com";
    public static String USER_WEB_TYPE_PROTOCOL_URL ="https://api.m.qq.com/f/aggrement?id=7";
    public static String USER_WEB_TYPE_GUAN_URL ="https://m.baidu.com";
    public static String USER_WEB_TYPE_HELP_URL ="https://m.baidu.com";

    // 私密文件夹分类
    public static final  int PRIVATE_ZONE_FILE_TYPE_DIR = 1021;
    public static final  int PRIVATE_ZONE_FILE_TYPE_DIR_IMG = 1022;
    public static final  int PRIVATE_ZONE_FILE_TYPE_DIR_HID = 1023;
    public static final  int PRIVATE_ZONE_FILE_TYPE_DIR_LIXIAN = 1024;
    public static final  int PRIVATE_ZONE_FILE_TYPE_DIR_RECEIVE = 1025;
    public static final  int PRIVATE_ZONE_FILE_TYPE_DIR_BEIFEN = 1026;
    public static final  int PRIVATE_ZONE_FILE_TYPE_DIR_LIBAO = 1027;
    //未确认类型
    public static final  int PRIVATE_ZONE_FILE_TYPE_NOFIND = 1028;
    // 文件分类
    public static final  int PRIVATE_ZONE_FILE_TYPE_3GP = 1029;
    public static final  int PRIVATE_ZONE_FILE_TYPE_7Z = 1030;
    public static final  int PRIVATE_ZONE_FILE_TYPE_AI = 1031;
    public static final  int PRIVATE_ZONE_FILE_TYPE_APE = 1032;
    public static final  int PRIVATE_ZONE_FILE_TYPE_APK = 1033;
    public static final  int PRIVATE_ZONE_FILE_TYPE_ASS = 1034;
    public static final  int PRIVATE_ZONE_FILE_TYPE_AVI = 1035;
    public static final  int PRIVATE_ZONE_FILE_TYPE_BAT = 1036;
    public static final  int PRIVATE_ZONE_FILE_TYPE_BT = 1037;
    public static final  int PRIVATE_ZONE_FILE_TYPE_CAB = 1038;
    public static final  int PRIVATE_ZONE_FILE_TYPE_CHM = 1039;
    public static final  int PRIVATE_ZONE_FILE_TYPE_CODE = 1040;
    public static final  int PRIVATE_ZONE_FILE_TYPE_CSS = 1041;
    public static final  int PRIVATE_ZONE_FILE_TYPE_DMG = 1042;
    public static final  int PRIVATE_ZONE_FILE_TYPE_DOC = 1043;
    public static final  int PRIVATE_ZONE_FILE_TYPE_EXE = 1044;
    public static final  int PRIVATE_ZONE_FILE_TYPE_FLA = 1045;
    public static final  int PRIVATE_ZONE_FILE_TYPE_FLV = 1046;
    public static final  int PRIVATE_ZONE_FILE_TYPE_GIF = 1047;
    public static final  int PRIVATE_ZONE_FILE_TYPE_HTML = 1048;
    public static final  int PRIVATE_ZONE_FILE_TYPE_IMG = 1049;
    public static final  int PRIVATE_ZONE_FILE_TYPE_IPA = 1050;
    public static final  int PRIVATE_ZONE_FILE_TYPE_ISO = 1051;
    public static final  int PRIVATE_ZONE_FILE_TYPE_JPEG = 1052;
    public static final  int PRIVATE_ZONE_FILE_TYPE_LOG = 1053;
    public static final  int PRIVATE_ZONE_FILE_TYPE_MKV = 1054;
    public static final  int PRIVATE_ZONE_FILE_TYPE_MOV = 1055;
    public static final  int PRIVATE_ZONE_FILE_TYPE_MP3 = 1056;
    public static final  int PRIVATE_ZONE_FILE_TYPE_MP4 = 1057;
    public static final  int PRIVATE_ZONE_FILE_TYPE_MSI = 1058;
    public static final  int PRIVATE_ZONE_FILE_TYPE_MUSIC = 1059;
    public static final  int PRIVATE_ZONE_FILE_TYPE_PDF = 1060;
    public static final  int PRIVATE_ZONE_FILE_TYPE_PNG = 1061;
    public static final  int PRIVATE_ZONE_FILE_TYPE_PPT = 1062;
    public static final  int PRIVATE_ZONE_FILE_TYPE_PSD = 1063;
    public static final  int PRIVATE_ZONE_FILE_TYPE_RAR = 1064;
    public static final  int PRIVATE_ZONE_FILE_TYPE_RAW = 1065;
    public static final  int PRIVATE_ZONE_FILE_TYPE_RM = 1066;
    public static final  int PRIVATE_ZONE_FILE_TYPE_RMVB = 1067;
    public static final  int PRIVATE_ZONE_FILE_TYPE_SRT = 1068;
    public static final  int PRIVATE_ZONE_FILE_TYPE_SSA = 1069;
    public static final  int PRIVATE_ZONE_FILE_TYPE_SWF = 1070;
    public static final  int PRIVATE_ZONE_FILE_TYPE_TXT = 1071;
    public static final  int PRIVATE_ZONE_FILE_TYPE_VIDEO = 1072;
    public static final  int PRIVATE_ZONE_FILE_TYPE_WMA = 1073;
    public static final  int PRIVATE_ZONE_FILE_TYPE_WMV = 1074;
    public static final  int PRIVATE_ZONE_FILE_TYPE_XLS = 1075;
    public static final  int PRIVATE_ZONE_FILE_TYPE_ZIP = 1076;
    public static final  int PRIVATE_ZONE_FILE_TYPE_KEYNOTE = 1077;
    public static final  int PRIVATE_ZONE_FILE_TYPE_NUMBERS = 1078;
    public static final  int PRIVATE_ZONE_FILE_TYPE_PAGES = 1079;
    public static final  int PRIVATE_ZONE_FILE_TYPE_BACK_OLD = 1080;



}
