package com.nesun.sec.apps.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.text.TextUtils;

import com.nesun.sec.apps.constant.Constant;
import com.nesun.sec.apps.dao.AppLockDao;
import com.nesun.sec.apps.engine.ProcessManagerEngine;
import com.nesun.sec.apps.utils.AppUtil;
import com.nesun.sec.apps.view.activity.gesture.GestureLockCheckActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * always check the current task stack.
 * It will start a activity to request password If the start app package name in app_lock.db
 */
public class AppLockService extends Service {

    private static final int LOCK_APP_TYPE =1;  // 1 退出应用即加锁， 2 锁屏后再加锁

    // constants
    private static final long SLEEP_TIME = 50;
    // data
    private boolean running;
    private String unlockedPackageName = "";
    private AppLockReceiver receiver = new AppLockReceiver();
    private List<String> lockedApps;
    private List<String> unlockedApps = new ArrayList<String>();
    private AppLockDataChangedObserver observer;
    private boolean exit;// used for exit safely when haven't lock password

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // 检查没有密码，停止服务
        String password = AppUtil.getGesturePassword(getApplicationContext());
        if(TextUtils.isEmpty(password)) {
            exit = true;
            stopSelf();
            return;
        }

        // 注册自定义解锁广播
        IntentFilter filter = new IntentFilter(Constant.ACTION_UNLOCK_APP);// 解锁app成功
        filter.addAction(Constant.ACTION_OPEN_APPLOCK); // 打开应用锁功能
        filter.addAction(Intent.ACTION_SCREEN_ON);// 监听屏幕开
        filter.addAction(Intent.ACTION_SCREEN_OFF);// 监听屏幕锁
        registerReceiver(receiver, filter);

        // 注册数据监听器
        observer = new AppLockDataChangedObserver(new Handler());
        getContentResolver().registerContentObserver(Constant.URI_APP_LOCK_DATA_CHANGED, true, observer);

        // 请求获取应用列表权限（高版本）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                !ProcessManagerEngine.hasGetUsageStatsPermission(this)) {
            ProcessManagerEngine.requestUsageStatesPermission(this);
        }
        // 启动线程，循环查找栈顶App
        startAppLock();
    }

    /**
     * 启动线程，循环查找栈顶App
     */
    private void startAppLock() {
        running = true;
        final ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final AppLockDao dao = new AppLockDao(this);
        lockedApps = dao.selectAll();

        new Thread() {
            @Override
            public void run() {
                while (running) {
                    SystemClock.sleep(SLEEP_TIME);

                    // 检查没有开启应用锁，直接退出
                    if (!AppUtil.isApplockStatus(getApplicationContext()))break;

                    // 获取包名
                    String packageName = ProcessManagerEngine.getTaskTopAppPackageName(AppLockService.this, am);;
                    if(TextUtils.isEmpty(packageName))
                        continue;
                    if (packageName.equals(getPackageName())) // 判断是自身界面，返回不做处理
                        continue;

                    // 这里有两种 锁类型，1 锁屏再上锁， 2切换app就上锁
                    if (AppUtil.getApplockType(getApplicationContext())==Constant.KEY_APPLOCK_TYPE_1){
                        boolean isUnLock = false;// 判断解锁列表合集，当前打开App存在已经解密过List
                        for (String str:unlockedApps){
                            if(packageName.equals(str)) isUnLock=true;
                        }
                        if (isUnLock)continue;

                    }else {// 第二种，上一次解锁App，跟当前栈顶不同，清空上一次解锁App
                        if(packageName.equals(unlockedPackageName)) continue;
                        else unlockedPackageName = "";
                    }

                    // 必须线程锁 查找当前 包名是否在锁SQL
                    synchronized (AppLockService.this) {// must synchronized when ues and set
                        if (!lockedApps.contains(packageName))
                            continue;
                    }

                    // 如果存在，启动解锁Activity
                    Intent intent = new Intent(AppLockService.this, GestureLockCheckActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(Constant.EXTRA_LOCKED_APP_PACKAGE_NAME, packageName);
                    startActivity(intent);
                }
            }
        }.start();
    }

    /**
     * unregister receiver and observer
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        // if exit, it means no lock password. So no need to unregister anything
        if(exit)
            return;
        running = false;// exit the thread
        unregisterReceiver(receiver);// unregister receiver
        getContentResolver().unregisterContentObserver(observer);// unregister observer
    }


    /**
     * App解锁广播监听
     */
    private class AppLockReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Constant.ACTION_UNLOCK_APP)) { // 解锁应用成功
                unlockedPackageName = intent.getStringExtra(Constant.EXTRA_LOCKED_APP_PACKAGE_NAME);
                unlockedApps.add(unlockedPackageName);
            } else if(action.equals(Constant.ACTION_OPEN_APPLOCK)) { // 开关重新开启，所有已解锁App清空
                startAppLock();// start app lock
                unlockedPackageName = "";// clean the unlocked app
                unlockedApps.clear();
            } else if(action.equals(Intent.ACTION_SCREEN_ON)) { // 监听屏幕开启
                startAppLock();// start app lock
            } else if(action.equals(Intent.ACTION_SCREEN_OFF)) { // 监听屏幕关闭
                startAppLock();// start app lock
                running = false;// stop app lock
                unlockedPackageName = "";// clean the unlocked app
                unlockedApps.clear();
            }
        }
    }

    /**
     * 应用锁列表SQL数据改变监听
     */
    private class AppLockDataChangedObserver extends ContentObserver {
        public AppLockDataChangedObserver(Handler handler) {
            super(handler);
        }
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            final AppLockDao dao = new AppLockDao(AppLockService.this);
            synchronized (AppLockService.this){// must synchronized when ues and set
                lockedApps = dao.selectAll();
            }
        }
    }

}
