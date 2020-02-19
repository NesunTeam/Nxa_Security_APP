package com.nesun.sec.apps.view.activity.applock;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nesun.sec.apps.R;
import com.nesun.sec.apps.bean.LockAppInfoBean;

import java.util.List;

public class AppLockAdapter extends ArrayAdapter<LockAppInfoBean> {

    private int resourceId;

    public AppLockAdapter(@NonNull Context context, int resource, @NonNull List<LockAppInfoBean> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LockAppInfoBean lockAppInfoBean = getItem(position);//获取当前项的实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ((ImageView) view.findViewById(R.id.imageView_list_applock)).setImageDrawable(lockAppInfoBean.getDraIcon());
        ((TextView) view.findViewById(R.id.textView_list_applock)).setText(lockAppInfoBean.getAppName());
        return view;
    }

}
