package com.nesun.sec.apps.view.activity.security;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nesun.sec.apps.R;
import com.nesun.sec.apps.bean.SecScanBean;
import com.victor.loading.rotate.RotateLoading;

import java.util.List;

public class SecScanAdapter extends ArrayAdapter<SecScanBean> {

    private int resourceId;
    private int selectItem=-1;

    public SecScanAdapter(@NonNull Context context, int resource, @NonNull List<SecScanBean> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SecScanBean info = getItem(position);//获取当前项的实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

        ((TextView)view.findViewById(R.id.textView_sec_scan_item_01)).setText(info.getScanType());
        ((TextView)view.findViewById(R.id.textView_sec_scan_item_02)).setText(info.getScanStatus());

        ImageView imageView = ((ImageView)view.findViewById(R.id.imageView_sec_scan_item_01));
        if (info.isScanSafe())imageView.setImageResource(R.mipmap.sec_scan_item_02);
        else imageView.setImageResource(R.mipmap.sec_scan_item_01);

        if (position == selectItem){
            ((ImageView)view.findViewById(R.id.imageView_sec_scan_item_01)).setVisibility(View.INVISIBLE);
            ((RotateLoading)view.findViewById(R.id.rotateloading_sec_scan_item_01)).start();
            ((TextView)view.findViewById(R.id.textView_sec_scan_item_02)).setText("扫描中");
        }
        return view;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }


}
