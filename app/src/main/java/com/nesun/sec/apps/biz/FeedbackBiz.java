package com.nesun.sec.apps.biz;

import android.text.TextUtils;

import com.nesun.sec.apps.bean.FeedbackBean;

public class FeedbackBiz implements IFeedbackBiz {


    @Override
    public void submitFeedback(final String feedMessage, final String feedPhone, final OnFeedbackListener onFeedbackListener) {

        if (TextUtils.isEmpty(feedMessage) ||TextUtils.isEmpty(feedPhone) ){
            onFeedbackListener.feedbackError();
            return;
        }

        // 模拟线程请求服务器
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }catch (Exception e){}
                if (feedPhone.equals("123456")){
                    FeedbackBean feedbackBean = new FeedbackBean();
                    feedbackBean.setFeedMessage(feedMessage);
                    feedbackBean.setFeedPhone(feedPhone);
                    onFeedbackListener.feedbackSuccess(feedbackBean);
                }else {
                    onFeedbackListener.feedbackFailed();
                }
            }
        }).start();

    }



}
