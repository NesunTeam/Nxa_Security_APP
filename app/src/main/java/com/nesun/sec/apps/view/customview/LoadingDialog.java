package com.nesun.sec.apps.view.customview;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.nesun.sec.apps.R;

public class LoadingDialog extends Dialog {

    private Context context;
    private static LoadingDialog dialog;
    private static ImageView ivProgress;
    private static TextView tvText;

    public LoadingDialog(Context context) {
        super(context);
        this.context = context;
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;

    }

    //显示dialog的方法
    public static LoadingDialog showDialog(Context context, String msg) {
        dialog = new LoadingDialog(context, R.style.LoadDialog);//dialog样式
        dialog.setContentView(R.layout.dialog_layout);//dialog布局文件
        tvText = dialog.findViewById(R.id.tvText);
        if (!TextUtils.isEmpty(msg)) {
            tvText.setText(msg);
        }
        ivProgress = dialog.findViewById(R.id.ivProgress);
        dialog.setCanceledOnTouchOutside(false);//点击外部不允许关闭dialog
        return dialog;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && dialog != null) {
            startAnimation();
        } else {
            endAnimation();
        }
    }

    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        super.setOnDismissListener(listener);
        endAnimation();
    }

    private void startAnimation() {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.umcsdk_anim_loading);
        ivProgress.startAnimation(animation);
    }

    private void endAnimation() {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.umcsdk_anim_loading);
        animation.cancel();
    }
}